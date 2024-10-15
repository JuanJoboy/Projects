#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mapReader.h"

/*
    - 'file' is a double pointer because I want to modify the pointer to the file thats
       passed into the function.
    - 'rows' and 'cols' are pointers because I want to modify the values that are passed
*/
int openAndReadDimensions(char* filename, FILE** file, int* rows, int* cols)
{
    int fail = FALSE;

    *file = fopen(filename, "r"); /* Dereference file to assign the result of fopen() to the pointer passed in. This changes the actual FILE* in the calling function. */

    if(*file == NULL)
    {
        perror("Error opening file");
        fail = TRUE;
    }
    else if(fscanf(*file, "%d %d", rows, cols) != 2) /* Reads the rows and columns on the first line */
    {
        handleError("Couldn't read the rows and columns on the first line\n");
        fclose(*file);
        *file = NULL;
        fail = TRUE;
    }

    return fail;
}

/*
    - returns a int** because it's creating a 2D array.
    - 'rows' and 'cols' are passed in by value because I don't want to modify the values that are passed in.
*/
int** allocateData(int rows, int cols)
{
    int i;
    int allocationFailed = FALSE;
    int** data = NULL;

    data = (int**)malloc(rows * sizeof(int*));
    if(data != NULL)
    {
        for(i = 0; i < rows && !allocationFailed; i++)
        {
            data[i] = (int*)malloc(cols * sizeof(int));
            /* data[i] is a pointer to the i-th row, which is an array of cols elements. */
            /* malloc(cols * sizeof(int)) allocates memory for cols values, which is how much memory you need for each row */

            if(data[i] == NULL)
            {
                perror("Memory allocation failed");
                allocationFailed = TRUE;
            }
        }

        if(allocationFailed)
        {
            cleanupData(data, rows);
            data = NULL;
        }
    }
    else
    {
        perror("Memory allocation failed");
    }

    return data;
}

int readData(FILE* file, int** data, int rows, int cols)
{
    int i, j;
    int fail = FALSE;
    int walls, numberOfWalls = 0;

    /* Reads the second line to see how many walls there are */
    if(fscanf(file, "%d", &walls) != 1)
    {
        handleError("Can't find the wall count on the second line\n");
        fail = TRUE;
    }
    else
    {
        /* Initially, fail is FALSE, so !fail is 1 (TRUE), allowing the loop to proceed. */
        /* If there was an error reading the file, fail will be TRUE, so !fail will be 0 (FALSE), and the loop will exit since the for loop needs to be the opposite of FALSE. */
        for(i = 0; i < rows && !fail; i++)
        {
            for(j = 0; j < cols; j++)
            {
                if(fscanf(file, "%d", &data[i][j]) != 1) /* If there isn't a valid integer in any spot, then the game crashes */
                {
                    handleError("Invalid tile in map detected\n");
                    fail = TRUE;
                }
                else if(data[i][j] == 1)
                {
                    numberOfWalls++;
                }

                if(fail == TRUE) /* If there was an error, break out of the inner loop */
                {
                    j = cols;
                }
            }

            if(fail == TRUE)
            {
                i = rows;
            }
        }

        if(!fail && numberOfWalls != walls)
        {
            handleError("Number of actual walls in-game don't line up with the second line\n");
            printf("Number of walls in-game: %d\n", numberOfWalls);
            printf("Number of walls on the second line: %d\n", walls);
            fail = TRUE;
        }
    }

    return fail;
}

void writeToTerminal(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight)
{
    int j;

    system("clear"); /* Clears the terminal */

    /* Top Border */
    for(j = 0; j < cols + 2; j++)
    {
        printf("* ");
    }
    printf("\n");

    #ifdef DARK
        darkVision(data, rows, cols, xCoord, yCoord, sight);
    #else
        notDark(data, rows, cols, xCoord, yCoord);
    #endif
    
    /* Bottom Border */
    for(j = 0; j < cols + 2; j++)
    {
        printf("* ");
    }
    printf("\n");
}

void notDark(int** data, int rows, int cols, int* xCoord, int* yCoord)
{
    int i, j;

    for(i = 0; i < rows; i++)
    {
        printf("* "); /* Left border */
        for(j = 0; j < cols; j++)
        {
            switch(data[i][j])
            {
                case 0:
                    printf("  "); /* Empty space */
                    break;
                case 1:
                    printf("O "); /* Wall */
                    break;
                case 2:
                    printf("@ "); /* Lantern */
                    break;
                case 3:
                    printf("P "); /* Player */
                    break;
                case 4:
                    printf("~ "); /* Snake */
                    break;
                case 5:
                    printf("$ "); /* Treasure */
                    break;
                default:
                    printf("? "); /* Unknown */
                    break;
            }
        }
        printf("*\n"); /* Right Border */
    }
}

void darkVision(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight)
{
    int i, j;

    for(i = 0; i < rows; i++)
    {
        printf("* "); /* Left border */
        for(j = 0; j < cols; j++)
        {
            if(abs(i - *yCoord) + abs(j - *xCoord) <= sight)
            {
                switch(data[i][j])
                {
                    case 0:
                        printf(". "); /* Empty space */
                        break;
                    case 1:
                        printf("O "); /* Wall */
                        break;
                    case 2:
                        printf("@ "); /* Lantern */
                        break;
                    case 3:
                        printf("P "); /* Player */
                        break;
                    case 4:
                        printf("~ "); /* Snake */
                        break;
                    case 5:
                        printf("$ "); /* Treasure */
                        break;
                    default:
                        printf("? "); /* Unknown */
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

int playerFinder(int** data, int rows, int cols, int* xCoord, int* yCoord)
{
    int i, j;
    int found = FALSE;

    for(i = 0; i < rows && !found; i++)
    {
        for(j = 0; j < cols && !found; j++)
        {
            if(data[i][j] == 3)
            {
                *xCoord = j; /* j represents the x coordinate because x is the column */
                *yCoord = i;
                found = TRUE;
            }
        }
    }

    return found;
}

int snakeFinder(int** data, int rows, int cols, int* xCoord, int* yCoord)
{
    int i, j;
    int found = FALSE;

    for(i = 0; i < rows && !found; i++)
    {
        for(j = 0; j < cols && !found; j++)
        {
            if(data[i][j] == 4)
            {
                *xCoord = j; /* j represents the x coordinate because x is the column */
                *yCoord = i;
                found = TRUE;
            }
        }
    }

    return found;
}

void cleanupData(int** data, int rows)
{
    int i;

    if(data != NULL)
    {
        for(i = 0; i < rows; i++)
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

void handleError(char* message)
{
    fprintf(stderr, "%s\n", message);
}