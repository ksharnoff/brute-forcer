import java.util.*;

class Brute{

  Print p = new Print();
  // array of characters contains all letters, upper and lower, all numbers, and some symbols (total length of 67)
  String[] characters = {"e", "a", "o", "r", "i", "n", "s", "l", "1", "t", "2", "m", "d", "c", "y", "0", "b", "h", "g", "u", "p", "3", "k", "9", "4", "5", "7", "6", "8", "f", "j", "w", "v", "z", "x", "S", "E", "A", "R", "M", "T", "B", "N", "O", "L", "I", "C", "D", "P", "H", "J", "q", "K", "G", "Y", "U", "F", "W", "V", "X", "@", "Z", ".", ",", "Q", "&", "?"};
  String actualPassword;
  int passLength;
  int characLength = characters.length;
  boolean success = false;
  String[] password;
  int attemps = 0;
  int pos = 0;
  long start;  // same variable from main, the start time before the cracking starts


  public Brute(String actualPass, long startingTime){
    actualPassword = actualPass;
    passLength = actualPassword.length();
    // have the password, makes the password into an array
    password = actualPassword.split("");
    start = startingTime;
  }

  public void brute(){
    int[] guess = new int[passLength];

    for (int i = 0; i < passLength; i++){
      guess[i] = 0;
    }

    while (!success){
      if (guess[pos] > (characLength-1)){
        while(guess[pos] > (characLength-1)){
          guess[pos] = 0;
          if (pos != (passLength-1)){
            pos++;
            guess[pos]++;
          }  
        }
        pos = 0;
      }

      success = check(guess);
      guess[pos]++;
    }
  }
  
  
  private boolean check(int[] guess){
    for (int i = 0; i < passLength; i++){
      if (!(characters[guess[i]].equals(password[i]))){
        attemps++;
        return false;
      }
    }

    p.print("Your password is: ");
    for (int i: guess){
      System.out.print(characters[i]);
    }
    return true;
  }
  
  
  public String weirdCharac(){
    boolean weirdCharacters = false;
    String toBeReturned = "";
    
    ArrayList<String> characterList = new ArrayList<String>(Arrays.asList(characters));
    for (String i: password){
      if(!(characterList.contains(i))){
        weirdCharacters = true;
        toBeReturned += i;
        toBeReturned += ", ";
      }
    }
    // if there are unfamiliar characters then it will return a string that lists them
    if (weirdCharacters){
      return toBeReturned;
    }
    // if there aren't any unfamiliar characters then it will return null
    return null;
  }
}