#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mapReader.h"
#include "gameHandler.h"

/* Rows and cols are pointers because I want to modify the values that are passed in from the main (0 and 0) */
int openMap(char *filename, FILE **file, int *rows, int *cols)
{
    int fail = FALSE;

    *file = fopen(filename, "r");

    if(*file == NULL)
    {
        perror("Error opening file");
        fail = TRUE;
    }
    else
    {
        fscanf(*file, "%d %d", rows, cols); /* Reads the first line to see the rows and columns */
    }

    return fail;
}

/*
    - Returns a int** because it's creating a 2D array.
    - Rows and cols are passed in by value because I don't want to modify the values that are passed in.
*/
int** allocateMapMemory(int rows, int cols)
{
    int allocationFailed = FALSE;
    int **data = NULL;

    data = (int**)malloc(rows * sizeof(int*));

    if(data != NULL)
    {
        for(int i = 0; i < rows && !allocationFailed; i++)
        {
            data[i] = (int*)malloc(cols * sizeof(int));

            if(data[i] == NULL)
            {
                errorMessage("Col memory allocation failed");
                allocationFailed = TRUE;
            }
        }

        if(allocationFailed)
        {
            cleanupData(data, rows); /* Cleanup any failed memory immediately */
            data = NULL;
        }
    }
    else
    {
        errorMessage("Row memory allocation failed");
    }

    return data;
}

void readMap(FILE *file, int **data, int rows, int cols)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            fscanf(file, "%d", &data[i][j]); /* Reads the actual map data at each tile*/
        }
    }
}

void writeToTerminal(int **data, int rows, int cols, int *xCoord1, int *yCoord1, int *xCoord2, int *yCoord2, int sight, int sight2)
{
    system("clear"); /* Clears the terminal */

    /* Top Border */
    for(int i = 0; i < cols + 2; i++)
    {
        printf("* ");
    }
    printf("\n");

    #ifdef DARK
        darkVision(data, rows, cols, xCoord1, yCoord1, xCoord2, yCoord2, sight, sight2);
    #else
        notDark(data, rows, cols, xCoord1, yCoord1);
    #endif
    
    /* Bottom Border */
    for(int i = 0; i < cols + 2; i++)
    {
        printf("* ");
    }
    printf("\n");
}

void notDark(int **data, int rows, int cols, int *xCoord, int *yCoord)
{
    for(int i = 0; i < rows; i++)
    {
        printf("* "); /* Left border */
        for(int j = 0; j < cols; j++)
        {
            switch(data[i][j])
            {
                case 0:
                    printf("  "); /* Empty space */
                    break;
                case 1:
                    printf("▧ "); /* Wall */
                    break;
                case 2:
                    printf("🔦"); /* Flashlight */
                    break;
                case 3:
                    printf("1️⃣ "); /* Player */
                    break;
                case 7:
                    printf("2️⃣ "); /* Player */
                    break;
                case 4:
                    printf("🐍"); /* Snake */
                    break;
                case 5:
                    printf("💰"); /* Treasure */
                    break;
                case 6:
                    printf("❤️ "); /* Heart */
                    break;
                case 8:
                    printf("🚑"); /* Ambulance */
                    break;
                case 9:
                    printf("🛡️ "); /* Shield */
                    break;
            }
        }
        printf("*\n"); /* Right Border */
    }
}

void darkVision(int **data, int rows, int cols, int *xCoord1, int *yCoord1, int *xCoord2, int *yCoord2, int sight, int sight2)
{
    for(int i = 0; i < rows; i++)
    {
        printf("* "); /* Left border */
        for(int j = 0; j < cols; j++)
        {
            if((abs(i - *yCoord1) + abs(j - *xCoord1) <= sight) || (abs(i - *yCoord2) + abs(j - *xCoord2) <= sight2))
            {
                switch(data[i][j])
                {
                    case 0:
                        printf("  "); /* Empty space */
                        break;
                    case 1:
                        printf("▧ "); /* Wall */
                        break;
                    case 2:
                        printf("🔦"); /* Flashlight */
                        break;
                    case 3:
                        printf("1️⃣ "); /* Player */
                        break;
                    case 7:
                        printf("2️⃣ "); /* Player */
                        break;
                    case 4:
                        printf("🐍"); /* Snake */
                        break;
                    case 5:
                        printf("💰"); /* Treasure */
                        break;
                    case 6:
                        printf("❤️ "); /* Heart */
                        break;
                    case 8:
                        printf("🚑 "); /* Ambulance */
                        break;
                    case 9:
                        printf("🛡️ "); /* Shield */
                        break;
                }
            }
            else
            {
                printf("  "); /* Anything outside the visible area is hidden */
            }
        }
        printf("*\n"); /* Right Border */
    }
}

void player1Finder(int **data, int rows, int cols, int *xCoord, int *yCoord)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(data[i][j] == 3)
            {
                *xCoord = j; /* j represents the x coordinate because x is the column */
                *yCoord = i;
            }
        }
    }
}

void player2Finder(int **data, int rows, int cols, int *xCoord, int *yCoord)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(data[i][j] == 7)
            {
                *xCoord = j; /* j represents the x coordinate because x is the column */
                *yCoord = i;
            }
        }
    }
}

void ambulanceFinder(int **data, int rows, int cols, int *ambulanceXCoord, int *ambulanceYCoord)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(data[i][j] == 8)
            {
                *ambulanceXCoord = j;
                *ambulanceYCoord = i;
                return;
            }
        }
    }
}

void snakeFinder(int **data, int rows, int cols, int *xCoord, int *yCoord)
{
    static int foundSnakes[MAX_SNAKES][2] = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}}; // Array to store found snake positions because all other implementations weren't properly tracking every snake. Static is used to keep the array values between function calls. Otherwise, the snake would keep getting overwritten.
    static int currentSnake = 0;
    
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(data[i][j] == 4)
            {
                // Check if this position was already assigned
                int alreadyFound = 0;
                for(int k = 0; k < currentSnake; k++)
                {
                    if(foundSnakes[k][0] == j && foundSnakes[k][1] == i)
                    {
                        alreadyFound = 1;
                        break;
                    }
                }
                
                if(!alreadyFound && currentSnake < MAX_SNAKES)
                {
                    foundSnakes[currentSnake][0] = j;
                    foundSnakes[currentSnake][1] = i;
                    *xCoord = j;
                    *yCoord = i;
                    currentSnake++;
                    return;
                }
            }
        }
    }
}

void cleanupData(int **data, int rows)
{
    if(data != NULL)
    {
        for(int i = 0; i < rows; i++)
        {
            if(data[i] != NULL)
            {
                free(data[i]);
                data[i] = NULL;
            }
        }
        free(data);
    }
}

void errorMessage(char *message)
{
    fprintf(stderr, "%s\n", message);
}