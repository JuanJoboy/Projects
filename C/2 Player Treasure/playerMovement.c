#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "playerMovement.h"
#include "gameHandler.h"
#include "mapReader.h"
#include "snakeMovement.h"
#include "linkedList.h"

int playerCoords(Player *playerState, Snake *snakeState, char ch, linkedList *playerList, linkedList *snakeList)
{
    int running = 1;
    int snakeCanUndo = 0;

    /* Old Coords (necessary to make sure the snake doesn't move unless I do) */
    int oldX = playerState->xCoord;
    int oldY = playerState->yCoord;

    /* Prev Coords (Without this I teleport back to the tile the P is initialised at when I hit a wall) */
    playerState->undoX = playerState->xCoord;
    playerState->undoY = playerState->yCoord;

    /* Without this, there would be a trail of P's everywhere. This line sets the spot I used to be at into an empty space */
    /* It goes [y][x] because the y deals with the rows and x deals with the columns */
    playerState->mapData[playerState->yCoord][playerState->xCoord] = 0;

    if(ch == 'u')
    {
        undoMovement(playerState, playerList);
        undoSnake(snakeState, snakeList);
    }
    else
    {
        movement(playerState, ch, playerList);
        snakeCanUndo = 1;
        hitWall(playerState); /* Takes in the state of the player, which allows it to take in the state of the undoX and undoY coords */

        if(winCondition(playerState, snakeState)) /* Without this if-statement, I cant get the treasure */
        {
            running = 0;
        }
    }

    if(playerState->mapData[playerState->yCoord][playerState->xCoord] == 2)
    {
        playerState->lanternCollected = 1;
        playerState->lanternXCoords = playerState->xCoord; /* Need to store the coords of the lantern so that when I undo, i can make it reappear again at the same spot, because if i just try to set the coords == 2, it won't work since 2 doesn't exist on the map anymore, it needs to be the specific spot where the lantern was */
        playerState->lanternYCoords = playerState->yCoord;
        #ifdef DARK
            playerState->sight = 6;
        #endif
    }

    playerState->mapData[playerState->yCoord][playerState->xCoord] = 3; /* Turns the current spot on the map into the player icon */

    /* The snake only moves if I move and if the boolean is 1, because without it, when I activate the undo feature, the snake will think that I moved due to my old coords and current coords not being the same and so it'll do a random-movement first before actually doing an undo-movement. Also running has to == 1 because without this check, the snake will move even when the game ends. */
    if(snakeCanUndo == 1 && (playerState->xCoord != oldX || playerState->yCoord != oldY) && running == 1)
    {
        snakeCoords(snakeState, playerState, snakeList);
    }

    printMap(playerState, snakeState); /* Without this the snake doesn't do it's killing animation until after the game ends. I also can't move at all without this line */
    
    if(winCondition(playerState, snakeState)) /* Without this, the snake cant kill me */
    {
        running = 0;
    }

    return running;
}

void movement(Player *state, char ch, linkedList *list)
{
    int properMovement;
    Player *newCoordNode = NULL;
    newCoordNode = (Player *)malloc(sizeof(Player)); /* Allocate memory to create a new node that stores my coords whenever i move */
    properMovement = 0;

    if(newCoordNode == NULL)
    {
        handleError("New node wasn't made properly\n");
    }
    else
    {
        memcpy(newCoordNode, state, sizeof(Player)); /* Creates a copy of my most recent state, and places it into the new node that was just created */

        switch(ch)
        {
            case 'w':
                if((state->yCoord) > 0)
                {
                    state->yCoord--;
                    properMovement = 1;
                }
                break;
            case 'a':
                if((state->xCoord) > 0)
                {
                    state->xCoord--;
                    properMovement = 1;
                }
                break;
            case 's':
                if((state->yCoord) < (state->rows - 1))
                {
                    state->yCoord++;
                    properMovement = 1;
                }
                break;
            case 'd':
                if((state->xCoord) < (state->cols - 1))
                {
                    state->xCoord++;
                    properMovement = 1;
                }
                break;
            default:
                break;
        }

        if(properMovement == 1)
        {
            insertFirst(list, newCoordNode); /* If i were to put "state" here instead of newCoordNode, then I would be constantly inserting at the same node instead of a new one, the same would go for every key-bind, every movement would overwrite the same node. Instead, with the new node I just made, I alter it to be its own thing and not just a copy of the previous one*/
        }
        else
        {
            free(newCoordNode); /* Used to have this underneath default: in the switch, but that wasn't properly freeing for some reason. So now I have this system where if a new node is properly made, then it's freed if it isn't a "w,a,s,d" key-bind (a proper movement). If it is a proper key-bind then it isn't freed yet. */
        }
    }
}

void undoMovement(Player *state, linkedList *list)
{
    Player *prevState = NULL;
    prevState = (Player *) removeFirst(list); /* removeFirst() returns the data of the removed node in void* format because it's generic. In order to actually use it, I have to cast it to Player*. */

    if(prevState != NULL)
    {
        if((state->lanternCollected == 1) && (prevState->lanternCollected == 0)) /* Checks if I currently have the lantern, and if I didn't have it before. If I do have the lantern now, and before, then I don't need to put it back, but if my last move didn't have the lantern, then I need to put it back on the map. */
        {
            state->lanternCollected = 0;
            state->mapData[state->lanternYCoords][state->lanternXCoords] = 2; /* Physically places the lantern back on the map */
            #ifdef DARK
                state->sight = 3;
            #endif
        }

        state->xCoord = prevState->xCoord; /* If the previous state isn't empty, then these 2 lines make me go back to where I was before. prevState has access to the most recent state of the player, and so setting these coords to my actual current coords allows me to go back to where I was before */
        state->yCoord = prevState->yCoord;
        
        free(prevState); /* Free the memory of that node since I've gone back in time to that node's spot, thus making me no longer ever needing it again */
    }
}

void hitWall(Player *state)
{
    if(state->mapData[state->yCoord][state->xCoord] == 1)
    {
        state->hitWallFlag = 1; /* If I hit a wall, the flag gets turned on */

        /* Sets the xCoord and yCoord to the coords of undoX and undoY, which are stored in the state parameter */
        /* Sets my position back to the previous position, which is just the same tile technically, I'm just not hitting the wall anymore, I'm just standing next to it now */
        state->xCoord = state->undoX;
        state->yCoord = state->undoY;
    }
}

int initializePlayer(Player *state, int mapRows, int mapCols, int** data)
{
    int fail = FALSE;

    state->mapData = data;
    state->rows = mapRows;
    state->cols = mapCols;

    #ifdef DARK
        state->sight = 3;
    #endif

    if(!playerFinder(data, mapRows, mapCols, &state->xCoord, &state->yCoord))
    {
        handleError("Player not found in the map.");
        fail = TRUE;
    }
    else
    {
        /* The below initializations stop the following error 'Conditional jump or move depends on uninitialized value(s)' */
        state->undoX = state->xCoord;
        state->undoY = state->yCoord;
        state->hitWallFlag = 0;
        state->lanternCollected = 0;
        state->lanternXCoords = -1; /* Set to -1 because it's not a valid coord and I just need to initialize it to something */
        state->lanternYCoords = -1;
    }

    return fail;
}