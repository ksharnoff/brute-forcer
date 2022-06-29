import java.util.*;

class Print{

  /*
  can print and println all of the following:
    string, int, float, long, double, boolean, int[], string[], ArrayList<String>
      for printing: int[], string[], ArrayList<String> it prints (x + " ");
  The String[] function named printQuotation is not currently used by the program
    It was used to print out the characters list. (to turn abc to "a", "b", "c") 
  */

  public void print(String x){
    System.out.print(x);
  }
  public void println(String x){
    System.out.println(x);
  }


  public void print(int x){
    System.out.print(x);
  }
  public void println(int x){
    System.out.println(x);
  }


  public void print(boolean x){
    System.out.print(x);
  }
  public void println(boolean x){
    System.out.println(x);
  }


  public void print(float x){
    System.out.print(x);
  }
  public void println(float x){
    System.out.println(x);
  }


  public void print(long x){
    System.out.print(x);
  }
  public void println(long x){
    System.out.println(x);
  }


  public void print(double x){
    System.out.print(x);
  }
  public void println(double x){
    System.out.println(x);
  }


  public void print(int[] x){
    for (int i: x){
      System.out.print(i + " ");
    }
  }
  public void println(int[] x){
      for (int i: x){
      System.out.println(i);
    }
  }


  public void print(String[] x){
    for (String i: x){
      System.out.print(i + " ");
    }
  }
  public void println(String[] x){
    for (String i: x){
      System.out.println(i);
    }
  }
  public void printQuotation(String[] x){
    for (String i: x){
      System.out.print("\""+i+"\""+", ");
    }
  }


   public void print(ArrayList<String> x){
    for (String i: x){
      System.out.print(i + " ");
    }
  }
   public void println(ArrayList<String> x){
      for (String i: x){
      System.out.println(i);
    }
  }


  public void skip(){
    println(" ");
  }

  public void blank(){
    println(" ");
    println(" ");
  }
}