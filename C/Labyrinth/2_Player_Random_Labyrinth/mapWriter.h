#ifndef MAPWRITER_H
#define MAPWRITER_H

char* mapName();
int tempOrPerm();
int rowDim();
int colDim();
void writeMap();
int isOverWalled(int** data, int i, int j, int rows, int cols);

#endif