import java.io.DataInputStream;

public class ClientReader implements Runnable
{
	
	private DataInputStream din;
	
	ClientReader(DataInputStream din,ClientWriter ClWriter){
	
		this.din = din;
		
		new Thread(this).start();
	
	}
	
	
	public void run(){
	
		byte[] reading_array = new byte[1024];
		
		try{
		
			while(true){
			
				System.out.write(reading_array,0,din.read(reading_array));
			
			}
		
		}
		
		catch(Exception ex){
			
			System.out.println(ex.getMessage());
		
		}	
	
	
	}

}
