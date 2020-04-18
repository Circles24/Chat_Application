import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.util.HashMap;

public class ClientHandler implements Runnable {

	HashMap<Long, ChatBox> map;
	byte[] buff;
	Socket skt;

	public ClientHandler(HashMap<Long, ChatBox> map, Socket skt) {

		this.map = map;
		this.skt = skt;
		buff = new byte[1024];

		new Thread(this).start();

	}

	public void run() {

		try {

			System.out.println("running client handler at ");

			DataOutputStream dout = new DataOutputStream(skt.getOutputStream());
			dout.writeBytes("\n1     ::       Initiate Chat\n2     ::       Join Chat\nElse  ::    Disconnect Chat\n");

			long token = 0;

			Scanner scn = new Scanner(skt.getInputStream());
			switch (scn.nextInt()) {

				case 1: {

					token = TokenManager.getToken();
					map.put(token, new ChatBox(skt));
					dout.writeBytes("New session created\n");
					dout.writeBytes("your token is " + token + "\n");
					dout.writeBytes("Waiting for recipient ... ... ... \n");

				}
					break;

				case 2: {

					ChatBox cb = null;
					dout.writeBytes("enter the Token\n");
					while (cb == null) {

						cb = map.get(scn.nextLong());
						if (cb == null)
							dout.writeBytes("Wrong Token retry\n");
					}
					dout.writeBytes("Token Accepted\n");
					cb.add(skt);

				}
					break;

				default: {
					skt.close();
					throw new Exception("you choose to exit\n");
				}

			}
		}

		catch (Exception ex) {

			System.out.println("Exception@ClientHandler.run :: " + ex.getMessage());
		}
	}
}