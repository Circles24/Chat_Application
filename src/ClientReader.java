import java.io.DataInputStream;

public class ClientReader implements Runnable {

	private DataInputStream din;
	private byte[] buff;
	private String name;

	ClientReader(DataInputStream din) {

		this.din = din;
		new Thread(this).start();
		buff = new byte[1024];

		new Thread(this).start();

	}

	public void run() {

		try {

			while (true)
				System.out.write(buff, 0, din.read(buff));

		}

		catch (Exception ex) {

			System.out.println(ex.getMessage());

		}

	}

}
