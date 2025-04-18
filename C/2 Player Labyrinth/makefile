CC = gcc
CFLAGS = -Wall -pedantic -g -Werror
VALGRIND_FLAGS = --leak-check=full -s --show-leak-kinds=all --track-origins=yes
OBJ = main.o terminal.o random.o newSleep.o mapReader.o gameHandler.o playerMovement.o snakeMovement.o linkedList.o
EXEC = labyrinth
HARDCORE = hardcore.txt
TUTORIAL = tutorial.txt
EXPERT = expert.txt
MAS = mas.txt
ALIZA = aliza.txt
COOL = cool.txt
IMPOSSIBLE = impossible.txt
MAZE = maze.txt

ifdef DARK
CFLAGS += -D DARK
DARK : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

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

gameHandler.o : gameHandler.c gameHandler.h newSleep.h terminal.h mapReader.h playerMovement.h
	$(CC) $(CFLAGS) gameHandler.c -c

playerMovement.o : playerMovement.c playerMovement.h gameHandler.h mapReader.h snakeMovement.h linkedList.h
	$(CC) $(CFLAGS) playerMovement.c -c

snakeMovement.o : snakeMovement.c snakeMovement.h random.h mapReader.h linkedList.h
	$(CC) $(CFLAGS) snakeMovement.c -c

linkedList.o : linkedList.c linkedList.h gameHandler.h
	$(CC) $(CFLAGS) linkedList.c -c

# make runHard
runHard : clean $(EXEC)
	./$(EXEC) $(HARDCORE)

# make runDarkHard
runDarkHard: clean
	$(MAKE) DARK=1
	./$(EXEC) $(HARDCORE)

# make runExpert
runExpert : clean $(EXEC)
	./$(EXEC) $(EXPERT)

# make runDarkExpert
runDarkExpert: clean
	$(MAKE) DARK=1
	./$(EXEC) $(EXPERT)

# make runTutorial
runTutorial : clean $(EXEC)
	./$(EXEC) $(TUTORIAL)

# make runDarkTutorial
runDarkTutorial: clean
	$(MAKE) DARK=1
	./$(EXEC) $(TUTORIAL)

runDarkMas: clean
	$(MAKE) DARK=1
	./$(EXEC) $(MAS)

runMas : clean $(EXEC)
	./$(EXEC) $(MAS)

runDarkAliza: clean
	$(MAKE) DARK=1
	./$(EXEC) $(ALIZA)

runAliza : clean $(EXEC)
	./$(EXEC) $(ALIZA)

runDarkCool: clean
	$(MAKE) DARK=1
	./$(EXEC) $(COOL)

runCool : clean $(EXEC)
	./$(EXEC) $(COOL)
	
runDarkImpossible: clean
	$(MAKE) DARK=1
	./$(EXEC) $(IMPOSSIBLE)

runImpossible : clean $(EXEC)
	./$(EXEC) $(IMPOSSIBLE)

runDarkMaze: clean
	$(MAKE) DARK=1
	./$(EXEC) $(MAZE)

runMaze : clean $(EXEC)
	./$(EXEC) $(MAZE)

# make valgrind
valgrind: $(EXEC) $(HARDCORE)
	valgrind $(VALGRIND_FLAGS) ./$(EXEC) $(HARDCORE)

# make clean
clean :
	rm -f $(EXEC) $(OBJ)    