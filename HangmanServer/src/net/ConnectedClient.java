package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import hangman.Hangman;
import hangman.Player;

public class ConnectedClient extends Thread {
	
	Socket socket;
	
	public ConnectedClient(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run () {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
			Hangman game = new Hangman();
			Player player = new RemotePlayer(in, out);
			game.playGame(player);
        
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
