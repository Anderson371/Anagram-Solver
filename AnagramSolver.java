/*
Anderson Lu
Cse 143 AN with May Wang
Homework 6, AnagramSolver
This program takes a file of dictionary words and finds
all the combinations of words that contain the  same letters
as the given word.
*/
import java.util.*;

public class AnagramSolver {
   //Creates the dictionary and map for the anagrams.
   private Map<String, LetterInventory> anagramInv;
   private List<String> dictionary;
   
   /*
   Takes in a List<String> "list" as a parameter and
   creates different sets of anagrams from the dictionary
   list.
   */
   public AnagramSolver(List<String> list) {
      anagramInv = new HashMap<>();
      dictionary = list;
      //Creates a dictionary and inventory of the words in list.
      for (String word : list) {
         LetterInventory inv = new LetterInventory(word);
         anagramInv.put(word, inv);
      }
   }
   
   /*
   Takes in String "s" and Integer "max" as parameters. Finds the
   combination of words from the letters of the requested string.
   Using recursive backtracking, prints all the possible combinations
   of the words to create an anagram from the requested amount of words.
   
   Pre: max >= 0
   Throws an IllegalArgument Exception if precondition is not met.
   
   Post: Prints all the possible anagram combinations from the given
   phrase.
   */
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      //Creates a LetterInventory to keep track of the resulting letters.
      LetterInventory current = new LetterInventory(s);
      //Creates a list to keep track of the current anagrams.
      List<String> curAnagram = new ArrayList<>();
      //Checks if word in the dictionary could be made into an anagram.
      //Adds the word if the word is able to make an anagram.
      for (String word : dictionary) {
         if (current.subtract(anagramInv.get(word)) != null) {
            curAnagram.add(word);
         }
      }
      //Creates a Stack to keep track of the final anagrams.
      Stack<String> printAnagram = new Stack<>();
      printHelper(max, current, curAnagram, printAnagram);
   }
   
   /*
   Takes in Integer "max", LetterInventory "current",
   List<String> "curAnagram", and Stack<String> printAnagram as parameters.
   Checks if the letters in the requested phrase is empty and returns the
   anagram made from the letters in the phrase. Prints all the possible
   combinations of anagrams from the requested phrase and amount of words.
   */
   private void printHelper(int max, LetterInventory current,
   List<String> curAnagram, Stack<String> printAnagram) {
      //Checks base case for empty LetterInventory.
      if (current.isEmpty()) {
         System.out.println(printAnagram);
      }
      if (max == 0 || max != printAnagram.size()) {
         //Subtracts each word from the LetterInventory.
         for (String word : curAnagram) {
            LetterInventory result = current.subtract(anagramInv.get(word));
            //Adds anagram if the resulting LetterInventory is not null.
            //Recurs to find the anagrams for the remaining letters.
            if (result != null) {
               printAnagram.push(word);
               printHelper(max, result, curAnagram, printAnagram);
               printAnagram.pop();
            }
         }
      }
   }
}