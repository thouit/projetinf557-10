
public class CreationMsg {
	
	public static String create(){
		String[] OPTIONS = {"OPTION1", "OPTION2"};
		String msg = buildMsg("127.000.000.001", "127.000.000.002", 2, OPTIONS, "DATA"); // formatter l'option !!
		return msg;
	}
	
	public static String createName(String NAME){
		char NAME1= (char) (Integer.parseInt(NAME.substring(0, 3)) & 0xff);
		char NAME2= (char) (Integer.parseInt(NAME.substring(4, 7)) & 0xff);
		char NAME3= (char) (Integer.parseInt(NAME.substring(8, 11)) & 0xff);
		char NAME4= (char) (Integer.parseInt(NAME.substring(12, 15)) & 0xff);
		return NAME1 + "" + NAME2 + "" + NAME3 + "" + NAME4;
	}
	
	public static String buildMsg(String DEST, String FROM, int nbOptions, String[] OPTIONS, String DATA){
		String NBOPTIONS = "" + (char) (nbOptions & 0xff);
		int dl = DATA.length();
		char dest1= (char) (Integer.parseInt(DEST.substring(0, 3)) & 0xff);
		char dest2= (char) (Integer.parseInt(DEST.substring(4, 7)) & 0xff);
		char dest3= (char) (Integer.parseInt(DEST.substring(8, 11)) & 0xff);
		char dest4= (char) (Integer.parseInt(DEST.substring(12, 15)) & 0xff);
		char from1= (char) (Integer.parseInt(FROM.substring(0, 3)) & 0xff);
		char from2= (char) (Integer.parseInt(FROM.substring(4, 7)) & 0xff);
		char from3= (char) (Integer.parseInt(FROM.substring(8, 11)) & 0xff);
		char from4= (char) (Integer.parseInt(FROM.substring(12, 15)) & 0xff);
		byte octet1 = (byte) (dl/256);
		byte octet2 = (byte) (dl%256);
		char DATAL1 = (char) (octet1 & 0xff);
		char DATAL2 = (char) (octet2 & 0xff);
		String options = "";
		for (int i = 0; i<OPTIONS.length ; i++){
			int n = OPTIONS[i].length();
			byte octet = (byte) (n);
			char octetchar =  (char) (octet & 0xff);
			char indiceOption = (char) ((10+(10*i)) & 0xff);
			options = options + indiceOption + octetchar + "" + OPTIONS[i];
		}
		//System.out.println(DATAL1);
		//System.out.println(DATAL2);
		String msg = DATAL1 + "" + DATAL2 + "" + dest1 + "" + dest2 + "" + dest3 + "" + dest4 + "" + from1 + "" + from2 + "" + from3 + "" + from4 + "" + NBOPTIONS + options + DATA;
		return msg; 
	}
	
	public static void main(String args[]){
		String[] OPTIONS = {"OPTION1", "OPTION2"};
		String msg = buildMsg("127.000.000.001", "127.000.000.002", 2, OPTIONS, "DATA");
		System.out.println(msg);
		String dataout = msg.substring(msg.length() - (256*msg.charAt(0) + msg.charAt(1)));
		System.out.println(dataout);
	}
}
