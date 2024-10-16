#ifndef PLAYERMOVEMENT_H
#define PLAYERMOVEMENT_H
#include "gameHandler.h"
#include "linkedList.h"

int playerCoords(Player *playerState, Snake *snakeState, char ch, linkedList *playerList, linkedList *snakeList);
void movement(Player *state, char ch, linkedList *list);
void undoMovement(Player *state, linkedList *list);
void hitWall(Player *state);
int initializePlayer(Player *state, int rows, int cols, int** data);

#endif