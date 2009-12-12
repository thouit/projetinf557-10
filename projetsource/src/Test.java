/**
 * Classe de Test pour la mise en forme, la création et la lecture des messages
 * pour le thread, ...
 * 
 * @author Thomas
 * 
 */
public class Test {

	public static void main(String[] args) {

		// // création d'une instance du Thread
		// InterfaceThread thread = new InterfaceThread();
		// // Activation du Thread
		// thread.start();
		// while (true) {
		// System.out.println("Ligne affichée par le main");
		// try {
		// //pause
		// Thread.sleep(800);
		// } catch (InterruptedException ex) {
		// }
		// }

		Msg msg = new Msg(CreationMsg.create());
		if (msg != null && msg.header != null) {
			System.out.println(msg.header + msg.options + msg.body);
			// envoie du msg aux classes traitant le message, d'abord le header
			// = header + options
			Header h = new Header(msg.header, msg.options, CreationMsg.createName("127.000.000.001"));
			// envoie du msg aux classes traitant le message, ensuite le message
			// en lui même = body
			h.reBuild();
			msg.options = h.options;
			msg.print();
		}
	}
}
