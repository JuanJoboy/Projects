#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "random.h"
#include "mapReader.h"
#include "gameHandler.h"
#include "playerMovement.h"
#include "snakeMovement.h"
#include "mapWriter.h"

int main()
{
    FILE *file = NULL;
    int fail = FALSE;
    int rows = 0;
    int cols = 0;
    int **data = NULL;
    Player players[MAX_PLAYERS]; // Array of however many players there are instead of having a million individual pointers to thingy's for each player
    Snake snakes[MAX_SNAKES];

    initRandom();
    
    char *filename = mapName(); // Get the filename from the user
    writeMap(filename); // Write the map to a file

    /* Open the file and read the first line to see how big the rows and columns are */
    if(openMap(filename, &file, &rows, &cols) == FALSE)
    {
        /* Allocate the map's 2d array info into the data variable */
        data = allocateMapMemory(rows, cols);

        if(data != NULL)
        {
            readMap(file, data, rows, cols);

            initializePlayer1(&players[0], rows, cols, data); // Can't use a for loop since the players have different icons (3 and 7) but the snakes are for-looped since they all have the same icon (4)
            initializePlayer2(&players[1], rows, cols, data);
            
            for(int i = 0; i < MAX_SNAKES; i++)
            {
                initializeSnake(&snakes[i], rows, cols, data);
            }
            
            loadingScreen();
            runGame(players, snakes); /* Enters the game loop */
            cleanupData(data, rows);
        }
        else
        {
            fail = TRUE;
        }
    }
    else
    {
        fail = TRUE;
    }

    if(file != NULL)
    {
        fclose(file);
    }

    free(filename); // Free the filename allocated by mapName
    return fail;
}