import java.io.*;
import java.net.*;
import java.util.*;
public class ClientOptions extends Thread {
	private String hostName;
	private int port;
	private int menuSelected;
	public ClientOptions(String host, int portNumber, int menuItem) {
		hostName = host;
		port = portNumber;
		menuSelected = menuItem;
	}// end ClientOptions constructor
	
	public void run() {
		double timeStart;
		double timeEnd;
		Socket clientSocket;
		int timeOut = 15000;
		
		try {
			clientSocket = new Socket(hostName, port);
			clientSocket.setSoTimeout(timeOut);
			
			// Output from server
			DataOutputStream outputServer = new DataOutputStream(clientSocket.getOutputStream());
			timeStart = System.currentTimeMillis();
			outputServer.writeChars("Request " +  menuSelected);
			
			// Read input from server
			BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(inputFromServer);
			
			timeEnd = System.currentTimeMillis();
			clientSocket.close();
			System.out.println("Response time = " + (timeEnd - timeStart));
			outputServer.close();
		}// end try
		catch (Exception e) {
			System.out.println("Can not open socket at " + hostName + ":" + port);
			e.printStackTrace();
			System.exit(-1);
		}
	}// end run method

	
	/*public static void main(String[] args) throws IOException {
		
		double startTime;
		boolean validInput = false;
		
		if(args.length < 2)  
		{
			System.out.println("Type: java -jar Server2 <server IP Address>, <port number>\n");
			System.exit(0);
		}
		
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
			
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Socket socket = null;
		FileWriter fw = new FileWriter("output.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		
	     
		int menuSelect, times;
	     
		while(true)
		{
			
	       	System.out.println("\n*** MENU ***: ");
	            
			System.out.println("\n1. Host current Date and Time\n" + 
			"2. Host uptime\n" + 
			"3. Host memory use\n" + 
			"4. Host IPV4 socket connections\n" + 
			"5. Host current users\n" + 
			"6. Host running processes\n" +
			"7. Quit\n" + 
			"\nSelect option: ");
			Scanner sc = new Scanner(System.in);

			

			while(!sc.hasNextInt())
			{
				System.out.println("User invalid input, input number between 1 or 7");
			        sc.next();
			}// end while !sc.hasNextInt loop
			menuSelect = sc.nextInt();
	        
			System.out.println("How many times would you like to execute this command?");
            times = sc.nextInt();
            Thread[] threads = new Thread[times];

            try
			{
				socket = new Socket(hostName, portNumber);
			}// end try
			catch(Exception e)
			{
				System.out.println("cant open socket !! " + args[0] + "\n");
				System.exit(100);
			}// end catch
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            for(int i=0; i<times; i++){ 
            	startTime = System.currentTimeMillis();
            	
				switch(menuSelect)
				{
					case 1:
						System.out.println("Date Request from Client");
						out.println("Request 1");
						validInput =true;
						break;
			    	case 2:
			    		System.out.println("Uptime Request from Client");
						out.println("Request 2");
				   		validInput =true;
				   		break;
			    	case 3:
				   		System.out.println("Memory Use Request from Client");
				   		out.println("Request 3");
				   		validInput =true;
				   		break;
			    	case 4:
				   		System.out.println("IPV4 Socket Connections Request from Client");
				   		out.println("Request 4");
				   		validInput =true;
				   		break;
			    	case 5:
				   		System.out.println("Current Users Request from Client");
				   		out.println("Request 5");
				   		validInput =true;
				   		break;
			    	case 6:
				   		System.out.println("Current OS Version Request from Client");
				   		out.println("Request 6");
				   		validInput =true;
				   		break;
			    	case 7:
				   		System.out.println("Quit");
				   		out.println("Request 7");
				   		System.exit(5);
				   		break;
			    	default:
				   		System.out.println("Invalid Input");
				   		validInput =false;
				   		break;
				}//end switch
				if (validInput)
		    	{
	       			String answer;
	   				while((answer = input.readLine()) != null && !answer.equals("ServerDone"))
	   				{
	   					bw.write(answer);
	   					bw.newLine();
	   					System.out.println(answer);
	   				
	   				}// end while answer loop
	                
	         	double endTime;
	         	double totalTime;
	         	endTime = System.currentTimeMillis();
	         	totalTime = endTime - startTime;
	         	bw.write((int) totalTime);
	         	bw.newLine();
	         	System.out.printf("Total time spent: %.3f\n ", totalTime);
	      		}// end if validInput check
        	}// end for i<times loop
		}// end while true loop
	}// end main method	
	*/
}// end ClientOptions class
	
