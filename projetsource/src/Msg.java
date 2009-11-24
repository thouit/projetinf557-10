public class Msg {
	String header;

	String options;

	String body;

	public Msg(String msg) {
		if (msg != null) {
			if (msg.length() > 12) {
				header = msg.substring(0, 11);
				int dataLength = (256 * msg.charAt(0) + msg.charAt(1));
				if (msg.length() > 12 + dataLength) {
					options = msg.substring(11, msg.length() - dataLength);
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
	
	public void print(){
		System.out.print((int) header.charAt(2) + "." + (int) header.charAt(3) + "." + (int) header.charAt(4) + "." + (int) header.charAt(5) + ":");
		System.out.print((int) header.charAt(6) + "." + (int) header.charAt(7) + "." + (int) header.charAt(8) + "." + (int) header.charAt(9) + ":");
		System.out.println((int) header.charAt(10) + ":" + options + ":" + body);
	}

	public static void main(String[] args) {
		Msg msg = new Msg(CreationMsg.create());
		msg.print();
	}
}
