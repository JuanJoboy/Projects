#ifndef GAMEHANDLER_H
#define GAMEHANDLER_H
#include "linkedList.h"
#include <time.h>

#define MAX_PLAYERS 2
#define MAX_SNAKES 4

typedef struct
{
    int **mapData;
    int rows;
    int cols;
    int xCoord;
    int yCoord;
    int prevX;
    int prevY;
    int hitWallFlag;
    int lanternCollected;
    int lanternXCoord;
    int lanternYCoord;
    int sight;
    int dead;
    int vanXCoord;
    int vanYCoord;
    int cardCollected;
    int waitingForRevive;
    time_t startTime;
    time_t endTime;
    int shieldCollected;
    int shieldXCoord;
    int shieldYCoord;
    time_t shieldStartTime;
    time_t shieldEndTime;
} Player;

typedef struct
{
    int **mapData;
    int rows;
    int cols;
    int xCoord;
    int yCoord;
    int prevX;
    int prevY;
} Snake;

typedef struct
{
    Player *players;
    Snake *snakes;
    linkedList **snakeLists;
} ThreadArgs;

void runGame(Player players[], Snake snakes[]);
void printMap(Player players[], Snake snakes[]);
void keyBinds();
int winCondition(Player players[], Snake snakes[]);

#endif