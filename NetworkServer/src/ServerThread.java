import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ServerThread extends Thread {
	private Socket clientConnection;
	DataInputStream inputFromClient;
	DataOutputStream outputToClient;
	BufferedReader input;
	String menuSelection;
	public ServerThread(Socket clientSocket) {
		clientConnection = clientSocket;
	}
	
	public void run() {
		System.out.println("Connection received from " + clientConnection.getInetAddress() + ":"
				+ clientConnection.getPort());
		try {
			inputFromClient = new DataInputStream(clientConnection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputToClient = new DataOutputStream(clientConnection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		input = new BufferedReader(new InputStreamReader(inputFromClient));
		try {
			menuSelection = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(menuSelection);
		selection(menuSelection.trim());

		// Close streams
		try {
			outputToClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inputFromClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//server.close();
		try {
			clientConnection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(menuSelection.equals("Request 7")){
			System.exit(0);
		}
	}
		
		public void selection(String menuSelect) {
			Process process;
			String output;
			BufferedReader br;
			try {
				switch (menuSelect) {

				case "Request 1":
					process = Runtime.getRuntime().exec("date");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 2":
					process = Runtime.getRuntime().exec("uptime");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 3":
					process = Runtime.getRuntime().exec("free");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));

					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 4":
					process = Runtime.getRuntime().exec("netstat");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));

					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 5":
					process = Runtime.getRuntime().exec("who");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));

					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 6":
					process = Runtime.getRuntime().exec("ps -e");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));

					while ((output = br.readLine()) != null)
						outputToClient.writeChars(output);
					break;
				case "Request 7":
					System.out.println("Quit option chosen.");
					outputToClient.writeChars("Closing socket...");
					break;
				default:
					System.out.println("Please enter a valid selection.");
					break;
				}
			} catch (Exception e) {
				System.out.println("There was an exception.  Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
		}

}
