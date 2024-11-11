#ifndef PLAYERMOVEMENT_H
#define PLAYERMOVEMENT_H
#include "gameHandler.h"
#include "linkedList.h"

int playerCoords(Player *playerState, Snake *snakeState, char keyBind, linkedList *playerList, linkedList *snakeList);
void movement(Player *state, char keyBind, linkedList *list);
void undoMovement(Player *state, linkedList *list);
void hitWall(Player *state);
void initializePlayer(Player *state, int rows, int cols, int **data);

#endif