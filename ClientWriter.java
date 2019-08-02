import java.io.DataOutputStream;
import java.util.Scanner;


public class ClientWriter extends Thread
{
	private DataOutputStream dout;
	private Scanner scn;
	
	ClientWriter(DataOutputStream dout){
	
		this.dout = dout;
		scn = new Scanner(System.in);
		
		start();
	
	}
	
	public void run(){
	
		String mesg;
		
		try{
	
			while(true)
			{
				
				dout.writeBytes(scn.nextLine());
				
			}
			
		}
		
		catch(Exception ex){
			
			System.out.println(ex.getMessage());
		
		}
	
	}
	

}
