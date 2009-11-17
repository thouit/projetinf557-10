import java.net.*;
import java.io.*;
import java.util.*;

//////////////////////////
// CLASS PHYSICAL LAYER //
//////////////////////////

class PhysicalLayer {

  // The received message buffer. We use 
  // an object in order to be shared between 
  // server and clients on the same PhysicalLayer
  ReceivedBuffer rb;

  // The percentage of packets not dropped
  int successRate = 100;

  // Socket, port and group for broadcasting
  MulticastSocket client;
  int port;
  String group;


  public PhysicalLayer() {
    try
      {
	rb = new ReceivedBuffer();
	client = new MulticastSocket();
	port = 11111;
	group = "225.6.7.8";

	// here, we create and launch the listenning
	// server waiting for messages. When a message
	// is caught by the server, it is enqueue in 
	// the received buffer.
	Thread t_s = new Thread(new Server(rb));    
	t_s.start();
      }
    catch(IOException e) 
      {
	System.out.println(e);
	System.exit(0); // kill program
      }
  }

  public void setSuccessRate(int s) {

    // s must be between 0 and 100
    if((s >= 0) && (s <= 100))
      successRate = s;
  }


  public void send(String message) {

    Random ran = new Random();

    // if a random number between 0 and 100 is 
    // smaller than successRate, then we send the 
    // message. Otherwise, we do nothing.
    if(ran.nextInt(100) <= successRate)
      {
	
	byte[] buf = message.getBytes();
	
	try
	  {
	    DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName(group), port);
	    
	    // send(packet, time) is deprecated. 
	    // This a solution proposed by Sun.
	    client.setTimeToLive(4);
	    client.send(pack);
	    client.setTimeToLive(4);
	  }
	catch(UnknownHostException e) 
	  {
	    System.out.println(e);
	    System.exit(0); // kill program
	  }
	catch(IOException e) 
	  {
	    System.out.println(e);
	    System.exit(0); // kill program
	  }
      }
  }

  public String receive() {			
    return rb.receive();
  }

  public void addMessageInBuffer(String message) {
    rb.receiveBuffer.addFirst(message);
  }  
} 


//////////////////
// CLASS SERVER //
//////////////////

class Server implements Runnable {

  ReceivedBuffer rb;

  public Server(ReceivedBuffer rb) {
    this.rb = rb;
  }

  public void run() {
    
    int port = 11111;
    String group = "225.6.7.8";
    MulticastSocket server = null;
    byte[] buf;

    try
      {		
	server = new MulticastSocket(port);
	server.joinGroup(InetAddress.getByName(group));
      } 
    catch(IOException e) 
      {
	System.out.println(e);
	System.exit(0); // kill program
      }
    
    while(true)
      {
	try
	  {
	    buf = new byte[16384];
	    DatagramPacket pack = new DatagramPacket(buf, buf.length);
	    server.receive(pack);

	    // the last two arguments are used to avoid nasty
	    // String format under Windows. Huh, so surprising...
	    String data = new String(pack.getData(), 0, pack.getLength());
	    
	    // if the data is not empty, we enqueue it into
	    // the received message buffer
	    if(data != null)
		rb.receiveBuffer.addFirst(data);
	  } 
	catch(IOException e) 
	  {
	    System.out.println(e); 
	    System.exit(0); // kill program
	  }
      }
  }
}


///////////////////////////
// CLASS RECEIVED BUFFER //
///////////////////////////

class ReceivedBuffer {

  // The received message buffer used 
  // in this class as a queue
  LinkedList<String> receiveBuffer;

  ReceivedBuffer() {
    receiveBuffer = new LinkedList<String>();
  }

    public boolean isEmpty() {
	return receiveBuffer.isEmpty();
    }

  public String receive() {			
    try
      {
	String s = receiveBuffer.removeLast();
	
	// Some messages are skipped if the thread 
	// is not busy (or doesn't fall asleep).
	try
	    {
		Thread.sleep(100); 
	    }
	catch(InterruptedException e) {System.out.println(e); System.exit(0);}

	return s;
      }
    catch(NoSuchElementException e) {return null;}
  }

}


////////////////////
// CLASS KEYBOARD //
////////////////////

class Keyboard {

  // A static method waiting for keyboard 
  // entry finished by the return buttom 
  // and return this entry through a String  
  public static String readString () {
    String readLine = null;
    try
      { 
	InputStreamReader reader = new InputStreamReader (System.in);
	BufferedReader input = new BufferedReader (reader);
	readLine = input.readLine();
      }
    catch (IOException e)
      {
	System.out.println(e);
	System.exit(0); // kill program
      }
    return readLine;
  }
}


///////////////////////////
/// CLASS RANDOM STRING ///
///////////////////////////

class RandomString {

  public static String nextString(int n) {
  
    // n is the length of the requested string
    char[] buf = new char[n];
    Random gen = new Random();
    
    // prevents of having non alpha-numerical characters in the string
    for(int i=0; i < n; i++)
      switch(gen.nextInt(3))
	{
	case 0:
	  // pick something between a and z
	  buf[i] = (char)(gen.nextInt(26)+65);
	  break;
	case 1:
	  // pick something between A and Z
	  buf[i] =  (char)(gen.nextInt(26)+97);
	  break;
	case 2:
	  // pick a number
	  buf[i] = (char)(gen.nextInt(10)+48);
	}
          
    return new String(buf);
  }
}


//////////////////////////
/// CLASS TYPE MESSAGE ///
//////////////////////////

class TypeMessage {
  
  // do not forget to expend this method 
  // if new message types are defined
  // Be aware that election messages are not 
  // taken into account here!  
  public static boolean isGoodType(String type) {
    return (type.equals("SERVICES") || 
	    type.equals("DISCOVER") || 
	    type.equals("MESSAGE") || 
	    type.equals("ACK") || 
	    type.equals("NACK") || 
	    type.equals("JOIN") || 
	    type.equals("ACCEPT"));
  }
}
