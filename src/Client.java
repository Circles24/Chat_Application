import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.io.IOException;

public class Client {

	Socket skt;
	String name;

	public Client(Socket skt, String name) {

		this.name = name;
		this.skt = skt;

		try {

			ClientReader ClReader = new ClientReader(new DataInputStream(skt.getInputStream()));
			ClientWriter ClWriter = new ClientWriter(new DataOutputStream(skt.getOutputStream()), name);

		}

		catch (Exception ex) {

			System.out.println("client initialization failed :: " + ex.getMessage());
		}

	}

	public static void main(String args[]) {

		try {

			InetAddress host = InetAddress.getByName(args[0]);

			int portNo = Integer.parseInt(args[1]);

			String name = args[2];

			System.out.println("Connecting to server");

			Socket skt = new Socket(host, portNo);

			Client cl = new Client(skt, name);
		}

		catch (ArrayIndexOutOfBoundsException ex) {

			System.out.println("Client < host > < port > < client-name >");
		}

		catch (Exception ex) {

			System.out.println(ex.getMessage());

		}
	}

}
