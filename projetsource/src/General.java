import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

public class General {

	public static void main(String[] args) {
		// the "main loop" of your networked application
		//
		PhysicalLayer pl = new PhysicalLayer("volvo"); // initiate
		// a
		// physical
		// layer
		SNACASD sd = new SNACASD(pl, 5, 1); // initiate a
		// name service
		// discovery
		// module

		String myID = "thomas"; // check the uniqueness of
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

		String msg = null;
		
		while (true) {
			// Process incoming messages
			msg = pl.receive();
			if (msg != null) {
				System.out.println(msg);
				StringTokenizer st = new StringTokenizer(msg, ":");

			}
			//pl.send("ALL:" + myID + ":HELLO:");
			
			// Sleep for milliseconds
		}
	}
}


