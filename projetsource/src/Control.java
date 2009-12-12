/**
 * Classe de controle du serveur.
 * C'est elle qui permet de lancer les commandes sur le serveur
 * pour ajouter des chemins, afficher la topologie, ...
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

class Control {
	DatagramSocket s;

	int port;

	String host;

	public Control(String host) {
		port = 11111;
		this.host = host;

		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println(e);
			System.exit(-1);
		}

	}

	public Control(String host, int port) {
		this.port = port;
		this.host = host;
		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println(e);
			System.exit(-1);
		}

	}

	/**
	 * Fonction pour creer des messages pour le serveur
	 * 
	 * @param option
	 * @param from
	 * @param to
	 * @return
	 */
	static String messageToServer(String option, String from, String to) {
		Message msg = new Message("255.255.255.255", "000.000.000.000", 0, "");
		msg.nbropt++;
		msg.opt.put(255, option + " " + from + " " + to);
		String s = msg.construireMessage();
		return s;
	}

	void send(String msg) {
		byte[] buf = msg.getBytes();
		try {
			DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName(host), port);

			s.send(pack);
		} catch (UnknownHostException e) {
			System.out.println(e);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println(e);
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		Control s = new Control("finistere");
		System.out.println("enter \"help\" for a list of the commands");
		System.out.println("(note: command output is on the server side)");
		while (true) {
			System.out.print("> ");
			StringTokenizer st = new StringTokenizer(Keyboard.readString(), " ");
			String cmd = st.nextToken();
			if (cmd.equals("help")) {
				System.out.println("\ttopo : print the topology");
				System.out.println("\taddroute A B : add a link between A and B");
				System.out.println("\tdelroute A B : remove a link between A and B");
				System.out.println("\texit : exit");
			} else if (cmd.equals("topo")) {
				s.send(messageToServer("topo", "", ""));
			} else if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("addroute")) {
				String from = st.nextToken();
				String to = st.nextToken();
				s.send(messageToServer("addroute", to, from));
			} else if (cmd.equals("delroute")) {
				String from = st.nextToken();
				String to = st.nextToken();
				s.send(messageToServer("delroute", to, from));
			}
			try {
				Thread.sleep(150);
			} catch (Exception e) {
			}
		}

		System.exit(0);
	}
}
