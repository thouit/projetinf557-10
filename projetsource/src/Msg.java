public class Msg {
	String header;

	String options;

	String body;

	public Msg(String msg) {
		if (msg != null) {
			if (msg.length() > 38) {
				header = msg.substring(0, 37);
				int dataLength = (256 * msg.charAt(0) + msg.charAt(1));
				if (msg.length() > 38 + dataLength) {
					options = msg.substring(37, msg.length() - dataLength);
					body = msg.substring(msg.length() - dataLength);
				} else {
					System.err.println("Message mal formate. Ce message n'est pas traite");
					this.header = null;
				}
			} else {
				System.err.println("Message mal formate. Ce message n'est pas traite");
			}
		}
	}

	public static void main(String[] args) {
		Msg msg = new Msg(CreationMsg.create());
		System.out.println("header > " + msg.header);
		System.out.println("options > " + msg.options);
		System.out.println("body > " + msg.body);
	}
}
