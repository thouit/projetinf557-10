import java.util.LinkedHashMap;
import java.util.StringTokenizer;


public class Header {
	
	String header;
	String options;
	
	int DATALENGTH;
	int OPTNBR;
	String DEST;
	String FROM;
	TraiterOptions to = new TraiterOptions();
	
	public Header(String header, String options, String MY_NAME){
		this.header=header;
		this.options=options;
		traiterHeader();
		traiterOptions();
		if(DEST.equals(MY_NAME)){
			to.JeSuisDest=true;
		}
		to.traiterOptions();
		
	}
	
	public void traiterHeader(){
		DATALENGTH = 256*header.charAt(0) + header.charAt(1);
		StringTokenizer st = new StringTokenizer(header, ":");
		st.nextElement();
		DEST = st.nextToken();
		FROM = st.nextToken();
		OPTNBR = st.nextToken().toCharArray()[0];
	}
	
	public void traiterOptions(){
		StringTokenizer st = new StringTokenizer(options, ":");
		if(st.countTokens()!=2*OPTNBR){
			System.out.println("Le nombre d'options ou le format ne correspond pas au nombre attendu");
		}else{
			for(int i=0; i<OPTNBR; i++){
				int opt = st.nextToken().toCharArray()[0];
				if(! to.OPTMAP.containsKey(opt)){
					to.OPTMAP.put(opt, st.nextToken());
				}else{
					System.out.println("Deux options sont identiques");
				}
			}
		}
	}
	
	
	

}
