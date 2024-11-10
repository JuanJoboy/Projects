#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include "playerMovement.h"
#include "gameHandler.h"
#include "mapReader.h"
#include "snakeMovement.h"
#include "linkedList.h"

int playerCoords(Player players[], Snake snakes[], char keyBind, linkedList *playerList[], linkedList *snakeList[])
{
    int running = 1;

    if(players[0].dead && players[1].dead)
    {
        return 0;  // End game when both players are dead
    }

    /* Prev Coords - without this here I teleport back to the tile the P is initialised at when I hit a wall. It also allows me to save my previous position every time I move (These lines are NOT related to the undo feature and linked lists) */
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        players[i].prevX = players[i].xCoord;
        players[i].prevY = players[i].yCoord;
    }

    /* Without this, there would be a trail of P's everywhere. This line sets the spot I used to be at into an empty space */
    /* It goes [y][x] because the y deals with the rows and x deals with the columns */
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        players[i].mapData[players[i].yCoord][players[i].xCoord] = 0;
    }

    if(keyBind == 'q' && players[0].dead == 0)
    {
        undoMovement(&players[0], playerList[0]);
        for(int i = 0; i < MAX_SNAKES; i++)
        {
            undoSnake(&snakes[i], snakeList[i]);
        }
    }
    else if(keyBind == 'u' && players[1].dead == 0)
    {
        undoMovement(&players[1], playerList[1]);
        for(int i = 0; i < MAX_SNAKES; i++)
        {
            undoSnake(&snakes[i], snakeList[i]);
        }
    }
    else
    {
        movement1(&players[0], keyBind, playerList[0]);
        movement2(&players[1], keyBind, playerList[1]);
        if(players[0].dead && players[1].dead)
        {
            return 1;
        }

        for(int i = 0; i < MAX_PLAYERS; i++)
        {
            hitWall(&players[i]);
        }

        if(winCondition(players, snakes)) /* Without this if-statement, I cant get the treasure */
        {
            running = 0;
        }
    }

    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].mapData[players[i].yCoord][players[i].xCoord] == 2)
        {
            players[i].lanternCollected = 1;
            players[i].lanternXCoord = players[i].xCoord; /* I need to store the coords of the lantern so that when I undo, I can make it reappear again at the same spot, because if I just try to set the coords == 2, it won't work since 2 doesn't exist on the map anymore, it needs to be the specific spot where the lantern was */
            players[i].lanternYCoord = players[i].yCoord;
            #ifdef DARK
                players[i].sight = 4;
            #endif
        }
    }

    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].mapData[players[i].yCoord][players[i].xCoord] == 9)
        {
            players[i].shieldCollected = 1;
            players[i].shieldXCoord = players[i].xCoord;
            players[i].shieldYCoord = players[i].yCoord;
            players[i].shieldStartTime = time(NULL);
            players[i].shieldEndTime = players[i].shieldStartTime + 8;
        }

        // If player has shield and it's still active
        if(players[i].shieldCollected == 1 && time(NULL) < players[i].shieldEndTime)
        {
            // Check for snake collision
            for(int j = 0; j < MAX_SNAKES; j++)
            {
                if(players[0].xCoord == snakes[j].xCoord && players[0].yCoord == snakes[j].yCoord)
                {
                    players[0].mapData[snakes[i].yCoord][snakes[i].xCoord] = 3;
                }
                if(players[1].xCoord == snakes[j].xCoord && players[1].yCoord == snakes[j].yCoord)
                {
                    players[1].mapData[snakes[i].yCoord][snakes[i].xCoord] = 7;
                }
            }
        }
        
        // Deactivate shield after time expires
        if(players[i].shieldCollected && time(NULL) >= players[i].shieldEndTime)
        {
            players[i].shieldCollected = 0;
        }
    }

    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].mapData[players[i].yCoord][players[i].xCoord] == 8)
        {
            players[i].vanXCoord = players[i].xCoord; /* I need to store the coords of the van so that when I undo, I can make it reappear again at the same spot, because if I just try to set the coords == 8, it won't work since 8 doesn't exist on the map anymore, it needs to be the specific spot where the van was */
            players[i].vanYCoord = players[i].yCoord;
        }

        if(players[i].vanXCoord != -1 && players[i].vanYCoord != -1) // Now that ive saved the coords of the van, as long as its not -1,-1 (it cant be since thats not a valid coord) I can make it always appear */
        {
            players[i].mapData[players[i].vanYCoord][players[i].vanXCoord] = 8;
        }
    }

    // Check if Player 1 collects reboot card
    if(players[0].xCoord >= 0 && players[0].xCoord < players[0].cols && players[0].yCoord >= 0 && players[0].yCoord < players[0].rows && players[0].mapData[players[0].yCoord][players[0].xCoord] == 6)
    {
        players[0].cardCollected = 1;
    }

    // Check if Player 2 should be revived
    if(players[0].xCoord >= 0 && players[0].xCoord < players[0].cols && players[0].yCoord >= 0 && players[0].yCoord < players[0].rows && players[0].mapData[players[0].yCoord][players[0].xCoord] == 8 && players[0].cardCollected == 1)
    {
        if(!players[0].waitingForRevive)
        {
            players[0].startTime = time(NULL);
            players[0].endTime = players[0].startTime + 5;
            players[0].waitingForRevive = 1;
        }
        else if(time(NULL) >= players[0].endTime)
        {
            freeList(playerList[1], &freePlayer);
            playerList[1] = createList();
            
            players[1].dead = 0;
            players[1].xCoord = players[1].vanXCoord;
            players[1].yCoord = players[1].vanYCoord;
            players[1].mapData[players[1].yCoord][players[1].xCoord] = 7;
            players[1].sight = 2;
            
            players[0].cardCollected = 0;
            players[0].waitingForRevive = 0;
        }
    }

    // Check if Player 2 collects reboot card
    if(players[1].xCoord >= 1 && players[1].xCoord < players[1].cols && players[1].yCoord >= 0 && players[1].yCoord < players[1].rows && players[1].mapData[players[1].yCoord][players[1].xCoord] == 6)
    {
        players[1].cardCollected = 1;
    }

    // Check if Player 1 should be revived
    if(players[1].xCoord >= 0 && players[1].xCoord < players[1].cols && players[1].yCoord >= 0 && players[1].yCoord < players[1].rows && players[1].mapData[players[1].yCoord][players[1].xCoord] == 8 && players[1].cardCollected == 1)
    {
        if(!players[1].waitingForRevive)
        {
            players[1].startTime = time(NULL);
            players[1].endTime = players[1].startTime + 5;
            players[1].waitingForRevive = 1;
        }
        else if(time(NULL) >= players[1].endTime)
        {
            freeList(playerList[0], &freePlayer);
            playerList[0] = createList();
            
            players[0].dead = 0;
            players[0].xCoord = players[0].vanXCoord;
            players[0].yCoord = players[0].vanYCoord;
            players[0].mapData[players[0].yCoord][players[0].xCoord] = 7;
            players[0].sight = 2;
            
            players[1].cardCollected = 0;
            players[1].waitingForRevive = 0;
        }
    }

    // Update player positions on map
    if(players[0].xCoord >= 0 && players[0].xCoord < players[0].cols && 
    players[0].yCoord >= 0 && players[0].yCoord < players[0].rows && !players[0].dead)
    {
        players[0].mapData[players[0].yCoord][players[0].xCoord] = 3;
    }

    if(players[1].xCoord >= 0 && players[1].xCoord < players[1].cols && 
    players[1].yCoord >= 0 && players[1].yCoord < players[1].rows && !players[1].dead)
    {
        players[1].mapData[players[1].yCoord][players[1].xCoord] = 7;
    }

    printMap(players, snakes); /* Without this the snake doesn't do it's killing animation until after the game ends. I also can't move at all without this line */
    
    if(winCondition(players, snakes)) /* Without this, the snake cant kill me */
    {
        running = 0;
    }

    return running;
}

