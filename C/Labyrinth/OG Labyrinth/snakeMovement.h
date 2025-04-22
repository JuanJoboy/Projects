#ifndef SNAKEMOVEMENT_H
#define SNAKEMOVEMENT_H
#include "gameHandler.h"
#include "linkedList.h"

void snakeCoords(Snake *snakeState, Player *playerState, linkedList *list);
void restrictMovement(Snake *state);
void snakeMove(Snake *state, linkedList *list);
void killPlayer(Snake *snakeState, Player *playerState);
void killPlayerHelper(Snake *snakeState, Player *playerState);
void undoSnake(Snake *state, linkedList *list);
void initializeSnake(Snake *state, int rows, int cols, int **data);

#endif