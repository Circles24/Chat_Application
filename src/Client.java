import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Client {

	private final Socket skt;
	private final String name;

	private static final String clientConfigFilePath;

	static {

		clientConfigFilePath = System.getenv("ClientConfigFilePath");
	}

	// main client constructor
	private Client(Socket skt, String name) {

		System.out.println("initializing the client");

		this.name = name;
		this.skt = skt;

		try {

			ClientReader ClReader = new ClientReader(new DataInputStream(skt.getInputStream()));
			ClientWriter ClWriter = new ClientWriter(new DataOutputStream(skt.getOutputStream()), name);

		}

		catch (Exception ex) {

			System.out.println("client initialization failed :: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public static void Launcher() throws Exception {

		HashMap<String, String> configMap = new HashMap<String, String>();

		System.out.println("provided config file :: " + clientConfigFilePath);

		try {

			File clientConfigFile = new File(clientConfigFilePath);
			BufferedReader buffReader = new BufferedReader(new FileReader(clientConfigFile));

			String line;
			String[] segmentedLine;

			System.out.println("reading configs now");

			while ((line = buffReader.readLine()) != null) {

				segmentedLine = line.split(" ");

				// empty line
				if (segmentedLine.length == 0)
					continue;

				// comment
				if (segmentedLine[0].equals("#"))
					continue;

				if (segmentedLine.length != 2)
					throw new Exception("corrupt config file");

				configMap.put(segmentedLine[0], segmentedLine[1]);
			}

			System.out.println("done reading configs");
		}

		catch (Exception ex) {

			System.out.println("Exception@Client.Launcher :: " + ex.getMessage());
			ex.printStackTrace();

			System.out.println("launching the client with default configs");

			// clearing the config map to launch client with default config
			configMap.clear();
			configMap.put("ServerAddress", "localhost");
			configMap.put("ServerPort", "1234");
		}

		finally {

			InetAddress serverAddress = InetAddress.getByName(configMap.get("ServerAddress"));
			int port = Integer.parseInt(configMap.get("ServerPort"));

			Scanner scn = new Scanner(System.in);
			System.out.println("enter the username");
			String name = scn.next();

			System.out.println("finally launching the client");

			new Client(new Socket(serverAddress, port), name);
		}

	}

	public static void main(String args[]) {

		try {

			Client.Launcher();
		}

		catch (Exception ex) {

			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
