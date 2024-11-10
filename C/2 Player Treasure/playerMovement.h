#ifndef PLAYERMOVEMENT_H
#define PLAYERMOVEMENT_H
#include "gameHandler.h"
#include "linkedList.h"

int playerCoords(Player players[], Snake snakes[], char keyBind, linkedList *playerList[], linkedList *snakeList[]);
void movement1(Player *state, char keyBind, linkedList *list);
void movement2(Player *state, char keyBind, linkedList *list);
void undoMovement(Player *state, linkedList *list);
void hitWall(Player *state);
void initializePlayer1(Player *state, int rows, int cols, int **data);
void initializePlayer2(Player *state, int rows, int cols, int **data);

#endif