void movement1(Player *state, char keyBind, linkedList *list)
{
    if(state->dead == 1)
    {
        return;
    }

    int properMovement;
    Player *newCoordNode = NULL;
    newCoordNode = (Player*)malloc(sizeof(Player)); /* Allocate memory to create a new node that stores my coords whenever i move */
    properMovement = 0;

    if(newCoordNode == NULL)
    {
        errorMessage("New player node wasn't made properly\n");
    }
    else
    {
        memcpy(newCoordNode, state, sizeof(Player)); /* Creates a copy of my most recent state, and places it into the new node that was just created */

        switch(keyBind)
        {
            /* Every case checks if i'm within the bounds of the map, and if I am, then it moves me accordingly */
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
            free(newCoordNode); /* Used to have this underneath default: in the switch, but that wasn't properly freeing for some reason. So now I have this system where if a new node is properly made, then it's freed immediately if it isn't a "w,a,s,d" key-bind (a proper movement). If it is a proper key-bind then it isn't freed yet. */
        }
    }
}

void movement2(Player *state, char keyBind, linkedList *list)
{
    if(state->dead == 1)
    {
        return;
    }

    int properMovement;
    Player *newCoordNode = NULL;
    newCoordNode = (Player*)malloc(sizeof(Player)); /* Allocate memory to create a new node that stores my coords whenever i move */
    properMovement = 0;

    if(newCoordNode == NULL)
    {
        errorMessage("New player node wasn't made properly\n");
    }
    else
    {
        memcpy(newCoordNode, state, sizeof(Player)); /* Creates a copy of my most recent state, and places it into the new node that was just created */

        switch(keyBind)
        {
            /* Every case checks if i'm within the bounds of the map, and if I am, then it moves me accordingly */
            case 'i':
                if((state->yCoord) > 0)
                {
                    state->yCoord--;
                    properMovement = 1;
                }
                break;
            case 'j':
                if((state->xCoord) > 0)
                {
                    state->xCoord--;
                    properMovement = 1;
                }
                break;
            case 'k':
                if((state->yCoord) < (state->rows - 1))
                {
                    state->yCoord++;
                    properMovement = 1;
                }
                break;
            case 'l':
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
            free(newCoordNode); /* Used to have this underneath default: in the switch, but that wasn't properly freeing for some reason. So now I have this system where if a new node is properly made, then it's freed immediately if it isn't a "w,a,s,d" key-bind (a proper movement). If it is a proper key-bind then it isn't freed yet. */
        }
    }
}

