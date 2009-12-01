/***
 * Classe qui traite le header et ses options
 * 
 * @author Thomas
 * 
 */
public class Header {

	String header;

	String options;

	int DATALENGTH;

	int OPTNBR;

	String DEST;

	String FROM;

	TraiterOptions to = new TraiterOptions();

	/**
	 * On construit le header
	 * 
	 * @param header
	 * @param options
	 * @param MY_NAME
	 */
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

	/**
	 * On extrait les en têtes du header
	 */
	public void traiterHeader() {
		// System.out.println(header);
		DATALENGTH = 256 * header.charAt(0) + header.charAt(1);
		DEST = header.substring(2, 6);
		FROM = header.substring(6, 10);
		OPTNBR = header.charAt(10);
		System.out.println("Nombre d'options : " + OPTNBR);
	}

	/**
	 * On traite les options en les stockant dans une hasmap avec comme clé leur
	 * type (un entier) et comme valeur leur contenu
	 */
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
				to.OPTMAP.put(opt, options.substring(pos, pos + taille));
			} else {
				System.out.println("*** Warning : Deux options sont identiques. La dernière n'est pas prise en compte ***");
			}
			pos += taille;
		}
		if (pos != options.length()) {
			System.err.println("Erreur : Mauvais format options");
		}
	}

	/**
	 * Permet de reconstruire le message après avoir modifié les options, pour
	 * le réenvoyer.
	 * 
	 * @return
	 */
	public String reBuild() {
		String opt = "";
		for (int i : to.OPTMAP.keySet()) {
			char indiceOption = (char) ((byte) (i) & 0xff);
			char longOption = (char) ((byte) (to.OPTMAP.get(i).length()) & 0xff);
			opt = opt + indiceOption + "" + longOption + "" + to.OPTMAP.get(i);
		}
		options = opt;
		return opt;
	}

}
