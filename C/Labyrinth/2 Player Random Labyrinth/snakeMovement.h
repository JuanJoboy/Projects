#ifndef SNAKEMOVEMENT_H
#define SNAKEMOVEMENT_H
#include "gameHandler.h"
#include "linkedList.h"

void snakeCoords(Snake snakes[], Player players[], linkedList *snakeList[]);
void restrictMovement(Snake *state);
void snakeMove(Snake *state, linkedList *list);
void killPlayer(Snake *snakes, Player players[]);
void killPlayerHelper(Snake *snake, Player *player);
void undoSnake(Snake *state, linkedList *list);
void initializeSnake(Snake *state, int rows, int cols, int **data);

#endif