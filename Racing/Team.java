import java.io.*;

/*
AUTHOR: Juan Joboy
STUDENT: 21837001
TITLE: Team
PURPOSE: Defines a Team class that represents a Formula One racing team
DATE: 15/05/2024
REQUIRES: None
*/
public class Team
{
    // Class Fields
    private String teamName;
    private String carCode;
    private String driver;
    private String prix;
    private int position;
    private double lap;

    /*
    Constructor with parameters
    IMPORT: pTeamName (String), pCarCode (String), pDriver (String), pPrix (String), pPosition (Integer), pLap (double)
    EXPORT: None
    PURPOSE: Object created with imported values
    */
    public Team(String pTeamName, String pCarCode, String pDriver, String pPrix, int pPosition, double pLap)
    {
        this.teamName = pTeamName;
        this.carCode = pCarCode;
        this.driver = pDriver;
        this.prix = pPrix;
        this.position = pPosition;
        this.lap = pLap;
    }

    /*
    Copy Constructor
    IMPORT: pTeam (Team)
    EXPORT: None
    PURPOSE: Object created with copied values from pTeam
    */
    public Team(Team pTeam)
    {
        this.teamName = new String(pTeam.getTeamName());
        this.carCode = new String (pTeam.getCarCode());
        this.driver = new String(pTeam.getDriver());
        this.prix = new String(pTeam.getPrix());
        this.position = pTeam.getPosition();
        this.lap = pTeam.getLap();
    }

    /*
    Default Constructor
    IMPORT: None
    EXPORT: None
    PURPOSE: Object created with default values
    */
    public Team()
    {
        this.teamName = "Red Bull Racing";
        this.carCode = "RB20";
        this.driver = "Max Verstappen";
        this.prix = "Monaco";
        this.position = 1;
        this.lap = 75.915;
    }

    // Accessors (getters)
    // These methods allow other parts of my program to retrieve the values of the private variables
    /*
    ACCESSOR: getTeamName
    IMPORT: None
    EXPORT: teamName (String)
    PURPOSE: Returns the team name
    */
    public String getTeamName()
    {
        return teamName;
    }

    /*
    ACCESSOR: getCarCode
    IMPORT: None
    EXPORT: carCode (String)
    PURPOSE: Returns the car code
    */
    public String getCarCode()
    {
        return carCode;
    }

    /*
    ACCESSOR: getDriver
    IMPORT: None
    EXPORT: driver (String)
    PURPOSE: Returns the driver names
    */
    public String getDriver()
    {
        return driver;
    }

    /*
    ACCESSOR: getPrix
    IMPORT: None
    EXPORT: prix (String)
    PURPOSE: Returns the Grand Prix name
    */
    public String getPrix()
    {
        return prix;
    }

    /*
    ACCESSOR: getPosition
    IMPORT: None
    EXPORT: position (Integer)
    PURPOSE: Returns the finishing position
    */
    public int getPosition()
    {
        return position;
    }

    /*
    ACCESSOR: getLap
    IMPORT: None
    EXPORT: lap (double)
    PURPOSE: Returns the fastest lap time
    */
    public double getLap()
    {
        return lap;
    }

    // Mutators (setters)
    // These methods allow other parts of my program to modify the values of the private variables
    /*
    MUTATOR: setTeamName
    IMPORT: pTeamName (String)
    EXPORT: None
    PURPOSE: State of teamName is updated to pTeamName value
    */
    public void setTeamName(String pTeamName)
    {
        teamName = pTeamName;
    }

    /*
    MUTATOR: setCarCode
    IMPORT: pCarCode (String)
    EXPORT: None
    PURPOSE: State of carCode is updated to pCarCode value
    */
    public void setCarCode(String pCarCode)
    {
        carCode = pCarCode;
    }

    /*
    MUTATOR: setDriver
    IMPORT: pDriver (String)
    EXPORT: None
    PURPOSE: State of driver is updated to pDriver value
    */
    public void setDriver(String pDriver)
    {
        driver = pDriver;
    }

    /*
    MUTATOR: setPrix
    IMPORT: pPrix (String)
    EXPORT: None
    PURPOSE: State of prix is updated to pPrix value
    */
    public void setPrix(String pPrix)
    {
        prix = pPrix;
    }

    /*
    MUTATOR: setPosition
    IMPORT: pPosition (Integer)
    EXPORT: None
    PURPOSE: State of position is updated to pPosition value
    */
    public void setPosition(int pPosition)
    {
        position = pPosition;
    }

    /*
    MUTATOR: setLap
    IMPORT: pLap (double)
    EXPORT: None
    PURPOSE: State of lap is updated to pLap value
    */
    public void setLap(double pLap)
    {
        lap = pLap;
    }

    /*
    ACCESSOR: toString
    IMPORT: None
    EXPORT: teamString (String)
    PURPOSE: Provides a more meaningful string representation of a Team object. When I call toString on a Team object, it will return a human-readable string that includes all the team's details: name, car code, driver(s), Grand Prix location, finishing position, and fastest lap time.
    */
    @Override
    public String toString()
    {
        String teamString;
        teamString = "Team Name is: " + teamName + "\nCar Code is: " + carCode + "\nDriver is: " + driver + "\nGrand Prix Location is: " + prix + "\nPosition is: " + position + "\nLap Time is: " + lap;
        return teamString;
    }

    /*
    ACCESSOR: equals
    IMPORT: inObject (Object)
    EXPORT: isEqual (boolean)
    PURPOSE: Returns true if the two objects are equivalent
    */
    @Override
    public boolean equals(Object inObject)
    {
        boolean isEqual = false; 
        Team inTeam = null;
        if(inObject instanceof Team)
        {
            inTeam = (Team)inObject;
            if(teamName.equals(inTeam.getTeamName()))
                if(carCode.equals(inTeam.getCarCode()))
                    if(driver.equals(inTeam.getDriver()))
                        if(prix.equals(inTeam.getPrix()))
                            if(position == inTeam.getPosition())
                                if(lap == inTeam.getLap())
                                isEqual = true;
        }
        return isEqual;
    }

    /*
    ACCESSOR: writeToFile
    IMPORT: pfilename (String), pTeam (Team[])
    EXPORT: None (technically exports a csv)
    PURPOSE: Writes the team data from the pTeam array to a CSV file named according to the provided file name
    */
    public static void writeToFile(String pFilename, Team[] pTeam)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try
        {
            fileStrm = new FileOutputStream(pFilename);
            pw = new PrintWriter(fileStrm);
            pw.println("TeamName, CarCode, Driver, GrandPrix, PositionFinished, FastestLap");
            for (int i = 0; i < pTeam.length; i++)
            {
                pw.println(pTeam[i].getTeamName() + "," + pTeam[i].getCarCode() + "," + pTeam[i].getDriver() + "," + pTeam[i].getPrix() + "," + pTeam[i].getPosition() + "," + pTeam[i].getLap());
            }
            pw.close();                     
        }
        catch(IOException error)
        {             
            System.out.println("Error in writing to file: " + error.getMessage());
        }
    }
}

