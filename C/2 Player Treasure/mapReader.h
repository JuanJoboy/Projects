#ifndef MAPREADER_H
#define MAPREADER_H
#include "gameHandler.h"

#define FALSE 0
#define TRUE !FALSE

int openMap(char* filename, FILE** file, int* rows, int* cols);
int** allocateMapMemory(int rows, int cols);
void readMap(FILE* file, int** data, int rows, int cols);
void writeToTerminal(int** data, int rows, int cols, int* xCoord1, int* yCoord1, int* xCoord2, int* yCoord2, int sight, int sight2);
void notDark(int** data, int rows, int cols, int* xCoord, int* yCoord);
void darkVision(int** data, int rows, int cols, int* xCoord1, int* yCoord1, int* xCoord2, int* yCoord2, int sight, int sight2);
void player1Finder(int** data, int rows, int cols, int* xCoord, int* yCoord);
void player2Finder(int** data, int rows, int cols, int* xCoord, int* yCoord);
void snakeFinder(int **data, int rows, int cols, int *xCoord, int *yCoord);
void vanFinder(int** data, int rows, int cols, int* vanXCoord, int* vanYCoord);
void cleanupData(int** data, int rows);
void errorMessage(char* message);

#endif