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
import pygame

def update_volume(volume):
    pygame.mixer.music.set_volume(float(volume) / 100)  # Normalize volume to 0-1 range

def play_audio(filename):
    pygame.init()
    pygame.mixer.init()
    pygame.mixer.music.load(filename)
    pygame.mixer.music.play()
    
    volume_slider = Scale(window, from_ = 0, to = 100, orient = HORIZONTAL, command = update_volume)
    volume_slider.pack()  # Add slider to the window

def button():
    button_image = PhotoImage(file = 'Python\\Jigsaw\\Images\\button_image.png')    

    button = Button(window,
                text = "click me!",
                activeforeground = '#00FF00', # Stops from flashing
                activebackground = 'black', # Stops from flashing
                font = ('Arial', 40, 'bold'),
                fg = '#00FF00',
                bg = 'black',
                relief = RAISED,
                bd = 15,
                image = button_image,
                compound = 'bottom')
    button.pack()

def achievements():
    pass

def settings():
    pass

def get_username():
    username = entry.get()
    print(username)
    entry.config(state = NORMAL)  # Disable entry box after submission

def create_text_input():
    global entry  # Declare entry as global to access it in get_username

    entry = Entry(window,
                font=('Arial', 40, 'bold'),
                fg='#00FF00',  # Green foreground text
                bg='black',     # Black background
                relief=RAISED,
                bd=15)
    entry.insert(0, ' ')  # Add a leading space for placeholder (optional)
    entry.pack()

    submit_button = Button(window, text="Submit", command=get_username)
    submit_button.pack(side=RIGHT)

    delete_button = Button(window, text="Delete", command=lambda: entry.delete(0, END))
    delete_button.pack(side=RIGHT)

    backspace_button = Button(window, text="Backspace", command=lambda: entry.delete(END, ANCHOR - 1))
    backspace_button.pack(side=RIGHT)

def opening():
    print("Welcome to the game")

def main_menu():
    window.state('zoomed')
    window.title("Jigsaw")
    
    icon = PhotoImage(file = 'Python\\Jigsaw\\Images\\controller_icon.png')
    window.iconphoto(True,icon)
    
    window.config(background = "sky blue")
    
    play_audio('Python\Jigsaw\Audio\main_menu.mp3')
    
    create_text_input()

window = Tk()

main_menu()

window.mainloop()

# finger - radiobutton, continent - click places on a map image, highlight correct answer somehow, psr - radiobutton, blackjack - display card images when hitting