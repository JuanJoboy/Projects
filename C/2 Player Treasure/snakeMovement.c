#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "snakeMovement.h"
#include "random.h"
#include "mapReader.h"
#include "linkedList.h"

void snakeCoords(Snake snakes[], Player players[], linkedList *snakeList[])
{
    for(int i = 0; i < MAX_SNAKES; i++) {
        // Store previous position
        snakes[i].prevX = snakes[i].xCoord;
        snakes[i].prevY = snakes[i].yCoord;

        // Clear current position
        snakes[i].mapData[snakes[i].yCoord][snakes[i].xCoord] = 0;

        // Move snake
        snakeMove(&snakes[i], snakeList[i]);
        restrictMovement(&snakes[i]);

        // Update map with new position
        snakes[i].mapData[snakes[i].yCoord][snakes[i].xCoord] = 4;

        // Check for player collision
        killPlayer(&snakes[i], players);
    }
}


void restrictMovement(Snake *state)
{
    if(state->mapData[state->yCoord][state->xCoord] == 1)
    {
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

    else if(state->mapData[state->yCoord][state->xCoord] == 6)
    {
        state->xCoord = state->prevX;
        state->yCoord = state->prevY;
    }

    else if(state->mapData[state->yCoord][state->xCoord] == 8)
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

void killPlayer(Snake *snakes, Player players[])
{
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(!players[i].dead)
        {
            int xDiffCoords = (players[i].xCoord) - (snakes->xCoord);
            int yDiffCoords = (players[i].yCoord) - (snakes->yCoord);

            if((xDiffCoords == 0) && (yDiffCoords == -1)) /* Im above */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == -1) && (yDiffCoords == -1)) /* Im above and to the left */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == -1) && (yDiffCoords == 0)) /* Im on the left */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == -1) && (yDiffCoords == 1)) /* Im below and to the left */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == 0) && (yDiffCoords == 1)) /* Im below */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == 1) && (yDiffCoords == 1)) /* Im below and to the right */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == 1) && (yDiffCoords == 0)) /* Im on the right */
            {
                killPlayerHelper(snakes, &players[i]);
            }
            else if((xDiffCoords == 1) && (yDiffCoords == -1)) /* Im above and to the right */
            {
                killPlayerHelper(snakes, &players[i]);
            }
        }
    }
}

void killPlayerHelper(Snake *snake, Player *player)
{
    player->dead = 1;
    snake->mapData[player->yCoord][player->xCoord] = 6; // Set X (reboot card) at player's current position before moving them
    snake->mapData[snake->yCoord][snake->xCoord] = 4;  // Place snake at player position
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

    snakeFinder(data, mapRows, mapCols, &state->xCoord, &state->yCoord);
}