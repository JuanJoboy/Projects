#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "snakeMovement.h"
#include "random.h"
#include "mapReader.h"
#include "linkedList.h"

void snakeCoords(Snake *snakeState, Player *playerState, linkedList *list)
{
    snakeState->undoX = snakeState->xCoord;
    snakeState->undoY = snakeState->yCoord;

    /* Clear the old snake position on the map */
    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 0;  /* Turns the current spot on the map into an empty space */

    snakeMove(snakeState, list);

    restrictMovement(snakeState);

    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 4; /* Turns the current spot on the map into the snake */

    killPlayer(snakeState, playerState);
}

void restrictMovement(Snake *state)
{
    if(state->mapData[state->yCoord][state->xCoord] == 1)
    {
        state->hitWallFlag = 1;
        state->mapData[state->yCoord][state->xCoord] = 1; /* Turns the spot back into a wall */
        state->xCoord = state->undoX; /* Reverts the snake's position back to the previous position that are stored in the undoX and undoY variables */
        state->yCoord = state->undoY;
    }
    
    else if(state->mapData[state->yCoord][state->xCoord] == 5)
    {
        state->mapData[state->yCoord][state->xCoord] = 5;
        state->xCoord = state->undoX;
        state->yCoord = state->undoY;
    }
    
    else if(state->mapData[state->yCoord][state->xCoord] == 2)
    {
        state->mapData[state->yCoord][state->xCoord] = 2;
        state->xCoord = state->undoX;
        state->yCoord = state->undoY;
    }
}

void snakeMove(Snake *state, linkedList *list)
{
    int properMovement;
    Snake *newCoordNode = NULL;
    newCoordNode = (Snake *)malloc(sizeof(Snake)); /* Allocate memory to create a new node that stores the snakes coords whenever it moves */
    properMovement = 0;

    if(newCoordNode == NULL)
    {
        handleError("New node wasn't made properly\n");
    }
    else
    {
        memcpy(newCoordNode, state, sizeof(Snake)); /* Creates a copy of the snakes most recent state, and places it into the new node that was just created */

        switch(randomUCP(0, 7))
        {
            case 0: /* up */
                if((state->yCoord) > 0)
                {
                    state->yCoord--;
                    properMovement = 1;
                }
                break;
            case 1: /* up and left */
                if(((state->yCoord) > 0) && ((state->xCoord) > 0))
                {
                    state->yCoord--;
                    state->xCoord--;
                    properMovement = 1;
                }
                break;
            case 2: /* left */
                if((state->xCoord) > 0)
                {
                    state->xCoord--;
                    properMovement = 1;
                }
                break;
            case 3: /* down and left */
                if(((state->yCoord) < (state->rows - 1)) && ((state->xCoord) > 0))
                {
                    state->yCoord++;
                    state->xCoord--;
                    properMovement = 1;
                }
                break;
            case 4: /* down */
                if((state->yCoord) < (state->rows - 1))
                {
                    state->yCoord++;
                    properMovement = 1;
                }
                break;
            case 5: /* down and right */
                if(((state->yCoord) < (state->rows - 1)) && ((state->xCoord) < (state->cols - 1)))
                {
                    state->yCoord++;
                    state->xCoord++;
                    properMovement = 1;
                }
                break;
            case 6: /* right */
                if((state->xCoord) < (state->cols - 1))
                {
                    state->xCoord++;
                    properMovement = 1;
                }
                break;
            case 7: /* up and right */
                if(((state->yCoord) > 0) && ((state->xCoord) < (state->cols - 1)))
                {
                    state->yCoord--;
                    state->xCoord++;
                    properMovement = 1;
                }
                break;
        }

        if (properMovement == 1)
        {
            insertFirst(list, newCoordNode);
        }
        else
        {
            free(newCoordNode);
        }
    }
}

void killPlayer(Snake *snakeState, Player *playerState)
{
    int xDiffCoords = (playerState->xCoord) - (snakeState->xCoord);
    int yDiffCoords = (playerState->yCoord) - (snakeState->yCoord);

    if((xDiffCoords == 0) && (yDiffCoords == -1)) /* Im above */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == -1) && (yDiffCoords == -1)) /* Im above and to the left */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == -1) && (yDiffCoords == 0)) /* Im on the left */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == -1) && (yDiffCoords == 1)) /* Im below and to the left */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == 0) && (yDiffCoords == 1)) /* Im below */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == 1) && (yDiffCoords == 1)) /* Im below and to the right */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == 1) && (yDiffCoords == 0)) /* Im on the right */
    {
        killPlayerHelper(snakeState, playerState);
    }
    else if((xDiffCoords == 1) && (yDiffCoords == -1)) /* Im above and to the right */
    {
        killPlayerHelper(snakeState, playerState);
    }
}

void killPlayerHelper(Snake *snakeState, Player *playerState)
{
    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 0; /* Clear where the snake used to be */
    snakeState->yCoord = playerState->yCoord; /* Set the snakes coords to my coords */
    snakeState->xCoord = playerState->xCoord;
    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 4; /* Kill me*/
}

void undoSnake(Snake *state, linkedList *list)
{
    Snake *prevState = NULL;
    prevState = (Snake *) removeFirst(list);

    if(prevState != NULL)
    {
        state->mapData[state->yCoord][state->xCoord] = 0;
        state->xCoord = prevState->xCoord;
        state->yCoord = prevState->yCoord;
        state->mapData[state->yCoord][state->xCoord] = 4;
        free(prevState);
    }
}

int initializeSnake(Snake *state, int mapRows, int mapCols, int** data)
{
    int fail = FALSE;

    state->mapData = data;
    state->rows = mapRows;
    state->cols = mapCols;

    if(!snakeFinder(data, mapRows, mapCols, &state->xCoord, &state->yCoord))
    {
        handleError("Snake initial position not found in the map.");
        fail = TRUE;
    }
    else
    {
        state->undoX = state->xCoord;
        state->undoY = state->yCoord;
        state->hitWallFlag = 0;
    }

    return fail;
}