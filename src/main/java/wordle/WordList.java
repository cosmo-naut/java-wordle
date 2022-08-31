package wordle;

import java.util.Random;

public class WordList {
	String[] words;
	Random random;
	
	public WordList(String filepath) {
		words = new String[] { "lemon", "hello", "pizza" };
		random = new Random();
	}
	
	public String getRandomWord() {
		return words[random.nextInt(words.length)];
	}
}
