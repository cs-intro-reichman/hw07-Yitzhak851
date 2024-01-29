/**
 * This program get a hash tag as a string,
 * and break it into its constituent words.
 * 
 * @author - Yitzhak Bar or
 * @version - 1.0
 */
public class HashTagTokenizer {
	public static void main(String[] args) {
		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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
	 * This function get a string (present word),
	 * and return boolean value that present
	 * if the word is exist in the dictionary.
	 * 
	 * @param word       - the word that we want to check if exist in the dictionary
	 * @param dictionary - the dictionary that we want to check if the word exist in
	 * @return - true if the word exist in the dictionary, false otherwise
	 */
	public static boolean existInDictionary(String word, String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function is a recursive function that accepts a hashthag word,
	 * as a string. whithout hash sign.
	 * and recursively decomposes it into its constituent words.
	 * print them out in the procces.
	 * in the order they are found line by line.
	 * 
	 * @param hashtag
	 * @param dictionary
	 */
	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
		// base case: if hashtag is a word in the dictionary, print it and return.
		if (existInDictionary(hashtag, dictionary)) {
			System.out.println(hashtag);
			return;
		}
		int N = hashtag.length();
		for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0, i);
			if (existInDictionary(prefix, dictionary)) {
				System.out.println(prefix);
				breakHashTag(hashtag.substring(i, N), dictionary);
				return;
			}
		}
	}
}