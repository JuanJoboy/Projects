import java.util.*;
import java.io.*;

/*
AUTHOR: Juan Joboy
STUDENT: 21837001
TITLE: F1Analysis
PURPOSE: Allows the user to look at the data in a csv file in a sorted manner
DATE: 15/05/2024
REQUIRES: Makes use of Team.java which contains a Team class that has variables and constructors for the construction of a team
*/

public class F1Analysis
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
        
        numTeams = 0;
        Team[] teams = new Team[numTeams];
                
        teams = readTeams(); // Calls a method to actually read the file

        System.out.println("\nWould you like an All Teams analysis or a Single Team analysis?");
        System.out.println("(1) All Teams");
        System.out.println("(2) Single Team");
        System.out.println("(0) Exit");
        System.out.print("\nEnter your choice: ");

        while(!close)
        {
            try
            {
                response = input.nextInt();

                if(response >= 0 && response <= 2)
                {
                    switch(response)
                    {
                        case 1:
                            allTeams(teams);
                            break;
                        case 2:
                            singleTeam(teams);;
                            break;
                        case 0:
                            System.out.println("\nExiting...");
                            close = true;
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Invalid input. Please enter a number between 0 and 2.");
                }
            }
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Clear the input buffer
            }
        }

    }

    /*
    METHOD: readTeams
    IMPORT: none
    EXPORT: teams (Team[])
    PURPOSE: Reads the csv file
    */
    public static Team[] readTeams()
    {
        Scanner input = new Scanner(System.in);
        String response, fileName;
        boolean close = false;

        FileInputStream fileStream = null; // Handles file input
        InputStreamReader rdr; // Reads characters from the file stream
        BufferedReader bufRdr; // read lines from the file stream more efficiently
        int lineNum,lineCounter; // LineNum keeps track of the line number while reading the file, and lineCounter counts the total lines in the file
        String line; // A string to store each line read from the file
        Team[] teams = new Team[0];


        while (!close)
        {
            try
            {
                System.out.print("\nEnter the name of the file containing the data: ");
                response = input.nextLine();
                fileName = response + ".csv";

                // Check if file exists
                File file = new File(fileName);
                if(file.exists())
                {
                    try 
                    {
                        fileStream = new FileInputStream(fileName); // Reads data from the file
                        rdr = new InputStreamReader(fileStream); // Converts the byte stream to characters
                        bufRdr = new BufferedReader(rdr); // For efficient line-by-line reading
                        lineCounter = -1; // To get rid of the header
                        line = bufRdr.readLine();
                        while(line != null)
                        {
                            line = bufRdr.readLine(); // Reads the next line to move the cursor.
                            lineCounter++; // Counts the lines
                        }
                        
                        teams = new Team[lineCounter]; // Rezises the array to the number of lines
                        fileStream = new FileInputStream(fileName);
                        rdr = new InputStreamReader(fileStream);
                        bufRdr = new BufferedReader(rdr);
                        line = bufRdr.readLine(); // Skip the header
                        System.out.println(line);
                        line = bufRdr.readLine();
                        lineNum = -1;
                        while(line != null)
                        {
                            lineNum++;
                            teams[lineNum] = createTeam(line); // Call a method to create an actual Team object from the line
                            System.out.println(line);
                            line = bufRdr.readLine();
                        }
                        fileStream.close();
                    }
                    catch(IOException errorDetails) 
                    {
                        if(fileStream != null) 
                        {
                            try 
                            {
                                fileStream.close();
                            }
                            catch(IOException ex2) 
                            {
                                System.out.println(ex2.getMessage());
                            }
                        }
                        System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
                    }
                    break; // Exit the loop if file is found
                }
                else
                {
                    System.out.println("File not found. Please try again.");
                }
            }
            catch(Exception e)
            {
              System.out.println("An error occurred: " + e.getMessage());
            }
        }

        return teams;
    }

    /*
    METHOD: createTeam
    IMPORT: line (String)
    EXPORT: team (Team)
    PURPOSE: Takes a string (line) representing a line from a CSV file and parses it to create a Team object
    */
    public static Team createTeam(String line)
    {
        String csvRow, teamName, carCode, driver, grandPrix;
        String[] splitLine;
        int numTeams, position, lineLength;
        double lap;
        Team team = null;

        numTeams = 0;
        csvRow = "";

        try
        {
            splitLine = line.split(","); // Splits the line string using commas (",") as delimiters, storing the elements in the splitLine array
            lineLength = splitLine.length; // Gets the number of elements
            // Extracts individual fields from the splitLine array based on their assumed positions
            teamName = splitLine[0];
            carCode = splitLine[1];
            driver = splitLine[2];
            grandPrix = splitLine[3];
            position = Integer.parseInt(splitLine[4]); // Converts position string to an int
            lap = Double.parseDouble(splitLine[5]); // Converts lap string to a double
            team = new Team(teamName, carCode, driver, grandPrix, position, lap); // Creates a new Team object data and assigns it to the team variable
        }
        catch(Exception error)
        {
            System.out.println(error);
        }

        return team;
    }
    
    /*
    METHOD: allTeams
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Displays all the methods relating to 'all team' data analysis
    */
    public static void allTeams(Team[] teams)
    {
        Scanner input = new Scanner(System.in);
        String menu;

        oneDriver(teams);
        noDriver(teams);
        fastestTeam(teams);
        descendingTeam(teams);
        slowestDriver(teams);
        fastestDriver(teams);
        
        System.out.println("\nWhat would you like to do now?");
        System.out.println("1. Go to Single Team Analysis");
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
            singleTeam(teams);
        }
        else if(menu.equals("2"))
        {
            System.out.println("Exiting program...");
            System.exit(0);
        }
    }

    /*
    METHOD: singleTeam
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Allows the user to pick out specific car codes from the csv and look at its data
    */
    public static void singleTeam(Team[] teams)
    {
        Scanner input = new Scanner(System.in);
        String response;
        boolean validCarCode = false;

        response = "";
    
        while (!validCarCode)
        {
            System.out.println("Enter the Car Code to view its data (or 2 to quit)");

            response = input.nextLine();
            if(response.equals("2"))
            {
                System.out.println("Exiting program...");
                System.out.println("Thank you for using the F1 Championship Manager.");
                System.exit(0);
            }

            // Check if the entered car code is valid
            boolean found = false;
            for(int i = 0; i < teams.length; i++)
            {
                if(teams[i].getCarCode().equalsIgnoreCase(response))
                {
                    found = true;

                    // Display data for the selected car code
                    System.out.println("Data for Car Code '" + response + "':");

                    // Print the drivers and their lap times for the selected car code
                    for(i = 0; i < teams.length; i++)
                    {
                        if(teams[i].getCarCode().equalsIgnoreCase(response)) 
                        {
                            System.out.println("Driver: " + teams[i].getDriver() + ", Lap Time: " + teams[i].getLap());
                        }
                    }
                }
            }
            if(!found)
            {
                System.out.println("Invalid Car Code. Please try again or enter 2 to quit.");
            }
            else
            {
                validCarCode = true;
            }
        }

        System.out.println("\nWould you like an All Teams analysis or a Single Team analysis?");
        System.out.println("(1) All Teams");
        System.out.println("(2) Single Team");
        System.out.println("(0) Exit");
        System.out.print("\nEnter your choice: ");
    }

    /*
    METHOD: oneDriver
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Lists each of the teams that competed with at least one driver completing the race
    */
    public static void oneDriver(Team[] teams)
    {
        int counter;
        String uniqueNames, tempName;
        boolean foundDuplicate;
        
        counter = 0;
        uniqueNames = "";
    
        // Collect unique team names
        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getTeamName();
            foundDuplicate = false;
            
            for(int j = 0; j < i; j++)
            {
                if(teams[j].getTeamName().equals(tempName))
                {
                    foundDuplicate = true;
                }
            }
            
            if(!foundDuplicate)
            {
                counter++;
                uniqueNames += tempName + ";";
            }
        }
    
        // Initialize arrays for unique team names and their total times
        String[] uniqueTeamNames = uniqueNames.split(";");
        double[] teamTimes = new double[uniqueTeamNames.length];
        
        // Calculate total times for each unique team
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getTeamName()))
                {
                    teamTimes[i] += teams[j].getLap();
                }
            }
        }
    
        // Sort the teams based on their total times in descending order
        highToLowSort(teamTimes, uniqueTeamNames);
    
        // Print teams with at least one driver completing the race
        String[] teamsWithCompletion = withCompletion(teams, uniqueTeamNames);
        System.out.println("\nTeams with at least one driver completing the race");
        for(int i = 0; i < teamsWithCompletion.length; i++)
        {
            System.out.println(teamsWithCompletion[i]);
        }
    }

    /*
    METHOD: noDriver
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Lists each of the teams that had no driver complete the race
    */
    public static void noDriver(Team[] teams)
    {
        int counter; // To count unique team names encountered.
        String uniqueNames, tempName; // uniqueNames is a string to store a list of unique team names, and tempName is a temporary string to hold a team name during processing
        boolean foundDuplicate, driversRaced;
        
        counter = 0;
        uniqueNames = "";
    
        // Collect unique team names
        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getTeamName();  // Stores the current team's name
            foundDuplicate = false;
            
            for(int j = 0; j < i; j++)
            {
                // tempName is used to temporarily store each team name during the loop iteration, while uniqueNames accumulates all the unique team names separated by semicolons.
                if(teams[j].getTeamName().equals(tempName))
                {
                    foundDuplicate = true; // If a previously encountered team (j) has the same name as tempName, foundDuplicate is set to true
                }
            }
            
            if(!foundDuplicate) // If foundDuplicate is still false (meaning no duplicate found), the team name is considered unique
            {
                counter++;
                uniqueNames += tempName + ";"; // The team name with a semicolon separator is appended to uniqueNames
            }
        }
    
        // Initialize arrays for unique team names and their total times
        String[] uniqueTeamNames = uniqueNames.split(";"); // uniqueNames is split by semicolons, resulting in an array of unique team names
        double[] teamTimes = new double[uniqueTeamNames.length]; // A double array teamTimes is created with the same size as uniqueTeamNames to store the total lap times for each unique team
        
        // Calculate total times for each unique team
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getTeamName()))
                {
                    teamTimes[i] += teams[j].getLap();
                    // If the current unique team name (uniqueTeamNames[i]) matches a team's name (teams[j].getTeamName()), the team's lap time (teams[j].getLap()) is added to the corresponding element (teamTimes[i]) in the teamTimes array. This accumulates the total time for that unique team.
                }
            }
        }
    
        // Sort the teams based on their total times in descending order
        highToLowSort(teamTimes, uniqueTeamNames);

        // Print teams with no driver completing the race
        String[] teamsWithoutCompletion = withoutCompletion(teams, uniqueTeamNames);
        System.out.println("\nTeams with no driver completing the race");
        for(int i = 0; i < teamsWithoutCompletion.length; i++)
        {
            System.out.println(teamsWithoutCompletion[i]);
        }
        
        driversRaced = participation(teams);

        if(driversRaced)
        {
            System.out.println("\nEvery driver ran the race.");
        }
    }

    /*
    METHOD: fastestTime
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Calculates the fastest team of the race and displays the times
    */
    public static void fastestTeam(Team[] teams)
    {
        int counter;
        String uniqueNames, tempName;
        boolean foundDuplicate;

        counter = 0;
        uniqueNames = "";
    
        // Collect unique team names
        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getTeamName();
            foundDuplicate = false;
            
            for(int j = 0; j < i; j++)
            {
                if(teams[j].getTeamName().equals(tempName))
                {
                    foundDuplicate = true;
                }
            }
            
            if(!foundDuplicate)
            {
                counter++;
                uniqueNames += tempName + ";";
            }
        }
    
        // Initialize arrays for unique team names and their total times
        String[] uniqueTeamNames = uniqueNames.split(";");
        double[] teamTimes = new double[uniqueTeamNames.length];
        
        // Calculate total times for each unique team
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getTeamName()))
                {
                    teamTimes[i] += teams[j].getLap();
                }
            }
        }
    
        highToLowSort(teamTimes, uniqueTeamNames);
    
        System.out.println("\nSorted Teams by Times");
        for (int i = uniqueTeamNames.length - 1; i >= 0; i--) // Iterates through the sorted arrays in reverse order (i = uniqueTeamNames.length - 1; i >= 0; i--) to print the team names, positions (based on the original order), and their corresponding total times.
        {
            System.out.println((uniqueTeamNames.length - i) + ". " + uniqueTeamNames[i] + ": " + teamTimes[i]);
        }

        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getTeamName()))
                {
                    teamTimes[i] += teams[j].getLap();
                    if(teams[j].getLap() == 205.50)
                    {
                        System.out.println(">   " + teams[j].getDriver() + " did not finish because his time was " + teams[j].getLap() + " seconds");
                    }
                }
            }
        }
    }

    /*
    METHOD: descendingTeam
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Sorts the teams according to which was fastest and displays to the user in descending order
    */
    public static void descendingTeam(Team[] teams)
    {
        int counter;
        String uniqueNames, tempName;
        boolean foundDuplicate;
        
        counter = 0;
        uniqueNames = "";
    
        // Collect unique team names
        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getTeamName();
            foundDuplicate = false;
            
            for(int j = 0; j < i; j++)
            {
                if(teams[j].getTeamName().equals(tempName))
                {
                    foundDuplicate = true;
                }
            }
            
            if(!foundDuplicate)
            {
                counter++;
                uniqueNames += tempName + ";";
            }
        }
    
        // Initialize arrays for unique team names and their total times
        String[] uniqueTeamNames = uniqueNames.split(";");
        double[] teamTimes = new double[uniqueTeamNames.length];
        
        // Calculate total times for each unique team
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getTeamName()))
                {
                    teamTimes[i] += teams[j].getLap();
                }
            }
        }

        highToLowSort(teamTimes, uniqueTeamNames);

        System.out.println("\nSorted Teams in descending order");
        for (int i = 0; i < uniqueTeamNames.length; i++)
        {
            System.out.println((uniqueTeamNames.length - i) + ". " + uniqueTeamNames[i]);
        }
    }

    /*
    METHOD: slowestDriver
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Sorts the drivers according to who was fastest and displays to the user in descending order
    */
    public static void slowestDriver(Team[] teams)
    { 
        int counter;
        String uniqueNames, tempName;
        boolean foundDuplicate;

        counter = 0;
        uniqueNames = "";

        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getDriver();
            foundDuplicate = false;

            for(int j = 0; j < i; j++)
            {
                if(teams[j].getDriver().equals(tempName))
                {
                    foundDuplicate = true;
                }
            }
            
            if(!foundDuplicate)
            {
                counter++;
                uniqueNames += tempName + ";";
            }
        }
        
        double[] teamTimes = new double[counter];
        String[] uniqueTeamNames = uniqueNames.split(";");

        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getDriver()))
                {
                    teamTimes[i] += (teams[j].getLap());
                }
            }
        }

        highToLowSort(teamTimes, uniqueTeamNames);

        System.out.println("\nSorted Drivers in descending order");
        for (int i = 0; i < uniqueTeamNames.length; i++)
        {
            System.out.println((uniqueTeamNames.length - i) + ". " + uniqueTeamNames[i] + ": " + teamTimes[i]); // .length reverses the order
        }
    }

    /*
    METHOD: fastestDriver
    IMPORT: teams (Team[])
    EXPORT: none
    PURPOSE: Sorts the drivers according to who was fastest and displays to the user in ascending order
    */
    public static void fastestDriver(Team[] teams)
    {
        int counter;
        String uniqueNames, tempName;
        boolean foundDuplicate;

        counter = 0;
        uniqueNames = "";

        // Collect unique team names
        for(int i = 0; i < teams.length; i++)
        {
            tempName = teams[i].getDriver();
            foundDuplicate = false;

            for(int j = 0; j < i; j++)
            {
                if(teams[j].getDriver().equals(tempName))
                {
                    foundDuplicate = true;
                }
            }
            
            if(!foundDuplicate)
            {
                counter++;
                uniqueNames += tempName + ";";
            }
        }

        // Initialize arrays for unique team names and their total times
        double[] teamTimes = new double[counter];
        String[] uniqueTeamNames = uniqueNames.split(";");

        // Calculate total times for each unique team
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            for(int j = 0; j < teams.length; j++)
            {
                if(uniqueTeamNames[i].equals(teams[j].getDriver()))
                {
                    teamTimes[i] += (teams[j].getLap());
                }
            }
        }

        lowToHighSort(teamTimes, uniqueTeamNames);

        System.out.println("\nSorted Drivers in ascending order");
        for (int i = 0; i < uniqueTeamNames.length; i++)
        {
            System.out.println(i + 1 + ". " + uniqueTeamNames[i] + ": " + teamTimes[i]);
        }
    }

    /*
    METHOD: lowToHighSort
    IMPORT: array (double[]), teamNames(String[])
    EXPORT: array (double[])
    PURPOSE: Sorts from low to high
    */
    public static double[] lowToHighSort(double[] array, String[] teamNames)
    {
        for(int pass = 0; pass < array.length - 1; pass++) // The outer loop (pass) controls the number of sorting passes (iterations). It runs array.length - 1 times.
        {
            for(int i = 0; i < array.length - 1 - pass; i++) // The inner loop (i) iterates through the array elements (except the last element in each pass to avoid out-of-bounds access).
            {
                if (array[i] > array[i + 1])
                {
                    // Swap times
                    double tempTime = array[i];
                    array[i] = array[i + 1]; // The next element (array[i + 1]) is overwritten with the value stored in tempTime (effectively swapping the values).
                    array[i + 1] = tempTime;
                    
                    // Swap corresponding team names
                    String tempName = teamNames[i];
                    teamNames[i] = teamNames[i + 1];
                    teamNames[i + 1] = tempName;
                }
            }
        }

        return array;
    }

    /*
    METHOD: highToLowSort
    IMPORT: array (double[]), teamNames(String[])
    EXPORT: array (double[])
    PURPOSE: Sorts from high to low
    */
    public static double[] highToLowSort(double[] array, String[] teamNames)
    {
        for(int pass = 0; pass < array.length - 1; pass++) // The outer loop (pass) controls the number of sorting passes (iterations). It runs array.length - 1 times.
        {
            for(int i = 0; i < array.length - 1 - pass; i++) // The inner loop (i) iterates through the array elements (except the last element in each pass to avoid out-of-bounds access).
            {
                if(array[i] < array[i + 1])
                {
                    // Swap times
                    double tempTime = array[i];
                    array[i] = array[i + 1]; // The next element (array[i + 1]) is overwritten with the value stored in tempTime (effectively swapping the values).
                    array[i + 1] = tempTime;
                    
                    // Swap corresponding team names
                    String tempName = teamNames[i];
                    teamNames[i] = teamNames[i + 1];
                    teamNames[i + 1] = tempName;
                }
            }
        }
        return array;
    }

    /*
    METHOD: withCompletion
    IMPORT: teams (Team[]), uniqueTeamNames (String[])
    EXPORT: teamsWithCompletion (String[])
    PURPOSE: Sorts which teams have at least 1 driver that finishes
    */
    public static String[] withCompletion(Team[] teams, String[] uniqueTeamNames)
    {
        int completionCount, index;
        String teamName;
        Team team;

        completionCount = 0;
        index = 0;

        for(int i = 0; i < uniqueTeamNames.length; i++) // The outer loop (i) iterates through each unique team name in uniqueTeamNames
        {
            teamName = uniqueTeamNames[i];

            for(int j = 0; j < teams.length; j++) // The inner loop (j) iterates through all teams in the teams array
            {
                team = teams[j]; // team is assigned the current Team object from the teams array
                if(team.getTeamName().equals(teamName) && team.getPosition() != 21)
                {
                    completionCount++;
                }
            }
        }
    
        String[] teamsWithCompletion = new String[completionCount]; // Creates a new string array named teamsWithCompletion with a size equal to the completionCount. This array will store the names of teams with at least one driver finishing

        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            teamName = uniqueTeamNames[i];

            for(int j = 0; j < teams.length; j++)
            {
                team = teams[j];
                if(team.getTeamName().equals(teamName) && team.getPosition() != 21)
                {
                    teamsWithCompletion[index++] = teamName;
                }
            }
        }
        return teamsWithCompletion;
    }
    
    /*
    METHOD: withoutCompletion
    IMPORT: teams (Team[]), uniqueTeamNames (String[])
    EXPORT: teamsWithCompletion (String[])
    PURPOSE: Sorts which teams have 0 drivers that finish
    */
    public static String[] withoutCompletion(Team[] teams, String[] uniqueTeamNames)
    {
        int noCompletionCount, index; // Counts teams where potentially no driver finishes, and tracks the index for storing team names in the output array
        String teamName;
        boolean hasCompletion;
        Team team;

        noCompletionCount = 0;
        index = 0;
    
        for(int i = 0; i < uniqueTeamNames.length; i++) // The outer loop (i) iterates through each unique team name
        {
            teamName = uniqueTeamNames[i];
    
            // Check if any driver from the team has finished the race
            hasCompletion = false;
            for(int j = 0; j < teams.length; j++) // The inner loop (j) iterates through all teams in the teams array
            {
                team = teams[j];
                if(team.getTeamName().equals(teamName) && team.getPosition() != 21)
                {
                    hasCompletion = true;
                }
            } // If hasCompletion remains false after checking all teams, it means no driver from this team finished, so noCompletionCount is incremented.
            
            // If no driver finished the race, increment the count
            if(!hasCompletion)
            {
                noCompletionCount++;
            }
        }
    
        // Create array to store teams without completion
        String[] teamsWithoutCompletion = new String[noCompletionCount];
    
        // Populate the array with team names without completion
        for(int i = 0; i < uniqueTeamNames.length; i++)
        {
            teamName = uniqueTeamNames[i];
    
            // Check if any driver from the team has finished the race
            hasCompletion = false;
            for(int j = 0; j < teams.length; j++)
            {
                team = teams[j];
                if(team.getTeamName().equals(teamName) && team.getPosition() != 21)
                {
                    hasCompletion = true;
                }
            }
            
            // If no driver finished the race, add the team to the array
            if(!hasCompletion)
            {
                teamsWithoutCompletion[index++] = teamName;
            }
        }
        return teamsWithoutCompletion;
    }
    
    /*
    METHOD: participation
    IMPORT: teams (Team[])
    EXPORT: check (boolean)
    PURPOSE: Checks if anyone in the team was placed 21st
    */
    public static boolean participation(Team[] teams)
    {
        boolean check = true; // Initialize check to true
        
        for(int i = 0; i < teams.length; i++)
        {
            Team team = teams[i];
            if(team.getPosition() == 21)
            {
                check = false; // If any team has position 21, set check to false
            }
        }
        // If check remains true, it implies all teams participated. If check was set to false during the loop (because a team had position 21), it indicates at least one team did not participate.
        
        return check;
    }
}