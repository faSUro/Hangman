/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class HangmanServer {
	
	@SuppressWarnings("resource")
	private void serverLoop(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			ConnectedClient client = new ConnectedClient(socket);
			client.start();
		}
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 8888;
        HangmanServer server = new HangmanServer();
        try {
        	server.serverLoop(port);
        } catch (IOException e) {
        	e.printStackTrace();
        }    	
    }
    
}
