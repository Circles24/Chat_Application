import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server implements Runnable
{

	int portNo;
	ServerSocket svrSkt;
	HashMap<Long, ChatBox> map;


	Server(int portNo)throws Exception
	{

		this.portNo = portNo;
		this.map = new HashMap<Long,ChatBox>();
		this.svrSkt = new ServerSocket(portNo);

		new Thread(this).start();

	}

	public void run(){

		System.out.println("Server is up at "+portNo);

		try{

			Socket skt;

			System.out.println(svrSkt);

			while(true){

				skt = null;

				skt = svrSkt.accept();

				System.out.println("New Client Connected");

				new ClientHandler(map,skt);

			}

		}

		catch(Exception ex){

			System.out.println("@server :: "+ex.getMessage());
		}

	}

	public static void main(String args[]){

		try{

			Server svr = new Server(Integer.parseInt(args[0]));

		}

		catch(ArrayIndexOutOfBoundsException ex){

			System.out.println("Usage Server < portNo >");
		}

		catch(Exception ex){

			System.out.println(ex.getMessage());
		}

	}

}