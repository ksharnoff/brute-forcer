# brute-forcer

### What is this?
This is a project made by me for my high school's math “Maker Fair” that took place in February 2022. This project is coded completely in Java, as I made while I took AP CS A. I coded it on [Replit for easy sharing](https://replit.com/@Sharnoff/Maker-Faire-Password-Cracker) at the fair. 

### What does it do?
![Hello-spaghetti](https://github.com/ksharnoff/brute-forcer/blob/main/hello-spaghetti_example.png)

This is a brute force password cracker. When run, it takes in a string as input and then tries out every combination of letter, number, and (some) symbols until it figures out the password. Once it has finished, it prints the password, the number of attempts it took, and the amount of time (in milliseconds or seconds). 

The program asks for five or less characters as input. This is because on Replit when there were five question marks, the character that would take the most time, it would take just over a minute. When it was six question marks it would take several minutes or sometimes give up. Because of this, I decided to limit the input to five characters. 

However, I wanted to also include the number of attempts and the amount of time of input more than five characters. In Hypothetical.java, it converts input of more than five characters to base 67 (the total number of characters that the program compares passwords to). This number in base 67 is the number of attempts it would take following the method used here. 

Unlike the amount of hypothetical attempts, the amount of time is not found quantitatively. Part of this is due to discrepancies for timing of Replit, for example in the example above, the time it took with the input “hello” varied from 1.4 seconds to 4.9 seconds. More about the method chosen is written in [a comment above the function hypoTime()](https://github.com/ksharnoff/brute-forcer/blob/main/Hypothetical.java#L63). Look at the section titled *How can this be improved for the future?* for more on the hypothetical time calculations. 

Something to note, is because of the order of the characters array, any input that has more than five characters, but all the characters after the fifth character is the letter “e” runs normally, not hypothetically. 

### How does it work?

***to be finished***




### How can this be improved for the future? 

These ideas are also written at the top of Main.java in a comment.
 - Make the time calculation more accurate
 	-  Have it take in data of ratios (between number of attempts and time) from previous runs to then have a more accurate calculation
 - Make the brute forcing blind to the length of code
	- Right now, the array in Brute.java is created with the knowledge of the input length. This is not accurate to how brute force password cracking would be done in the wild. This could be fixed by running the brute() method for each possible length of the password (1-5), recreating an array each run through. 
 - Have the unknown character check only print each character once
 	- Right now it prints all instances, not each character (right now: "^, &, ^, ^" possible future: "^, &"). This can be fixed by adding the unknown characters to an arraylist that is printed out at the end. This would make the printing of the unknown characters more clear as it is printing elements of any arraylist rather than substringing a string. 

### Sources
Here are my sources for the ordering of my characters array. 
- For the letters, numbers, and some symbols: [“Character Occurrence In Passwords,” on csgillespie.wordpress.com, June 16 2011](https://csgillespie.wordpress.com/2011/06/16/character-occurrence-in-passwords/)
	- [Specific png of the chart](https://csgillespie.files.wordpress.com/2011/06/figure2.png)
- For more data on symbols: [“Most Frequently-Used Special Characters in 10 Million Passwords [OC]” by u/minimaxir on r/dataisbeautiful, February 10 2015](https://www.reddit.com/r/dataisbeautiful/comments/2vfgvh/most_frequentlyused_special_characters_in_10/)
	- [Specific png of the chart, on imgur](https://i.imgur.com/aoIa6UX.png)

Here are some sources for the information that I learned about passcodes in making this project.
- Data on patterns in passwords, I found this useful while looking at dictionary style attacks: [“Unmasked: What 10 million passwords reveal about the people who choose them,” on WP Engine, updated November 28 2021](https://wpengine.com/resources/passwords-unmasked-infographic/) 
- Information about forced changing passwords, I found this useful while looking at dictionary style attacks: [“Time for Password Expiration to Die,” by Lance Spitzner on Sans.org, June 27 2019](https://www.sans.org/blog/time-for-password-expiration-to-die/)
- Information about how to make a password: [“How to Choose a Password - Computerphile,” by Computerphile on YouTube.com, July 20 2016](https://youtu.be/3NjQ9b3pgIg)

