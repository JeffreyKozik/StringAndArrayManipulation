/** Jeffrey Kozik, determines and performs various String related methods */

public class HW2{
  
  /** takes a string, returns true if the string is in alphabetical order, false if it is not */
  public static boolean isAlphabeticalOrder(String string){
    /** Determines if any letter is of a lower value than the letter to its left, if so returns false. 
      * int lastLetter stores the int value of the last letter in the string
      * lastLetter starts at the int value of 'a' because all letters aren't less than 'a'
      * Each iteration the loop goes through 2 if statement containing 1 if statements each
      * The first big if statement is invoked if the current character is a lowercase letter.
      * If so, the if statement within is invoked if the current character is greater than lastLetter and false is returned
      * Regardless, lastLetter is now set to the current character
      * The second big if statement is invoked if the current character is an uppercase letter.
      * If so, the if statement within is invoked if the lowercased current character is greater than lastLetter and false is returned
      * Regardless, lastLetter is now set to the lowercased current character
      * Finally, after the for statement is through, if no letters have been determined to be less than the letter to their left, true is returned
      */
    for(int i = 0, lastLetter = (int)'a'; i < string.length(); i++){
        if((string.charAt(i) >= 'a') && (string.charAt(i) <= 'z')){
          if(string.charAt(i) < lastLetter){
            return false;
          }
          lastLetter = (int)string.charAt(i);
        }
        if((string.charAt(i) >= 'A') && (string.charAt(i) <= 'Z')){
          if((string.charAt(i) - 'A' + 'a') < lastLetter){
            return false;
          }
          lastLetter = (int)(string.charAt(i) - 'A' + 'a');
        }
    }
    return true;
  }
  
  /** takes a string, int, and char and returns the string without the first int instances of the char */
  public static String removeNChar(String string, int num, char symbol){
    /* stores the revised string to return */
    StringBuilder builder = new StringBuilder();
    /** stores how many chars have been removed, so that once the amount requested is reached, the rest aren't removed */
    int tracker = 0;
    /** Determines if the current char is the one that was requested to be removed and if the amount of requested chars have been removed; adds characters to builder accordingly
      * The first if statement checks if the current char is the one supposed to be removed, or even if it is, if the amount of the requested char has already been removed
      * If either of these two conditions are met, the current char is added to builder
      * The else if statement increments tracker by one if the current char is what is requested to be removed and the limit hasn't been reached yet
      */
    for(int i = 0; (i < string.length()); i++){
      if((string.charAt(i) != symbol) || (tracker >= num)){
        builder.append(string.charAt(i));
      }else if(tracker < num){
        tracker++;
      }
    }
    return builder.toString();
  }
  
  /** takes two Strings: string and removeString; returns string with every instance of removeString removed from it in one pass*/
  public static String removeString(String string, String removeString){
    /** StringBuilder to store the final String that will be returned */
    StringBuilder builder = new StringBuilder();
    /** StringBuilder to store the current state of the inputted string */
    StringBuilder removeBuilder = new StringBuilder();
    /** Determines upon each iteration if a contiguous block of characters the same length as removeString is equal to removeString. If not, builder is adjusted accordingly
      * The nested for loop within is executed, forming removeBuilder which stores a StringBuilder the same length as removeBuilder, or if its the last letters, a string of those
      * If removeBuilder is shorter than removeString, those last letters can't be removeString, so we append them to builder, and we know these are the end so we return builder
      * If removeBuilder doesn't equal removeString, we know the first character of removeBuilder can't be part of removeString so we add it to builder
      * If removeBuilder is equal to removeString we know none of these characters should be added to builder, so we jump ahead in the for loop to past these characters
      * We reset removeBuilder at the end of the for loop so that we can use it for the next iteration
      */
    for(int i = 0; i < string.length(); i++){
      /** Forms removeBuilder which stores a StringBuilder the same length as removeBuilder, or if its the last letters, a string of those
        * Each iteration the next character in string is added to removeBuilder until either string runs out of characters, or removeBuilder is the same length as removeString
        */
      for(int j = i; (j < (removeString.length() + i)) && (j < string.length()); j++){
        removeBuilder.append(string.charAt(j));
      }
      if(removeBuilder.toString().length() < (removeString.length())){
        builder.append(removeBuilder);;
        return builder.toString();
      }
      if(!(removeBuilder.toString().equals(removeString))){
        builder.append(string.charAt(i));
      }else{
        i+=(removeString.length() - 1);
      }
      removeBuilder = new StringBuilder();
    }
    return builder.toString();
  }
  
