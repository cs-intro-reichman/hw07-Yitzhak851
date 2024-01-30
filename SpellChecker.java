
public class SpellChecker {
	public static void main(String[] args) {
		String word = args[0]; // the word to be checked
		int threshold = Integer.parseInt(args[1]); // the threshold value
		String[] dictionary = readDictionary("dictionary.txt");

		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	/**
	 * This function accepts a string as input,
	 * and returns the string without the first character.
	 * 
	 * @param str - the string to be checked
	 * @return - the tail of the string
	 */
	public static String tail(String str) {
		// declare variables:
		int a = str.length();
		str.toLowerCase(); // str in lower case
		String tail = str.substring(a - a + 1); // tail - the string without the first char
		return tail;
	}

	/**
	 * This function accepts a string as input,
	 * and returns the first character of the string.
	 * 
	 * @param str - the string to be checked
	 * @return - the head of the string
	 */
	public static char head(String str) {
		char head = str.charAt(0);
		return head;
	}

	/**
	 * This function accepts two strings as input,
	 * and return the edit distance between them, as an integer.
	 * 
	 * @param word1 - the first string
	 * @param word2 - the second string
	 * @return - the levenshtein distance between the two strings
	 */
	public static int levenshtein(String word1, String word2) {
		int a = word1.length();
		int b = word2.length();
		if (a == 0) {return b;}
		if (b == 0) {return a;}
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int delete = levenshtein(tail(word1), word2);
		int insert = levenshtein(word1, tail(word2));
		int replace = levenshtein(tail(word1), tail(word2));
		return (1 + Math.min(delete, Math.min(insert, replace)));
	}

	/**
	 * This founction get a input file that contain the most
	 * 3,000 common words in English => read them line by line
	 * and return this words into a array of String.
	 * 
	 * @param fileName - the name of the file that read from
	 * @return - the tail of the string
	 */
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	/**
	 * This function receives a word,
	 * a threshold value for distance,
	 * and a dictionary as a input.
	 * and returns the word from the dictionary that most closely,
	 * according to the levenshtein distance, to the input word.
	 * if the smallest distance found is bigger than the threshold,
	 * this indicates that no word in the dictionary is sufficiently similar to the
	 * input word.
	 * in this case, the function returns the input word.
	 * 
	 * @param word       - the word to check
	 * @param threshold  - the threshold value for distance
	 * @param dictionary - the dictionary
	 * @return - the word from the dictionary that most closely to the input word
	 */
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// the minimum distance found so far
		int min = threshold + 1;
		// the word to return
		String correction = word;
		// this loop runs on the dictionary and checks the distance between the input
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);
			// identifies the word with the minimum distance
			// checks whether this distance exceeds the given threshold
			if (distance < min) {
				min = distance;
				// if the distance is smaller than the threshold, the word is replaced
				correction = dictionary[i];
			}
		}
		// if the minimum distance found is bigger than the threshold,
		// this indicates that no word in the dictionary is sufficiently similar to the
		// input word.
		return correction;
	}

}
