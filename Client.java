public class Client{
	
	String Name;
	Socket skt;
	
	
	Client(String Name,Socket skt){
		
		this.Name = Name;
		this.skt = skt;
		
		ClientReader ClReader = new ClientReader(skt.getInputStream());
		ClientWriter ClWriter = new ClientWriter(skt.getOutputSteam());
	
	}


}
