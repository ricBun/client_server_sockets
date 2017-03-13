package threaded_sockets;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerThread extends Thread {

	Socket socket;
	CopyOnWriteArrayList<String> players;
	
	//Constructor that initializes the socket
	ServerThread(Socket socket, CopyOnWriteArrayList<String> players) {
		this.socket = socket;
		this.players = players;
	}

	public void run() {

		try {
			//use getInputStream to pickup what is coming from the client
			//this inputstream will be placed into the Buffered Reader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//store inputstream into message
			String message;
			
			//write message to user
			PrintWriter serverToClient = new PrintWriter(socket.getOutputStream(), true);
			
			//while there is a message, keep reading
			while ((message = bufferedReader.readLine()) != null) {
				
				//parsed message
				String[] parsed = message.split("_", 2);
				
				switch (parsed[0]) {
				case "JOIN":
					if (players.contains(parsed[1])) {
						//Server message:
						System.out.println(parsed[1] + " already in!");
						//Client message
						serverToClient.println(parsed[1] + " already in!");
					}
					else {
						players.add(parsed[1]);
						//Server message
						System.out.println(parsed[1] + " is now playing!");
						//Client message
						serverToClient.println(parsed[1] + " is now playing!");
					}
					break;
				case "LEAVE":
					if(players.contains(parsed[1])) {
						players.remove(parsed[1]);
						//Server message:
						System.out.println(parsed[1] + " has left the game!");
						//Client message
						serverToClient.println(parsed[1] + " has left the game!");
					}
					else {
						//Server message
						System.out.println(parsed[1] + " is not playing!");
						//Client message
						serverToClient.println(parsed[1] + " is not playing!");
					}
					break;
				case "LIST":
					//Server message:
					System.out.println("List request: " + players);
					//Client message
					serverToClient.println(players);
					break;
				default:
					System.out.println(message);
					break;
				}
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
