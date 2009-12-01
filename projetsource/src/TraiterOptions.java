import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Classe de traitement et de stockage des options
 * 
 * @author Thomas
 * 
 */
public class TraiterOptions {
	HashMap<Integer, String> OPTMAP = new LinkedHashMap<Integer, String>();

	boolean jeSuisDest = false;

	/*
	 * Table des correspondances
	 */

	/*
	 * Methodes
	 */

	/**
	 * Traitement des options
	 */
	public void traiterOptions() {
		for (int i : OPTMAP.keySet()) {
			// options globales (concernant le destinataire et les
			// non-destinataires
			switch (i) {
			case 0:
				System.out.println("option 0");
				break;

			case 1:
				System.out.println("option 1");
				break;

			case 2:
				System.out.println("option 2");
				break;
			}
			// options relatives au destinataire
			if (jeSuisDest) {
				switch (i) {
				case 10:
					System.out.println("option 10");
					break;

				case 11:
					System.out.println("option 11");
					break;

				case 12:
					System.out.println("option 12");
					break;
				}

			} else {// options relatives aux non destinataires
				switch (i) {
				// On modifie les options si besoin.
				case 20:
					System.out.println("option 20");
					break;

				case 21:
					System.out.println("option 21");
					break;

				case 22:
					System.out.println("option 22");
					break;
				}

			}

		}

	}

}
