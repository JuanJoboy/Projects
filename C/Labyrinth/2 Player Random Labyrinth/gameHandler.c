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
int messageCounter = 2;

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
        usleep(1000000); // Sleep for 1 second
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

void loadingScreen()
{
    char skip = 0;
    system("clear");  // Clear screen first
    
    while(skip != 'e')
    {
        toolTips();
        
        disableBuffer();
        scanf(" %c", &skip);
        enableBuffer();
        
        system("clear");  // Clear for next iteration
    }
}


void toolTips()
{
    system("clear");
    printf("\033[H");  // ANSI escape code to move the cursor to the top-left corner

    printf("\n\n");
    printf("     Welcome to Labyrinth!\n");
    printf("     ========================\n\n");

    printf("     Player 1 Controls:               Player 2 Controls:\n");
    printf("     -----------------------          -----------------------\n");
    printf("     Press 'w' to move up             Press 'i' to move up\n");
    printf("     Press 'a' to move left           Press 'j' to move left\n");
    printf("     Press 's' to move down           Press 'k' to move down\n");
    printf("     Press 'd' to move right          Press 'l' to move right\n");
    printf("     Press 'q' to undo                Press 'u' to undo\n\n");

    printf("     Information\n");
    printf("     -----------\n");
    printf("     * There are 2 players on the map\n");
    printf("     * Player 1 can be identified by the '1' on the map, and player 2 can be identified by the '2' on the map\n");
    printf("     * In dark mode, the player can see themselves and 2 tiles around them\n");
    printf("     * In dark mode, spaces that the player can walk on can be identified by '.'\n");
    printf("     * In dark mode, blank spaces are areas that the player can't see\n");
    printf("     * In non-dark mode, blank spaces are the areas that the player can walk on\n\n");

    printf("     * '   ' represents walkable space\n");
    printf("     * '▧  ' represents walls\n");
    printf("     * '🔦 ' represents the flashlight\n");
    printf("     * '🛡️  ' represents the shield\n");
    printf("     * '💰 ' represents the treasure\n");
    printf("     * '❤️  ' represents the heart\n");
    printf("     * '🚑 ' represents the ambulance\n");
    printf("     * '🐍 ' represents the snake\n\n");

    printf("     * There are 5 snakes around the map that move every 1 second \n");
    printf("     * If a snake is 1 tile away from a player, then they are in the danger zone and can be killed\n");
    printf("     * Snakes can't go through anything on the map other than the players and walkable areas\n");
    printf("     * If the snake kills both players, you lose\n\n");

    printf("     Game Mechanics\n");
    printf("     --------------\n");
    printf("     * The aim of the game is to find the treasure\n");
    printf("     * If either player goes onto the 💰, you win\n\n");

    printf("     * You nor the snake can go through walls\n\n");

    printf("     * There are 2 flashlights and 2 shields on the map\n");
    printf("     * The flashlight enhances your sight from seeing 2 tiles around you, to seeing 4 tiles around you\n");
    printf("     * The flashlight is a permanent buff until you die\n\n");

    printf("     * The shield gives you the ability to go through snakes without dying\n");
    printf("     * The shield is a one time buff and lasts 8 seconds, with the buff starting immediately on pick-up\n");
    printf("     * Once the 8 seconds are up, the shield breaks and your immunity goes away\n\n");

    printf("     * There is 1 ambulance on the map, this ambulance allows for dead players to come back\n");
    printf("     * When a player dies, their number (either 1 or 2) turns into a heart\n");
    printf("     * If the dead player had a flashlight, then they'll drop it and it'll reappear at where it spawned\n");
    printf("     * If they picked up both flashlights, only the most recently picked up flashlight will be placed back on the map\n");
    printf("     * In order to revive a dead player, the other player must go to the tile with the heart\n");
    printf("     * Once on the tile, the heart will be picked up\n");
    printf("     * Now find the ambulance and wait on its tile for 5 seconds\n");
    printf("     * After 5 seconds, the dead player will be revived at the ambulance\n");
    printf("     * They may not be visually shown after 5 seconds but they will be there, so just move around\n");
    printf("     * Revived players always spawn with a sight of 2\n");
    printf("     * Players can be revived infinitely and the ambulance doesn't have a cooldown\n\n");

    printf("     Visual Bugs\n");
    printf("     -----------\n");
    printf("     * You go invisible when you enter a tile where a player just died, and when a snake goes on you when you have a shield\n");
    printf("     * The 'Player's heart has been collected' message can appear if the snake kills you while your on its tile\n\n");

    printf("     Start Game\n");
    printf("     ----------\n");
    printf("     Scroll up to see all the tool tips\n");
    printf("     Press 'e' to enter the labyrinth...\n");
}

