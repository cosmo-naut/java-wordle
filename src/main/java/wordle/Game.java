package wordle;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	static final int MAX_GUESSES = 6;
	static final int WORD_LENGTH = 5;
	static Scanner inputScanner;

	public static void main(String[] args) {
		WordList wordList = new WordList("filepath");
		inputScanner = new Scanner(System.in);
		boolean playAgain = true;
		
		while (playAgain) {
			String secretWord = wordList.getRandomWord();
			ArrayList<Guess> guesses = new ArrayList<Guess>();
			
			while (guesses.size() < MAX_GUESSES) {
				System.out.println(String.format(Messages.REQUEST_GUESS, WORD_LENGTH));
				String input = getUserInput();
				if (input.length() != WORD_LENGTH)
					continue;
				
				Guess newGuess = new Guess(input);
				
				guesses.add(newGuess);
				
				for (Guess guess : guesses) {
					System.out.println(guess.compare(secretWord));
				}
				
				if (newGuess.matches(secretWord)) {
					System.out.println(String.format(Messages.VICTORY, secretWord));
				}
				
				if (guesses.size() >= MAX_GUESSES) {
					System.out.println(String.format(Messages.LOSS, secretWord));
				}
			}
			
			if (queryUser(Messages.COPY_RESULTS)) {
				// do emoji
			}
			
			if (!queryUser(Messages.PLAY_AGAIN)) {
				playAgain = false;
			}
		}
		

	}
	
	public static String getUserInput() {
		return inputScanner.nextLine().toLowerCase();
	}
	
	public static boolean queryUser(String query) {
		System.out.println(query);
		return getUserInput().equals("y") || getUserInput().equals("yes");
	}
}
