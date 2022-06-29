/*
Future changes that could be made:
  - make the time calculation more accurate
    (have it take in data from the previous runs + blank run, and then average the ratio)
  - make the brute forcing blind to the length of code
  - have the unknown character check only print each character once
    (right now it prints all instances, not each character (ex: "^, &, ^, ^" vs: "^, &")
*/

import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException{
    while (true){
      Print p = new Print();
      Scanner in = new Scanner(System.in);
      p.skip();

      p.println("Write a five character (or less) password and this program will guess every combination until it figures it out!");
      p.println("Write here and then press return: ");
      String inputPass = in.next();

      long start = System.currentTimeMillis(); // timer starts!

      astericks(inputPass);  // rewerites over the password as astericks
      p.blank();

      Brute b = new Brute(inputPass, start);
      String weirdCharacString = b.weirdCharac(); // checks for unknown charaters

      if (weirdCharacString == null){ // if there are no uknown characters

        Hypothetical h = new Hypothetical(inputPass, start);
        Boolean moreThanSix = h.sixCharac();

        if (!moreThanSix){ // if the password has a length less than 6
          bruteCall(start, b, p);
        }

        else{ // if the password has a length of more than five


          if (h.hypoAttempts()){ // if after being trimmed (remove "e" from end) the password is still too long
            long hypoAttempts = h.hypoAttemps;
            long hypoTime = h.hypoTime(); // this is in seconds
            double hypoTimeReduced; 
            String timeAndUnit = "";

            if (hypoTime >= (86400*365*2)){ //converts to years if more than two years (86400 is how many seconds in a day)
              hypoTimeReduced = hypoTime / 86400*365; 
              timeAndUnit += (int)hypoTimeReduced;
              timeAndUnit += " years";
            }
            else if (hypoTime >= 86400*5){ //convert to days if it's more than five days
              hypoTimeReduced = hypoTime / 86400; 
              timeAndUnit += (int)hypoTimeReduced;
              timeAndUnit += " days";
            }
            else if (hypoTime >= 3600){ //converts to hours
              hypoTimeReduced = hypoTime / 3600;
              timeAndUnit += (int)hypoTimeReduced;
              timeAndUnit += " hours";
            }
            else{ // converts to minutes
              hypoTimeReduced = hypoTime / 60;
              timeAndUnit += (int)hypoTimeReduced;
              timeAndUnit += " minutes";
            }

            if (hypoAttempts == (long)(Math.pow(2,63))-1){ // this is for if it maxes out the long value
              p.println("Wow your password was really long! \nWith this server's power it would have taken more than \n\t9223372036854775807 attempts \nand more than \n\t" + timeAndUnit + "\nto guess it!");
            }
            else{
              p.println("Uh oh that's a bit too long for the \nserver to handle. \nIf it was to continue until it figured out the answer it would take exactly \n\t" + hypoAttempts + " atttemps \n and around \n\t" + timeAndUnit + "!");
            }
            p.println("Please try a password with five or less characters!");
          }
          else{ // if after being trimmed it is < 6, runs brute normally
            bruteCall(start, b, p);
          }
        }
      }
      else{ // if there is an unknown character
        p.println("Whoops!");
        // length of weirdCharacString will be at least three: "i, "
        if (weirdCharacString.length() < 4){ // if there is only one unknown character
          p.println("I don't know this character:"); 
        }
        else{ // if several instances of unknown characters
          p.println("I don't know these characters:");
        }
        weirdCharacString = weirdCharacString.substring(0,weirdCharacString.length()-2); // excludes the last thing -- to get rid of the ", " at the end
        p.println("\t" + weirdCharacString);

        p.println("Press run again and try not to use any accents or odd symbols!");
      }
      p.skip();
      p.println("---------");
    }
  } 


  public static void astericks(String x){
    System.out.print("\033[F\r");
    for (int i = 0; i < x.length(); i++){
      System.out.print("*");
    }
  }


  // the text of brute is written here so it can be called twice
  // I have it take in Brute b because it has to keep the same input data
  // Print p could be made static in this class in the future to elimintate parameters 
  public static void bruteCall(long StartTime, Brute b, Print p){
    b.brute(); // at the end of brute() it prints out the password

    p.skip();
    p.println("That took: \n\t" + b.attemps + " attemps");

    long end = System.currentTimeMillis();
    long totTime = end-StartTime;

    if (totTime > 1000){
      double totTimeDou = ((double)totTime)/1000;
      System.out.print("and \n\t" + totTimeDou + " seconds");
    }
    else{
      System.out.print("and \n\t" + totTime + " miliseconds");
    }
    p.skip();
  }
}