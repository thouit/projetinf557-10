import java.net.*;
import java.io.*;
import java.util.*;


class InServer implements Runnable{
	ReceivedBuffer rb;
	int port;
	public InServer(ReceivedBuffer rb,int port){
		this.rb = rb;
		this.port = port;
	}

	public InServer(ReceivedBuffer rb){
		this.rb = rb;
		this.port = 11111;
	}

	public void run(){
		DatagramSocket s;
		byte [] buf;


		try{
			s = new DatagramSocket(port);

			while(true){
				buf = new byte[16384];
				DatagramPacket pack = new DatagramPacket(buf,buf.length);
				s.receive(pack);
				//System.out.println("INFO: "+pack.getSocketAddress());

				String data = new String(pack.getData(),0, pack.getLength());
				if(data != null){
					rb.receiveBuffer.addFirst(data);

				}
			}
		}catch(IOException e){
			System.out.println(e);
			System.exit(-1);
		}
	}
}



class CentralServer{

	ReceivedBuffer rb;
	HashMap<String,List<String>> neighbour_table; /** Id <-> Neighbour */
	HashMap<String,String> table;
	DatagramSocket sender;
	int in_port;
	int out_port;

	public CentralServer(){
		in_port = 11111;
		out_port = 11111;
		try{
			rb = new ReceivedBuffer();
			neighbour_table = new LinkedHashMap<String,List<String>>(30);
			table = new LinkedHashMap<String,String>(30);


			Thread t_s = new Thread(new InServer(rb));
			t_s.start();
			sender = new DatagramSocket();
		}catch(SocketException e){
			System.out.println(e);
			System.exit(-1);
		}
	}
	public CentralServer(int in,int out){
		in_port = in;
		out_port = out;
		try{
			rb = new ReceivedBuffer();
			neighbour_table = new LinkedHashMap<String,List<String>>(30);
			table = new LinkedHashMap<String,String>(30);


			Thread t_s = new Thread(new InServer(rb,in_port));
			t_s.start();
			sender = new DatagramSocket();
		}catch(SocketException e){
			System.out.println(e);
			System.exit(-1);
		}
	}

	void addRoute(String from, String to){
		if(!table.containsKey(from)){
			System.out.println("addRoute: unknown from : "+from);
		}else if(!table.containsKey(to)){
			System.out.println("addRoute: unknown to : "+to);
		}else{
			// WARNING: there is no checking if the link
			// from -> to was already there
			List<String> tos = neighbour_table.get(from);
			tos.add(to);
			tos = neighbour_table.get(to);
			tos.add(from);
			System.out.println("Route added : "+from+" -> "+to);
		}
	}

	void delRoute(String from,String to){
		if(!table.containsKey(from)){
			System.out.println("delRoute: unknown from : "+from);
		}else if(!table.containsKey(to)){
			System.out.println("delRoute: unknown to : "+to);
		}else{
			List<String> tos = neighbour_table.get(from);
			tos.remove(to);
			tos = neighbour_table.get(to);
			tos.remove(from);
			System.out.println("Route deleted : "+from+" -> "+to);
		}
	}

	void insertNode(String from,String real_name){
		if(table.containsKey(from) && table.get(from).equals(real_name)){
			System.out.println("Node "+from+"("+real_name+") already known, nothing changed");
		}else{
			table.put(from,real_name);
			neighbour_table.put(from,new LinkedList<String>());
			List<String> l = neighbour_table.get(from);
			l.add(from);
			System.out.println("Node "+from+"("+real_name+") added to the network");
		}
	}

	void showTopology(){
		System.out.println("Ids -> Computer:");
		for (Map.Entry<String, String> e : table.entrySet()) {
			System.out.println("\t"+e.getKey()+" -> "+e.getValue());
		}
		System.out.println("\nTopology:");
		for(Map.Entry<String,List<String>> e: neighbour_table.entrySet()){
			System.out.println(e.getKey()+":");
			for(String to : e.getValue()){
				System.out.println("\t"+to);
			}
		}

	}



	void route(){
		try{
			while(true){
				Message msg = rb.receive();
				if(msg == null || (! msg.good)){
					Thread.sleep(100);
				}else{
					if(msg==null){
						System.out.println("Erreur le message n'est pas bien formate");
					}else{
						msg.print();
						if(msg.dest.equals("255.255.255.255")){//si le message s'adresse au serveur
							if(msg.opt.containsKey(255)){
								String option = msg.opt.get(255);
								StringTokenizer st = new StringTokenizer(option," ");
								if(st.countTokens()<1){
									System.out.println("Le message pour le serveur n'est pas conforme");
								}else{
									String type = st.nextToken();
									if(type.equals("addroute")){
										String from = st.nextToken();
										String dest = st.nextToken();
										addRoute(from,dest);
									}else if(type.equals("delroute")){
										String from = st.nextToken();
										String dest = st.nextToken();
										delRoute(from,dest);
									}else if(type.equals("serverjoin")){
										insertNode(msg.from,st.nextToken());
									}else if(type.equals("topo")){
										showTopology();
									}
								}
							}

						}else if(! table.containsKey(msg.from)){
							System.out.println("Error, unknown sender : "+msg.from);
						}else{
							try{
								List<String> tos = neighbour_table.get(msg.from);
								if( ! msg.dest.equals("255.255.255.254")){
									System.out.println("FROM "+msg.from+" TO " +msg.dest);
								}
								if(tos.contains(msg.dest)){//si on a le destinataire dans ses voisins, on le lui envoi
									System.out.println("   ENVOI DIRECT");
									System.out.println("   "+msg.dest);
									System.out.println("   "+table.get(msg.dest));
									byte [] buf = msg.construireMessage().getBytes();
									InetAddress real_dest = InetAddress.getByName(table.get(msg.dest));
									DatagramPacket pack = new DatagramPacket(buf,buf.length,real_dest,out_port);
									sender.send(pack);
									//System.out.println("    ENVOI de "+msg.from+" a "+destinataire);
								}else{//sinon on envoie a tous  les voisins
									for(String to : tos){
										if( ! msg.dest.equals("255.255.255.254")){
											System.out.println("FROM "+msg.from+" TO " +msg.dest);
										}
										byte [] buf = msg.construireMessage().getBytes();
										InetAddress real_dest = InetAddress.getByName(table.get(to));
										DatagramPacket pack = new
										DatagramPacket(buf,buf.length,real_dest,out_port);
										sender.send(pack);
										//System.out.println("    ENVOI de "+msg.from+" a "+table.get(to));
									}
								}
							}catch(UnknownHostException e){
								System.out.println(e);
							}catch(IOException e){
								System.out.println(e);
							}
						}
					}
				}
			}
		}catch(InterruptedException e){
			System.out.println(e);
			System.exit(-1);
		}

	}

	public static void main(String [] args){
		CentralServer c = new CentralServer();
		c.route();
	}
}


