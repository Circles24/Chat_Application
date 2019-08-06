import java.io.DataOutputStream;
import java.io.DataInputStream;

public class ChatProcessor implements Runnable
{

	DataInputStream din;
	DataOutputStream dout;
	byte[] buff;

	public ChatProcessor(DataInputStream din,DataOutputStream dout)throws Exception
	{

		this.din = din;
		this.dout = dout;
		dout.writeBytes("\n______________ CHAT ________________\n");

		buff = new byte[1024];

		new Thread(this).start();

	}

	public void run(){

		try
		{

			while(true)dout.write(buff,0,din.read(buff));
		}

		catch(Exception ex){

			System.out.println("@ChatProcessor :: "+ex.getMessage());
		}	
	}
}