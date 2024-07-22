import java.util.*;

public class Playground
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
                    System.out.println("Please enter a name\n");
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
                        System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");
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
                                System.out.println("\nEnter a valid number " + name);
                                scanner.next(); // Clear the buffer (consume the invalid input)
                            }
                            response2 = scanner.nextInt();
                            
                            if (response2 == 42)
                            {
                                System.out.println("You have accessed the magical exit button, farewell " + name);
                                close = true;
                                break;
                            }
                            if (response2 < 1 || response2 > 4)
                            {
                                System.out.println("\n" + name + " please pick either '1' or '2' or '3' or '4'");
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
                                            System.out.println("\nEnter an integer " + name);
                                            scanner.next(); // Clear the buffer (consume the invalid input)
                                        }
                                        response2 = scanner.nextInt();

                                        if (response2 > 10)
                                        {
                                            System.out.println("\nI only have 10 fingers.");
                                        }

                                        if (response2 < 0)
                                        {
                                            System.out.println("\nI don't have a negative amount of fingers.");
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }
                                    
                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");
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
                                        System.out.println("Nice try, better luck next time " + name);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("Nice try, better luck next time " + name);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (finSystemWins == 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (finSystemWins > 30 && finUserWins < finSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                            System.out.println("(Type out the whole name and make sure to capitalize the first letter)");                            scanner.nextLine(); // Consumes a line.

                            String[] continents = { "Asia", "Africa", "North America", "South America", "Antarctica", "Europe", "Australia" };

                                do
                                {
                                    do
                                    {
                                        randomNum = random.nextInt(continents.length); // get a random index (randomNum) between 0 and the length of thearray
                                        randomString2 = continents[randomNum]; // uses the randomNum to get a random string from the array
                                        message = scanner.nextLine();

                                        if (!message.matches("\\w+(?: \\w+)*") || Character.isLowerCase(message.charAt(0)) )
                                        {
                                            if (!message.matches("\\w+(?: \\w+)*"))
                                            { // Invalid message format
                                                System.out.println("Type normally please " + name);
                                            }
                                            
                                            else
                                            {
                                                System.out.println("Capitalize the first letter");
                                            }
                                        }
                            
                                        if (message.length() > 1)
                                        {
                                            if (message.equalsIgnoreCase("South America") || message.equalsIgnoreCase("North America"))
                                            {
                                                if (Character.isUpperCase(message.charAt(1)))
                                                {
                                                    System.out.println("Only capitalize the first letter, not any other letter.");
                                                    break;                                                        
                                                }
                                            }

                                            else
                                            {
                                                for (int i = 1; i < message.length(); i++)
                                                {
                                                    if (Character.isUpperCase(message.charAt(i)))
                                                    {
                                                        System.out.println("Only capitalize the first letter, not any other letter.");
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        
                                        if (message.matches("[0-9]+") || !Arrays.asList(continents).contains(message))
                                        { 
                                            if (message.matches("[0-9]+")) // Check specifically for numbers.
                                            {
                                                System.out.println("Numbers aren't continents " + name + ". Use letters please.");
                                            }
                                            
                                            else
                                            {
                                                System.out.println("Enter one of the 7 continents");
                                            }
                                        }

                                        if (("Mu").equals(message) || "Thoth".equals(message) || "Atlantis".equals(message))
                                        {
                                            System.out.println("\nYOU SHUT YOUR MOUTH\nTRIPLE DOG DEATH BARRAGE");
                                            System.out.println("    . `  `. .`  ` .\r\n" + //
                                                                                                "                        . `     `.  ;  .`     ` .\r\n" + //
                                                                                                "                     .`           \\   /           `.\r\n" + //
                                                                                                "                   .`     . - .   ( @ )   . - .     `.\r\n" + //
                                                                                                "                  /    .`      `.  '-'  .'      `.    \\\r\n" + //
                                                                                                "          /\\    .`    /   .--.   `-._.-'   .--.   \\    `.    /\\\r\n" + //
                                                                                                "        .`  `. /    .'  .`    `. `.   .` .`    `.  `.    \\ .`  `.\r\n" + //
                                                                                                "      .`     .`    /   /        \\  \\ /  /        \\   \\    `.     `.\r\n" + //
                                                                                                "     /      /    .'   /   . ''' .\\     /. ''' .   \\   `.    \\      \\\r\n" + //
                                                                                                "    |    .`(    /    |   /        \\   /        \\   |    \\    )`.    |\r\n" + //
                                                                                                "     \\   | /  .'      \\ |   /##\\  |   |  /##\\   | /      `.  \\ |   /\r\n" + //
                                                                                                "      |  / | /         \\\\  | ###| /   \\ |### |  //         \\ | \\  |\r\n" + //
                                                                                                "    .`\\  | \\/)       _.-'.  \\##/ |     | \\##/  .'-._       (\\/ |  /`.\r\n" + //
                                                                                                "   /   | |  (      .`     `-.   /       \\   .-'     `.      )  | |   \\\r\n" + //
                                                                                                "  |  .`\\ \\   )               `.'         `.'               (   / /`.  |\r\n" + //
                                                                                                "  /\\/  | |                  .'             `.                  | |  \\/\\\r\n" + //
                                                                                                "  \\   / /     /            (   .-.     .-.   )            \\     \\ \\   /\r\n" + //
                                                                                                "   `./ |    .`              `.(   `._.'   ).'              `.    | \\ /\r\n" + //
                                                                                                "   / \\ \\   /.`\\      )._           ) (           _.(      /`.\\   / /  \\\r\n" + //
                                                                                                "  /  |  `-'/   \\    /  ''--.__    .' '.    __.--''  \\    /   \\`-'  |   \\\r\n" + //
                                                                                                "  |   `---'/  .`'.  '.       ''--..___..--''       .'  .'`.  \\`---'    |\r\n" + //
                                                                                                "  |    _.-/  /   |'.  '.                         .'  .'|   \\  \\-._     |\r\n" + //
                                                                                                "  /\\ .`  |  /|  /   '.  ''---....._____.....---''  .'   \\  |\\ |   `.  /\r\n" + //
                                                                                                " |  /     \\/ \\ |       - . _     _.---._     _ . -       \\ /|/      \\|\r\n" + //
                                                                                                " \\ /          \\/            ''--._______.--''             \\|         \\\r\n" + //
                                                                                                " .`        \\                                                 /        `.\r\n" + //
                                                                                                "/       ;   |  `.                                       .`  |   ;       \\\r\n" + //
                                                                                                "| (      )  /    \\                  ^                  /    \\  (      ) |\r\n" + //
                                                                                                "|  `.      / .`   `.              .` `.              .`   `. \\      .`  |\r\n" + //
                                                                                                "\\         \\         `-._         /     \\         _.-`         /         /\r\n" + //
                                                                                                " \\         \\  _.-`   _  `--.__.-`       `-.__.--`  _   `-._  /         /\r\n" + //
                                                                                                " |          `-._    / )   _                   _   ( \\    _.-`          |\r\n" + //
                                                                                                "  \\             `-.` (   / )    `-.___.-`    ( \\   ) `.-`             /\r\n" + //
                                                                                                "   \\      `.         /.-' / )               ( \\ '-.\\         .`      /\r\n" + //
                                                                                                "    `-._     `.     |  .-' / )  `-.___.-`  ( \\ '-.  |     .`     _.-`\r\n" + //
                                                                                                "    /   `--._        \\  .-' / )           ( \\ '-.  /        _.--`  \\\r\n" + //
                                                                                                "    |   |   \\`--.._   |  .-' /  `-.___.-`  \\ '-.  |   _..--`/  |    |\r\n" + //
                                                                                                "     `./     \\ .`  `-/.__.--'               '--.__.\\-`  `. /    `.  |\r\n" + //
                                                                                                "      |     / |_           -._            _.-            _| \\    |\\/\r\n" + //
                                                                                                "       `.  | /  ''--..__            _            __..--''  \\ |  .`\r\n" + //
                                                                                                "       | \\/  |==  ==    ''--..__/\\_/ \\_/\\__..--''    ==  ==|  \\/ \\\r\n" + //
                                                                                                "       \\     \\ _==   ==   ==   / o)|-|(o \\   ==   ==   ==_ /     /\r\n" + //
                                                                                                "       |    /|  ''--..__==  == \\ (\\) (/) / ==  ==__..--'' ||\\    |\r\n" + //
                                                                                                "        \\  / |      |  |''--..__\\ )\\_/( /__..--''   _.-'\\ \\\\ \\  /\r\n" + //
                                                                                                "         \\|  /     /  /          \\/___\\/     \\\\ _.-'     \\ \\\\ |/\r\n" + //
                                                                                                "             |    /  /  :                     \\\\\\    @ .-'  ||\r\n" + //
                                                                                                "             \\_.-`  /          :            : || \\_.-'      \\\\\r\n" + //
                                                                                                "             /___.-`                          ||       _     \\\\\r\n" + //
                                                                                                "             | :                       :       \\\\     / (    ||\r\n" + //
                                                                                                "             /               :                  \\\\    =:_\\   \\\\\r\n" + //
                                                                                                "             |   :                              ||     )      \\\\\r\n" + //
                                                                                                "             |         :                 :      \\\\          _.//\r\n" + //
                                                                                                "              \\     /\\                           \\\\     _.-'\r\n" + //
                                                                                                "              |    |  \\    |\\   :            |\\   ||_.-'   |\r\n" + //
                                                                                                "              | : /   |   /  |      /|      /  |   /|     /\r\n" + //
                                                                                                "              \\  /    \\  |   \\     | |     | `./  |  \\   /\r\n" + //
                                                                                                "              | / `    |/  `. |    / \\  : /    | /    \\  |\r\n" + //
                                                                                                "              \\|\\ `            \\  |   |  | `   \\|    ` \\ |\r\n" + //
                                                                                                "                 | `     `.     \\ /   / /  `  `.     ` ||/\r\n" + //
                                                                                                "                 \\  `        ,   \\|   \\ | `          ` /\r\n" + //
                                                                                                "                  | `.      ,    |    |/            ` |   .    .\r\n" + //
                                                                                                "                   \\             /     \\           ,`/     \\  /\r\n" + //
                                                                                                "                    \\    . - .   |     |   . - .    /     ( O  )\r\n" + //
                                                                                                "                     \\  .`-._ . /       \\ . _.-'.  /       )  (\r\n" + //
                                                                                                "                     \\  : _.-': |       | :`-._ :  /      (    )\r\n" + //
                                                                                                "                     |   `- -`  \\       /  `- -`   |        \\ /\r\n" + //
                                                                                                "          )\\         |    ___   |       |   ___    |         |\r\n" + //
                                                                                                "       )\\/ (         /_.-'___'-._\\     /_.-'___'-._\\        /\r\n" + //
                                                                                                "      (  @  )       [__.-'   '-._]     [_.-'   '-.__]      |\r\n" + //
                                                                                                "       \\( )/       /|\\ _ \\   /_  /     \\  _\\   /_  /   /|  /\r\n" + //
                                                                                                "           \\      | /|`   | |  -_| /\\  |_-  | |  ` |  | / /\r\n" + //
                                                                                                "       |\\   |  |\\/ | /.-` | |`-._\\/ |  /_.-`| |`-. \\ /  |/|\\       /|\r\n" + //
                                                                                                "  /\\  /  \\   \\ \\ \\ \\ \\-_ /   \\.-'/\\ \\  \\'-./   \\ _-/ |  / / \\ /\\  | \\\r\n" + //
                                                                                                " | / |  / /\\ | /  |/ /`-.\\ _ /.-'\\|  \\ /'-.\\ _ /.-`\\ / | /  / | \\ \\ |\r\n" + //
                                                                                                " \\ | \\ | |  \\\\\\|  \\ .`-_ // \\ \\ .-\\  // -./ / \\\\ _-`.  \\ | |  / / | \\\r\n" + //
                                                                                                " / \\ / \\ |  //|\\  .`,`__//___\\ \\__/   \\__/ /___\\\\__`,`. |/ \\ / | /  |\r\n" + //
                                                                                                " \\ |/  |/  |/ |/ /_-_--_--_---,--.`) (`,--.---_--_--_-_\\\\|  \\| \\/   \\\r\n" + //
                                                                                                " / \\|  /   /\\ /\\(_`'_`'_`'_) (____)   (____) (_`'_`'_`'_)/  /\\  |/");
                                        }
                                        
/* 
!Arrays.asList(continents).contains(message)

! (logical NOT operator): The exclamation mark (!) negates the entire expression. So, the whole line essentially asks: "Is the user's guess (message) NOT present in the list of continents (continents array converted to a list)?"

In simpler terms:

If the user enters a continent name that's not in the continents array (like "Mars" or "Atlantis"), this line will evaluate to true because that guess is not present in the list of valid continents.
If the user enters a valid continent name (like "Asia" or "Europe"), this line will evaluate to false because the guess is present in the list. */                                        
                                    } while (!Arrays.asList(continents).contains(message) || !message.matches("\\w+(?: \\w+)*") || Character.isLowerCase(message.charAt(0)) || !(message.length() > 1) || message.matches("[0-9]+"));
                                
                                    if (message.equals(randomString2))
                                    {
                                        System.out.println("\nWow you're right I was in " + randomString2);
                                        System.out.println("Damn your kinda good, but don't get too smug.");

                                        numGames++; // Tally counter
                                        userWins++;
                                        conNumGames++;
                                        conUserWins++;
                                        System.out.println("\n" + name + " wins: #" + conUserWins + " (Total: " + userWins + ")");
                                        System.out.println("System wins: #" + conSystemWins + " (Total: " + systemWins + ")");
                                        System.out.println("Number of games played: #" + conNumGames + " (Total: " + numGames + ")");

                                        if (conSystemWins == 20 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (conSystemWins == 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (conSystemWins > 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nSorry, I was actually in " + randomString2);
                                        System.out.println("Better luck next time " + name);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (conSystemWins == 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (conSystemWins > 30 && conUserWins < conSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                System.out.println("Only type out the first letter of the word in lowercase.");
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
                                            System.out.println("\n" + name + " leave it in lowercase");
                                        }

                                        if (Character.isUpperCase(response))
                                        {
                                            System.out.println("\n" + name + ", Dont use caps.");
                                        }

                                        if (Character.isDigit(response))
                                        {
                                            System.out.println("Numbers aren't letters " + name + ". Use letters please.");
                                        }

                                    } while (!(response == 'p' || response == 's' || response == 'r') && !(response == 'P' || response == 'S' || response == 'R') || (Character.isUpperCase(response) || (Character.isDigit(response))));

                                    if (response == 'p' && randomChar2 == "rock")
                                    {
                                        System.out.println("\nWow you just beat me, I held up " + randomChar2);
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nWow you just beat me, I held up " + randomChar2);
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nWow you just beat me, I held up " + randomChar2);
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("Would you like to play again?");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nI win, I held up " + randomChar2);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nI win, I held up " + randomChar2);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                        System.out.println("\nI win, I held up " + randomChar2);

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
                                            System.out.println("\nYou'll win eventually " + name + ", keep going!!");
                                        }

                                        if (psrSystemWins == 30 && psrUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                            System.out.println("And because you suck so bad, I will forever repeat the above message every time you lose");
                                        }
                                    
                                        if (psrSystemWins > 30 && conUserWins < psrSystemWins)
                                        {
                                            System.out.println("\nYou suck.");
                                        }

                                        System.out.println("\nWould you like to play again? (y/n)");
                                        System.out.println("\t> (Y)es");
                                        System.out.println("\t> (N)o");

                                        do
                                        {
                                            stringResponse = scanner.nextLine();

                                            if (stringResponse.length() > 0 && !(stringResponse.equalsIgnoreCase("Y") || stringResponse.equalsIgnoreCase("N")))
                                            {
                                                System.out.println("Please enter 'Y' or 'y' or 'N' or 'n'");;
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
                                System.out.println("Oh wait, thats just you, please select on your keyboard either '1' or '2' or '3' or '4'");
                        }

                    }
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
