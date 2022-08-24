/*
Anderson Lu
Cse 143 AN with May Wang
Homework 1, LetterInventory
LetterInventory stores and keeps track of the amount
of alphabetical letters given in a string of data.
*/
public class LetterInventory {
   
   // Creates the inventory and size of inventory,
   // along with a class constant for the alphabet length.
   private int[] inv;
   private int size;
   public static final int ALPHABET = 26;
   
   /*
   Takes a String "data" as a parameter and creates an
   inventory for the letters. Makes all the
   characters lowercase and converts each
   letter to the ascii value. Adds amount of letters
   from ascii value to the list and size.
   */
   public LetterInventory (String data) {
      //Sets the inventory to length of the alphabet.
      inv = new int[ALPHABET];
      data = data.toLowerCase();
      //Goes through the length of the data given.
      for (int i = 0; i < data.length(); i++) {
         char c = data.charAt(i);
         //Changes character to ascii value and checks for letters.
         int value = (int) c - 'a';
         if (value >= 0 && value < ALPHABET + 1) {
            //Increases the count of that letter and size.
            inv[value]++;
            size++;
         }
      }
   }
   
   // Returns the sum of all the counts in the inventory.
   public int size() {
      return size;
   }
   
   // Returns whether the inventory is empty or not.
   public boolean isEmpty() {
      return size < 1;
   }
   
   /*
   Takes a Character "letter" as a parameter and
   returns the amount of a certain letter in the
   inventory. Throws an IllegalArgumentException
   if a nonalphabetical character is assigned, or
   the amount of that certain letter is a negative value.
   
   Pre: letter is an alphabetical letter and count >= 0.
   Throws an IllegalArgumentException if preconditions not met.
   
   Post: Returns the count of that letter.
   */
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      //Converts character to ascii value.
      int count = (int) letter - 'a';
      //Checks if character is a letter or if the count is negative.
      if (!Character.isLetter(letter) || inv[count] < 0) {
         throw new IllegalArgumentException();
      }
      return inv[count];
   }
   
   // Returns the inventory written out in alphabetical order and lower cased.
   public String toString() {
      String write = "";
      //Loops through the inventory alphabetically.
      for (int i = 0; i < inv.length; i++) {
         //Loops through each letter's counts.
         for(int j = 0; j < inv[i]; j++){
            write += (char)(i + 'a');
         }
      }
      return '[' + write + ']';
   }
   
   /*
   Takes a Character "letter" and an Integer "value" as
   parameters. Assigns a value for the counter of any given letter.
   Throws an IllegalArgumentException if given number is negative,
   or if given a nonalphabetical letter.
   
   Pre: letter is an alphabetical letter or value >= 0.
   Throws IllegalArgumentException if preconditions are not met.
   
   Post: Changes the counter for the letter given.
   */
   public void set(char letter, int value) {
      if (value < 0 || !Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
      letter = Character.toLowerCase(letter);
      //Converts letter to ascii value.
      int counter = (int) letter - 'a';
      //Changes the size from the given value.
      if (value < inv[counter]) {
         size = size - (inv[counter] - value);
      } else if (value > inv[counter]) {
         size = size + (value - inv[counter]);
      }
      inv[counter] = value;
   }
   
   // Takes a LetterInventory object other as a parameter
   // and returns the other (new) inventory added to this (old) inventory.
   public LetterInventory add(LetterInventory other) {
      return operation(other, "+");
   }
   
   /*
   Takes a LetterInventory object other as a parameter
   and returns the result of subtracting the other (new)
   inventory from this (old) inventory. Returns null if 
   the operation requested results in a negative value of 
   letters.
   
   Pre: other inventory - this inventory >= 0
   Returns null if preconditions are not met.
   
   Post: Subtracts the other inventory from this inventory and
   returns the result.
   */
   public LetterInventory subtract(LetterInventory other) {
      return operation(other, "-");
   }
   
   /*
   Takes a LetterInventory object other and a String sign as parameters
   and determines whether the two inventories should be added together
   or subtracted from each other. If subtracted, subtracting the
   other (new) inventory from this (old) inventory. Returns the
   result of either operation that is requested.
   
   Pre: other inventory - this inventory >= 0
   Returns null if preconditions are not met.
   
   Post: Subtracts the other inventory from this inventory and
   returns the result.
   
   */
   private LetterInventory operation(LetterInventory other, String sign) {
      //Creates a new inventory for the result of the opertation.
      LetterInventory invOpt = new LetterInventory("");
      //Cycles through the letters of the alphabet.
      for (int i = 0; i < ALPHABET; i++) {
         //Checks if the operation is addition or subtraction.
         if (sign.equals("+")) {
            //Adds the inventories.
            invOpt.inv[i] = this.inv[i] + other.inv[i];
            invOpt.size = this.size + other.size;
         } else {
            //Subtracts the inventories other from this.
            invOpt.inv[i] = this.inv[i] - other.inv[i];
            invOpt.size = this.size - other.size;
            //Returns null if the count becomes negative.
            if (invOpt.inv[i] < 0) {
               return null;
            }
         }
      }
      //returns the result of the operation.
      return invOpt;
   }
   
}