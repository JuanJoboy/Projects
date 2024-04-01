import java.util.*;

import javafx.util.converter.CharacterStringConverter;

public class Jigsaw
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String name, stringResponse, fingers, message, randomString2, randomChar2;
        double numOne, numTwo;
        char response, menuPicker, playAgain;
        int response2, response3, randomNum, answer;
        boolean close = false;

        double userWins = 0;
        double systemWins = 0;
        double numGames = 0;
        double draw = 0;

        double finUserWins = 0;
        double finSystemWins = 0;
        double finNumGames = 0;

        double conUserWins = 0;
        double conSystemWins = 0;
        double conNumGames = 0;

        double psrUserWins = 0;
        double psrSystemWins = 0;
        double psrNumGames = 0;
        double psrDraw = 0;

        try
        {
            System.out.println("What is your name?");
            name = scanner.nextLine();

            do
            {
                if (name.isEmpty())
                {
                    System.out.println("Ok dude, you're not some nonchalant and mysterious entity; you're some hunched over, ugly ass, broke ass loser who's playing this dumbass game.\n");
                    System.out.println("Now just tell me what your name is.");
                    name = scanner.nextLine();
                }

            } while (name.isEmpty());
            System.out.println("Hello " + name + " would you like to play a game?");

            do
            {
                System.out.println("\t> (Y)es");
                System.out.println("\t> (N)o");
                stringResponse = scanner.nextLine();

                if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                {
                    for (int i = 1; i < stringResponse.length(); i++)
                    {
                        System.out.println("ermmmm, can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                        break;
                    }
                }
                                            
            } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));

            switch (stringResponse)
            {
                case "Y":
                case "y":

                    while (!close)
                    {
                        do
                        {
                            System.out.println("\nFrom the menu below pick a game");
                            System.out.println("\t> 1. How many fingers am I putting up? ");
                            System.out.println("\t> 2. Which continent am I on?");
                            System.out.println("\t> 3. Paper, Scissors, Rock.");
                            System.out.println("\t> 4. Exit and see your final score.");

                            while (!scanner.hasNextInt()) // Use a loop to keep prompting until a valid integer is entered
                            {
                                System.out.println("\nain't no way this jackass " + name + " thought they were slick by not putting an integer");
                                scanner.next(); // Clear the buffer (consume the invalid input)
                            }
                            response2 = scanner.nextInt();
                            
                            if (response2 == 42)
                            {
                                System.out.println("You have accessed the magical exit button, farewell " + name + ", you absolute prick");
                                close = true;
                                break;
                            }
                            if (response2 < 1 || response2 > 4)
                            {
                                System.out.println("\nOMG " + name + " can you just pick either '1' or '2' or '3' or '4'");
                            }
                        } while (!close && (response2 < 1 || response2 > 4));

                        switch (response2)
                        {
                            case 1:
                                System.out.println("\nYou have picked the finger guessing game.");
                           
                                do
                                {
                                    do
                                    {
                                        System.out.println("\nHow many fingers am I putting up?");

                                        while (!scanner.hasNextInt()) // Use a loop to keep prompting until a valid integer is entered
                                        {
                                            System.out.println("\nWere you dropped on your head as a baby " + name + "?");
                                            System.out.println("Just answer the question already, how many fingers am I putting up?");
                                            System.out.println("(Here's a clue on how to do that, press a number on your keyboard and hit enter, I hope that helps!!)");
                                            scanner.next(); // Clear the buffer (consume the invalid input)
                                        }
                                        response2 = scanner.nextInt();

                                        if (response2 > 10)
                                        {
                                            System.out.println("\nI only have 10 fingers dumbass.");
                                        }

                                        if (response2 < 0)
                                        {
                                            System.out.println("\nWhy are you like this " + name + "? HOW WOULD I HAVE A NEGATIVE AMOUNT OF FINGERS!!");
                                        }
                                    } while (response2 < 0 || response2 > 10);
                                    randomNum = random.nextInt(11); // This will generate a number from the range 0-10

                                    if (randomNum == 1 && response2 == randomNum) //Not super necessary but makes the word 'finger' singular instead of plural since the computer only has 1 finger up
                                    {
                                        System.out.println("\nYou win. I had " + randomNum + " finger up!");
                                        System.out.println("Wow " + name + " you're a good guesser, nice job.");

                                        numGames++; // Tally counter
                                        userWins++;
                                        finNumGames++;
                                        finUserWins++;
                                        System.out.println("\n" + name + " wins: #" + finUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + finSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + finNumGames + " (Total: " + numGames + ")");

                                        if (finSystemWins == 20 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }
                                    
                                    else if (randomNum != 1 && response2 == randomNum) //Not super necessary but makes the word 'finger' plural instead of singular since the computer has either multiple or 0 fingers up
                                    {
                                        System.out.println("\nYou win. I had " + randomNum + " fingers up!");
                                        System.out.println("Wow " + name + " you're a good guesser, nice job.");

                                        numGames++; // Tally counter
                                        userWins++;
                                        finNumGames++;
                                        finUserWins++;
                                        System.out.println("\n" + name + " wins: #" + finUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + finSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + finNumGames + " (Total: " + numGames + ")");

                                        if (finSystemWins == 20 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }
                                    
                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }
                                    
                                    else if (randomNum == 1 && response2 != 1)// accounts for both lose conditions (losing to 1 or more or 0 fingers)
                                    {
                                        System.out.println("\nYou lose. I had " + randomNum + " finger up!");
                                        System.out.println("Nice try bozo, better luck next time " + name);

                                        numGames++; // Tally counter
                                        systemWins++;
                                        finNumGames++;
                                        finSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + finUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + finSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + finNumGames + " (Total: " + numGames + ")");

                                        if (systemWins == 11 && userWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (finSystemWins == 20 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }

                                    else // accounts for both lose conditions (losing to 1 or more or 0 fingers)
                                    {
                                        System.out.println("\nYou lose. I had " + randomNum + " fingers up!");
                                        System.out.println("Nice try bozo, better luck next time " + name);

                                        numGames++; // Tally counter
                                        systemWins++;
                                        finNumGames++;
                                        finSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + finUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + finSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + finNumGames + " (Total: " + numGames + ")");

                                        if (systemWins == 11 && userWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (finSystemWins == 20 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }
                                } while (stringResponse.equalsIgnoreCase("Y") && !stringResponse.equalsIgnoreCase("N"));
                                break;

                            case 2:
                            System.out.println("\nYou have picked the continent guessing game");                                    
                            System.out.println("\nFrom these 7 continents - Asia, Africa, North America, South America, Antarctica, Europe, and Australia");
                            System.out.println("Where am I?");
                            System.out.println("(Make sure to capitalize the first letter)");
                            scanner.nextLine(); // Consumes a line.

                            String[] continents = { "Asia", "Africa", "North America", "South America", "Antarctica", "Europe", "Australia" };

                                do
                                {
                                    do
                                    {
                                        randomNum = random.nextInt(continents.length); // get a random index (randomNum) between 0 and the length of thearray
                                        randomString2 = continents[randomNum]; // uses the randomNum to get a random string from the array
                                        message = scanner.nextLine();

                                        if (Character.isLowerCase(message.charAt(0)))
                                        {
                                            System.out.println("\n" + name + " didn't I tell you to capitalize the first letter, it's a proper noun dummy.");
                                        }
                            
                                        if (message.length() > 1)
                                        {
                                            if (message.equalsIgnoreCase("South America") || message.equalsIgnoreCase("North America"))
                                            {
                                                if (Character.isUpperCase(message.charAt(1)))
                                                {
                                                    System.out.println("I said ONLY the FIRST letter, NOT EVERY OTHER LETTER " + name.toUpperCase() + "!");
                                                    break;                                                        
                                                }
                                            }

                                            else
                                            {
                                                for (int i = 1; i < message.length(); i++)
                                                {
                                                    if (Character.isUpperCase(message.charAt(i)))
                                                    {
                                                        System.out.println("\nI said ONLY the FIRST letter, NOT EVERY OTHER LETTER " + name.toUpperCase() + "!");
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if (!Arrays.asList(continents).contains(message))
                                        {
                                            if (!message.matches("\\w+(?: \\w+)*"))
                                            { // Allows letters, digits, and underscores
                                                System.out.println("Type normally please " + name);
                                            }
                                        }
                                        
                                        if (message.matches("[0-9]+"))
                                        { // Optional: Check specifically for numbers. This technically doesn't have to be here
                                            System.out.println("Numbers aren't continents " + name + ". How about you try using letters ok?!");
                                        }

                                        if (("Mu").equalsIgnoreCase(message) || "Thoth".equalsIgnoreCase(message) || "Atlantis".equalsIgnoreCase(message))
                                        {
                                            System.out.println("Enter a REAL continent");
                                        }
                                        
                                    } while ((!Character.isUpperCase(message.charAt(0)) || (message.length() > 1 && Character.isUpperCase(message.charAt(1))) || !Arrays.asList(continents).contains(message)) || !message.matches("\\w+(?: \\w+)*") && !message.matches("[a-zA-Z]+") || message.matches("[0-9]+"));
                                
                                    if (message.equals(randomString2))
                                    {
                                        System.out.println("\nWow you're right I was in " + randomString2);
                                        System.out.println("Damn your kinda good, but don't get too cocky.");

                                        numGames++; // Tally counter
                                        userWins++;
                                        conNumGames++;
                                        conUserWins++;
                                        System.out.println("\n" + name + " wins: #" + conUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + conSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + conNumGames + " (Total: " + numGames + ")");

                                        if (conSystemWins == 20 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (conSystemWins == 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (conSystemWins > 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }

                                    else
                                    {
                                        //randomString2 = randomString2.toUpperCase(); // makes randomString2 (the random variable for the continents into uppercase)
                                        System.out.println("\nYou absolute fool, I was in " + randomString2);
                                        System.out.println("Man yo ass for realium don't know shit.");

                                        numGames++; // Tally counter
                                        systemWins++;
                                        conNumGames++;
                                        conSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + conUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + conSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + conNumGames + " (Total: " + numGames + ")");

                                        if (conSystemWins == 11 && conUserWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (conSystemWins == 20 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (conSystemWins == 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (conSystemWins > 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }
                                } while (stringResponse.equalsIgnoreCase("Y") && !stringResponse.equalsIgnoreCase("N"));
                                break;

                                case 3:
                                System.out.println("\nYou have picked Paper, Scissors, Rock.");
                                System.out.println("Hit me with your best shot jackass, there's only three options but knowing you, I know you'll fail.");
                                System.out.println("To make it easier for you, you only have to type out the first letter of the word in lowercase.");
                                scanner.nextLine(); // Consumes a line.

                                String[] randomChar = { "paper", "scissors", "rock" };

                                do
                                {
                                    do
                                    {
                                        response = scanner.next().charAt(0);
                                        randomNum = random.nextInt(randomChar.length); // get a random index (randomNum) between 0 and the length of the array
                                        randomChar2 = randomChar[randomNum]; // uses the randomNum to get a random string from the array

                                        if (response != 'p' && response != 's' && response != 'r')
                                        {
                                            System.out.println("\n" + name + "I literally just said to use either 'p' or 's' or 'r'");
                                        }
                                        
                                        if (response == 'P' && response == 'S' && response == 'R')
                                        {
                                            System.out.println("\n" + name + " didn't I tell you to leave it in lowercase?");
                                        }

                                        if (Character.isUpperCase(response))
                                        {
                                            System.out.println("\n" + name + ", dawg, whats up with the caps huh?");
                                        }

                                        if (Character.isDigit(response))
                                        {
                                            System.out.println("Numbers aren't letters " + name + ". How about you try using letters ok?!");
                                        }

                                    } while (!(response == 'p' || response == 's' || response == 'r') && !(response == 'P' || response == 'S' || response == 'R') || (Character.isUpperCase(response) || (Character.isDigit(response))));

                                    if (response == 'p' && randomChar2 == "rock")
                                    {
                                        System.out.println("\nWow you just beat my monkey ass, i held up " + randomChar2);
                                        System.out.println("I will have my revenge soon");

                                        numGames++; // Tally counter
                                        userWins++;
                                        psrNumGames++;
                                        psrUserWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }

                                    if (response == 'r' && randomChar2 == "scissors")
                                    {
                                        System.out.println("\nWow you just beat my monkey ass, i held up " + randomChar2);
                                        System.out.println("I will have my revenge soon");

                                        numGames++; // Tally counter
                                        userWins++;
                                        psrNumGames++;
                                        psrUserWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }

                                    if (response == 's' && randomChar2 == "paper")
                                    {
                                        System.out.println("\nWow you just beat my monkey ass, i held up " + randomChar2);
                                        System.out.println("I will have my revenge soon");

                                        numGames++; // Tally counter
                                        userWins++;
                                        psrNumGames++;
                                        psrUserWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }

                                    else if (response == randomChar2.charAt(0))
                                    {
                                        switch (response)
                                        {
                                            case 'p':
                                            if (response == 'p')
                                            {
                                                System.out.println("\nHey won't you look at that " + name + ", we tied, we both held up " + response + "aper");
                                                System.out.println("I will have my revenge soon");
                                            }

                                            case 's':
                                            if (response == 's')
                                            {
                                                System.out.println("\nHey won't you look at that " + name + ", we tied, we both held up " + response + "cissors");
                                                System.out.println("I will have my revenge soon");
                                            }

                                            case 'r':
                                            if (response == 'r')
                                            {
                                                System.out.println("\nHey won't you look at that " + name + ", we tied, we both held up " + response + "ock");
                                                System.out.println("I will have my revenge soon");
                                            }
                                            break;
                                        }

                                        numGames++; // Tally counter
                                        psrNumGames++;
                                        draw++;
                                        psrDraw++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can play again but i assure you, " + name + ", you won't win.");
                                        }
                                    }

                                    if (response == 'p' && randomChar2 == "scissors")
                                    {
                                        scanner.next();
                                        System.out.println("\nHAHAHAH I WIN, I HELD UP " + randomChar2.toUpperCase());
                                        System.out.println("You truly are the dumbest bastard i've ever had the displeasure of playing with.");

                                        numGames++; // Tally counter
                                        systemWins++;
                                        psrNumGames++;
                                        psrSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 11 && psrUserWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }

                                    if (response == 's' && randomChar2 == "rock")
                                    {
                                        scanner.nextLine();
                                        System.out.println("\nHAHAHAH I WIN, I HELD UP " + randomChar2.toUpperCase());
                                        System.out.println("You truly are the dumbest bastard i've ever had the displeasure of playing with.");

                                        numGames++; // Tally counter
                                        systemWins++;
                                        psrNumGames++;
                                        psrSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 11 && psrUserWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }

                                    if (response == 'r' && randomChar2 == "paper")
                                    {
                                        scanner.nextLine();
                                        System.out.println("\nHAHAHAH I WIN, I HELD UP " + randomChar2.toUpperCase());
                                        System.out.println("You truly are the dumbest bastard i've ever had the displeasure of playing with.");

                                        numGames++; // Tally counter
                                        systemWins++;
                                        psrNumGames++;
                                        psrSystemWins++;
                                        System.out.println("\n" + name + " wins: #" + psrUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + psrSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Draws: #" + psrDraw + " (Total: " + draw + ")");
                                        System.out.println("Number of games played: #" + psrNumGames + " (Total: " + numGames + ")");

                                        if (psrSystemWins == 11 && psrUserWins == 0)
                                        {
                                            System.out.println("Wow, how have you failed this many times " + name + ", statistically you should have won by now");
                                        }

                                        if (psrSystemWins == 20 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nOk, I enjoy winning as much as the next guy but this is just really sad " + name);
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nWOW OK, JEEZ, I DIDN'T THINK ANYONE CAN BE THIS BAD.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("ermmmm, " + name + "can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                                            }
                                        } while (!(stringResponse.equalsIgnoreCase("Y")) && !(stringResponse.equalsIgnoreCase("N")));
                                                
                                        
                                        if (stringResponse.equalsIgnoreCase("N"))
                                        {
                                            System.out.println("\nSee you in another game " + name + "!");
                                        }
                                    
                                        if (stringResponse.equalsIgnoreCase("Y"))
                                        {
                                            System.out.println("\nYou can try to get your revenge " + name + ", but you won't win.");
                                        }
                                    }
                                } while (stringResponse.equalsIgnoreCase("Y") && !stringResponse.equalsIgnoreCase("N"));
                                break;

                            case 4:
                                System.out.println("\nThanks for playing, " + name + "!");
                                System.out.println("Final tally:");
                                int percGameUser, percGameSys, percGameDraw;
                             
                                percGameUser = (int)((finUserWins / finNumGames) * 100);
                                percGameSys = (int)((finSystemWins / finNumGames) * 100);
                                System.out.println("\nFinger Guessing Game");
                                System.out.println(name + "'s wins: " + finUserWins);
                                System.out.println("System's wins: " + finSystemWins);
                                System.out.println("Games played: " + finNumGames);
                                System.out.println("You won " + percGameUser + "% of games and lost " + percGameSys + "% of games");

                                percGameUser = (int)((conUserWins / conNumGames) * 100);
                                percGameSys = (int)((conSystemWins / conNumGames) * 100);
                                System.out.println("\nContinent Guessing Game");
                                System.out.println(name + "'s wins: " + conUserWins);
                                System.out.println("System's wins: " + conSystemWins);
                                System.out.println("Games played: " + conNumGames);
                                System.out.println("You won " + percGameUser + "% of games and lost " + percGameSys + "% of games");

                                percGameUser = (int)((psrUserWins / psrNumGames) * 100);
                                percGameSys = (int)((psrSystemWins / psrNumGames) * 100);
                                percGameDraw = (int)((psrDraw / psrNumGames) * 100);
                                System.out.println("\nPaper Scissors Rock");
                                System.out.println(name + "'s wins: " + psrUserWins);
                                System.out.println("System's wins: " + psrSystemWins);
                                System.out.println("Games tied: " + psrDraw);
                                System.out.println("Games played: " + psrNumGames);
                                System.out.println("You won " + percGameUser + "% of games, lost " + percGameSys + "% of games and drew " + percGameDraw + "% of games");

                                percGameUser = (int)((userWins / numGames) * 100);
                                percGameSys = (int)((systemWins / numGames) * 100);
                                percGameDraw = (int)((draw / numGames) * 100);
                                System.out.println("\nTotal");
                                System.out.println(name + "'s Total wins: " + userWins);
                                System.out.println("System's Total wins: " + systemWins);
                                System.out.println("Games tied: " + draw);
                                System.out.println("Games played: " + numGames);
                                System.out.println("Overall you won " + percGameUser + "% of games, lost " + percGameSys + "% of games and drew " + percGameDraw + "% of games");
                                close = true;
                                break;

                            case 42:
                                close = true;
                                break;

                            default:
                                System.out.println("Oh shit, the system must've been hit with the ID10T error");
                                System.out.println("Oh wait, that error is just yo dumbass brain, select on your keyboard either '1' or '2' or '3' or '4'");
                        }

                    }

            } close = true;
        }

        catch (InputMismatchException error)
        {
            System.out.println(error + " was found");
        }

        catch (ArrayIndexOutOfBoundsException error)
        {
            System.out.println(error + " was found");
        }

        catch (StringIndexOutOfBoundsException error)
        {
            System.out.println(error + " was found");
            System.out.println("Come on man, enter something, anything, don't just hit enter\n");
        }
    }

    public static void gameTwo(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String name, stringResponse, fingers, message, randomString2, randomChar2;
        double numOne, numTwo;
        char response, menuPicker, playAgain;
        int response2, response3, randomNum, answer;
        boolean close = false;

        double userWins = 0;
        double systemWins = 0;
        double numGames = 0;
        double draw = 0;

        double finUserWins = 0;
        double finSystemWins = 0;
        double finNumGames = 0;

        double conUserWins = 0;
        double conSystemWins = 0;
        double conNumGames = 0;

        double psrUserWins = 0;
        double psrSystemWins = 0;
        double psrNumGames = 0;
        double psrDraw = 0;
        
        try
        {
            name = scanner.nextLine();

            do
            {
                if (name.isEmpty())
                {
                    System.out.println("Ok dude, you're not some nonchalant and mysterious entity; you're some hunched over, ugly ass, broke ass loser who's playing this dumbass game.\n");
                    System.out.println("Now just tell me what your name is.");
                    name = scanner.nextLine();
                }

            } while (name.isEmpty());

            do
            {
                stringResponse = scanner.nextLine();

                if (stringResponse.length() > 0 && !stringResponse.equalsIgnoreCase("N"))
                {
                    for (int i = 1; i < stringResponse.length(); i++)
                    {
                        System.out.println("ermmmm, can you like just either put 'Y' or 'y' or 'N' or 'n', ok??");
                        break;
                    }
                }
                                            
            } while (!stringResponse.equalsIgnoreCase("N"));

            while (!close)
            {
                switch (stringResponse)
                {
                    case "n":
                    case "N":

                        do
                        {
                            System.out.println("Kill yourself " + name + ", we're gonna play some fun games whether you like it or not, now pick from the menu below");
                            System.out.println("\t> 1. Hangman");
                            System.out.println("\t> 2. Tic Tac Toe");
                            System.out.println("\t> 3. Mad Libs");
                            System.out.println("\t> 4. Cut");
                            System.out.println("\t> 5. Exit and see your final score.");

                            while (!scanner.hasNextInt()) // Use a loop to keep prompting until a valid integer is entered
                            {
                                System.out.println("\nain't no way this jackass " + name + " thought they were slick by not putting an integer");
                                scanner.next(); // Clear the buffer (consume the invalid input)
                            }
                            response2 = scanner.nextInt();
                            
                            if (response2 == 42)
                            {
                                System.out.println("You have accessed the magical exit button, farewell " + name + ", you absolute prick");
                                close = true;
                                break;
                            }
                            if (response2 < 1 || response2 > 5)
                            {
                                System.out.println("\nOMG " + name + " can you just pick either '1' or '2' or '3' or '4' or '5'");
                            }
                        } while (!close && (response2 < 1 || response2 > 5));

                        switch (response2)
                        {
                            case 1:


                            case 2:


                            case 3:


                            case 4:

                            
                            case 5:


                            case 42:
                                close = true;
                                break;

                            default:
                                System.out.println("Oh shit, the system must've been hit with the ID10T error");
                                System.out.println("Oh wait, that error is just yo dumbass brain, select on your keyboard either '1' or '2' or '3' or '4'");
                        }
                } close = true;
            }
        }
                
        catch (InputMismatchException error)
        {
            System.out.println(error + " was found");
        }

        catch (ArrayIndexOutOfBoundsException error)
        {
            System.out.println(error + " was found");
        }

        catch (StringIndexOutOfBoundsException error)
        {
            System.out.println(error + " was found");
            System.out.println("Come on man, enter something, anything, don't just hit enter\n");
        }
    }
}
