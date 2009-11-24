import java.util.HashMap;
import java.util.LinkedHashMap;

public class TraiterOptions {
	HashMap<Integer, String> OPTMAP = new LinkedHashMap<Integer, String>();

	boolean jeSuisDest = false;

	/*
	 * Table des correspondances
	 */

	/*
	 * Methodes
	 */

	public void traiterOptions() {
		// peut être réorganisé en traitant les options générales puis les options du destinataire si on est le destinataire
		if (jeSuisDest) {
			for (int i : OPTMAP.keySet()) {
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

				default:
					System.out.println("option inconnue");
					break;

				}
			}
		} else {
			for (int i : OPTMAP.keySet()) {
				switch (i) {

				case 10:
					System.out.println("option 0");
					break;

				case 11:
					System.out.println("option 1");
					break;

				case 12:
					System.out.println("option 2");
					break;

				default:
					System.out.println("option inconnue");
					break;

				}
			}
		}
	}

}
