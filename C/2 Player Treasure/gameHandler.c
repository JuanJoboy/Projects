#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h> // for usleep()
#include <time.h>
#include "gameHandler.h"
#include "newSleep.h"
#include "terminal.h"
#include "mapReader.h"
#include "playerMovement.h"
#include "snakeMovement.h"

pthread_mutex_t snakeMoveMutex = PTHREAD_MUTEX_INITIALIZER;

// Modify snakeThread function
void *snakeThread(void *arg)
{
    ThreadArgs *args = (ThreadArgs *)arg;
    Player *players = args->players;
    Snake *snakes = args->snakes;
    linkedList **snakeLists = args->snakeLists;

    while(1)
    {
        pthread_mutex_lock(&snakeMoveMutex);
        snakeCoords(snakes, players, snakeLists);
        printMap(players, snakes);  // Update display after snake moves

        if(winCondition(players, snakes))
        {
            pthread_mutex_unlock(&snakeMoveMutex);
            pthread_exit(NULL);
        }
        
        pthread_mutex_unlock(&snakeMoveMutex);
        usleep(500000); // Sleep for 0.5 seconds
    }

    return NULL;
}

void runGame(Player players[], Snake snakes[])
{
    char keyBind;
    int running = 1;

    linkedList *playerLists[MAX_PLAYERS];
    linkedList *snakeLists[MAX_SNAKES];

    // Initialize lists
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        playerLists[i] = createList();
    }
    for(int i = 0; i < MAX_SNAKES; i++)
    {
        snakeLists[i] = createList();
    }
    
    printMap(players, snakes);

    pthread_t snakeThreadID;
    ThreadArgs threadArgs = {players, snakes, snakeLists};
    pthread_create(&snakeThreadID, NULL, snakeThread, &threadArgs);

    while(running) 
    {
        disableBuffer();
        scanf(" %c", &keyBind);
        enableBuffer();

        if(keyBind == 'e')
        {
            running = 0;
        }
        else
        {
            pthread_mutex_lock(&snakeMoveMutex);
            running = playerCoords(players, snakes, keyBind, playerLists, snakeLists);
            pthread_mutex_unlock(&snakeMoveMutex);
        }
    }

    // Clean up snake thread
    pthread_cancel(snakeThreadID);
    pthread_join(snakeThreadID, NULL);

    /* If I exit or win or lose, all valid nodes in the list are freed immediately */
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        freeList(playerLists[i], &freePlayer);
    }
    for(int i = 0; i < MAX_SNAKES; i++)
    {
        freeList(snakeLists[i], &freeSnake);
    }
}

