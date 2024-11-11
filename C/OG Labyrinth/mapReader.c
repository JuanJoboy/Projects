#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mapReader.h"

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
    int i;
    int allocationFailed = FALSE;
    int **data = NULL;

    data = (int**)malloc(rows * sizeof(int*));

    if(data != NULL)
    {
        for(i = 0; i < rows && !allocationFailed; i++)
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
    int i, j;
    int walls;
    
    fscanf(file, "%d", &walls); /* Reads the second line to see how many walls there are */

    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            fscanf(file, "%d", &data[i][j]); /* Reads the actual map data at each tile*/
        }
    }
}

void writeToTerminal(int **data, int rows, int cols, int *xCoord, int *yCoord, int sight)
{
    int i;

    system("clear"); /* Clears the terminal */

    /* Top Border */
    for(i = 0; i < cols + 2; i++)
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
    for(i = 0; i < cols + 2; i++)
    {
        printf("* ");
    }
    printf("\n");
}

void notDark(int **data, int rows, int cols, int *xCoord, int *yCoord)
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
            }
        }
        printf("*\n"); /* Right Border */
    }
}

void darkVision(int **data, int rows, int cols, int *xCoord, int *yCoord, int sight)
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

void playerFinder(int **data, int rows, int cols, int *xCoord, int *yCoord)
{
    int i, j;

    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            if(data[i][j] == 3)
            {
                *xCoord = j; /* j represents the x coordinate because x is the column */
                *yCoord = i;
            }
        }
    }
}

void snakeFinder(int **data, int rows, int cols, int*xCoord, int*yCoord)
{
    int i, j;

    for(i = 0; i < rows; i++)
    {
        for(j = 0; j < cols; j++)
        {
            if(data[i][j] == 4)
            {
                *xCoord = j;
                *yCoord = i;
            }
        }
    }
}

void cleanupData(int **data, int rows)
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

void errorMessage(char *message)
{
    fprintf(stderr, "%s\n", message);
}