public class Test {

	public static void main(String[] args) {
		Msg msg = new Msg(CreationMsg.create());
		if (msg != null && msg.header != null) {
			System.out.println(msg.header + msg.options + msg.body);
			// envoie du msg aux classes traitant le message, d'abord le header
			// = header + options
			new Header(msg.header, msg.options, "Thomas");
			// envoie du msg aux classes traitant le message, ensuite le message
			// en lui même = body
		}
	}
}
