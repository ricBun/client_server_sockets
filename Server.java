package threaded_sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

	public static final int PORT = 29732;
	
	//list of players
	static CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<String>();
	
	public static void main (String args[]) {
		try {
			new Server().runServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runServer() throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server up and ready for connections...");
		
		while (true) {
			//listen for connections and accept client connections
			Socket socket = serverSocket.accept();
			
			//Instantiation of server thread, passing in socket with port 29732, and a thread protected list of players
			new ServerThread(socket, players).start();
		}
		
	}
}