void printMap(Player players[], Snake snakes[])
{
    system("tput cup 0 0");

    // Process flashlight collection for each player
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].yCoord >= 0 && players[i].yCoord < players[i].rows && players[i].xCoord >= 0 && players[i].xCoord < players[i].cols && players[i].mapData[players[i].yCoord][players[i].xCoord] == 2)
        {
            // Set flashlight coordinates and mark as collected
            players[i].flashlightXCoord = players[i].xCoord;
            players[i].flashlightYCoord = players[i].yCoord;
            players[i].flashlightCollected = 1;
        }
    }

    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].yCoord >= 0 && players[i].yCoord < players[i].rows && players[i].xCoord >= 0 && players[i].xCoord < players[i].cols && players[i].mapData[players[i].yCoord][players[i].xCoord] == 9)
        {
            players[i].shieldXCoord = players[i].xCoord;
            players[i].shieldYCoord = players[i].yCoord;
            players[i].shieldCollected = 1;
        }
    }

    // Check revival and card collection conditions for Player 1 and Player 2
    if(players[0].dead == 0 && players[1].dead == 1)
    {
        // Player 1 is alive, Player 2 is dead
        // Place the heart at Player 2's last position if not collected
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

            // Return flashlight if Player 2 had it
            if(players[1].flashlightCollected == 1)
            {
                if(players[1].flashlightYCoord >= 0 && players[1].flashlightYCoord < players[1].rows && players[1].flashlightXCoord >= 0 && players[1].flashlightXCoord < players[1].cols)
                {
                    players[1].mapData[players[1].flashlightYCoord][players[1].flashlightXCoord] = 2;
                }
                players[1].flashlightCollected = 0;
            }

            // Return shield if Player 2 had it
            if(players[1].shieldCollected == 1)
            {
                if(players[1].shieldYCoord >= 0 && players[1].shieldYCoord < players[1].rows && players[1].shieldXCoord >= 0 && players[1].shieldXCoord < players[1].cols)
                {
                    players[1].mapData[players[1].shieldYCoord][players[1].shieldXCoord] = 9;
                }
                players[1].shieldCollected = 0;
            }
        }
    }
    else if(players[0].dead == 1 && players[1].dead == 0)
    {
        // Player 2 is alive, Player 1 is dead
        // Place the heart at Player 1's last position if not collected
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

            // Return flashlight if Player 1 had it
            if(players[0].flashlightCollected == 1)
            {
                if(players[0].flashlightYCoord >= 0 && players[0].flashlightYCoord < players[0].rows && players[0].flashlightXCoord >= 0 && players[0].flashlightXCoord < players[0].cols)
                {
                    players[0].mapData[players[0].flashlightYCoord][players[0].flashlightXCoord] = 2;
                }
                players[0].flashlightCollected = 0;
            }

            // Return shield if Player 1 had it
            if(players[0].shieldCollected == 1)
            {
                if(players[0].shieldYCoord >= 0 && players[0].shieldYCoord < players[0].rows && players[0].shieldXCoord >= 0 && players[0].shieldXCoord < players[0].cols)
                {
                    players[0].mapData[players[0].shieldYCoord][players[0].shieldXCoord] = 9;
                }
                players[0].shieldCollected = 0;
            }
        }
    }

    writeToTerminal(players[0].mapData, players[0].rows, players[0].cols, &players[0].xCoord, &players[0].yCoord, &players[1].xCoord, &players[1].yCoord, players[0].sight, players[1].sight);

    keyBinds();

    // Position coordinates below controls
    printf("\033[15;90H");
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        printf("Player%d: [%d][%d]\n", i+1, players[i].xCoord, players[i].yCoord);
        printf("\033[%d;90H", 16+i);  // Next line, same column
    }

    printf("\033[18;90H");
    for(int i = 0; i < MAX_SNAKES; i++)
    {
        printf("Snake%d:  [%d][%d]\n", i+1, snakes[i].xCoord, snakes[i].yCoord);
        printf("\033[%d;90H", 19+i);
    }

    // Time and messages at bottom
    time_t current_time;
    time(&current_time);
    printf("\033[26;90H%s", ctime(&current_time));

    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].hitWallFlag)
        {
            printf("\033[27;90HOUCH, WHO PUT THIS WALL HERE?!\n");
            players[i].hitWallFlag = 0;
        }
    }

    if(players[0].shieldCollected == 1 && players[0].shieldEndTime > time(NULL))
    {
        printf("\033[28;90HShield Time Remaining: %d seconds\n", (int)(players[0].shieldEndTime - time(NULL)));
    }
    if(players[1].shieldCollected == 1 && players[1].shieldEndTime > time(NULL))
    {
        printf("\033[29;90HShield Time Remaining: %d seconds\n", (int)(players[1].shieldEndTime - time(NULL)));
    }

    if(players[0].cardCollected == 1)
    {
        printf("\033[30;90HPlayer 2's heart has been collected!\n");
    }
    if(players[1].cardCollected == 1)
    {
        printf("\033[30;90HPlayer 1's heart has been collected!\n");
    }
}