void printMap(Player players[], Snake snakes[])
{
    system("tput cup 0 0");

    // Process lantern collection for each player
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].yCoord >= 0 && players[i].yCoord < players[i].rows && players[i].xCoord >= 0 && players[i].xCoord < players[i].cols && players[i].mapData[players[i].yCoord][players[i].xCoord] == 2)
        {
            // Set lantern coordinates and mark as collected
            players[i].lanternXCoord = players[i].xCoord;
            players[i].lanternYCoord = players[i].yCoord;
            players[i].lanternCollected = 1;
        }
    }

    // Check revival and card collection conditions for Player 1 and Player 2
    if(players[0].dead == 0 && players[1].dead == 1)
    {
        // Player 1 is alive, Player 2 is dead
        // Place the reboot card at Player 2's last position if not collected
        if(players[0].cardCollected == 0)
        {
            players[1].mapData[players[1].yCoord][players[1].xCoord] = 6;
        }
        else
        {
            players[1].mapData[players[1].yCoord][players[1].xCoord] = 0;  // Clear card if collected
        }

        // Player 1 collects the card at Player 2's position
        if(players[0].mapData[players[0].yCoord][players[0].xCoord] == 6)
        {
            players[0].cardCollected = 1;
            players[1].mapData[players[1].yCoord][players[1].xCoord] = 0; // Remove card from map
            players[1].sight = -1; // Update sight of the dead player

            // Return lantern if Player 2 had it
            if(players[1].lanternCollected == 1)
            {
                if(players[1].lanternYCoord >= 0 && players[1].lanternYCoord < players[1].rows && players[1].lanternXCoord >= 0 && players[1].lanternXCoord < players[1].cols)
                {
                    players[1].mapData[players[1].lanternYCoord][players[1].lanternXCoord] = 2;
                }
                players[1].lanternCollected = 0;
            }
        }
    }
    else if(players[0].dead == 1 && players[1].dead == 0)
    {
        // Player 2 is alive, Player 1 is dead
        // Place the reboot card at Player 1's last position if not collected
        if(players[1].cardCollected == 0)
        {
            players[0].mapData[players[0].yCoord][players[0].xCoord] = 6;
        }
        else
        {
            players[0].mapData[players[0].yCoord][players[0].xCoord] = 0;  // Clear card if collected
        }

        // Player 2 collects the card at Player 1's position
        if(players[1].mapData[players[1].yCoord][players[1].xCoord] == 6)
        {
            players[1].cardCollected = 1;
            players[0].mapData[players[0].yCoord][players[0].xCoord] = 0;  // Remove card from map
            players[0].sight = -1;  // Update sight of the dead player

            // Return lantern if Player 1 had it
            if(players[0].lanternCollected == 1)
            {
                if(players[0].lanternYCoord >= 0 && players[0].lanternYCoord < players[0].rows && players[0].lanternXCoord >= 0 && players[0].lanternXCoord < players[0].cols)
                {
                    players[0].mapData[players[0].lanternYCoord][players[0].lanternXCoord] = 2;
                }
                players[0].lanternCollected = 0;
            }
        }
    }

    writeToTerminal(players[0].mapData, players[0].rows, players[0].cols, &players[0].xCoord, &players[0].yCoord, &players[1].xCoord, &players[1].yCoord, players[0].sight, players[1].sight);

    keyBinds();

    // Position coordinates below controls
    printf("\033[9;60H");
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        printf("Player%d: [%d][%d]\n", i+1, players[i].xCoord, players[i].yCoord);
        printf("\033[%d;60H", 10+i);  // Next line, same column
    }

    printf("\033[11;60H");
    for(int i = 0; i < MAX_SNAKES; i++)
    {
        printf("Snake%d:  [%d][%d]\n", i+1, snakes[i].xCoord, snakes[i].yCoord);
        printf("\033[%d;60H", 12+i);
    }

    // Time and messages at bottom
    time_t current_time;
    time(&current_time);
    printf("\033[16;60H%s", ctime(&current_time));

    // Wall hit messages
    int msg_line = 17;
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].hitWallFlag)
        {
            printf("\033[%d;60HOUCH, WHO PUT THIS WALL HERE?!\n", msg_line++);
            players[i].hitWallFlag = 0;
        }
    }

    if(players[0].cardCollected == 1)
    {
        printf("\033[18;60HPlayer 2's reboot card has been collected!\n");
    }
    if(players[1].cardCollected == 1)
    {
        printf("\033[18;60HPlayer 1's reboot card has been collected!\n");
    }
}

void keyBinds()
{
    // Move cursor to right side for controls
    printf("\033[2;60H");  // Move to line 2, column 40
    printf("Press 'w | i' to move up");
    printf("\033[3;60H");
    printf("Press 'a | j' to move left");
    printf("\033[4;60H");
    printf("Press 's | k' to move down");
    printf("\033[5;60H");
    printf("Press 'd | l' to move right");
    printf("\033[6;60H");
    printf("Press 'q | u' to undo");
    printf("\033[7;60H");
    printf("Press 'e' to exit\n");
}

int winCondition(Player players[], Snake snakes[])
{
    int playersAlive = MAX_PLAYERS;

    // Check if any player found treasure
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].mapData[players[i].yCoord][players[i].xCoord] == 5)
        {
            printf("\033[19;60HPlayer %d found the treasure!\n", i + 1);
            fflush(stdout);
            sleep(1);
            return 1;
        }

        if(players[i].dead) // Count how many players are still alive
        {
            playersAlive--;
        }
    }

    // Check for new snake collisions
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(!players[i].dead) // Only check living players
        {
            for(int j = 0; j < MAX_SNAKES; j++)
            {
                if(players[i].yCoord == snakes[j].yCoord && players[i].xCoord == snakes[j].xCoord)
                {
                    players[i].dead = 1;
                    playersAlive--;
                    printf("\033[19;60HPlayer %d was bitten!\n", i + 1);

                    if((!players[0].dead && players[1].dead))
                    {
                        players[1].mapData[players[1].yCoord][players[1].xCoord] = 6;
                    }
                    else if((players[0].dead && !players[1].dead))
                    {
                        players[0].mapData[players[0].yCoord][players[0].xCoord] = 6;
                    }
                    break; // This entire section is needed to display the printf message
                }
            }
        }
    }

    if(playersAlive <= 0)
    {
        printf("\033[19;60HAll players have been bitten!\n");
        return 1;
    }

    return 0;
}