
public class CreationMsg {
	
	public static String create(){
		String DEST = "127.000.000.001";
		String FROM = "127.000.000.002";
		String NBOPTIONS = "2";
		String DATA = "type:message:";
		int dl = DATA.length();
		byte octet1 = (byte) (dl/256);
		byte octet2 = (byte) (dl%256);
		char DATAL1 = (char) (octet1 & 0xff);
		char DATAL2 = (char) (octet2 & 0xff);
		//System.out.println(DATAL1);
		//System.out.println(DATAL2);
		String msg = DATAL1 + "" + DATAL2 + ":" + DEST + ":" + FROM + ":" + NBOPTIONS + ":a:rien:b:rien:" + DATA;
		//System.out.println(msg);
		return msg;
	}
	
	public static void main(String args[]){
		String DEST = "127.000.000.001";
		String FROM = "127.000.000.002";
		String NBOPTIONS = "2";
		String DATA = "type:message:";
		int dl = DATA.length();
		byte octet1 = (byte) (dl/256);
		byte octet2 = (byte) (dl%256);
		char DATAL1 = (char) (octet1 & 0xff);
		char DATAL2 = (char) (octet2 & 0xff);
		System.out.println(DATAL1);
		System.out.println(DATAL2);
		String msg = DATAL1 + "" + DATAL2 + ":" + DEST + ":" + FROM + ":" + NBOPTIONS + ":a:rien:b:rien:" + DATA;
		System.out.println(msg);
		
		String dataout = msg.substring(msg.length() - (256*msg.charAt(0) + msg.charAt(1)));
		System.out.println(dataout);
	}
}
