import java.io.IOException;
import java.util.Scanner;

public class ThreadClient {
	static String hostName;
	static int portNumber;
	static int menuSelected;
	
	public static void main(String[] args) throws IOException {
		// check the args to ensure the correct number of args are given
		if(args.length < 2) {
			System.out.println("Please enter arguments in this format <server IP Address> <port number>\n");
			System.exit(0);
		}// end if args.length < 2 check
		hostName = args[0];
		
		try {
			portNumber = Integer.parseInt(args[1]);
		}// end try
		catch (NumberFormatException e) {
			System.out.println("User invalid input, please enter a port number as an Integer.");
			System.exit(-1);
		}// end catch (NumberFormatException)
		while(true) {
		menu();
		System.out.println("How many times would you like to run this action:");
		Scanner keyboard = new Scanner(System.in);
		int numberOfTimes = keyboard.nextInt();
		Thread[] theThreads = new Thread[numberOfTimes];
		for(int index = 0; index < numberOfTimes; index++)
			theThreads[index] = new ClientOptions(hostName, portNumber, menuSelected);
		runThreads(numberOfTimes, theThreads);
		}
	}// end main method 
	
	public static void menu() {
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
		menuSelected = sc.nextInt();
		
	}// end menu method
	
	
	public static void runThreads(int times, Thread[] theThreads) {
		System.out.println("Concurrent Client # " + times);
		for(int index = 0; index < theThreads.length; index++) {
			System.out.println("Thread # " + (index + 1));
			theThreads[index].run();
		}// end runThreads method
	}// end runThreads method
}// end ThreadClient class
