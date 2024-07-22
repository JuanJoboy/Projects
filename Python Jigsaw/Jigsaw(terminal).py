from math import *
import time
import random
from os import *
from shutil import *
import threading
from tkinter import *
import random
from smtplib import *
import re

num_games = 0
user_wins = 0
sys_wins = 0
ties = 0
finger_num_games = 0
finger_user_wins = 0
finger_system_wins = 0
continent_num_games = 0
continent_user_wins = 0
continent_system_wins = 0
psr_num_games = 0
psr_user_wins = 0
psr_system_wins = 0
psr_ties = 0
bj_num_games = 0
bj_user_wins = 0
bj_system_wins = 0
bj_ties = 0

def opening():
    global name, start_time
    start_time = time.time() # Start timer when user enters name
    name = ""
    name = input("What is your name?: ")
    while len(name) == 0:
        name = input("Bruh you are not the nonchalant dread-head, enter a name: ")

def menu(name):
    open = True
    while open:
        print("\nHello " + name + ", From the menu below pick a game")
        print("\t> 1. How many fingers am I putting up? ")
        print("\t> 2. Which continent am I on?")
        print("\t> 3. Paper, Scissors, Rock.")
        print("\t> 4. Blackjack")
        print("\t> 5. Exit and see your final score.")
        
        try:
            choice = int(input("Game Choice: "))
            if choice < 1 or choice > 5 or choice == 42:
                choice = int(input("Enter in a valid choice: "))
            if choice == 1:
                finger_guessing()
            if choice == 2:
                continent_guessing()
            if choice == 3:
                paper_scissors_rock()
            if choice == 4:
                blackjack()
            elif choice == 5:
                scores()
                print("\nExiting, farewell " + name)
                exit()
            else:
                choice = int(input("Game Choice: "))
        except ValueError as e:
            print("Invalid input, please enter a number")

def finger_guessing():
    global num_games, user_wins, sys_wins, finger_num_games, finger_user_wins, finger_system_wins
    print("\nWelcome to the finger guessing game!")
    print("I have 5 fingers on my 2 hands FYI")
    
    open = True
    while open:
        try:
            choice = int(input("\nOk, so how many fingers am I putting up?: "))
            if choice < 0:
                print("\nHow can I have a negative amount of fingers?")
            elif choice > 10:
                print("\nI only have 10 fingers remember")
            else:
                break
        except ValueError:
            print("\nOnly enter an integer")
    
    fingers = random.randint(0,10)
    if choice and fingers == 1:
        print("\nYou guessed it! I was putting up", fingers, "finger.")
        user_wins += 1
        finger_user_wins += 1
        num_games += 1
        finger_num_games += 1
    elif (choice == fingers) and (choice != 1 and fingers != 1):
        print("\nYou guessed it! I was putting up", fingers, "fingers.")
        user_wins += 1
        finger_user_wins += 1
        num_games += 1
        finger_num_games += 1
    else:
        print("\nSorry, I was putting up", fingers, "fingers. You were off by", abs(choice - fingers))
        sys_wins += 1
        finger_system_wins += 1
        num_games += 1
        finger_num_games += 1
    
    finger_scores()
    
    if not play_again():
        menu(name)
    else:
        finger_guessing()
    
        
def continent_guessing():
    global num_games, user_wins, sys_wins, continent_num_games, continent_user_wins, continent_system_wins
    print("\nYou have picked the continent guessing game!")
    print("From these 7 continents - Asia, Africa, North America, South America, Antarctica, Europe, and Australia")
    continent_list = ["asia", "africa", "north america", "south america", "antarctica", "europe", "australia"]
    continents = random.choice(continent_list)

    open = True
    while open:
        choice = input("\nOk, so where am I?: ").lower()
        if re.match("[0-9]+", choice):
            print("\nNumbers aren't continents " + name + ". How about you try using letters ok?!")
        elif choice not in continent_list:
            print("\nEnter one of the 7 continents!")
        else:
            break
        
    if choice == continents:
        print("\nYou guessed it! I was in " + continents)
        user_wins += 1
        continent_user_wins += 1
        num_games += 1
        continent_num_games += 1
    else:
        print("\nYou absolute fool, I was in " + continents)
        sys_wins += 1
        continent_system_wins += 1
        num_games += 1
        continent_num_games += 1
    
    continent_scores()
    
    if not play_again():
        menu(name)
    else:
        continent_guessing()
    