void keyBinds()
{
    printf("\033[2;90HPlayer 1 Controls:          Player 2 Controls:\n");
    printf("\033[3;90H------------------          ------------------\n");
    printf("\033[4;90Hw - up                      i - up\n");
    printf("\033[5;90Ha - left                    j - left\n");
    printf("\033[6;90Hs - down                    k - down\n");
    printf("\033[7;90Hd - right                   l - right\n");
    printf("\033[8;90H1 - up-left                 7 - up-left\n");
    printf("\033[9;90H2 - down-right              8 - down-right\n");
    printf("\033[10;90H3 - up-right                9 - up-right\n");
    printf("\033[11;90H4 - down-left               0 - down-left\n");
    printf("\033[12;90Hq - undo                    u - undo\n\n");
    printf("\033[13;90He - exit                    e - exit\n");
}

int winCondition(Player players[], Snake snakes[])
{
    int playersAlive = MAX_PLAYERS;

    // Check if any player found treasure
    for(int i = 0; i < MAX_PLAYERS; i++)
    {
        if(players[i].mapData[players[i].yCoord][players[i].xCoord] == 5)
        {
            printf("\033[28;90HPlayer %d found the treasure!\n", i + 1);
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
                if(players[i].yCoord == snakes[j].yCoord && players[i].xCoord == snakes[j].xCoord && players[i].shieldCollected == 0)
                {
                    players[i].dead = 1;
                    playersAlive--;
                    printf("\033[28;90HPlayer %d was bitten!\n", i + 1);

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

                if(players[i].yCoord == snakes[j].yCoord && players[i].xCoord == snakes[j].xCoord && players[i].shieldCollected == 1)
                {
                    printf("\033[28;90HPlayer %d deflected a vile snake!\n", i + 1);
                    break; // This entire section is needed to display the printf message
                }
            }
        }
    }

    if(playersAlive <= 0)
    {
        printf("\033[28;90HAll players have been bitten!\n");
        return 1;
    }

    return 0;
}