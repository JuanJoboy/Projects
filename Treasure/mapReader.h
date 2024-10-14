#ifndef MAPREADER_H
#define MAPREADER_H

#define FALSE 0
#define TRUE !FALSE

int openAndReadDimensions(char* filename, FILE** file, int* rows, int* cols);
int** allocateData(int rows, int cols);
int readData(FILE* file, int** data, int rows, int cols);
void writeToTerminal(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight);
void notDark(int** data, int rows, int cols, int* xCoord, int* yCoord);
void darkVision(int** data, int rows, int cols, int* xCoord, int* yCoord, int sight);
int playerFinder(int** data, int rows, int cols, int* xCoord, int* yCoord);
int snakeFinder(int** data, int rows, int cols, int* xCoord, int* yCoord);
void cleanupData(int** data, int rows);
void handleError(char* message);

#endif