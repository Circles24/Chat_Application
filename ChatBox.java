import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ChatBox 
{
	
	Socket s1;
	Socket s2;

	public ChatBox(Socket s1){

		this.s1 = s1;
	}

	public boolean isEmpty()
	{

		return (s1 == null) && (s2 == null);
	}
	

	public boolean isFull()
	{

		return (s1 != null) && (s2 != null);
	}


	public boolean isPartiallyFull()
	{

		return (s1 != null) && (s2 == null);
	}

	public void add(Socket s2)throws Exception
	{

		if(isFull())throw new Exception("ChatBox already Full");

		this.s2 = s2;

		System.out.println("ChatBox running");

		try{

			new ChatProcessor(new DataInputStream(s1.getInputStream()),new DataOutputStream(s2.getOutputStream()));
			new ChatProcessor(new DataInputStream(s2.getInputStream()),new DataOutputStream(s1.getOutputStream()));

		}

		catch(Exception ex){

			System.out.println("@ChatBox :: "+ex.getMessage());
		}

	}

}