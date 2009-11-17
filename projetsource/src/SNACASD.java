import java.util.*;

class SNACASD {

	String myName;

	PhysicalLayer pl;

	int retries;

	int maxtime;

	final long TIMEOUTMYNAME = 10000;

	final long TIMEOUTFRIENDNAME = 20000;

	HashMap<String, Timer> hm;

	HashMap<String, Timer> hm2;

	Timer myNameIs;

	public SNACASD(PhysicalLayer pl, int retries, int maxtime) {
		this.pl = pl;
		this.retries = retries;
		this.maxtime = maxtime;

		hm = new HashMap<String, Timer>();
		hm2 = new HashMap<String, Timer>();
		myNameIs = new Timer();
	}

	public boolean verifyUniqueness(String name) {
		waitRandomly();

		for (int i = 0; i < retries; i++) {
			int counter = 0;

			// We try get "name" as our name. Then, we want to
			// know if someone is nammed "name" by sending a
			// discover message. Thus, we send this message to
			// "name" with NEW as default ID.
			pl.send(name + ":NEW:DISCOVER:");
			waitRandomly();

			String message = "";

			do {
				message = pl.receive();
				if (message != null) {
					String[] splitMessage = message.split(":");
					if (splitMessage[2] == null
							|| !TypeMessage.isGoodType(splitMessage[2])) {
						System.out.println("Message \"" + message
								+ "\" uncorrectly formatted");
						// System.exit(0);
					}
					if (splitMessage[2].equals("SERVICES")) {
						if (splitMessage[1].equals(name))
							return false;
					} else {
						if (splitMessage[2].equals("DISCOVER")) {
							if (splitMessage[0].equals(name))
								counter++;
						}
					}
				}
			} while (message != null);

			// Be carefull of the loopback !
			if (counter > 1)
				return false;
		}

		return true;
	}

	public Timer getTimer() {
		return myNameIs;
	}

	public String getUniqueName() {
		String tentative;

		do {
			// 10-character random string
			tentative = RandomString.nextString(10);
		} while (!verifyUniqueness(tentative));

		// every TIMEOUTMYNAME milliseconds, we send
		// to ALL that we are the guy named "tentative"
		ServicesTask task = new ServicesTask(pl, tentative);
		myNameIs.schedule(task, TIMEOUTMYNAME, TIMEOUTMYNAME);

		return tentative;
	}

	public String getUniqueName(String name) {
		if (verifyUniqueness(name)) {
			// every TIMEOUTMYNAME milliseconds, we send
			// to ALL that we are the guy named "name"
			ServicesTask task = new ServicesTask(pl, name);
			myNameIs.schedule(task, TIMEOUTMYNAME, TIMEOUTMYNAME);

			return name;
		} else
			return getUniqueName();
	}

	// WARNING: this method is void here!!!
	// In the TD, it was a boolean method but it was
	// useless at the time, sorry :)
	public void handleDiscover() {
		// we assume here that message is a
		// well-formatted DISCOVER message.

		pl.send("ALL:" + myName + ":SERVICES:");
	}

	public void handleServices(String message) {
		String[] splitMessage = message.split(":");
		Timer timer = new Timer();

		if (splitMessage[0].equals("ALL") || splitMessage[0].equals(myName)) {
			String friendName = splitMessage[1];

			if (hm.containsKey(friendName))
				hm.get(friendName).cancel();

			if (hm2.containsKey(friendName)) {
				hm2.get(friendName).cancel();
				hm2.remove(friendName);
			}

			FriendTask task = new FriendTask(this, friendName);
			timer.schedule(task, TIMEOUTFRIENDNAME);
			hm.put(friendName, timer);
		}
	}

	public boolean isFriendAlive(String friend) {
		return hm.containsKey(friend);
	}

	public void search4Friend(String friendName) {
		pl.send(friendName + ":" + myName + ":DISCOVER:");
		Friend2Task task = new Friend2Task(hm, hm2, friendName);
		Timer timer = new Timer();
		timer.schedule(task, TIMEOUTFRIENDNAME);
		hm2.put(friendName, timer);
	}

	public void waitRandomly() {
		Random ran = new Random();

		try {
			Thread.sleep((maxtime * ran.nextInt(1001)));
		} catch (InterruptedException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
}

class ServicesTask extends TimerTask {

	PhysicalLayer pl;

	String name;

	ServicesTask(PhysicalLayer pl, String name) {
		this.pl = pl;
		this.name = name;
	}

	public void run() {
		pl.send("ALL:" + name + ":SERVICES:");
	}
}

class FriendTask extends TimerTask {

	SNACASD snacasd;

	String friendName;

	FriendTask(SNACASD snacasd, String friendName) {
		this.snacasd = snacasd;
		this.friendName = friendName;
	}

	public void run() {
		snacasd.search4Friend(friendName);
	}
}

class Friend2Task extends TimerTask {

	HashMap<String, Timer> hm;

	HashMap<String, Timer> hm2;

	String friendName;

	Friend2Task(HashMap<String, Timer> hm, HashMap<String, Timer> hm2,
			String friendName) {
		this.hm = hm;
		this.hm2 = hm2;
		this.friendName = friendName;
	}

	public void run() {
		hm.remove(friendName);
		hm2.remove(friendName);
	}
}
