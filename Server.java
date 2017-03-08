package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		int number, temp;
		try {
			//Creating server socket
			ServerSocket serverSocket = new ServerSocket(29733);
			
			//accept incoming request to socket from Client
			Socket ss = serverSocket.accept();
			
			//scan for the number that the Client wants to pass to server
			Scanner clientIn = new Scanner(ss.getInputStream());
			
			//store the number
			number = clientIn.nextInt();
			
			//manipulate the data, e.g. add 10 to it
			temp = number + 10;
			
			//passing new number to client
			PrintStream serverOutput = new PrintStream(ss.getOutputStream());
			serverOutput.println(temp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
