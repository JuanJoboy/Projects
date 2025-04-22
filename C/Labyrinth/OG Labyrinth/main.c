#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "random.h"
#include "mapReader.h"
#include "gameHandler.h"
#include "playerMovement.h"
#include "snakeMovement.h"

int main(int argc, char *argv[])
{
    FILE *file = NULL;
    int rows = 0, cols = 0;
    int fail = FALSE;
    int **data = NULL;
    Player *playerState = NULL;
    Snake *snakeState = NULL;

    if(argc != 2)
    {
        errorMessage("\nUsage: ./treasure <map_file.txt>\n"); 
        fail = TRUE;
    }
    else
    {
        initRandom();

        playerState = (Player*)malloc(sizeof(Player));
        snakeState = (Snake*)malloc(sizeof(Snake));

        if(playerState != NULL && snakeState != NULL)
        {
            /* Open the file and read the first line to see how big the rows and columns are */
            if(openMap(argv[1], &file, &rows, &cols) == FALSE)
            {
                /* Allocate the map's 2d array info into the data variable */
                data = allocateMapMemory(rows, cols);

                if(data != NULL)
                {
                    readMap(file, data, rows, cols);

                    initializePlayer(playerState, rows, cols, data);
                    initializeSnake(snakeState, rows, cols, data);
                    
                    runGame(playerState, snakeState); /* Enters the game loop */
                    
                    free(playerState);
                    playerState = NULL;
                    free(snakeState);
                    snakeState = NULL;
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
        }
        else
        {
            errorMessage("Memory allocation for player or snake failed in the main().");
            fail = TRUE;
        }
    }

    if(file != NULL)
    {
        fclose(file);
    }

    return fail;
}