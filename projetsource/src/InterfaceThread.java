/**
 * Code le thread qui va nous permettre de lancer l'interface graphique d'envoie
 * des messages en parallele du fonctionnement de chaque poste.
 * 
 * @author Thomas
 * 
 */
public class InterfaceThread extends Thread {

	PhysicalLayer pl;

	public InterfaceThread(PhysicalLayer pl) {
		this.pl = pl;
	}

	public void run() {
		// long start = System.currentTimeMillis();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SendInterface(pl).setVisible(true);
			}
		});
	}
}