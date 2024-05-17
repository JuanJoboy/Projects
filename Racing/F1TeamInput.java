import java.util.*;

/*
AUTHOR: Juan Joboy
STUDENT: 21837001
TITLE: F1TeamInput
PURPOSE: Allows the user to input team data and write it out to a CSV file
DATE: 15/05/2024
REQUIRES: Makes use of Team.java which contains a Team class that has variables and constructors for the construction of a team
*/

public class F1TeamInput
{

    /*
    METHOD: main
    IMPORT: String[] args
    EXPORT: None
    PURPOSE: The main method acts as an entry point to the program
    */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numTeams, response;
        boolean close = false;

        System.out.println("Welcome to the FIA F1 Data Entry Program");

        // Input Validation Loop
        do
        {
            System.out.println("How many F1 Teams are there?");

            while(!input.hasNextInt()) // Loops until a valid integer is entered
            {
                System.out.println("Please enter a valid number of teams (positive integer greater than 1).");
                input.next(); // Clears the input buffer (discards non-numeric input) to stop an infinite error loop
            }

            numTeams = input.nextInt();

            if(numTeams <= 1)
            {
                System.out.println("At least 2 teams are required.");
            }
            else if(numTeams >= 11)
            {
                System.out.println("At most 10 teams are allowed.");
            }
        } while(numTeams <= 1 || numTeams >= 11); // Continue looping until a valid number is entered

        dataMenu(); // Calls the method to display the menu

