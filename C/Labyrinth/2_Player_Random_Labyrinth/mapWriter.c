#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "mapWriter.h"
#include "mapReader.h"
#include "gameHandler.h"
#include"random.h"

int minInput = 8;
int maxInputHeight = 40;
int maxInputWidth = 57;
int rows;
int cols;

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

int tempOrPerm()
{
    int valid = 0;
    int choice = 0;

    do
    {
        printf("Do you want to create a temporary or permanent map?\n");
        printf("\t(1) Temporary\n");
        printf("\t(2) Permanent\n");
        printf("Enter your choice: ");
        valid = scanf("%d", &choice);
        
        if(valid != 1)
        {
            while(getchar() != '\n');
            printf("Error: Please enter a valid number\n");
        }
        
        if(choice < 1 && valid == 1)
        {
            printf("Invalid input. Please enter a number above 0\n");
        }
        if(choice > 2 && valid == 1)
        {
            printf("Invalid input. Please enter a number below 3\n");
        }
    }
    while(valid != 1 || choice < 1 || choice > 2);

    return choice;
}

int rowDim()
{
    int valid;

    do
    {
        printf("Enter the number of rows (%d - %d): ", minInput, maxInputHeight);
        valid = scanf("%d", &rows);
        
        if(valid != 1)
        {
            while(getchar() != '\n');
            printf("Error: Please enter a valid number\n");
        }
        
        if(rows < minInput && valid == 1)
        {
            printf("Invalid input. Please enter a number above %d\n", minInput);
        }
        if(rows > maxInputHeight && valid == 1)
        {
            printf("Invalid input. Please enter a number below %d\n", maxInputHeight);
        }
    }
    while(valid != 1 || rows < minInput || rows > maxInputHeight);

    return rows;
}

int colDim()
{
    int valid;

    do
    {
        printf("Enter the number of columns (%d - %d): ", minInput, maxInputWidth);
        valid = scanf("%d", &cols);
        
        if(valid != 1)
        {
            while(getchar() != '\n');
            printf("Error: Please enter a valid number\n");
        }
        
        if(cols < minInput && valid == 1)
        {
            printf("Invalid input. Please enter a number above %d\n", minInput);
        }
        if(cols > maxInputWidth && valid == 1)
        {
            printf("Invalid input. Please enter a number below %d\n", maxInputWidth);
        }
    }
    while(valid != 1 || cols < minInput || cols > maxInputWidth);

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

    int area = (rows / 2) * (cols / 2);
    int flashScaler = (int) ceil((area / 50) + 1);
    int shieldScaler = (int) ceil((area / 50));
    int cardScaler = (int) ceil((area / 50) - 1);

    // Clamp values to min/max bounds
    if(flashScaler > 8) flashScaler = 8;
    if(shieldScaler > 6) shieldScaler = 6;
    if(cardScaler > 5) cardScaler = 5;

    if(flashScaler < 2) flashScaler = 2;
    if(shieldScaler < 2) shieldScaler = 2;
    if(cardScaler < 1) cardScaler = 1;

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

    // Place 5 flashlights
    while(flashlightCounter < flashScaler)
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

    // Place 4 shields
    while(shieldCounter < shieldScaler)
    {
        int i = randomUCP(0, rows-1);
        int j = randomUCP(0, cols-1);

        if(data[i][j] == 0)
        {
            data[i][j] = 9;
            shieldCounter++;
        }
    }

    // Place 3 cards
    while(cardCounter < cardScaler)
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

                // /* Check if the current cell is surrounded by walls */
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