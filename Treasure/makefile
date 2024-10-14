CC = gcc
CFLAGS = -Wall -ansi -pedantic
VALGRIND_FLAGS = --leak-check=full -s --show-leak-kinds=all --track-origins=yes
OBJ = treasure.o terminal.o random.o newSleep.o mapReader.o gameHandler.o playerMovement.o snakeMovement.o linkedList.o
EXEC = treasure
PARAMETER = map.txt

ifdef DARK
CFLAGS += -D DARK
DARK : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

treasure.o : treasure.c random.h mapReader.h gameHandler.h playerMovement.h snakeMovement.h
	$(CC) $(CFLAGS) treasure.c -c 

terminal.o : terminal.c terminal.h
	$(CC) $(CFLAGS) terminal.c -c

random.o : random.c random.h
	$(CC) $(CFLAGS) random.c -c

newSleep.o : newSleep.c newSleep.h
	$(CC) $(CFLAGS) newSleep.c -c

mapReader.o : mapReader.c mapReader.h
	$(CC) $(CFLAGS) mapReader.c -c

gameHandler.o : gameHandler.c gameHandler.h newSleep.h terminal.h mapReader.h playerMovement.h
	$(CC) $(CFLAGS) gameHandler.c -c

playerMovement.o : playerMovement.c playerMovement.h gameHandler.h mapReader.h snakeMovement.h linkedList.h
	$(CC) $(CFLAGS) playerMovement.c -c

snakeMovement.o : snakeMovement.c snakeMovement.h random.h mapReader.h linkedList.h
	$(CC) $(CFLAGS) snakeMovement.c -c

linkedList.o : linkedList.c linkedList.h gameHandler.h
	$(CC) $(CFLAGS) linkedList.c -c

# make run
run : clean $(EXEC)
	./$(EXEC) $(PARAMETER)

# make runDark (enables dark mode)
runDark: clean
	$(MAKE) DARK=1
	./$(EXEC) $(PARAMETER)

# make valgrind
# Press 'e' to exit the game and see the full valgrind
valgrind: $(EXEC) $(PARAMETER)
	valgrind $(VALGRIND_FLAGS) ./$(EXEC) $(PARAMETER)

# make clean
clean :
	rm -f $(EXEC) $(OBJ)    