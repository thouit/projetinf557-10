import java.util.*;

class SNACASD {

	String myName;

	PhysicalLayer pl;

	int retries;

	int maxtime;

	final long TIMEOUTMYNAME = 10000;

	final long TIMEOUTFRIENDNAME = 20000;

	Timer myNameIs;

	public SNACASD(PhysicalLayer pl, int retries, int maxtime) {
		this.pl = pl;
		this.retries = retries;
		this.maxtime = maxtime;

		myNameIs = new Timer();
		
	}

	public boolean verifyUniqueness(String name) {
		waitRandomly();

		for (int i = 0; i < retries; i++) {
			int counter = 0;

			// We try get "name" as our name. Then, we want to
			// know if someone is nammed "name" by sending a
			// discover message. Thus, we send this message to
			// "name" with NEW as default ID.
			pl.send(name + ":NEW:DISCOVER:");
			waitRandomly();

			String message = "";

			do {
				message = pl.receive();
				if (message != null) {
					String[] splitMessage = message.split(":");
					if (splitMessage[2] == null
							|| !TypeMessage.isGoodType(splitMessage[2])) {
						System.out.println("Message \"" + message
								+ "\" uncorrectly formatted");
						// System.exit(0);
					}
					if (splitMessage[2].equals("SERVICES")) {
						if (splitMessage[1].equals(name))
							return false;
					} else {
						if (splitMessage[2].equals("DISCOVER")) {
							if (splitMessage[0].equals(name))
								counter++;
						}
					}
				}
			} while (message != null);

			// Be carefull of the loopback !
			if (counter > 1)
				return false;
		}

		return true;
	}

	public Timer getTimer() {
		return myNameIs;
	}

	public String getUniqueName() {
		String tentative;

		do {
			// 10-character random string
			tentative = RandomString.nextString(10);
		} while (!verifyUniqueness(tentative));

		// every TIMEOUTMYNAME milliseconds, we send
		// to ALL that we are the guy named "tentative"
		ServicesTask task = new ServicesTask(pl, tentative);
		myNameIs.schedule(task, TIMEOUTMYNAME, TIMEOUTMYNAME);

		return tentative;
	}

	public String getUniqueName(String name) {
		if (verifyUniqueness(name)) {
			// every TIMEOUTMYNAME milliseconds, we send
			// to ALL that we are the guy named "name"
			ServicesTask task = new ServicesTask(pl, name);
			myNameIs.schedule(task, TIMEOUTMYNAME, TIMEOUTMYNAME);

			return name;
		} else
			return getUniqueName();
	}

	public void waitRandomly() {
		Random ran = new Random();

		try {
			Thread.sleep((maxtime * ran.nextInt(1001)));
		} catch (InterruptedException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
}

class ServicesTask extends TimerTask {

	PhysicalLayer pl;

	String name;

	ServicesTask(PhysicalLayer pl, String name) {
		this.pl = pl;
		this.name = name;
	}

	public void run() {
		pl.send("ALL:" + name + ":SERVICES:");
	}
}


