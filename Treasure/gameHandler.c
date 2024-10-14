#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "gameHandler.h"
#include "newSleep.h"
#include "terminal.h"
#include "mapReader.h"
#include "playerMovement.h"

void characterInput(Player *playerState, Snake *snakeState)
{
    char ch;
    int running = 1;

    linkedList *player1List = NULL;
    linkedList *player2List = NULL;
    linkedList *snake1List = NULL;
    linkedList *snake2List = NULL;
    linkedList *snake3List = NULL;
    player1List = createList();
    player2List = createList();
    snake1List = createList();
    snake2List = createList();
    snake3List = createList();
    
    keyBinds(); /* Prints the key-binds immediately */
    
    while(running) 
    {
        disableBuffer();
        scanf(" %c", &ch);
        enableBuffer();

        if(ch == 'e')
        {
            running = 0; /* If i press "e", I exit the game */
        }
        else
        {
            running = playerCoords(playerState, snakeState, ch, player1List, snake1List, player2List, snake2List, snake3List); /* If I win or lose, I exit the game */
        }
    }

    /* If I exit or win or lose, all valid nodes in the list are freed. The undo functions free the nodes that are undone when I press "u" and any invalid nodes such as nodes created when I press "j" or something are immediately freed in the movement function */
    freeList(player1List, &freePlayer);
    freeList(snake1List, &freeSnake);
    freeList(player2List, &freePlayer);
    freeList(snake2List, &freeSnake);
    freeList(snake3List, &freeSnake);
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
        newSleep(2.0);
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