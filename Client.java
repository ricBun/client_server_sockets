package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main (String args[]) {
		//accepting input from user
		Scanner in = new Scanner(System.in);
		int number;
		String clientString = ""; 
		String temp = "";
		
		try {
			//socket with ip address and port #
			Socket clientSocket = new Socket("127.0.0.1", 29733);
			
			//get input stream from server
			Scanner serverInput = new Scanner(clientSocket.getInputStream());
			
			//store string from user
			System.out.println("Submit a message");
			
			while ((clientString = in.nextLine()) != null) {
				//pass string to the server
				PrintStream serverOutput = new PrintStream(clientSocket.getOutputStream());
				serverOutput.println(clientString);
				
				/*
				if (clientString.equals("exit")) {
					//clientSocket.close();
					break;
				}
				*/
				
				//store result from server
				temp = serverInput.nextLine();
				
				//print result from server
				System.out.println(temp);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