  /* takes a char and a string and moves all instances of that char to the right within the string */
  public static String moveAllXsRight(char symbol, String string){
    /* stores the revised string to return */
    StringBuilder builder = new StringBuilder();
    /* stores a temporary string of as many of the desired char (symbol) that there are in a row */
    StringBuilder symbolBuilder = new StringBuilder();
    /* Upon each iteration adds the next set of chars to the revised string - either one non symbol char or a character followed by a series of symbol characters
     * The first if statement determines if the current char is symbol, if not it immediately appends it to the revised string
     * The else statement goes into a while statement which forms symbolBuilder, and then appends a char followed by symbolBuilder, adjusts i, and resets symbolBuilder
     * The while loop starts at the current position and keeps going until the end of contiguous symbols. It adds all of these symbols to symbolBuilder
     * The if statement determines if the char following the last symbol in symbolBuilder exists and double checks it isn't a symbol; if conditions met appends this char
     * Then symbolBuilder is appended
     * i is set to the position of the last symbol in symbolBuilder so that symbols aren't double counted
     * symbolBuilder is reset
     */ 
    for(int i = 0; i < string.length(); i++){
      if(string.charAt(i) != symbol){
        builder.append(string.charAt(i));
      }else{
        int j = i;
        while((j < string.length()) && (string.charAt(j) == symbol)){
            symbolBuilder.append(string.charAt(j));
            j++;
        }         
        if(j < string.length() && (string.charAt(j) != symbol)){
          builder.append(string.charAt(j));
        }
        builder.append(symbolBuilder);
        i+=(j-i);
        symbolBuilder = new StringBuilder();
      }
    }
    return builder.toString();
  }
  
  /** takes a char symbol and a two dimensional char array and adjust the char array with all of the symbols shifted down as far as they can go, doesn't return anything */
  public static void moveAllXsdown(char symbol, char[][] symbolArray){
    /** Upon each iteration goes through a row starting with the second to last one and ending after the first row has been cycled through - helps adjust the array
      * Each iteration the loop goes into the nested for loop within
      */
    for(int i = symbolArray.length - 2; i >= 0; i--){
      /** Upon each iteration goes through a column starting with the last one and ending after the first column has been cycled through - adjusts the array as it goes
        * Each iteration it checks an if statement
        * The if statement determines if 1. The current position is equal to the desired char (symbol) and 2 if the following position it could be shifted to even exists
        * If both of these are true, the if statement executes, switching the location of the two chars so that symbol moves down 1 and char moves up
        */
      for(int j = symbolArray[i].length - 1; j >= 0; j--){
        if((symbolArray[i][j] == symbol) && (j < symbolArray[i+1].length)){
          symbolArray[i][j] = symbolArray[i+1][j];
          symbolArray[i+1][j] = symbol;
        }
      }
    }
  }
  
  /** takes a char symbol and a two dimension char array and adjusts it so that the first instance of symbol is moved down, left as far as it can go, doesn't return anything */
  public static void moveXDownLeft(char symbol, char[][] symbolArray){
    /** stores a former value of i for reference*/
    int tempi = 0;
    /** stores a former value of j for reference */
    int tempj = 0;
    /** Upon each iteration goes through a row starting with the first one and ending with the last one; helps determine the row the first instance of symbol is in 
      * Each iteration the loop goes into the nested for loop within
      */
    for(int i = 0; i < symbolArray.length; i++){
      /** Upon each iteration goes through a column starting with the first one and ending with the last one; helps determine the column first instance of symbol is in
        * Each iteration the loop goes into a nested for loop within
        */
      for(int j = 0; j < symbolArray[i].length; j++){
        if(symbolArray[i][j] == symbol){
          tempi = i;
          tempj = j;
          /** Now that the location of the first instance of symbol has been determined, this loop actually adjusts the array to move that instance as far down, left as it can
            * The loop runs until the symbol has been moved as far down, left it can go. Each time the loop runs it goes into an if statement
            * The if statement determines if the symbol is able to be shifted down and left
            * If it is, the location of the symbol is switched with the location of the char that is closest to the down and left
            * Tempi and tempj are set to i and j, because they're storing the current location of symbol
            */
          for(; i < (symbolArray.length - 1); i++, j--){
            if(tempj < symbolArray[i].length){
              symbolArray[tempi][tempj] = symbolArray[i][j];
              symbolArray[i][j] = symbol;
              tempi = i;
              tempj = j;
            }
          }
        }
      }
    }
  }
}
