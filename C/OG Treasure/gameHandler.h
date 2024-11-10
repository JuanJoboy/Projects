#ifndef GAMEHANDLER_H
#define GAMEHANDLER_H

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
    int hitWallFlag;
} Snake;

void runGame(Player *playerState, Snake *snakeState);
void printMap(Player *playerState, Snake *snakeState);
void keyBinds();
int winCondition(Player *playerState, Snake *snakeState);

#endif