void undoMovement(Player *state, linkedList *list)
{
    Player *undoState = NULL;
    undoState = (Player*) removeFirst(list); /* removeFirst() returns the data of the removed node in void* format because it's generic. In order to actually use it, I have to cast it to Player*. */

    if(undoState != NULL)
    {
        if((state->lanternCollected == 1) && (undoState->lanternCollected == 0)) /* Checks if I currently have the lantern, and if I didn't have it before. If I do have the lantern now, and before, then I don't need to put it back, but if my last move didn't have the lantern, then I need to put it back on the map. */
        {
            state->lanternCollected = 0;
            state->mapData[state->lanternYCoord][state->lanternXCoord] = 2; /* Physically places the lantern back on the map */
            #ifdef DARK
                state->sight = 2;
            #endif
        }

        if((state->shieldCollected == 1) && (undoState->shieldCollected == 0))
        {
            state->shieldCollected = 0;
            state->mapData[state->shieldYCoord][state->shieldXCoord] = 9;
        }

        state->xCoord = undoState->xCoord; /* If the previous state isn't empty, then these 2 lines make me go back to where I was before. undoState has access to the most recent state of the player, and so setting these coords to my actual current coords allows me to go back to where I was before */
        state->yCoord = undoState->yCoord;
        
        free(undoState); /* Free the memory of that node since I've gone back in time to that node's spot, thus making me no longer ever needing it again */
    }
}

void hitWall(Player *state)
{
    if(state->mapData[state->yCoord][state->xCoord] == 1)
    {
        state->hitWallFlag = 1; /* If I hit a wall, the flag gets turned on */

        /* Sets the xCoord and yCoord to the coords of prevX and prevY, which are stored in the state parameter */
        /* Sets my position back to the previous position, which is just the same tile technically, I'm just not hitting the wall anymore, I'm just standing next to it now */
        state->xCoord = state->prevX;
        state->yCoord = state->prevY;
    }
}

void initializePlayer1(Player *state, int mapRows, int mapCols, int **data)
{
    state->mapData = data;
    state->rows = mapRows;
    state->cols = mapCols;
    state->xCoord = -1; /* Set to -1 because it's not a valid coord and I just need to initialize it to something */
    state->yCoord = -1;
    state->prevX = state->xCoord;
    state->prevY = state->yCoord;
    state->hitWallFlag = 0;
    state->lanternCollected = 0;
    state->lanternXCoord = -1;
    state->lanternYCoord = -1;
    state->dead = 0;
    state->vanXCoord = -1;
    state->vanYCoord = -1;
    state->cardCollected = 0;
    state->waitingForRevive = 0;
    state->startTime = 0;
    state->endTime = 0;
    state->shieldCollected = 0;
    state->shieldXCoord = -1;
    state->shieldYCoord = -1;

    #ifdef DARK
        state->sight = 2;
    #endif

    player1Finder(data, mapRows, mapCols, &state->xCoord, &state->yCoord);
    vanFinder(data, mapRows, mapCols, &state->vanXCoord, &state->vanYCoord);
}

void initializePlayer2(Player *state, int mapRows, int mapCols, int **data)
{
    state->mapData = data;
    state->rows = mapRows;
    state->cols = mapCols;
    state->xCoord = -1; /* Set to -1 because it's not a valid coord and I just need to initialize it to something */
    state->yCoord = -1;
    state->prevX = state->xCoord;
    state->prevY = state->yCoord;
    state->hitWallFlag = 0;
    state->lanternCollected = 0;
    state->lanternXCoord = -1;
    state->lanternYCoord = -1;
    state->dead = 0;
    state->vanXCoord = -1;
    state->vanYCoord = -1;
    state->cardCollected = 0;
    state->waitingForRevive = 0;
    state->startTime = 0;
    state->endTime = 0;
    state->shieldCollected = 0;
    state->shieldXCoord = -1;
    state->shieldYCoord = -1;

    #ifdef DARK
        state->sight = 2;
    #endif

    player2Finder(data, mapRows, mapCols, &state->xCoord, &state->yCoord);
    vanFinder(data, mapRows, mapCols, &state->vanXCoord, &state->vanYCoord);
}


