CC = gcc
CFLAGS = -Wall -pedantic -g -Werror -pthread
VALGRIND_FLAGS = --leak-check=full -s --show-leak-kinds=all --track-origins=yes
OBJ = main.o terminal.o random.o newSleep.o mapReader.o gameHandler.o playerMovement.o snakeMovement.o linkedList.o mapWriter.o
EXEC = labyrinth

ifdef DARK
CFLAGS += -D DARK
DARK : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) $(LIBS)

main.o : main.c random.h mapReader.h gameHandler.h playerMovement.h snakeMovement.h
	$(CC) $(CFLAGS) main.c -c 

terminal.o : terminal.c terminal.h
	$(CC) $(CFLAGS) terminal.c -c

random.o : random.c random.h
	$(CC) $(CFLAGS) random.c -c

newSleep.o : newSleep.c newSleep.h
	$(CC) $(CFLAGS) newSleep.c -c

mapReader.o : mapReader.c mapReader.h gameHandler.h
	$(CC) $(CFLAGS) mapReader.c -c

mapWriter.o : mapWriter.c mapWriter.h
	$(CC) $(CFLAGS) mapWriter.c -c

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
	./$(EXEC)

# make runDark
runDark: clean
	$(MAKE) DARK=1
	./$(EXEC)

# make valgrind
valgrind: clean $(EXEC)
	valgrind $(VALGRIND_FLAGS) ./$(EXEC)

# make clean
clean :
	rm -f $(EXEC) $(OBJ)

# zips the source code - make zip
zip : clean
	zip -r ../2_Player_Random_Labyrinth.zip . -i "*.c" "*.h" "makefile" "README.txt"