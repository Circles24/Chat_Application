import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server implements Runnable {

	private int portNo;
	private ServerSocket svrSkt;
	private HashMap<Long, ChatBox> map;

	private final static String ServerConfigFilePath;

	static {

		ServerConfigFilePath = System.getenv("ServerConfigFilePath");
	}

	private Server(HashMap<String, String> configHashMap) throws Exception {

		// main Server constructor

		this.portNo = Integer.parseInt(configHashMap.get("ServerPort"));

		this.map = new HashMap<Long, ChatBox>();
		this.svrSkt = new ServerSocket(portNo);

		new Thread(this).start();
	}

	// this method is used to launch the server
	public static void Launcher() throws Exception {

		HashMap<String, String> configMap = new HashMap<String, String>();

		System.out.println("provided config file " + ServerConfigFilePath);

		try {

			// reading config file
			File ServerConfigFile = new File(ServerConfigFilePath);
			BufferedReader buffReader = new BufferedReader(new FileReader(ServerConfigFile));

			String line;
			String[] segmentedLine;

			System.out.println("Reading the configs now");

			while ((line = buffReader.readLine()) != null) {
				// splitting the contents of the input
				segmentedLine = line.split(" ");

				// empty line needs to be continued
				if (segmentedLine.length == 0)
					continue;
				// entered line is comment
				if (segmentedLine[0].equals("#"))
					continue;

				if (segmentedLine.length != 2)
					throw new Exception("corrupt config file");

				configMap.put(segmentedLine[0], segmentedLine[1]);
			}

			System.out.println("Done reading configs");

		}

		catch (Exception ex) {

			System.out.println("Exception@Server :: " + ex.getMessage());
			ex.printStackTrace();

			System.out.println("Running the server now with default configs");

			// creating a server with default config
			configMap.clear();
			configMap.put("ServerPort", "1234");
		}

		finally {
			// launching the server
			new Server(configMap);
		}

	}

	public void run() {

		System.out.println("Server is up at " + portNo);

		try {

			Socket skt;

			System.out.println(svrSkt);

			while (true) {

				System.out.println("Waiting for connection ... ... ...");

				skt = svrSkt.accept();

				System.out.println("New Client Connected " + skt.getLocalAddress() + ":" + skt.getLocalPort());

				new ClientHandler(map, skt);

			}

		}

		catch (Exception ex) {

			System.out.println("Exception@Server.run :: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public static void main(String args[]) {

		System.out.println("***********************[Setting up the Server]***********************");

		try {

			Server.Launcher();
		}

		catch (Exception ex) {

			System.out.println("modify config files for flexibility");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}