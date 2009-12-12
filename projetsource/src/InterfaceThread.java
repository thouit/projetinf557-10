public class InterfaceThread extends Thread {
	
	PhysicalLayer pl;
	
	public InterfaceThread(PhysicalLayer pl){
		this.pl=pl;
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendInterface(pl).setVisible(true);
            }
        });
	}
}