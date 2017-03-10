package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		String clientMessage = "";
		String temp = "";
		try {
			//Creating server socket
			ServerSocket serverSocket = new ServerSocket(29733);
			
			System.out.println("Server up and ready for connections...");
			
			//accept incoming request to socket from Client
			Socket ss = serverSocket.accept();
			
			//scan for the message that the Client wants to pass to server
			Scanner clientIn = new Scanner(ss.getInputStream());
			
			while (clientIn.hasNextLine()) {
				//store the Message
				if ((clientMessage = clientIn.nextLine()) != null) {
					System.out.println("client typed: " + clientMessage);
					
					//manipulate the string, uppercase
					temp = clientMessage.toUpperCase();
					
					//passing new string to client
					PrintStream serverOutput = new PrintStream(ss.getOutputStream());
					serverOutput.println(temp);
				}
			}
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
