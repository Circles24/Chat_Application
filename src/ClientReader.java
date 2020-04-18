import java.io.DataInputStream;

// ClientReader reads data from other clients DataOutputStream and writes it to users console
public class ClientReader implements Runnable {

	private DataInputStream din;
	private byte[] buff;
	private String name;

	ClientReader(DataInputStream din) {

		this.din = din;
		buff = new byte[1024];

		new Thread(this).start();

	}

	public void run() {

		try {

			while (true)
				System.out.write(buff, 0, din.read(buff));
		}

		catch (Exception ex) {

			System.out.println("Exception@ClientReader.run :: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

}
