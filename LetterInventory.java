// Rohan Date
// CSE 143 AK with Jin Terada White
// Homework 1
// LetterInventory stores an inventory of letters
// in the alphabet.

public class LetterInventory {
   private int size; //number of elements in the current list
   private int[] elementData; //list of letters/symbols
   
   public static final int ALPHABET = 26;
   
 
   //post: constructs an inventory of letters
   //      from the parameter String data.
   public LetterInventory(String data) {
      elementData = new int[ALPHABET];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         if (Character.isLetter(data.charAt(i))) {
            size++;
            elementData[data.charAt(i) - 'a']++;
         }
      }
   }
   
   //post: this method returns the size of the inventory
   //      the size is the count of each letter
   public int size() {
      return size;
   }
   
   //post: if the inventory is empty (when size = 0), the method will
   //      return true. If the inventory isn't empty the method will return false.
   public boolean isEmpty() {
      return size == 0;
   }
   
   //pre: method throws an IllegalArgumentException if the char letter
   //     is not in the alphabet.
   //post: method reads through the inventory and checks how many of the
   //      given letter is in the inventory. The method returns
   //      a count of the given letter. The method takes in a parameter: char letter. 
   public int get(char letter) {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
      letter = Character.toLowerCase(letter);
      return elementData[letter - 'a'];
   }
   
   //post: converts the array into a string, and then returns the array
   //      in the sequence of letters. 
   public String toString() {
      if (size == 0) {
         return "[]";
      } else {
         String allLetters = "[";
         for (int i = 0; i < elementData.length; i++) {
            for (int j = 0; j < elementData[i]; j++) {
               allLetters += (char) ('a' + i);
            }
         }
         allLetters += "]";
         return allLetters.toLowerCase();
      }
   }
   
   //pre: char letter must be a letter and int value 
   //     must be a positive number, method throws an
   //     IllegalArgumentException otherwise.
   //post: takes in parameters char letter and int
   //      value. Increments size based on the 
   //      difference between value and count of
   //      the letter.
   public void set(char letter, int value) {
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException();
      }
      letter = Character.toLowerCase(letter);
      size += value - elementData[letter - 'a'];
      elementData[letter - 'a'] = value;
   }
   
   //post:this is a private helper method that reduces redundancy in the 
   //     add and subtract methods. It takes in a LetterInventory and a String.
   //     The string allows the sign to be changed from plus to minus.
   //     This method returns a Letter inventory that is the sum/difference using
   //     "this" LetterInventory and the other LetterInventory.when the difference 
   //     between this LetterInventory and the other LetterInventory is negative, 
   private LetterInventory checkOperation(LetterInventory other , String sign) {
      LetterInventory sum = new LetterInventory("");
      
      int totalCount = 0;
      
      for (int i = 0; i < ALPHABET; i++) {
         char result = (char) ('a' + i);
         
         if (sign.equals("+")) {
            totalCount = this.get(result) + other.get(result);
         } else {
            totalCount = this.get(result) - other.get(result);
            if (totalCount < 0) {
               return null;
            }
         }
         sum.set(result , totalCount);
      }
      return sum;
   }
   
   //post: takes in LetterInventory other then performs
   //      the subtraction operation, returning a Letter-
   ///     Inventory object that holds the difference 
   //      between the "this" and "other" LetterInventory
   //      objects.
   public LetterInventory subtract(LetterInventory other) {
      return checkOperation(other , "-");
   }
   
   //post: takes in LetterInventory other and then performs
   //      the addition operation, returning a Letter-
   //      Inventory object that holds the sum of the 
   //      "this" and "other" LetterInventory objects.
   public LetterInventory add(LetterInventory other) {
      return checkOperation(other , "+");
   }
}