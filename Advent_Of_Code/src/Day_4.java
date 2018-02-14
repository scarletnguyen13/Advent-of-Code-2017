/*
	 	--- Day 4: High-Entropy Passphrases ---
	
	A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password. A passphrase consists of a series of words (lowercase letters) separated by spaces.
	
	To ensure security, a valid passphrase must contain no duplicate words.
	
	For example:
	
	aa bb cc dd ee is valid.
	aa bb cc dd aa is not valid - the word aa appears more than once.
	aa bb cc dd aaa is valid - aa and aaa count as different words.
	The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
	
	Your puzzle answer was 477.
	
	--- Part Two ---
	
	For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.
	
	For example:
	
	abcde fghij is a valid passphrase.
	abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
	a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
	iiii oiii ooii oooi oooo is valid.
	oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
	Under this new system policy, how many passphrases are valid?
	
	Your puzzle answer was 167.
	
	Both parts of this puzzle are complete! They provide two gold stars: **
 */

import java.util.*;
import java.io.*;
public class Day_4 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(new File("day4input.txt"));
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		while (console.hasNextLine()) {
			String[] seriesArray = console.nextLine().split(" ");
			ArrayList<String> seriesList = new ArrayList<>(Arrays.asList(seriesArray));
			Set<String> set = new HashSet<String>(seriesList);
			boolean isAnagram = false;
			if(!(set.size() < seriesList.size())){
				for (int a = 0; a < seriesList.size(); a++) {
					if (checkAnagramInAList(seriesList.get(a), seriesList)) {
						isAnagram = true;
						break;
					}
				}	
				if (!isAnagram) { array.add(seriesList); }
			}
		}
		System.out.println(array.size());
	}
	
	public static boolean isAnagram(String firstWord, String secondWord) {
	     char[] word1 = firstWord.toLowerCase().replaceAll("[\\s]", "").toCharArray();
	     char[] word2 = secondWord.toLowerCase().replaceAll("[\\s]", "").toCharArray();
	     Arrays.sort(word1); Arrays.sort(word2);
	     return Arrays.equals(word1, word2);
	}
	
	public static boolean checkAnagramInAList(String word, ArrayList<String> list) {
		for (int a = 0; a < list.size(); a++) {
			if (list.get(a) != word && isAnagram(word,list.get(a))) { return true; }
		}
		return false;
	}
}
