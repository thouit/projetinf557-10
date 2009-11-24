import java.util.HashMap;


public class Test {

	public static void main(String[] args){
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(18, "18v");
		hm.put(27, "27v");
		hm.put(45, "45v");
		hm.put(11, "11v");
		for (Integer i : hm.keySet()){
			System.out.println(i + " : " + hm.get(i));
		}
	}
}
