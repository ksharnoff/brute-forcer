import java.util.*;

class Hypothetical extends Brute{

  Print p = new Print();
  ArrayList<String> passwordList = new ArrayList<String>();
  long hypoAttemps = 0; 
  long hypoSeconds; // has to be a long as the the longest long for attemps would be a long for time as well 
  int numCharac; // this equals 67 just having it check it in case the character list changs
  int fiveQMarkAttemptsNum = 1350125106; // this is the longest that i've been able to do, therefore the minimum for it to calculate the hypothetical

  public Hypothetical(String actualPass, long startingTime){
    super(actualPass, startingTime);
    numCharac = characters.length;
    for (String i: password){
      passwordList.add(i);
    }
  }

  // five characters is the hard limit (on replit) of how many to do -- as figured out by testing
  public boolean sixCharac(){
    if (passLength > 5){
      return true;
    }
    return false;
  }

  public boolean hypoAttempts(){
    // first you trim it to get rid of the e at the end -- letter e would count as a zero attempt guess
    while (passwordList.get(passwordList.size()-1).equals("e")&&(passwordList.size()>5)){
      passwordList.remove(passwordList.size()-1);
    }
 
    if (passwordList.size() < 6){
      return false; // if after trimming it does not reach limit of length, then it exits and runs brute in main
    }

    ArrayList<Integer> indexes = new ArrayList<Integer>();
    for (int i = 0; i < passwordList.size(); i++){
      for (int j = 0; j < numCharac; j++){
        if (passwordList.get(i).equals(characters[j])){
          indexes.add(j);
        }
      }
    } 

    // next thing is to rewrite the number in base 67:
    // add one as the indexes are staring on 0 not 1
    // it's written with several valariables to make more sense, that each step is correct
    for (int i = 0; i < indexes.size(); i++){
      long sixtySevenPower = (long)(Math.pow(67, i));
      long indexed = indexes.get(i);
      long toBeAdded = sixtySevenPower*indexed;
 
      hypoAttemps += toBeAdded;
    }
    hypoAttemps --;
    hypoAttempsOverflow();
    return true;
  }

  /*
  The following number: 0.000000056 which is used as a ratio between attemps and time is incorrect in several ways:
    1. Cacluated via times from replit - slow as it's being
    2. It is calculated just by a ratio, instead of:
      - adding the base amount time necessary (if guesseed correctly first try (ex: "eee"))
      - then adding the ratio between attemps and time
  I chose this number because it worked as a subsitute for the time
  I found this number by vibe checking a bunch of ratios under the 6 letter limit
  */
  public long hypoTime(){
    hypoSeconds = (long)(hypoAttemps*0.000000056); 
    return hypoSeconds;
  }

 
  public boolean hypoAttempsOverflow(){
    // this is called to check if the number has overflowed so far, if so it sets it to the max long
    if (hypoAttemps < fiveQMarkAttemptsNum){ 
      hypoAttemps = (((long)Math.pow(2,63))-1);
      return true;
    }
    return false;
  }
}
