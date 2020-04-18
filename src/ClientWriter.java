import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.DataInputStream;

// this class reads data from console and writes it to the dataInputStream of another client
public class ClientWriter implements Runnable {
	private DataOutputStream dout;
	private DataInputStream din;
	private byte[] buff;
	private final String name;

	ClientWriter(DataOutputStream dout, String name) {

		this.dout = dout;
		this.name = name;
		din = new DataInputStream(System.in);
		buff = new byte[1024];
		new Thread(this).start();

	}

	public void run() {

		try {

			while (true)
				dout.write(buff, 0, din.read(buff));
		}

		catch (Exception ex) {

			System.out.println("Exception@ClientWriter.run :: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

}
