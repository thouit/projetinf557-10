/**
 * Classe qui facilite la creation des message pour l'utilisateur. Il est
 * possible de rentrer des parmametres "lisibles" et le programme construit le
 * message correspondant.
 * 
 * @author Thomas
 * 
 */
public class CreationMsg {

	/**
	 * Genere un message generique pour tester les fonctions du programme
	 * 
	 * @return
	 */
	public static String create() {
		String[] OPTIONS = new String[2];
		String val1 = "OPTION1!";
		int n = val1.length();
		byte octet = (byte) (n);
		char octetchar = (char) (octet & 0xff);
		char indiceOption = (char) ((byte) (12) & 0xff);
		OPTIONS[0] = indiceOption + "" + octetchar + val1;
		String val2 = "OPTION2!";
		n = val2.length();
		octet = (byte) (n);
		octetchar = (char) (octet & 0xff);
		indiceOption = (char) ((byte) (25) & 0xff);
		OPTIONS[1] = indiceOption + "" + octetchar + val2;
		String opt = indiceOption + "" + octetchar +""+ val1+indiceOption + "" + octetchar + val2;
		String msg = buildMsg("127.000.000.001", "127.000.000.002", 2, OPTIONS, "DATA");
		return msg;
	}

	/**
	 * Encode une adresse ip entree sous la forme "XXX.XXX.XXX.XXX"
	 * 
	 * @param NAME
	 * @return
	 */
	public static String createName(String NAME) {
		char NAME1 = (char) (Integer.parseInt(NAME.substring(0, 3)) & 0xff);
		char NAME2 = (char) (Integer.parseInt(NAME.substring(4, 7)) & 0xff);
		char NAME3 = (char) (Integer.parseInt(NAME.substring(8, 11)) & 0xff);
		char NAME4 = (char) (Integer.parseInt(NAME.substring(12, 15)) & 0xff);
		return NAME1 + "" + NAME2 + "" + NAME3 + "" + NAME4;
	}

	/**
	 * Formate un message en fonctions des valeurs entree.
	 * 
	 * @param DEST
	 * @param FROM
	 * @param nbOptions
	 * @param OPTIONS
	 * @param DATA
	 * @return
	 */
	public static String buildMsg(String DEST, String FROM, int nbOptions, String[] OPTIONS, String DATA) {
		String NBOPTIONS = "" + (char) (nbOptions & 0xff);
		int dl = DATA.length();
		char dest1 = (char) (Integer.parseInt(DEST.substring(0, 3)) & 0xff);
		char dest2 = (char) (Integer.parseInt(DEST.substring(4, 7)) & 0xff);
		char dest3 = (char) (Integer.parseInt(DEST.substring(8, 11)) & 0xff);
		char dest4 = (char) (Integer.parseInt(DEST.substring(12, 15)) & 0xff);
		char from1 = (char) (Integer.parseInt(FROM.substring(0, 3)) & 0xff);
		char from2 = (char) (Integer.parseInt(FROM.substring(4, 7)) & 0xff);
		char from3 = (char) (Integer.parseInt(FROM.substring(8, 11)) & 0xff);
		char from4 = (char) (Integer.parseInt(FROM.substring(12, 15)) & 0xff);
		byte octet1 = (byte) (dl / 256);
		byte octet2 = (byte) (dl % 256);
		char DATAL1 = (char) (octet1 & 0xff);
		char DATAL2 = (char) (octet2 & 0xff);
		String options = "";
		for (int i = 0; i < OPTIONS.length; i++) {
			// int n = OPTIONS[i].length();
			// byte octet = (byte) (n);
			// char octetchar = (char) (octet & 0xff);
			// char indiceOption = (char) ((10+(10*i)) & 0xff);
			options = options + OPTIONS[i];
		}
		// System.out.println(DATAL1);
		// System.out.println(DATAL2);
		String msg = DATAL2 + "" + DATAL1 + "" + dest1 + "" + dest2 + "" + dest3 + "" + dest4 + "" + from1 + "" + from2 + "" + from3 + "" + from4 + "" + NBOPTIONS + options + DATA;
		return msg;
	}

	public static void main(String args[]) {
		String msg = create();
		System.out.println(msg);
		String dataout = msg.substring(msg.length() - (256 * msg.charAt(0) + msg.charAt(1)));
		System.out.println(dataout);
	}
}
