public class Header {

	String header;

	String options;

	int DATALENGTH;

	int OPTNBR;

	String DEST;

	String FROM;

	TraiterOptions to = new TraiterOptions();

	public Header(String header, String options, String MY_NAME) {
		this.header = header;
		this.options = options;
		traiterHeader();
		traiterOptions();
		if (DEST.equals(MY_NAME)) {
			to.jeSuisDest = true;
		}
		to.traiterOptions();

	}

	public void traiterHeader() {
		System.out.println(header);
		DATALENGTH = 256 * header.charAt(0) + header.charAt(1);
		DEST = header.substring(2, 6);
		FROM = header.substring(6, 10);
		OPTNBR = header.charAt(10);
		System.out.println(OPTNBR);
	}

	public void traiterOptions() {
		System.out.println("OPTIONS : " + options);
		int pos = 0;
		for (int i = 0; i < OPTNBR; i++) {
			int opt = options.charAt(pos);
			pos++;
			int taille = options.charAt(pos);
			pos++;
			System.out.println("Numéro de l'option : " + opt + " Taille de l'option : " + taille);
			if (!to.OPTMAP.containsKey(opt)) {
				to.OPTMAP.put(opt, options.substring(pos, pos + taille)); // a tester !!!!!!!
			} else {
				System.out.println("*** Warning : Deux options sont identiques ***");
			}
			pos += taille;
		}
		if (pos != options.length()) {
			System.err.println("Erreur : Mauvais format options");
		}
	}

}
