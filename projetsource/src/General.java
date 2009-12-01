import java.net.InetAddress;
import java.net.UnknownHostException;

public class General {

	public static void main(String[] args) {
		// the "main loop" of your networked application
		//
		PhysicalLayer pl = new PhysicalLayer("volvo"); // initiate
		// a
		// physical
		// layer
		//SNACASD sd = new SNACASD(pl, 5, 1); // initiate a
		// name service
		// discovery
		// module

		String myID = CreationMsg.createName("127.000.000.001"); // check the uniqueness of
		// some
		// id so you try to keep the
		// same all the time
		// retrieve the name of your computer
		String localname = null;
		try {
			localname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tell it to the main hub
		pl.send("server:" + myID + ":serverjoin:" + localname);

		Msg msg = null;
		
		while (true) {
			// Process incoming messages
			msg = new Msg(pl.receive());
			if (msg != null && msg.header != null) {
				System.out.println(msg.header + msg.options + msg.body);
				//envoie du msg aux classes traitant le message, d'abord le header = header + options
				new Header(msg.header, msg.options, myID);
				//envoie du msg aux classes traitant le message, ensuite le message en lui même = body
				
				//puis renvoie du message si destinataire autre que moi.
				
			}
			//pl.send("ALL:" + myID + ":HELLO:");
			
			// Sleep for milliseconds
		}
	}
}


