
public class Msg {
	String header;
	String options;
	String body;

	public Msg(String msg){
		header = msg.substring(0, 37);
		options = msg.substring(37, msg.length() - (256*msg.charAt(0) + msg.charAt(1)));
		body = msg.substring(msg.length() - (256*msg.charAt(0) + msg.charAt(1)));
	}
	
	public static void main(String[] args){
		Msg msg = new Msg(CreationMsg.create());
		System.out.println("header > " + msg.header);
		System.out.println("options > " + msg.options);
		System.out.println("body > " + msg.body);
	}
}
