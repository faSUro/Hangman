/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package net;

import hangman.Player;
import hangman.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Manage a player playing with the terminal.
 * 
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class RemotePlayer extends Player {
    
    BufferedReader in;
    PrintWriter out;
        
    /**
     * Constructor.
     * @param out 
     * @param in 
     */
    public RemotePlayer(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
    
    @Override
    public void update(Game game) {
        switch(game.getResult()) {
            case FAILED:
                printBanner("Hai perso!  La parola da indovinare era '" +
                            game.getSecretWord() + "'");
                break;
            case SOLVED:
                printBanner("Hai indovinato!   (" + game.getSecretWord() + ")");
                break;
            case OPEN:
                int rem = Game.MAX_FAILED_ATTEMPTS - game.countFailedAttempts();
                out.print("\n" + rem + " tentativi rimasti\n");
                out.println(game.getKnownLetters());
                break;
        }
    }
    
    private void printBanner(String message) {
        out.println(message);
    }

    /**
     * Ask the user to guess a letter.
     * 
     * @param game
     * @return
     */
    @Override
    public char chooseLetter(Game game) {
        for (;;) {
            out.print("Inserisci una lettera: ");
            String line = null;
            try {
                line = in.readLine().trim();
            } catch (IOException e) {
                line = "";
            }
            if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
                return line.charAt(0);
            } else {
                out.println("Lettera non valida.");
            }
        }
    }
}
