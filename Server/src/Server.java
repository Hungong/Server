import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Server {
	static String data = "";

	public static void main(String[] args) throws IOException {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					BufferedReader in;
					BufferedWriter out;
					ServerSocket listener = null;
					listener = new ServerSocket(9999);

					while (true) {
						System.out.println("Server is waiting to Acept Server...");

						Socket socketOfServer = listener.accept();

						log("----------New Connect with Client#  " + "at" + socketOfServer + "---------");

						in = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
						out = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
						ReadFolder();
						out.write(data);
						out.newLine();
						out.flush();
						String line = in.readLine();
						if (line.equals("OK")) {
							Thread th = new Thread(new Runnable() {
								BufferedReader inth = new BufferedReader(
										new InputStreamReader(socketOfServer.getInputStream()));
								BufferedWriter outth = new BufferedWriter(
										new OutputStreamWriter(socketOfServer.getOutputStream()));
								Byte byteArray = new Byte((byte) 10);

								@Override
								public void run() {
									// TODO Auto-generated method stub
									while (true) {
										try {
											String s = inth.readLine();
											System.out.println("Thread2" + s);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							});
							th.start();
						}

						// in = new BufferedReader(new
						// InputStreamReader(socketOfServer.getInputStream()));
						// out = new BufferedWriter(new
						// OutputStreamWriter(socketOfServer.getOutputStream()));
						//
						// String line = in.readLine();
						// System.out.println("ReadLine" + line);
						// if (line.equals("OK")) {
						// ReadFolder();
						// out.write(data);
						// out.newLine();
						// out.flush();
						// }
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		thread.start();

	}

	// public static void Connect() throws IOException {
	// ServerSocket listener = null;
	//
	// try {
	// listener = new ServerSocket(9999);
	// System.out.println("Server is waitin to Acept Server...");
	// Socket socketOfSerVer = listener.accept();
	// log("----------New Connect with Client#" + "at" + socketOfSerVer +
	// "---------");
	//
	// while (true) {
	// in = new BufferedReader(new
	// InputStreamReader(socketOfServer.getInputStream()));
	// out = new BufferedWriter(new
	// OutputStreamWriter(socketOfServer.getOutputStream()));
	// String line = in.readLine();
	// System.out.println("ReadLine" + line);
	// // if(line.equals("Ok")){
	// //// out.write(json);
	// // }
	// }
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	public static void log(String message) {
		System.out.println(message);
	}

	private static class ServiceThread extends Thread {
		private int clientNumber;
		private Socket socketOfServer;

		public ServiceThread(Socket socketServer, int ClientNumber) {
			this.clientNumber = ClientNumber;
			this.socketOfServer = socketServer;
			log("New Connect with Client#" + this.clientNumber + "at" + socketServer);
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

				while (true) {
					String line = in.readLine();
					System.out.println(line);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void ReadFolder() {
		File Folder = new File("C:\\Users\\hungo\\Desktop\\Apk_MultiTool\\APK-Multi-Tool-Stable-RELEASE");
		File[] lisFile = Folder.listFiles();
		JSONObject json = new JSONObject();
		JSONArray jsonArrFile = new JSONArray();
		JSONArray jsonArrFolder = new JSONArray();

		for (File nameFile : lisFile) {
			if (nameFile.isFile()) {
				jsonArrFile.add(nameFile.getName());
			} else {
				jsonArrFolder.add(nameFile.getName());
			}
		}

		json.put("File", jsonArrFile);
		json.put("Folder", jsonArrFolder);
		data = json.toString();
		System.out.println(json);

	}
}

class ClientConnection {
	InputStream in;
	OutputStream out;

	public ClientConnection(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		startRun();
	}

	public void startRun() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// in.read()

					// check package
				}
			}
		});
		t.start();
	}
}
