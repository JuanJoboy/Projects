#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mapWriter.h"
#include "mapReader.h"
#include "gameHandler.h"
#include"random.h"

int minInput = 7;
int maxInput = 41;

char* mapName()
{
    char* filename = (char*)malloc(100 * sizeof(char));

    if(filename == NULL)
    {
        perror("Memory allocation failed");
        return NULL;
    }
    
    do
    {
        printf("\n\nEnter the name of the file to write to: ");
        scanf("%s", filename);
    }
    while(filename == NULL || strlen(filename) == 0);

    filename = strcat(filename, ".txt");

    return filename;
}

int rowDim()
{
    int rows;
    int valid;

    do
    {
        printf("Enter the number of rows (8 - 40): ");
        valid = scanf("%d", &rows);
        
        if(valid != 1)
        {
            while(getchar() != '\n');
            printf("Error: Please enter a valid number\n");
        }
        
        if(rows <= minInput && valid == 1)
        {
            printf("Invalid input. Please enter a number above %d\n", minInput);
        }
        if(rows >= maxInput && valid == 1)
        {
            printf("Invalid input. Please enter a number below %d\n", maxInput);
        }
    }
    while(valid != 1 || rows <= minInput || rows >= maxInput);

    return rows;
}

int colDim()
{
    int cols;
    int valid;

    do
    {
        printf("Enter the number of columns (8 - 40): ");
        valid = scanf("%d", &cols);
        
        if(valid != 1)
        {
            while(getchar() != '\n');
            printf("Error: Please enter a valid number\n");
        }
        
        if(cols <= minInput && valid == 1)
        {
            printf("Invalid input. Please enter a number above %d\n", minInput);
        }
        if(cols >= maxInput && valid == 1)
        {
            printf("Invalid input. Please enter a number below %d\n", maxInput);
        }
    }
    while(valid != 1 || cols <= minInput || cols >= maxInput);

    return cols;
}

void writeMap(char* filename)
{
    int rows = rowDim();
    int cols = colDim();
    int **data = allocateMapMemory(rows, cols);
    int playerCounter = 0;
    int snakeCounter = 0;
    int treasureCounter = 0;
    int flashlightCounter = 0;
    int ambulanceCounter = 0;
    int shieldCounter = 0;
    int cardCounter = 0;

    FILE *file = fopen(filename, "w");
    fprintf(file, "%d %d\n", rows, cols); /* Writes the first line to the file with the rows and columns */

    // Initialize the map with 0s
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            data[i][j] = 0;
        }
    }

    // Place 2 players
    while(playerCounter < MAX_PLAYERS)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            if(playerCounter == 0)
            {
                data[i][j] = 3; // Player 1
            }
            if(playerCounter == 1)
            {
                data[i][j] = 7; // Player 2
            }
            playerCounter++;
        }
    }

    // Place 5 snakes
    while(snakeCounter < MAX_SNAKES)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 4;
            snakeCounter++;
        }
    }
    
    // Place 1 treasure
    while(treasureCounter < 1)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 5;
            treasureCounter++;
        }
    }

    // Place 2 flashlights
    while(flashlightCounter < 2)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 2;
            flashlightCounter++;
        }
    }

    // Place 1 ambulance
    while(ambulanceCounter < 1)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 8;
            ambulanceCounter++;
        }
    }

    // Place 2 shield
    while(shieldCounter < 2)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 9;
            shieldCounter++;
        }
    }

    // Place 1 card
    while(cardCounter < 1)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 6;
            cardCounter++;
        }
    }

    // Fill remaining spaces randomly
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if(data[i][j] == 0)
            {
                int val = randomUCP(0, 9);

                if(val == 3 || val == 7 || val == 4 || val == 5 || val == 2 || val == 8 || val == 9 || val == 6)
                {
                    val = randomUCP(0, 100);

                    if(val < 50)
                    {
                        val = 1; // 50% chance to be wall
                    }
                    else
                    {
                        val = 0; // otherwise, path
                    }
                }

                // /* Check if the current cell is surrounded by walls */ PS: Doesnt really do too much
                // if(isOverWalled(data, i, j, rows, cols))
                // {
                //     val = 0; // ensure a path
                // }

                data[i][j] = val;
            }
        }
    }

    // Write map data
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            fprintf(file, "%d ", data[i][j]);
        }
        fprintf(file, "\n");
    }
    
    fclose(file);
    cleanupData(data, rows);
}

int isOverWalled(int** data, int i, int j, int rows, int cols)
{
    int wallCount = 0;
    int directions[8][2] =
    {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},  // N, S, W, E
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // NW, NE, SW, SE
    };

    for(int d = 0; d < 8; d++)
    {
        int ni = i + directions[d][0];
        int nj = j + directions[d][1];

        if(ni >= 0 && ni < rows && nj >= 0 && nj < cols)
        {
            if(data[ni][nj] == 1)
            {
                wallCount++;
            }
        }
    }

    return wallCount >= 6; // too many walls around
}