def paper_scissors_rock():
    global num_games, user_wins, sys_wins, ties, psr_num_games, psr_user_wins, psr_system_wins, psr_ties
    print("You have picked paper, scissors, rock.")
    moves_list = ["paper", "scissors", "rock"]
    moves = random.choice(moves_list)

    open = True
    while open:
        choice = input("Enter in your choice: ").lower()
        if re.match(r"[0-9]+", choice):
            print("\nNumbers aren't one of the moves " + name + ". How about you try using one of the 3 moves ok?!")
        elif choice not in moves_list:
            print("\nEnter one of the 3 moves!")
        else:
            if choice == moves:
                print(name + ": " + choice)
                print("Computer: " + moves)
                print("Tie!")
                ties += 1
                psr_ties += 1
                num_games += 1
                psr_num_games += 1
            elif (choice == "paper" and moves == "rock") or (choice == "scissors" and moves == "paper") or (choice == "rock" and moves == "scissors"):
                print(name + ": " + choice)
                print("Computer: " + moves)
                print("You win!")
                user_wins += 1
                psr_user_wins += 1
                num_games += 1
                psr_num_games += 1
            else:
                print(name + ": " + choice)
                print("Computer: " + moves)
                print("You lose!")
                sys_wins += 1
                psr_system_wins += 1
                num_games += 1
                psr_num_games += 1
            break
    
    psr_scores()
    
    if not play_again():
        menu(name)
    else:
        paper_scissors_rock()

def blackjack():
    global num_games, user_wins, sys_wins, ties, bj_num_games, bj_user_wins, bj_system_wins, bj_ties
    print("\nYour final challenge, play a game of Blackjack.")

    cards_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11]
    user_score = 0
    computer_score = 0

    while user_score <= 21 and computer_score <= 21:
        user_card = random.choice(cards_list)
        computer_card = random.choice(cards_list)

        user_score += user_card
        computer_score += computer_card
        
        if computer_score > 21 and user_score < 21:
            print(f"\nDamn it, you drew {user_card} so you win with your score of {user_score}")
            print(f"I drew a {computer_card} making me have {computer_score}")
            bj_user_wins += 1
            bj_num_games += 1
            break
        elif computer_score < 21 and user_score > 21:
            print(f"\nI win, I drew {computer_card} giving me a score of {computer_score}")
            print(f"You drew a {user_card} making you have {user_score}")
            bj_system_wins += 1
            bj_num_games += 1
            break
        elif computer_score > 21 and user_score > 21:
            print(f"\nHeh, I drew a {computer_card} and you drew a {user_card}, I guess we both suck at this game since you're at {computer_score} and I'm at {user_score}")
            bj_ties += 1
            bj_num_games += 1
            break
        elif computer_score == 21 and user_score == 21:
            print(f"\nI drew a {computer_card} and you drew a {user_card}, it's a tie, we both have a score of {user_score}")
            bj_ties += 1
            bj_num_games += 1
            break
        elif computer_score == 21 and user_score != 21:
            print(f"\nI drew a {computer_card} so I win, my score is equal to {computer_score} and yours isn't")
            bj_system_wins += 1
            bj_num_games += 1
            break
        elif computer_score != 21 and user_score == 21:
            print(f"\nYou drew a {user_card} so you win, your score is equal to {user_score} and mine isn't")
            bj_user_wins += 1
            bj_num_games += 1
            break
        
        print(f"\nYou drew {user_card} and now your current score is: {user_score}")
        print(f"I drew {computer_card} and now my current score is: {computer_score}")
        print("Would you like to hit or stand")
        while not 'h' or 's':
            choice = input("Enter in your choice: ").lower()
            if choice == 'h' or choice == 's':
                break
            else:
                print("Invalid input. Please enter 'h' or 's'.")

        if choice == 's':
            print(f"\nYour final score is: {user_score}")
            print(f"My final score is: {computer_score}")
            if computer_score > 21 and user_score < 21:
                print(f"\nYou win with your score of {user_score}")
                bj_user_wins += 1
                bj_num_games += 1
                break
            elif computer_score < 21 and user_score > 21:
                print(f"\nI win with my score of {computer_score}")
                bj_system_wins += 1
                bj_num_games += 1
                break
            elif computer_score > 21 and user_score > 21:
                print(f"\nHeh, I guess we both suck at this game since you're at {user_score} and I'm at {computer_score}")
                bj_ties += 1
                bj_num_games += 1
                break
            elif user_score < 21 and computer_score < 21:
                if abs(21 - user_score) < abs(21 - computer_score):
                    print(f"\nYou win, your score of {user_score} is closer to 21 than my score of {computer_score}.")
                    bj_user_wins += 1
                    bj_num_games += 1
                    break
                elif abs(21 - user_score) > abs(21 - computer_score):
                    print(f"\nI win, my score of {computer_score} is closer to 21 than your score of {user_score}.")
                    bj_system_wins += 1
                    bj_num_games += 1
                    break
            elif computer_score == 21 and user_score == 21:
                print(f"\nIt's a tie, we both have a score of {user_score}")
                bj_ties += 1
                bj_num_games += 1
                break
            elif computer_score == 21 and user_score != 21:
                print(f"\nI win, my score is equal to {computer_score} and yours isn't")
                bj_system_wins += 1
                bj_num_games += 1
                break
            elif computer_score != 21 and user_score == 21:
                print(f"\nYou win, your score is equal to {user_score} and mine isn't")
                bj_user_wins += 1
                bj_num_games += 1
                break

    blackjack_scores()
    
    if not play_again():
        menu(name)
    else:
        blackjack()

