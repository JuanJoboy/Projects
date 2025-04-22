#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"
#include "gameHandler.h"

linkedList *createList() /* Returns a pointer to a new linked list */
{
    linkedList *tempList = NULL;
    tempList = (linkedList *)malloc(sizeof(linkedList)); /* Allocates memory for the linked list */

    tempList->head = NULL; /* Sets the head of the linked list to NULL */

    return tempList; /* Returns the linked list */
}

void insertFirst(linkedList *list, void *newData)
{
    node *newNode = NULL;
    newNode = (node *)malloc(sizeof(node)); /* Allocates memory for a new node */

    newNode->data = newData; /* Sets the data of the new node to the input data */
    newNode->next = list->head; /* Sets the next pointer of the new node to the current head of the linked list */
    list->head = newNode; /* Sets the head of the linked list to the new node */
}

void *removeFirst(linkedList *list) /* Returns a pointer to the data of the removed node */
{
    void *tempData = NULL; /* If the list is empty, return NULL */
    node *tempNode = NULL;
    
    tempNode = list->head; /* Store the current head node */

    if(list->head != NULL)
    {
        tempData = tempNode->data; /* Assigns the first node's "data" to tempData */

        list->head = list->head->next; /* Makes the head point to the second node in the list (if it doesn't exist, it's NULL). Thus removing the first node */

        free(tempNode); /* Free the old head node */
        tempNode = NULL;
    }
    
    return tempData; /* Return the data of the removed node or NULL if the list was empty */
}

void freeList(linkedList *list, void (*freer)(void *data))
{
    node *current = list->head; /* Sets the current node to the head of the linked list */
    node *temp; /* Creates a temp pointer to the next node */

    while(current != NULL)
    {
        temp = current->next; /* Sets the temp node to the next node of the current node */

        (*freer)(current->data); /* Frees the memory allocated for the data of the current node */
        current->data = NULL;

        free(current); /* Frees the memory allocated for the current node */
        current = NULL;
        
        current = temp; /* Moves to the next node */
    }

    free(list);
    list = NULL;
}

void freePlayer(void *data)
{
    Player *player = (Player*) data; /* Since the list is generic and uses void datatypes, I have to cast it to Player */
    free(player);
}

void freeSnake(void *data)
{
    Snake *snake = (Snake*) data;
    free(snake);
}