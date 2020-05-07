/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import hangman.Hangman;
import hangman.Player;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {
	
	private void serverLoop(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		serveClient(socket);
	}

    private void serveClient(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Hangman game = new Hangman();
        Player player = new RemotePlayer(in, out);
        game.playGame(player);
        socket.close();
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
