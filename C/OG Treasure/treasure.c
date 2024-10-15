#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "random.h"
#include "mapReader.h"
#include "gameHandler.h"
#include "playerMovement.h"
#include "snakeMovement.h"

int main(int argc, char* argv[])
{
    FILE* file = NULL;
    int rows = 0, cols = 0;
    int fail = FALSE;
    int** data = NULL;
    Player *playerState = NULL;
    Snake *snakeState = NULL;

    if(argc != 2)
    {
        handleError("\nUsage: ./treasure <map_file.txt>\n"); 
        fail = TRUE;
    }
    else
    {
        initRandom();

        playerState = (Player*)malloc(sizeof(Player));
        snakeState = (Snake*)malloc(sizeof(Snake));
        if(playerState == NULL || snakeState == NULL)
        {
            handleError("Memory allocation for player or snake failed.");
            fail = TRUE;
        }
        else
        {
            /* Equivalent to: if (openAndReadDimensions(argv[1], &file, &rows, &cols) == FALSE) */
            if((!openAndReadDimensions(argv[1], &file, &rows, &cols)) && (playerState != NULL && snakeState != NULL))
            {
                data = allocateData(rows, cols);
                if(data != NULL)
                {
                    if(!readData(file, data, rows, cols))
                    {
                        /* Initialize player and snake struct */
                        if((initializePlayer(playerState, rows, cols, data) == TRUE) || (initializeSnake(snakeState, rows, cols, data) == TRUE))
                        {
                            fail = TRUE;
                        }
                        else
                        {
                            writeToTerminal(playerState->mapData, playerState->rows, playerState->cols, &playerState->xCoord, &playerState->yCoord, playerState->sight); /* Initial map display */
                            characterInput(playerState, snakeState); /* Enter character input loop */
                        }
                    }
                    else
                    {
                        fail = TRUE;
                    }
                    
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
    }

    if(file != NULL)
    {
        fclose(file);
    }

    return fail;
}