        while(!close)
        {
            try
            {
                response = input.nextInt();

                if(response > 0 || response < 3)
                {
                    Team[] teams = null; // Initializes an array of Team objects (initially set to null, but is modified later on)

                    switch(response)
                    {
                        case 1:
                            teams = team(numTeams); // Calls the method that asks the user to enter team data
                            break;
            
                        case 2:
                            information(teams, numTeams); // Calls the method to display the data
                            break;
                        
                        case 3:
                            csv(teams, numTeams); // Calls the method to generate a CSV file
                            close = true;
                            break;
            
                        case 0:
                            System.out.println("Thank you for using the F1 Championship Manager.");
                            System.out.println("Now exiting program...");
                            close = true;
                            break;
            
                        default:
                            System.out.println("Invalid input. Please enter a number between 0 and 3.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Invalid input. Please enter a number between 0 and 3.");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Clears the input buffer (consumes invalid input) to stop an infinite error loop
            }
        }
    }

    /*
    METHOD: dataMenu
    IMPORT: None
    EXPORT: None
    PURPOSE: Displays the menu options for the program
    */
    public static void dataMenu()
    {
        System.out.println("\t> (1) Enter Data");
        System.out.println("\t> (2) Print Out Current Data");
        System.out.println("\t> (3) Print Out To A CSV File");
        System.out.println("\t> (0) Exit");
        System.out.println("\nChoose an option from the menu above to enter team data into.");
    }      

    /*
    METHOD: teamName
    IMPORT: None
    EXPORT: teamNameValue (String)
    PURPOSE: Collects the team name from the user
    */    
    public static String teamName()
    {
        Scanner input = new Scanner(System.in);
        boolean validTeam = false;
        String teamName = ""; // String to store the team name
        String teamNameValue = ""; // String to hold the validated team name
        String[] teamList = {"Red Bull Racing", "Ferrari", "McLaren", "Mercedes", "Aston Martin", "RB", "Haas F1 Team", "Williams", "Kick Sauber", "Alpine"}; // Array of valid team names

        do
        {
            try
            {
                System.out.print("\nHere is a list of the teams: " + Arrays.toString(teamList)); // Prints the array of valid team names
                System.out.print("\nTeam Name: ");
                teamName = input.nextLine();
                    
                for(int i = 0; i < teamList.length; i++)
                {
                    if(teamList[i].equalsIgnoreCase(teamName)) // Allows the user to not have to worry about uppercase or lowercase
                    {
                        validTeam = true;
                        teamNameValue = teamName; // Stores the validated team name
                    }
                }

                if(!validTeam)
                {
                    System.out.println("Please enter a valid team name.");
                }
                else
                {      
                    System.out.println("Team " + teamName + " added!");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid team name.");
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
            }            
        } while(!validTeam);

        return teamNameValue;
    }

    /*
    METHOD: carCode
    IMPORT: None
    EXPORT: carValue (String)
    PURPOSE: Collects the car code from the user
    */
    public static String carCode()
    {
        Scanner input = new Scanner(System.in);
        String car = ""; // String to store user input for car code
        String carValue = ""; // String to hold the validated car code
        boolean validCar = false;
        String[] carList = {"RB20", "SF-24", "MCL38", "W15", "AMR24", "VCARB 01", "VF-24", "FW46", "C44", "A524"}; // Array of valid car codes

        do
        {
            try
            {
                System.out.print("\nHere is a list of the Car Codes: " + Arrays.toString(carList)); // Prints the array of valid car codes
                System.out.print("\nCar Code: ");
                car = input.nextLine();
                    
                for(int i = 0; i < carList.length; i++)
                {
                    if(carList[i].equalsIgnoreCase(car)) // Allows the user to not have to worry about uppercase or lowercase
                    {
                        validCar = true;
                        carValue = car; // Stores the validated car code
                    }
                }

                if(!validCar)
                {
                    System.out.println("Please enter a valid car code.");
                }
                else
                {
                    System.out.println("Car " + car + " added!");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid car code.");
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
            }            
        } while(!validCar);

        return carValue;
    }

    /*
    METHOD: driver
    IMPORT: number (Integer)
    EXPORT: driverValue (String)
    PURPOSE: Collects the driver name from the user, and considers if it's the first driver (number 1) or the second driver (number 2) for a team
    */
    public static String driver(int number)
    {
        Scanner input = new Scanner(System.in);
        String driver = ""; // String to store user input for driver name
        String driverValue = ""; // String to hold the validated driver name
        boolean validDriver = false;
        String[] driverOne = {"Max Verstappen", "Charles Leclerc", "Oscar Piastri", "George Russell", "Lance Stroll", "Daniel Ricciardo", "Nico Hulkenberg", "Alexander Albon", "Guanyu Zhou", "Pierre Gasly"}; // Array of valid primary drivers for a team
        String[] driverTwo = {"Sergio Perez", "Carlos Sainz", "Lando Norris", "Lewis Hamilton", "Fernando Alonso", "Yuki Tsunoda", "Kevin Magnussen", "Logan Sargeant", "Valtteri Bottas", "Esteban Ocon"}; // Array of valid secondary drivers for a team

        do
        {
            try
            {
                if(number == 1) // Checks if its the first driver
                {    
                    System.out.print("\nHere is a list of the first set of drivers: " + Arrays.toString(driverOne)); // Prints an array of valid drivers
                    System.out.print("\nDriver One: ");
                    driver = input.nextLine();
                    
                    for(int i = 0; i < driverOne.length; i++)
                    {
                        if(driverOne[i].equalsIgnoreCase(driver)) // Allows the user to not have to worry about uppercase or lowercase
                        {
                            validDriver = true;
                            driverValue = driver; // Stores the validated driver
                        }
                    }
                    if(!validDriver)
                    {
                        System.out.println("Please enter a valid driver.");
                    }
                }
                else if(number == 2) // Checks if its the second driver
                {
                    System.out.print("\nHere is a list of the first set of drivers: " + Arrays.toString(driverTwo)); // Prints an array of valid drivers
                    System.out.print("\nDriver Two: ");
                    driver = input.nextLine();
                
                    for(int i = 0; i < driverTwo.length; i++)
                    {
                        if (driverTwo[i].equalsIgnoreCase(driver))
                        {
                            validDriver = true;
                            driverValue = driver; // Stores the validated driver
                        }
                    }

                    if(!validDriver)
                    {
                        System.out.println("Please enter a valid driver.");
                    }
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid driver.");
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
            }            
        } while(!validDriver);
        
        System.out.println(driver + " added!");
        return driverValue;
    }

    /*
    METHOD: grandPrix
    IMPORT: None
    EXPORT: prixValue (String)
    PURPOSE: Collects the Grand Prix name from the user
    */
    public static String grandPrix()
    {
        Scanner input = new Scanner(System.in);
        String prix = ""; // String to store user input for Grand Prix name
        String prixValue = ""; // String to hold the validated Grand Prix name
        boolean validPrix = false;
        String[] prixList = {"Bahrain", "Saudi Arabian", "Australian", "Japanese", "Chinese", "Miami", "Emilia Romagna", "Monaco", "Canadian", "Spanish", "Austrian", "British", "Hungarian", "Belgian", "Dutch", "Italian", "Azerbaijan", "Singapore", "United States", "Mexican", "Brazilian", "Silver Las Vegas", "Qatar", "Abu Dhabi"}; // Array of valid Grand Prix names

        do
        {
            try
            {
                System.out.print("\nHere is a list of the Grand Prix's: " + Arrays.toString(prixList)); // Prints the array of valid Grand Prix's
                System.out.print("\nGrand Prix: ");
                prix = input.nextLine();
                    
                for(int i = 0; i < prixList.length; i++)
                {
                    if(prixList[i].equalsIgnoreCase(prix)) // Allows the user to not have to worry about uppercase or lowercase
                    {
                        validPrix = true;
                        prixValue = prix; // Stores the validated Grand Prix
                    }
                }

                if(!validPrix)
                {
                System.out.println("Please enter a valid Grand Prix.");
                }
                else
                {
                    System.out.println(prix + " Grand Prix added!");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid Grand Prix.");
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
            }            
        } while(!validPrix);

        return prixValue;
    }

    /*
    METHOD: position
    IMPORT: None
    EXPORT: position (Integer)
    PURPOSE: Collects the driver's finishing position from the user
    */
    public static int position()
    {
        Scanner input = new Scanner(System.in);
        int position = 0;
        boolean validPosition = false;
        int[] positionList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

        do
        {
            try
            {
                System.out.println("\nIf the driver did not finish the race, input their position as 21.");
                System.out.print("Here is a list of the possible positions: " + Arrays.toString(positionList)); // Prints an array of valid positions
                System.out.print("\nPosition: ");
                position = input.nextInt();
                validPosition = position > 0 && position < 22; // Declares what a valid position should be

                if(!validPosition)
                {
                    System.out.println("Please enter a valid position.");
                }
                else
                {
                    System.out.println("Position " + position + " added!");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid position.");
                input.nextLine(); // Consume the rest of the line
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
                input.nextLine(); // Consume the rest of the line
            }            
        } while(!validPosition);

        return position;
    }

    /*
    METHOD: lap
    IMPORT: None
    EXPORT: lap (double)
    PURPOSE: Collects the driver's fastest lap time from the user, and allows a special case entry (205.50) to indicate the driver did not finish the race
    */
    public static double lap()
    {
        Scanner input = new Scanner(System.in);
        double lap = 0.0;

        do
        {
            try
            {
                System.out.println("\nIf the driver did not finish the race, enter their time as 205.50 seconds");
                System.out.print("Fastest Lap Time: ");
                
                if(input.hasNextDouble())
                {
                    lap = input.nextDouble();
                
                    if(lap <= 0 || lap <= 0.0)
                    {
                        System.out.println("Please enter a number greater than 0.");
                        System.out.print("\nFastest Lap Time: ");
                        input.nextLine(); // Consume the rest of the line
                    }
                    else
                    {
                        System.out.println(lap + " second lap time added!");
                        break; // I need this break to exit the loop after a successful input
                    }
                }
                else
                {
                    System.out.println("Invalid input. Please enter a positive time in seconds as your lap time.");
                    System.out.print("\nFastest Lap Time: ");
                    input.nextLine(); // Consume the rest of the line
                }
            }
        
            catch(ArithmeticException error)
            {
                System.out.println("Invalid input. Please enter a valid lap time.");
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a valid lap time.");
            }
            catch(Exception error)
            {
                // Catch other unexpected exceptions
                System.out.println("An unexpected error occurred. Please try again.");
            }
        } while(!input.hasNextDouble() || lap <= 0 || lap <= 0.0);

        return lap;
    }

    /*
    METHOD: team
    IMPORT: numTeams (Integer)
    EXPORT: teams (Team[])
    PURPOSE: Organizes the information about the teams and their drivers. Then it makes a new Team object and stores it in the teams array.
    */
    public static Team[] team(int numTeams)
    {
        // Declare variables to store team and driver details
        Scanner input = new Scanner(System.in);
        String teamNameValue, carCodeValue,  driverValue,  grandPrixValue, menu;
        int positionValue, driverNum, index;
        double lapValue;
        boolean close = true;
        
        // Create arrays with the specified sizes
        int numDrivers = 2;
        Team[] teams = new Team[numTeams*numDrivers]; // The array can now store however many teams there are by 2 (the number of drivers)

        // Loop through the teams array
        for(int i = 0; i < numTeams; i++)
        {
            System.out.println("\nEnter details for Team " + (i + 1)); // Add 1 so that it doesn't say Team 0 when it should be Team 1

            // Take the user inputs from the methods to get team information
            teamNameValue = teamName();
            carCodeValue = carCode();
            grandPrixValue = grandPrix();
            
            // Loop through each driver in the team
            for (int j = 0; j < numDrivers; j++)
            {
                driverNum = j + 1; // Add 1 so that it doesn't say Driver 0 when it should be Driver 1
                System.out.println("\nEnter details for Driver " + driverNum + " of Team " + (i + 1));
                
                // Take the user inputs from the methods to get driver information
                driverValue = driver(driverNum); // driverNum checks if it's doing Driver 1 or Driver 2
                positionValue = position();
                lapValue = lap();

                // Calculate the index for the team object in the teams array
                index = 2 * i + j;
                
                teams[index] = new Team(teamNameValue, carCodeValue, driverValue, grandPrixValue, positionValue, lapValue);
            }
        }

        // Menu loop for user interaction after team data is collected
        do
        {
            System.out.println("\nWhat would you like to do now?");
            System.out.println("1. Preview my data.");
            System.out.println("2. Export my data to a csv file.");
            System.out.println("3. Exit");

            // Loop for validating user input for the menu
            do
            {
                menu = input.nextLine();
                if(menu.length() > 0 && !menu.equals("1") && !menu.equals("2") && !menu.equals("3"))
                {
                    System.out.println("Invalid input. Please enter 1, 2 or 3.");
                }
            } while(menu.length() == 0 || !menu.equals("1") && !menu.equals("2") && !menu.equals("3"));

            if(menu.equals("1"))
            {
                information(teams, numTeams); // Calls method to preview team data
            }
            else if(menu.equals("2"))
            {
                csv(teams, numTeams); // Calls method to export data to a CSV file
            }
            else if(menu.equals("3"))
            {
                System.out.println("Exiting program...");
                close = true;
                System.exit(0);
            }
        } while(!close);

        return teams;
    }

    /*
    METHOD: information
    IMPORT: teams (Team[]), numTeams (Integer)
    EXPORT: None
    PURPOSE: Displays the collected information and handles situations where no data is available, allowing the user to enter new data or exit the program
    */
    public static void information(Team[] teams, int numTeams)
    {
        Scanner input = new Scanner(System.in);
        String teamName, carCode, driver, grandPrix, menu;
        int position;
        double lap;

        try
        {
            System.out.println("\nThe Current Data Looks Like This");

            // Check if the teams array is null or empty (no data)
            if(teams == null || teams.length == 0)
            {
                System.out.println("\n...\n"); // Just some added spacing for better presentation
                System.out.println("\nNo data to display. What would you like to do?");
                System.out.println("1. Enter data");
                System.out.println("2. Exit");
    
                do
                {
                    menu = input.nextLine();
                    if(menu.length() > 0 && !menu.equals("1") && !menu.equals("2"))
                    {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    }
                } while(menu.length() == 0 || !menu.equals("1") && !menu.equals("2"));
    
                if(menu.equals("1"))
                {
                    teams = team(numTeams); // If no data exists and user chooses to enter data, this calls the team method to collect data
                }
                else
                {
                    System.out.println("Exiting program...");
                    System.exit(0); // Closes the program if the user does not want to enter any information
                }
            }
            else
            {
                for(int i = 0; i < numTeams * 2; i++)
                {
                    // Extract details from the Team object using getter methods that are taken from the Team Class
                    Team team = teams[i]; // Get the current Team object from the array
                    teamName = team.getTeamName();
                    carCode = team.getCarCode();
                    driver = team.getDriver();
                    grandPrix = team.getPrix();
                    position = team.getPosition();
                    lap = team.getLap();

                    // Prints the details of the teams
                    System.out.println("\nTeam Name: " + teamName);
                    System.out.println("Car Code: " + carCode);
                    System.out.println("Driver: " + driver);
                    System.out.println("Grand Prix: " + grandPrix);
                    System.out.println("Position: " + position);
                    System.out.println("Fastest Lap Time: " + lap); 
                }

                System.out.println("\nWhat would you like to do now?");
                System.out.println("1. Export my data to a csv file.");
                System.out.println("2. Exit");

                do
                {
                    menu = input.nextLine();
                    if(menu.length() > 0 && !menu.equals("1") && !menu.equals("2"))
                    {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    }
                } while(menu.length() == 0 || !menu.equals("1") && !menu.equals("2"));

                if(menu.equals("1"))
                {
                    csv(teams, numTeams); // Calls method to export data to a CSV file
                }
                else if(menu.equals("2"))
                {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
            }
        }
        catch (NullPointerException error)
        {
            // Handles NullPointerException errors if the teams array is null
            System.out.println("\nNo data to display.");
        }
    }

    /*
    METHOD: csv
    IMPORT: teams (Team[]), numTeams (Integer)
    EXPORT: None
    PURPOSE: Allows the user to export the collected information to a CSV file, and it handles situations where no data exists, prompting the user to enter data or exit the program.
    */
    public static void csv(Team[] teams, int numTeams)
    {
        Scanner input = new Scanner(System.in);
        String csv, menu;
        Boolean close = true;

        try
        {
            do
            {                 
                // Check if any teams have been created
                if(teams == null || teams.length == 0)
                {
                    System.out.println("\nNo teams have been created yet. What would you like to do?");
                    System.out.println("1. Enter data");
                    System.out.println("2. Exit");
                
                    do
                    {
                        menu = input.nextLine();

                        if(menu.length() > 0 && !menu.equals("1") && !menu.equals("2"))
                        {
                            System.out.println("Invalid input. Please enter 1 or 2.");
                        }
                    } while(menu.length() == 0 || !menu.equals("1") && !menu.equals("2"));
                    
                    if(menu.equals("1"))
                    {
                        csv = ""; // Initializes an empty string for the CSV filename, but will be modified shortly after
                        teams = team(numTeams); // Calls the team method to collect data if none exists
                        csv = csv + ".csv";  // Adds ".csv" to the filename
                        Team.writeToFile(csv, teams); // Writes the data to a CSV
                        System.out.println("\nFile has been successfully written and named " + csv);
                        System.out.println("Thank you for using the F1 Championship Manager.");
                    }
                    else
                    {
                        System.out.println("Exiting program...");
                        close = true;
                    }
                }
                else
                {
                    System.out.print("Please Enter A Name For Your CSV File (Without the file extension): ");

                    do
                    {
                        csv = input.nextLine(); // Loops until user enters a valid filename (something that isn't null)
                    } while(csv.isEmpty());

                    csv = csv + ".csv"; // Adds ".csv" to the filename
                    Team.writeToFile(csv, teams); // Writes the data to a CSV
                    System.out.println("\nFile has been successfully written and named " + csv);
                    System.out.println("Thank you for using the F1 Championship Manager.");
                    System.exit(0); // Exits the program immediately after successful export
                }
            } while(!close);
        }
        catch (NullPointerException error)
        {
            System.out.println("Error: " + error.getMessage());
        }
    }
}