package threaded_sockets;

import java.io.*;
import java.net.*;

public class Client {
	public static void main (String args[]) {
		// ensure user passes in user name
	    if(args.length == 0)
	    {
	        System.out.println("Please enter a username!");
	        System.exit(0);
	    }
		
		//name used to differentiate each client
		String name = args[0];
		
		try {
			//instantiate socket for this particular client
			Socket clientSocket = new Socket("localhost", 29732);
			
			//send messages to server, true used to flush output after every line
			PrintStream serverOutput = new PrintStream(clientSocket.getOutputStream(), true);
			//reading client commands
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			//reading messages sent from  server to client
			BufferedReader serverToClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//Storing client input
			String clientInput = "";
			while(!clientInput.equals("exit")) {
				
				clientInput = bufferedReader.readLine();
				//print client name and client input
				serverOutput.println(name + ": " + clientInput);
				
				switch (clientInput) {
					case "JOIN":
						//tell server user wants to join game!
						serverOutput.println("JOIN_" + name);
						String joinMessage = serverToClient.readLine();
						System.out.println(joinMessage);
						break;
					case "LEAVE":
						//tell server user wants to leave game!
						serverOutput.println("LEAVE_" + name);
						String leaveMessage = serverToClient.readLine();
						System.out.println(leaveMessage);
						break;
					case "LIST":
						//tell server user wants list of current players!
						serverOutput.println("LIST");
						String listOfPlayers = serverToClient.readLine();
						System.out.println(listOfPlayers);
						break;
					default:
						break;
				}
			}
			//close socket, and remove player if in the list
			serverOutput.println("LEAVE_" + name);
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
