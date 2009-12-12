import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Classe de gestion d'un message dans sa globalité. Cette classe découpe un
 * message suivant Header, Options et Data. Elle permet un accès facile aux
 * différents éléments de chaque message.
 * 
 * @author Thomas
 * 
 */

public class Message {

	public int datalength;

	public String dest;

	public String from;

	public int nbropt;

	public HashMap<Integer, String> opt = new LinkedHashMap<Integer, String>();

	public String data;

	public boolean good = true;

	// sera modifie si le message n'est pas au bon format

	public Message(String msg) {
		if (msg != null) {
			String header = "";
			String options = "";
			if (msg.length() >= 12) {
				// un message sans data ne sert a rien
				header = msg.substring(0, 11);
				datalength = (256 * msg.charAt(1) + msg.charAt(0));
				// on recupere la datalength;
				if (datalength > 0) {
					data = msg.substring(msg.length() - datalength);
				} else {
					data = "";
				}
				traiterHeader(header);
				if (datalength > 0) {
					options = msg.substring(11, msg.length() - datalength);
				} else {
					options = msg.substring(11);
				}
				traiterOptions(options);
			} else {
				good = false;
				System.err.println("Message mal formate. Ce message n'est pas traite");
			}
		} else {
			good = false;
		}
	}

	public Message(String dest, String from, int nbropt, String data) {
		this.dest = dest;
		this.from = from;
		this.nbropt = nbropt;
		this.data = data;
		this.good = true;
	}

	/**
	 * Fonction qui interprète le header
	 * 
	 * @param header
	 */
	public void traiterHeader(String header) {
		datalength = 256 * (int) header.charAt(1) + (int) header.charAt(0);
		dest = (int) header.charAt(2) + "." + (int) header.charAt(3) + "." + (int) header.charAt(4) + "." + (int) header.charAt(5);
		from = (int) header.charAt(6) + "." + (int) header.charAt(7) + "." + (int) header.charAt(8) + "." + (int) header.charAt(9);
		nbropt = (int) (header.charAt(10));
	}

	/**
	 * Fonction qui interprète les options
	 * 
	 * @param options
	 */
	public void traiterOptions(String options) {
		int pos = 0;
		for (int i = 0; i < nbropt; i++) {
			int option = options.charAt(pos);
			pos++;
			int taille = options.charAt(pos);
			pos++;
			if (!opt.containsKey(opt)) {
				opt.put(option, options.substring(pos, pos + taille));
			} else {
				System.out.println("*** Warning : Deux options sont identiques. La derniere n'est pas prise en compte ***");
			}
			pos += taille;
		}
		if (pos != options.length()) {
			System.out.println("*** Erreur : " + pos + " " + options.length() + "***");
			System.err.println("*** Erreur : Mauvais format d'options ***");
		}
	}

	/**
	 * Fonction qui recontruit le message pour un envoi future.
	 * 
	 * @return
	 */
	public String construireMessage() {
		char dest1 = (char) (Integer.parseInt(dest.substring(0, 3)) & 0xff);
		char dest2 = (char) (Integer.parseInt(dest.substring(4, 7)) & 0xff);
		char dest3 = (char) (Integer.parseInt(dest.substring(8, 11)) & 0xff);
		char dest4 = (char) (Integer.parseInt(dest.substring(12, 15)) & 0xff);
		char from1 = (char) (Integer.parseInt(from.substring(0, 3)) & 0xff);
		char from2 = (char) (Integer.parseInt(from.substring(4, 7)) & 0xff);
		char from3 = (char) (Integer.parseInt(from.substring(8, 11)) & 0xff);
		char from4 = (char) (Integer.parseInt(from.substring(12, 15)) & 0xff);
		int dl = data.length();
		char dl1 = (char) (dl & 0xff);
		char dl2 = (char) ((dl >> 8) & 0xff);
		String option = "";
		for (int i : opt.keySet()) {
			char indiceOption = (char) ((byte) (i) & 0xff);
			char longOption = (char) ((byte) (opt.get(i).length()) & 0xff);
			option += indiceOption + "" + longOption + "" + opt.get(i);
		}
		char nopt = (char) (nbropt & 0xFF);
		String msg = dl1 + "" + dl2 + "" + dest1 + "" + dest2 + "" + dest3 + "" + dest4 + "" + from1 + "" + from2 + "" + from3 + "" + from4 + "" + nopt + option + data;

		return msg;
	}

	/**
	 * Fonction qui imprime le message à l'ecran dans un format lisible par
	 * l'utilisateur.
	 */
	public void print() {
		System.out.print(datalength + ":" + dest + ":" + from + ":" + nbropt + ":");
		for (int i : opt.keySet()) {
			System.out.print(i + ":" + opt.get(i) + ":");
		}
		System.out.println(data);
	}

}
