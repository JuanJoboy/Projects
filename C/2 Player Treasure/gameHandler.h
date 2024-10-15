#ifndef GAMEHANDLER_H
#define GAMEHANDLER_H

typedef struct
{
    int** mapData;
    int rows;
    int cols;
    int xCoord;
    int yCoord;
    int undoX;
    int undoY;
    int hitWallFlag;
    int lanternCollected;
    int lanternXCoords;
    int lanternYCoords;
    int sight;
} Player;

typedef struct
{
    int** mapData;
    int rows;
    int cols;
    int xCoord;
    int yCoord;
    int undoX;
    int undoY;
    int hitWallFlag;
} Snake;

void characterInput(Player *playerState, Snake *snakeState);
void printMap(Player *playerState, Snake *snakeState);
void keyBinds();
int winCondition(Player *playerState, Snake *snakeState);

#endif