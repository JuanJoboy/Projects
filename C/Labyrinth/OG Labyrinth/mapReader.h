#ifndef MAPREADER_H
#define MAPREADER_H

#define FALSE 0
#define TRUE !FALSE

int openMap(char* filename, FILE** file, int* rows, int* cols);
int** allocateMapMemory(int rows, int cols);
void readMap(FILE* file, int** data, int rows, int cols);
void writeToTerminal(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight);
void notDark(int** data, int rows, int cols, int* xCoord, int* yCoord);
void darkVision(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight);
void playerFinder(int** data, int rows, int cols, int* xCoord, int* yCoord);
void snakeFinder(int** data, int rows, int cols, int* xCoord, int* yCoord);
void cleanupData(int** data, int rows);
void errorMessage(char* message);

#endif