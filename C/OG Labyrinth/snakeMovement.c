#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "snakeMovement.h"
#include "random.h"
#include "mapReader.h"
#include "linkedList.h"

void snakeCoords(Snake *snakeState, Player *playerState, linkedList *list)
{
    snakeState->prevX = snakeState->xCoord;
    snakeState->prevY = snakeState->yCoord;

    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 0;  /* Turns the current spot on the map into an empty space */

    snakeMove(snakeState, list); /* Allows the snake to move */

    restrictMovement(snakeState); /* Restricts it from going into walls, the treasure and the lantern */

    snakeState->mapData[snakeState->yCoord][snakeState->xCoord] = 4; /* Turns the current spot on the map into the snake */

    killPlayer(snakeState, playerState); /* If i'm close enough the snake will kill me */
}

void restrictMovement(Snake *state)
{
    if(state->mapData[state->yCoord][state->xCoord] == 1)
    {
        state->hitWallFlag = 1; /* An unecessary flag just so the hiss hiss message can play in the printMap() function */
        state->xCoord = state->prevX; /* Reverts the snake's position back to the previous position that are stored in the prevX and prevY variables */
        state->yCoord = state->prevY;
    }
    
    else if(state->mapData[state->yCoord][state->xCoord] == 5)
    {
        state->xCoord = state->prevX;
        state->yCoord = state->prevY;
    }
    
    else if(state->mapData[state->yCoord][state->xCoord] == 2)
    {
        state->xCoord = state->prevX;
        state->yCoord = state->prevY;
    }
}

void snakeMove(Snake *state, linkedList *list)
{
    int properMovement;
    int randomMoves;
    Snake *newCoordNode = NULL;
    newCoordNode = (Snake*)malloc(sizeof(Snake)); /* Allocate memory to create a new node that stores the snakes coords whenever it moves */
    properMovement = 0;

    if(newCoordNode == NULL)
    {
        errorMessage("New snake node wasn't made properly\n");
    }
    else
    {
        memcpy(newCoordNode, state, sizeof(Snake)); /* Creates a copy of the snakes most recent state, and places it into the new node that was just created */

        randomMoves = randomUCP(0, 7); /* Generates a random number between 0 and 7. The assignment of the numbers to movements is arbitrary, I just like the way anticlockwise looks in the switch */
        switch(randomMoves)
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
    Snake *undoState = NULL;
    undoState = (Snake*) removeFirst(list);

    if(undoState != NULL)
    {
        state->mapData[state->yCoord][state->xCoord] = 0;
        state->xCoord = undoState->xCoord;
        state->yCoord = undoState->yCoord;
        state->mapData[state->yCoord][state->xCoord] = 4;
        free(undoState);
    }
}

void initializeSnake(Snake *state, int mapRows, int mapCols, int **data)
{
    state->mapData = data;
    state->rows = mapRows;
    state->cols = mapCols;
    state->xCoord = -1;
    state->yCoord = -1;
    state->prevX = state->xCoord;
    state->prevY = state->yCoord;
    state->hitWallFlag = 0;

    snakeFinder(data, mapRows, mapCols, &state->xCoord, &state->yCoord);
}