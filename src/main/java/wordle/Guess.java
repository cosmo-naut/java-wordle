package wordle;

public class Guess {
	enum Result { NONE, PARTIAL, MATCH }
	
	String word;
	
	public Guess(String word) {
		this.word = word;
	}
	
	public String compare(String to) {
		Result[] results = new Result[word.length()];
		String copy = to;
		
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == to.charAt(i)) {
				results[i] = Result.MATCH;
				copy = removeFrom(copy, i);
			}
			else
				results[i] = Result.NONE;
		}
				
		for (int i = 0; i < word.length(); i++) 
			if (results[i] != Result.MATCH &&
			copy.contains(word.subSequence(i, i+1))) {
				results[i] = Result.PARTIAL;
				copy = removeFrom(copy, copy.indexOf(word.substring(i, i+1)));
			}
		
		return formatOutput(results);
	}
	
	private String removeFrom(String string, int index)
	{
		char[] chars = string.toCharArray();
		chars[index] = '_';
		return new String(chars);
	}
	
	public String formatOutput(Result[] results)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(word);
		builder.append("\n");
		
		for (Result r : results) {
			switch (r) {
				case MATCH: 
					builder.append("*");
					break;
				case PARTIAL:
					builder.append("~");
					break;
				default:
					builder.append("_");
			}
		}
		return builder.toString();
	}
	
	public boolean matches(String other) {
		return word.equals(other);
	}
}
