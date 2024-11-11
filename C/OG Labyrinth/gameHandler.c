#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "gameHandler.h"
#include "newSleep.h"
#include "terminal.h"
#include "mapReader.h"
#include "playerMovement.h"

void runGame(Player *playerState, Snake *snakeState)
{
    char keyBind;
    int running = 1;

    linkedList *playerList = NULL;
    linkedList *snakeList = NULL;
    playerList = createList();
    snakeList = createList();
    
    printMap(playerState, snakeState);

    while(running) 
    {
        disableBuffer();
        scanf(" %c", &keyBind);
        enableBuffer();

        if(keyBind == 'e')
        {
            running = 0; /* I know we're meant to ignore every other key but having an exit button is so much cleaner than pressing ctrl+c */
        }
        else
        {
            running = playerCoords(playerState, snakeState, keyBind, playerList, snakeList); /* If I win or lose, I exit the game */
        }
    }

    /* If I exit or win or lose, all valid nodes in the list are freed immediately */
    freeList(playerList, &freePlayer);
    freeList(snakeList, &freeSnake);
}

void printMap(Player *playerState, Snake *snakeState)
{
    system("tput cup 0 0");

    writeToTerminal(playerState->mapData, playerState->rows, playerState->cols, &playerState->xCoord, &playerState->yCoord, playerState->sight);

    keyBinds();

    printf("Player: [%d][%d]\n", playerState->xCoord, playerState->yCoord);
    printf("Snake : [%d][%d]\n", snakeState->xCoord, snakeState->yCoord);

    if(playerState->hitWallFlag)
    {
        printf("\nOUCH, WHO PUT THIS WALL HERE?!\n");
        playerState->hitWallFlag = 0; /* Reset the flag to be turned off */
    }
    if(snakeState->hitWallFlag)
    {
        printf("\nHISS! HISS HISS HISSSSSS?!\n");
        snakeState->hitWallFlag = 0; /* Reset the flag to be turned off */
    }
}

void keyBinds()
{
    printf("Press 'w' to move up\n");
    printf("Press 'a' to move left\n");
    printf("Press 's' to move down\n");
    printf("Press 'd' to move right\n");
    printf("Press 'u' to undo\n");
    printf("Press 'e' to exit\n");
}

int winCondition(Player *playerState, Snake *snakeState)
{
    int exit = 0;

    if(playerState->mapData[playerState->yCoord][playerState->xCoord] == 5)
    {
        printf("\nYou found the treasure!\n");
        printf("You win!\n");
        fflush(stdout); /* Forces the message to be printed immediately */
        newSleep(2.0); /* Sleep for 2 seconds so that the message isn't overwritten by the terminal stuff */
        exit = 1;
    }
    else if((playerState->yCoord == snakeState->yCoord) && (playerState->xCoord == snakeState->xCoord))
    {
        printf("\nThe snake bit you!\n");
        printf("You lose!\n");
        fflush(stdout);
        newSleep(2.0);
        exit = 1;
    }

    return exit;
}