def play_again():
    print("\nWould you like to play again?")
    print("\t> Y")
    print("\t> N")
    
    while not 'y' or 'n':
        play = input("Enter your choice: ").lower()
        if play == 'y':
            return True
        elif play == 'n':
            return False
        else:
            print("Invalid input. Please enter Y or N.")

def timer_total():
    end_time = time.time()
    total_time = end_time - start_time
    print("Your total playtime is", time.strftime("%H:%M:%S", time.gmtime(total_time)))

def finger_scores():
    print("\n" + name + " wins: #" + str(finger_user_wins) + " (Total: " + str(user_wins) + ")")
    print("System wins: #" + str(finger_system_wins) + " (Total: " + str(sys_wins) + ")")
    print("Number of games played: #" + str(finger_num_games) + " (Total: " + str(num_games) + ")")

def continent_scores():
    print("\n" + name + " wins: #" + str(continent_user_wins) + " (Total: " + str(user_wins) + ")")
    print("System wins: #" + str(continent_system_wins) + " (Total: " + str(sys_wins) + ")")
    print("Number of games played: #" + str(continent_num_games) + " (Total: " + str(num_games) + ")")

def psr_scores():
    print("\n" + name + " wins: #" + str(psr_user_wins) + " (Total: " + str(user_wins) + ")")
    print("System wins: #" + str(psr_system_wins) + " (Total: " + str(sys_wins) + ")")
    print("Ties: #" + str(psr_ties) + " (Total: " + str(ties) + ")")
    print("Number of games played: #" + str(psr_num_games) + " (Total: " + str(num_games) + ")")

def blackjack_scores():
    print("\n" + name + " wins: #" + str(bj_user_wins) + " (Total: " + str(user_wins) + ")")
    print("System wins: #" + str(bj_system_wins) + " (Total: " + str(sys_wins) + ")")
    print("Ties: #" + str(bj_ties) + " (Total: " + str(ties) + ")")
    print("Number of games played: #" + str(bj_num_games) + " (Total: " + str(num_games) + ")")

def scores():
    print("\nThanks for playing, " + name + "!")
    timer_total()
    print("Your final score is: ")

    games =[
        {"name": "Finger Guessing Game", "user_wins": finger_user_wins, "sys_wins": finger_system_wins, "num_games": finger_num_games},
        {"name": "Continent Guessing Game", "user_wins": continent_user_wins, "sys_wins": continent_system_wins, "num_games": continent_num_games},
        {"name": "Paper Scissors Rock", "user_wins": psr_user_wins, "sys_wins": psr_system_wins, "num_games": psr_num_games, "draw": psr_ties},
        {"name": "Blackjack", "user_wins": bj_user_wins, "sys_wins": bj_system_wins, "num_games": bj_num_games, "draw": bj_ties},
        {"name": "Total", "user_wins": user_wins, "sys_wins": sys_wins, "num_games": num_games, "draw": ties}]

    try:
        for game in games:
            print("\n" + game["name"])
            print(name + "'s wins: " + str(game["user_wins"]))
            print("System's wins: " + str(game["sys_wins"]))
            if "draw" in game:
                print("Games tied: " + str(game["draw"]))
            print("Games played: " + str(game["num_games"]))
            
            if game["num_games"] > 0:
                perc_user = int((game["user_wins"] / game["num_games"]) * 100)
                perc_sys = int((game["sys_wins"] / game["num_games"]) * 100)
                if "draw" in game:
                    perc_draw = int((game["draw"] / game["num_games"]) * 100)
                    print("You won " + str(perc_user) + "% of games, lost " + str(perc_sys) + "% of games and drew " + str(perc_draw) + "% of games")
                else:
                    print("You won " + str(perc_user) + "% of games and lost " + str(perc_sys) + "% of games")
    except ZeroDivisionError:
        if user_wins == 0 and num_games == 0:
            print("\nLMAO YOU SUCK!! YOU CAN'T WIN FOR SHIT")
            print("\nWait a second... You didn't play a game, Dude come on, don't be like that.")
            menu(name)
        elif user_wins == 0 and num_games != 0:
            print("\nLMAO YOU SUCK!! YOU CAN'T WIN FOR SHIT")
        if sys_wins == 0 and num_games != 0:
            print("\nOh damn you're good!")

opening()
menu(name)