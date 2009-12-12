import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Reprise de la classe BellmanFord utilisée en TD. C'est notre classe qui
 * permet de lier tous les éléments du projet. L'envoie de message La réception
 * de message Le traitement des options Le renvoie ou le traitement des messages
 */
/*
 * When this timer expires, then one of our 1-step neighbours hasn't sent HELLO
 * for some times, so we remove it from our table
 */
class TimerHello extends TimerTask {
	BellmanFord bf;

	String name;

	TimerHello(BellmanFord bf, String name) {
		this.bf = bf;
		this.name = name;
	}

	public void run() {
		bf.removeEntry(name);
	}
}

/*
 * This timer advertises periodically HELLO msg to our 1-step neighbours to say
 * that we are alive
 */
class TimerOk extends TimerTask {
	PhysicalLayer pl;

	String name;

	Message msg;

	TimerOk(PhysicalLayer pl, String name) {
		this.pl = pl;
		this.name = name;
		msg = new Message("255.255.255.254", this.name, 0, "");
		msg.nbropt++;
		msg.opt.put(254, "hello");
	}

	public void run() {
		pl.send(msg);
	}
}

/*
 * Just a debugging timer which advertises on the local console the current
 * distance vector
 */
class DEBUG extends TimerTask {
	BellmanFord bf;

	DEBUG(BellmanFord bf) {
		this.bf = bf;
	}

	public void run() {
		// bf.printVector();
	}
}

class BellmanFord {
	PhysicalLayer pl;

	String myID;

	/*
	 * the routing table : t = table.get(toto) gives us every path known to
	 * reach toto t.get(tutu) gives us the length of the path to reach toto via
	 * tutu
	 * 
	 * Note: since there is hashtable manipulation called by a timer, it's safer
	 * to use a concurrent hashmap to avoid accessing the table by two threads
	 * at the same time
	 */
	ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> table;

	Timer timerok;

	/*
	 * this table stores the timer for HELLO msg, to check if our 1-step
	 * neighbours are still alive
	 */
	HashMap<String, Timer> helloTable;

	BellmanFord(PhysicalLayer layer, String name) {
		pl = layer;
		myID = name;
		table = new ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>(20);
		helloTable = new LinkedHashMap<String, Timer>(20);
		timerok = new Timer();
		TimerOk oktask = new TimerOk(pl, myID);
		timerok.schedule(oktask, 5000, 5000);

		Timer timerDEBUG = new Timer();
		DEBUG dtask = new DEBUG(this);
		timerDEBUG.schedule(dtask, 3000, 3000);

	}

	/*
	 * this method computes the String which represents the
	 * ALL:me:VECTOR:n:x1:y1:l1:....:xn:yn:ln message
	 */

	// elle ne fait que la ligne vector:n:x1:y1:l1:....:xn:yn:ln

	String preProcessVector() {
		/*
		 * + on String is really inefficient and should be avoid (especially
		 * when loops are involved
		 */
		StringBuilder sb = new StringBuilder();
		int size = table.size();
		sb.append("vector ").append(size).append(" ");

		/* efficient way to process a hashmap */
		for (Map.Entry<String, ConcurrentHashMap<String, Integer>> e : table.entrySet()) {
			// destination
			String dest = e.getKey();
			// all the known path to this destination
			ConcurrentHashMap<String, Integer> steps = e.getValue();
			String by = "";
			int min = 0;
			boolean first = true;
			// let's find the shortest one and the next hop
			for (Map.Entry<String, Integer> f : steps.entrySet()) {
				if (first) {
					by = f.getKey();
					min = f.getValue().intValue();
					first = false;
				} else {
					int val = f.getValue().intValue();
					if (val < min) {
						min = val;
						by = f.getKey();
					}
				}
			}
			sb.append(dest).append(" ").append(by).append(" ");
			sb.append(min);
		}
		return sb.toString();
	}

	/* compute the distance vector and send it on the network */
	void processVector() {
		String s = preProcessVector();
		System.out.println("DEBUG Vector : " + s);
		Message mess = new Message("255.255.255.254", myID, 1, "");
		mess.opt.put(254, s);
		pl.send(mess);
	}

	/*
	 * for debugging purpose, compute the distance vector and print it on the
	 * local console
	 */
	void printVector() {
		System.out.println("Remainder : " + preProcessVector());
	}

	/* This method adds an entry to the routing table. */
	void addEntry(String who, String by, int dist) {
		// we don't care to known how to reach ourself
		if (who.equals(myID)) {
			return;
		}
		// if the entry is not known yet
		if (!table.containsKey(who)) {
			// the only path I know to this dest is this new one
			ConcurrentHashMap<String, Integer> step = new ConcurrentHashMap<String, Integer>(20);
			step.put(by, new Integer(dist));
			table.put(who, step);
			processVector();
		} else {
			/*
			 * This case should happen when one of my neighbour has died, and is
			 * back live again.
			 */
			ConcurrentHashMap<String, Integer> step = table.get(who);
			step.put(by, new Integer(dist));
			processVector();
		}
	}

	/* Death detection */
	void removeEntry(String who) {
		if (table.containsKey(who)) {
			System.out.println(who + " has vanished");
			// for every path we know ...
			for (Map.Entry<String, ConcurrentHashMap<String, Integer>> e : table.entrySet()) {
				String dest = e.getKey();
				ConcurrentHashMap<String, Integer> steps = e.getValue();
				// ... that starts with the dead node ..
				// ... has to be removed
				steps.remove(who);
				// we remove empty sets of path from our table to avoid "x:::"
				// in our distance vector
				if (steps.size() == 0) {
					table.remove(dest);
				}
			}
			processVector();
		}
	}

