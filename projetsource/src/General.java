import java.net.InetAddress;
import java.net.UnknownHostException;

public class General {

	public static void main(String[] args) {

		PhysicalLayer pl = new PhysicalLayer("volvo"); // initiate

		String myID = CreationMsg.createName("127.000.000.001");

		String localname = null;
		try {
			localname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pl.send("server:" + myID + ":serverjoin:" + localname);

		Msg msg = null;

		while (true) {
			// On re�oit le message entrant
			msg = new Msg(pl.receive());
			if (msg != null && msg.header != null) {
				System.out.println(msg.header + msg.options + msg.body);
				// envoie du msg aux classes traitant le message, d'abord le
				// header = header + options
				Header h = new Header(msg.header, msg.options, myID);
				// envoie du msg aux classes traitant le message, ensuite le
				// message en lui m�me = body
				if (h.to.jeSuisDest) {
					System.out.println("Nous sommes destinataire du message !\r\nEnvoie du message � la couche sup�rieure. On n'envoie que le DATA");
				} else {
					System.out.println("Nous ne sommes pas destinataire du message !\r\nOn r�envoie le message en modifiant les options sur le r�seau");
					// le message � �t� modifi� par TraiterOptions, donc on peut
					// le reconstruire et le r�envoyer.
					msg.options = h.reBuild();
					// on renvoie le message
				}

			}
			// Sleep for milliseconds
		}
	}
}
