import java.util.*;

public class Numerology
{
    public static void main(String[] args)
    {
        String choice;
        boolean close = false;

        while(!close)
        {
            choice = menu();
            switch(choice)
            {
                case "1":
                    //birthday(1);
                    birthday(1);
                    break;
                case "2":
                    //birthday(2);
                    birthday(2);
                    break;
                case "0":
                    System.out.println("Thanks for using the Numerology Analysis.");
                    System.out.println("Goodbye...");
                    close = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }
    }

    private static String menu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice;
        
        do
        {
            System.out.println("Would you like to enter 1 birthday or compare 2 birthdays?");
            System.out.println("\t> 1. Solo Birthday Analysis");
            System.out.println("\t> 2. Duo Birthday Analysis");
            System.out.println("\t> 0. Exit");
        
            choice = scanner.nextLine();
        
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("0"))
            {
                System.out.println("Please enter either 1, 2 or 0.\n");
            }
        } while(!choice.equals("1") && !choice.equals("2") && !choice.equals("0"));
        
        return choice;
    }

    private static void birthday(int numBirth)
    {
        int[] days = new int[numBirth];
        int[] months = new int[numBirth];
        int[] years = new int[numBirth];
        
        for(int i = 0; i < numBirth; i++)
        {
            int day, month, year;
        
            day = dayInput();
            month = monthInput();
            year = yearInput();  

            days[i] = day;
            months[i] = month;
            years[i] = year;
            display(day, month, year, lifePathNumber(day, month, year));
        }
        
        compareBirthdays(days, months, years);
        System.out.println(); // A blank line just for aesthetics
    }
    
    private static void compareBirthdays(int[] days, int[] months, int[] years)
    {
        for(int i = 0; i < days.length - 1; i++) // days.length acts as the numBirth limit. It could be months.length or years.length too since they all take in the amount of numBirth. Stops one element short because the inner loop will handle comparisons, and comparing an element with itself wouldn't be useful
        {
            for (int j = i + 1; j < days.length; j++) // j = i + 1, so that it doesn't compare the same birthday with itself
            {
                if(lifePathNumber(days[i], months[i], years[i]) == lifePathNumber(days[j], months[j], years[j]))
                {
                    System.out.println("Birthdays " + (i + 1) + " and " + (j + 1) + " have the same life path number!");
                }
                
                else
                {
                    System.out.println("Birthdays " + (i + 1) + " and " + (j + 1) + " do not have the same life path number :(");
                }
            }
        }
    }
          

    private static int dayInput()
    {
        Scanner scanner = new Scanner(System.in);
        int day;

        do
        {
            try
            {
                System.out.println("Enter the birthday in the format DD/MM/YYYY");
                System.out.println("Enter the day");
                day = scanner.nextInt();
            }
                
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input, please enter a valid number\n");
                scanner.nextLine();
                day = dayInput();
            }
        } while(!isValidDay(day, 0, 0));

        return day;
    }

    private static int monthInput()
    {
        Scanner scanner = new Scanner(System.in);
        int month;
        
        do
        {
            try
            {
                System.out.println("Enter the month");
                month = scanner.nextInt();
            }
            
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input, please enter a valid number\n");
                scanner.nextLine();
                month = monthInput();
            }
        } while(!isValidMonth(month));

        return month;
    }

    private static int yearInput()
    {
        Scanner scanner = new Scanner(System.in);
        int year;

        do
        {
            try
            {
                System.out.println("Enter the year (1901 - 2024)");
                year = scanner.nextInt();
            }
            
            catch(InputMismatchException error)
            {
                System.out.println("Invalid input, please enter a valid number\n");
                scanner.nextLine();
                year = yearInput();
            }
        } while(!isValidYear(year));

        return year;
    }

    private static void display(int day, int month, int year, int sum)
    {
        System.out.println("Your birthday is: " + day + "/" + month + "/" + year);
        System.out.println("Your life path number is: " + sum);
        System.out.println("Your lucky colour is: " + luckyColour(sum));
        generationDeterminer(year);
        System.out.println(); // Empty line for prettier formatting
    }

    private static boolean isLeapYear(int year)
    {
        boolean validLeapYear = true;

        // Check for divisibility by 4 but not 100
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
        {
            validLeapYear = true;
        }

        else
        {
            validLeapYear = false;
        }

        return validLeapYear;
    }

    private static boolean isValidDay(int day, int month, int year)
    {
        boolean validDay = true;
        
        if (day >= 1 && day <= 31)
        {
            // Check for February with special logic for leap years
            if(month == 2)
            {
                if(isLeapYear(year) && day <= 29)
                {
                    validDay = true;
                }
                
                else if(!isLeapYear(year) && day <= 28)
                {
                    validDay = true;
                }
                
                else
                {
                    validDay = false;
                }
            }
            
            else
            {
                switch (month)
                {
                    case 4: case 6: case 9: case 11:
                    validDay = day <= 30;  // Months with 30 days
                    break;
                    case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    validDay = day <= 31;  // Months with 31 days
                    break;
                }
            }
        }
        
        else
        {
            validDay = false;
        }
        
        return validDay;
    }

    private static boolean isValidMonth(int month)
    {
        boolean validMonth = true;

        if (month >= 1 && month <= 12)
        {
            validMonth = true;
        }

        else
        {
            validMonth = false;
        }

        return validMonth;
    }
    
    private static boolean isValidYear(int year)
    {
        boolean validYear = true;

        if (year >= 1901 && year <= 2024)
        {
            validYear = true;
        }

        else
        {
            validYear = false;
        }

        return validYear;
    }

    private static void generationDeterminer(int year)
    {

        if(year >= 2010 && year <= 2024)
        {
            System.out.println("You belong to Generation Alpha.");
        }

        else if(year >= 1995 && year <= 2009)
        {
            System.out.println("You belong to Generation Z.");
        }

        else if(year >= 1980 && year <= 1994)
        {
            System.out.println("You are a Millennial.");
        }

        else if(year >= 1965 && year <= 1979)
        {
            System.out.println("You belong to Generation X.");
        }

        else if(year >= 1946 && year <= 1964)
        {
            System.out.println("You are a Baby Boomer.");
        }

        else if(year >= 1901 && year <= 1945)
        {
            System.out.println("You belong to the Silent Generation.");
        }

        else
        {
            System.out.println("Your year does not fall within the range of 1901 - 2024.");
            System.out.println("Your generation is unknown.");
        }
    }

    private static boolean masterNumber(int number)
    {
        boolean masterNumber = true;

        if(number == 11 || number == 22 || number == 33)
        {
            masterNumber = true;
        }

        else
        {
            masterNumber = false;
        }

        return masterNumber;
    }

    public static int dayAddition(int day)
    {
        while(day >= 10 && !masterNumber(day))
        {
            day = (day / 10) + (day % 10); // Gets the first then second digit and adds them
        }
        
        return day;
    }

    public static int monthAddition(int month)
    {
        while(month >= 10 && !masterNumber(month))
        {
            month = (month / 10) + (month % 10); // Gets the first then second digit and adds them
        }

        return month;
    }

    public static int yearAddition(int year)
    {
        int yearSum;

        yearSum = 0;
        
        for (int i = 0; i < 4; i++) // Iterates 4 times for the 4 digits in a year
        {
            yearSum = yearSum + year % 10; // Extracts the last digit and adds it to the sum (7 = 0 + 1987 % 10)
            year = year / 10; // Chops off the last digit from the year to prepare for the next iteration (198 = 1987 / 10)
        }
        
        while (yearSum >= 10 && !masterNumber(yearSum))
        {
            yearSum = (yearSum % 10) + (yearSum / 10); // Gets the first then second digit and adds them (7 = (25 % 10) + (25 / 10))
        }

        return yearSum;
    }
      
    private static int lifePathNumber(int day, int month, int year)
    {
        int birthdaySum;
        
        birthdaySum = dayAddition(day)+ monthAddition(month) + yearAddition(year);
        
        while (birthdaySum >= 10 && !masterNumber(birthdaySum))
        {
            birthdaySum = (birthdaySum % 10) + (birthdaySum / 10); // Gets the first then second digit and adds them (7 = (25 % 10) + (25 / 10))
        }
        
        return birthdaySum;
    }
      
    private static String luckyColour(int birthdaySum)
    {
        String colour;
    
        switch (birthdaySum)
        {
            case 1:
                colour = "Red";
                break;
            case 2:
                colour = "Orange";
                break;
            case 3:
                colour = "Yellow";
                break;
            case 4:
                colour = "Green";
                break;
            case 5:
                colour = "Sky Blue";
                break;
            case 6:
                colour = "Indigo";
                break;
            case 7:
                colour = "Violet";
                break;
            case 8:
                colour = "Magenta";
                break;
            case 9:
                colour = "Gold";
                break;
            case 11:
                colour = "Silver";
                break;
            case 22:
                colour = "White";
                break;
            case 33:
                colour = "Crimson";
                break;
            default:
                colour = "Unknown Colour";
                break;
        }
        return colour;
    }
}