	// return true if the distance vector has changed
	boolean handleHello(String from) {
		// System.out.println("Received hello from :"+from);
		if (!helloTable.containsKey(from)) {
			// Hello from an unknown person
			Timer t = new Timer();
			TimerHello hellotask = new TimerHello(this, from);
			t.schedule(hellotask, 10000);
			helloTable.put(from, t);
			// we add it as a 1-step neightbour since HELLO are 1-hop messages
			addEntry(from, from, 1);
			return true;
		} else {
			// Hello from someone we already know , timer maintenance
			Timer t = helloTable.get(from);
			t.cancel();
			t = new Timer();
			TimerHello hellotask = new TimerHello(this, from);
			t.schedule(hellotask, 10000);
			helloTable.put(from, t);
			/*
			 * We a close neighbours dies, we may still have some other path to
			 * reach it. When it comes back alive, we need to update our routing
			 * table
			 */
			if (shortestPath(from) > 1) {
				addEntry(from, from, 1);
				return true;
			}
			return false;
		}
	}

	/* Compute the length to the shortest path we know to reach dest */
	int shortestPath(String dest) {
		if (!table.containsKey(dest)) {
			System.out.println("unknown dest for shortest path");
			return -1;
		}
		ConcurrentHashMap<String, Integer> steps = table.get(dest);
		int min = 0;
		boolean first = true;
		for (Integer i : steps.values()) {
			if (first) {
				min = i.intValue();
				first = false;
			} else {
				if (i.intValue() < min) {
					min = i.intValue();
				}
			}
		}
		return min;
	}

	// return true if the distance vector has changed
	boolean handleVector(String from, StringTokenizer st) {
		// Upon reception of a VECTOR message
		int num = Integer.parseInt(st.nextToken());
		boolean changed = false;
		// go through all the vector (WARNING no st well-formation test here)
		for (int i = 0; i < num; i++) {
			String dest = st.nextToken(); // the final destination
			// not usefull for the standard algoirthm but nice for looping
			// detection
			/* String by = */st.nextToken();
			int length = Integer.parseInt(st.nextToken()); // the length to
			// reach dest
			if (dest.equals(myID)) {
				// we always know how to reach ourself
				continue;
			}
			if (!table.containsKey(dest)) {
				// if we don't know how to reach this destination : create the
				// entry
				addEntry(dest, from, length + 1);
				changed = true;
			} else {
				// We know how to reach the destination ...
				ConcurrentHashMap<String, Integer> steps = table.get(dest);
				int oldSP = shortestPath(dest);
				steps.put(from, new Integer(length + 1));
				if (shortestPath(dest) != oldSP) {
					changed = true;
				}
			}
		}
		if (changed) {
			processVector();
		}
		return changed;
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: java BellmanFord name centralserver");
			System.exit(0);
		}
		String ID = args[0];
		PhysicalLayer pl = new PhysicalLayer(args[1]);
		// BellmanFord router = new BellmanFord(pl, ID);

		try {
			// retrieve the hostname of your computer
			String localname = InetAddress.getLocalHost().getHostName();
			// tell it to the main hub
			Message mess = new Message("255.255.255.255", ID, 0, "");
			mess.nbropt++;
			mess.opt.put(255, "serverjoin " + localname);

			// creation d'une instance du Thread
			InterfaceThread thread = new InterfaceThread(pl);
			// Activation du Thread
			thread.start();

			/**
			 * On lance ici l'interface d'envoie et de réception des messages
			 */
			pl.send(mess);
			while (true) {
				Message msg = pl.receive();
				// On regarde d'abord si on recoit un message dans un format
				// correct
				// C'est-à-dire dans un format qui correspon à celui que l'on
				// a implémenté dans notre projet
				// Sinon c'est soit un message à ignoré soit un message
				// "exception"
				// qui doit être traité à part
				if (msg != null && msg.good) {
					// System.out.println("DEBUG msg : "+msg);
					System.out.println("*** Nouveau message recu ***");
					// msg.print();
					String dest = msg.dest;
					String from = msg.from;
					System.out.println("ID:" + ID + " DEST:" + dest + " FROM:" + from);
					if (dest.equals(ID)) {
						if (from.equals(ID)) {
							// je suis l envoyeur de ce message
							// donc je ne fais rien
							continue;
						} else if (dest.equals(ID)) {
							// si je suis le destinataire
							msg.from = ID;
							TraiterOptions to = new TraiterOptions();
							to.jeSuisDest = true;
							to.OPTMAP = msg.opt;
							to.traiterOptions();
							msg.opt = to.OPTMAP;
							System.out.println("-> Je suis le destinataire de ce message : ");
							msg.print();
							continue;
						}
					} else if (dest.toLowerCase().equals("255.255.255.254") && msg.opt.get(254).length() != 0) {
						StringTokenizer str = new StringTokenizer(msg.opt.get(254).toLowerCase(), " ");
						String typ = str.nextToken();
						if (typ.equals("hello")) {
							// router.handleHello(from);
						} else if (typ.equals("vector")) {
							// System.out.println("VECTOR RECIEVED from "+
							// from);
							// router.handleVector(from,str);
						}
					} else if (!from.equals(ID)) {
						// je ne suis pas destinataire et le message ne me
						// concerne pas
						// cependant je dois quand même traiter certaines
						// options
						msg.from = ID;
						TraiterOptions to = new TraiterOptions();
						to.OPTMAP = msg.opt;
						to.traiterOptions();
						msg.opt = to.OPTMAP;
						pl.send(msg);
						System.out.println("-> Je ne suis pas le destinataire de ce message, je renvoie : ");
						msg.print();
						continue;
					}
				}
			}
		} catch (UnknownHostException e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
