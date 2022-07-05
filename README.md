# brute-forcer


## Why and how was this made?
This is a project made by me for my high school's math “Maker Fair” that took place in February 2022. This project is coded completely in Java, as I made while I took AP CS A. I coded it on [Replit](https://replit.com/@Sharnoff/Maker-Faire-Password-Cracker) for easy sharing at the fair. 

## What does it do?
![“hello”, “spaghetti” examples run to show standard output](https://github.com/ksharnoff/brute-forcer/blob/main/Examples/hello-spaghetti_example.png)

This is a password cracker using the brute force attack method. When run, it takes in a string as input and then tries out every combination of letter, number, and (some) symbols until it figures out the password. Once it has finished, it prints the password, the number of attempts it took, and the amount of time (in milliseconds or seconds). 

The program asks for five or less characters as input. This is because on Replit when there were five question marks, the character that would take the most time, it would take just over a minute. When it was six question marks it would take several minutes or sometimes give up. Because of this, I decided to limit the input to five characters. 

However, I wanted to know the hypothetical amount of time in order to evaluate passwords on this system. When an input is more than five characters, the exact number of attempts and a guess at the amount of time that would be taken is calculated and then printed out. 

## How does it work?

#### Brute.java

The `Brute` class takes in the input (that is less than 6 characters) and runs through every combination until it reaches the real one.

This is done with the `characters` string array which contains all the characters (67 in total length, all upper/lowercase letters, all numbers, and some symbols) and the `brute()` function. If the input is five characters, the `guess` array is first set up as [0, 0, 0, 0, 0]. Then, it cycles through going [1, 0, 0, 0, 0] → [2, 0, 0, 0, 0] … [67, 0, 0, 0, 0]. After it has reached the limit of the characters, it moves onto the next column. [0, 1, 0, 0, 0] → [1, 1, 0, 0, 0] … [67, 1, 0, 0, 0] → [0, 2, 0, 0, 0]. Using the indexes of the `characters` array to translate, each time the numbers of the `guess` array are compared in `check(int[] guess)` to the actual password. If the password matches, then it is marked as done and it prints out the translated version of the `guess` array.

`Brute` also has the function `weirdCharac()` which checks if the input has any character not in the `characters` array, this is talked more about in the section titled Main.java.


#### Hypothetical.java

The `Hypothetical` class figures out the hypothetical amount of attempts and time that the program would take to brute force the password if it could run infinitely.

To find the hypothetical attempts, `hypoAttemps`, the input of more than five characters is converted to base 67 (the total number of characters that the program compares passwords to). This number in base 67 is the exact number of attempts it would take following the method used here. 

Unlike the amount of hypothetical attempts, the amount of time, `hypoSeconds`, is not found quantitatively. Part of this is due to discrepancies for timing of Replit, for example in the example above, the time it took with the input “hello” varied from 1.4 seconds to 4.9 seconds. More about the method chosen is written in [a comment above the function `hypoTime()`](https://github.com/ksharnoff/brute-forcer/blob/main/Hypothetical.java#L63). Look at the section titled *How can this be improved for the future?* for more on the hypothetical time calculations. 

#### Main.java
![​”!#$%^”, “cheese” examples run to show special output](https://github.com/ksharnoff/brute-forcer/blob/main/Examples/!%23%24%25%5E-cheese_examples.png)

The `Main` class is most simply an if-else tree. Each possible option in the tree is talked about in the next section, with each exception to a normal run of the program represented by an input in the image right above this. 


> !#$%^

After the input is taken in, it is evaluated for any characters that are not in the `characters` array from `Brute`. If there are any characters not there, then it prints what they are and asks for new input. 

Next, the length is checked. If it is less than six characters then `brute()` is run and it prints out the number of attempts and the time.


> cheese

Next, any “e”s at the end of the word is trimmed, and if doing so brings the number of characters below six, `brute()` is run as normal. The reason for timing “e”s is that the character “e” is counted as zero attempts (because of its position as index 0 in the `characters` array in `Brute`), so it does not matter how long the input is. 

If the length, after the word is trimmed, is still greater than five, then the hypothetical attempts and time is calculated as normal. The time is calculated in seconds, so after that number is returned to `Main` it is converted to minutes, hours, days, or years, depending on the length. It is then printed out as normal, as seen in the previous section. If the number of attempts reached the limit of a long, then a different message is printed, saying that it would take more than, *not exactly*, 2<sup>64​​</sup> attempts. 

## How can this be improved for the future? 
These ideas are also written at the top of `Main` in a comment.
 - Make the time calculation more accurate
 	-  Have it take in data of ratios (between number of attempts and time) from previous runs to then have a more accurate calculation
 - Make the brute forcing blind to the length of code
	- Right now, the `guess` array in `Brute` is created with the knowledge of the input length. This is not accurate to how brute force password cracking would be done in the wild. This could be fixed by running the `brute()` method for each possible length of the password (1-5), recreating an array each run through. 
 - Have the unknown character check only print each character once
 	- Right now it prints all instances, not each character (right now: "^, &, ^, ^" possible future: "^, &"). This can be fixed by adding the unknown characters to an arraylist that is printed out at the end. This would make the printing of the unknown characters more clear as it is printing elements of any arraylist rather than substringing a string. 

## Sources
Here are my sources for the ordering of my characters array. 
- For the letters, numbers, and some symbols: [“Character Occurrence In Passwords,” on csgillespie.wordpress.com, June 16 2011](https://csgillespie.wordpress.com/2011/06/16/character-occurrence-in-passwords/)
	- [Specific png of the chart](https://csgillespie.files.wordpress.com/2011/06/figure2.png)
- For more data on symbols: [“Most Frequently-Used Special Characters in 10 Million Passwords [OC]” by u/minimaxir on r/dataisbeautiful, February 10 2015](https://www.reddit.com/r/dataisbeautiful/comments/2vfgvh/most_frequentlyused_special_characters_in_10/)
	- [Specific png of the chart, on imgur](https://i.imgur.com/aoIa6UX.png)

Here are some sources for the information that I learned about passcodes in making this project.
- Data on patterns in passwords, I found this useful while looking at dictionary style attacks: [“Unmasked: What 10 million passwords reveal about the people who choose them,” on WP Engine, updated November 28 2021](https://wpengine.com/resources/passwords-unmasked-infographic/) 
- Information about forced changing passwords, I found this useful while looking at dictionary style attacks: [“Time for Password Expiration to Die,” by Lance Spitzner on Sans.org, June 27 2019](https://www.sans.org/blog/time-for-password-expiration-to-die/)
- Information about how to make a password: [“How to Choose a Password - Computerphile,” by Computerphile on YouTube.com, July 20 2016](https://youtu.be/3NjQ9b3pgIg)

