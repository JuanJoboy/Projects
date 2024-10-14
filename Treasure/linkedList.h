#ifndef LINKEDLIST_H
#define LINKEDLIST_H

typedef struct node
{
    void *data;
    struct node *next;
} node;

typedef struct linkedList
{
    node *head;
} linkedList;

linkedList *createList();
void insertFirst(linkedList *list, void *newData);
void *removeFirst(linkedList *list);
void freeList(linkedList *list, void (*freer)(void* data));
void freePlayer(void *data);
void freeSnake(void *data);

#endif