import java.util.HashMap;
import java.util.LinkedHashMap;


public class TraiterOptions {
	HashMap<Integer,String> OPTMAP = new LinkedHashMap<Integer,String>();
	boolean JeSuisDest = false;
	
	/*
	 * Table des correspondances
	 */
	
	/*
	 * Methodes
	 */
	
	public void traiterOptions(){
		for(int i : OPTMAP.keySet()){
			switch (i) {
				
			case 0 :
				System.out.println("option 0");
				break;

			case 1 :
				System.out.println("option 1");
				break;

			case 2 :
				System.out.println("option 2");
				break;

			default: 
				System.out.println("option inconnue");
				break;

		}
		}
	}
	
}
