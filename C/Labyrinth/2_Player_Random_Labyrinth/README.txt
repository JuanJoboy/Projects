2 PLAYER RANDOM LABYRINTH
===================================

1. Game Overview
---------------
A 2-player cooperative maze game where players must find treasure while avoiding snakes.

Key Features:
* Random map generation
* Multiplayer cooperation 
* Multiple enemy snakes with AI movement
* Dark mode with limited vision
* Revival mechanics
* Shield powerups
* Undo movement system
* Diagonal movement options

2. Game Elements
---------------
PLAYERS
- Player 1 (1️⃣)
- Player 2 (2️⃣)

ENEMIES
- Snakes (🐍) x5

ITEMS
- Treasure (💰) - Win condition
- Flashlight (🔦) - Increases vision range
- Shield (🛡️) - Temporary immunity
- Heart (❤️) - Dropped on death
- Ambulance (🚑) - Revival point

ENVIRONMENT
- Walls (▧)
- Empty spaces ( )

3. Controls
-----------
PLAYER 1                PLAYER 2
w: Move up             i: Move up
a: Move left           j: Move left
s: Move down           k: Move down
d: Move right          l: Move right

1: Up-left              7: Up-left
2: Down-right           8: Down-right  
3: Up-right             9: Up-right
4: Down-left            0: Down-left

q: Undo move           u: Undo move

GENERAL
e: Exit game

4. Gameplay Mechanics
-------------------
A. Map Generation
   * Random placement of:
     - 2 players
     - 5 snakes
     - 1 treasure
     - 2 flashlights
     - 2 shields
     - 1 ambulance
     - Walls (50% chance per empty tile)
   * Map size: 8x8 to 40x57
   * Best played with a map of size 30x30

B. Core Mechanics
   * Movement:
     - One tile at a time
     - 8-directional movement
     - Wall collision
     - Undo system
   
   * Snake Behavior:
     - Moves every second
     - Random 8-directional movement
     - Lethal on contact (unless shielded)
     - Cannot move through walls/items

C. Power-ups
   * Flashlight:
     - Permanent vision upgrade
     - Increases sight by 1.5x everytime a flashlight is picked up
     - One dropped on death, no matter how many are picked up
   
   * Shield:
     - 8 seconds of snake immunity
     - One-time use
     - Timer shown on screen
     - Can be stacked

D. Revival System
   1. Dead player drops heart
   2. Living player must:
      - Collect dropped heart
      - Reach ambulance
      - Wait 5 seconds
   3. Revived player:
      - Spawns at ambulance
      - Base vision (2 tiles)
      - Loses collected items

5. Technical Implementation
--------------------------
A. Thread Management
   - Separate snake thread
   - Resource mutex protection

B. Memory Management
   - Dynamic map allocation
   - Linked list move history
   - Resource cleanup

C. State Tracking
   - Player status
   - Item collection
   - Shield timer
   - Revival progress

9. Technical Requirements
-----------------------
- Linux/Unix terminal
- GCC compiler
- Make utility
-----------------------
10. Step By Step Installation Guide
-----------------------------------
Step 1: Install Visual Studio Code (VS Code)

  1.  Open a web browser (like Chrome, Firefox, or Edge).
  2.  Go to the official VS Code website: https://code.visualstudio.com/
  3.  Click the "Download for Windows" button.
  4.  Once the download is complete, open the downloaded `.exe` file to run the installer.
  5.  Follow the on-screen instructions to install VS Code. Make sure to check the box that says "Add to PATH" during the installation process. This will allow you to open VS Code from the command line later.
  6.  Click "Finish" once the installation is complete. VS Code will likely open automatically.

Step 2: Install the Windows Subsystem for Linux (WSL)

1. Open PowerShell as Administrator:
    * Click the Start button (Windows logo in the bottom-left corner).
    * Type "PowerShell".
    * Right-click on "Windows PowerShell" and select "Run as administrator". Click "Yes" if prompted.

2. Enable the WSL feature:
    * In the Administrator PowerShell window, type the following command and press Enter:
        ```
        wsl --install
        ```
    * This command will enable the necessary WSL features and install the default Ubuntu distribution.

3. Restart Your Computer
    * Once the installation is complete, you will be prompted to restart your computer. **Make sure to restart your computer.

Step 3: Complete the Ubuntu Installation

1.  After restarting, the Ubuntu terminal should automatically open. If it doesn't, you can open it by:
    * Clicking the Start button.
    * Typing "Ubuntu" and selecting the "Ubuntu" app.

2. Create a User Account and Password:
    * The first time you run Ubuntu, it will prompt you to create a new user account and password for your Ubuntu environment.
    * Choose a username and a strong, memorable password.
    * You will need to enter the password when prompted in the terminal (it won't show any characters as you type, this is normal).

Step 4: Update and Upgrade Ubuntu Packages

1.  Once you have created your user account and are in the Ubuntu terminal, it's important to update the package lists and upgrade the installed packages to their latest versions.

2. Run the following commands one by one, pressing Enter after each:
    sudo apt update
    sudo apt upgrade

    * You will be prompted for your Ubuntu user password.
    * Enter it and press Enter.
    * If prompted to confirm the upgrade (usually by asking "Do you want to continue? [Y/n]"), type `y` and press Enter.

Step 5: Install GCC (GNU Compiler Collection)

1.  To compile your C program, you need to install GCC.
    
    * In the Ubuntu terminal, run the following command and press Enter:
    sudo apt install build-essential

    * This command installs GCC, G++, make, and other essential tools for building software.
    * If prompted to confirm the installation, type `y` and press Enter.

Step 6: Install the Remote - SSH Extension in VS Code

1.  Open VS Code.
2.  Click on the Extensions icon in the Activity Bar on the left side (it looks like four squares forming one larger square).
3.  In the search bar, type "Remote - SSH".
4.  Find the extension published by Microsoft and click the "Install" button.
5.  Once installed, you might see a notification to reload VS Code. Click "Reload".
6.  Also download "C/C++ Extension Pack" and "Makefile Tools".

Step 7: Connect VS Code to Your WSL Ubuntu Environment

1.  Click on the "Remote Explorer" icon in the Activity Bar on the left side (it looks like a computer monitor with an arrow pointing into it).
  
  * If you don't see it, you can also access it by pressing `Ctrl+Shift+P` (or `Cmd+Shift+P` on macOS) and typing "Remote-SSH: Connect to Host...".

2.  In the dropdown menu that appears, select "WSL: Ubuntu".

3.  VS Code will open a new window, connecting to your WSL Ubuntu environment. You might see a prompt asking for your password again the first time you connect in a new window.

Step 8: Open The Folder

    1. Open your file explorer and scroll down on the left till you see Linux at the bottom
    2. Open it
    3. Open Ubuntu
    4. Open the 'home' folder
    5. Open the folder with your name
    6. Drag the zip file that you downloaded from my github into this area
    7. Unzip the zip file
    8. Right-Click on any one of the files
    9. Click on 'Open' that has the VSCode symbol next to it
    10. If it asks for permission regarding wsl-local-host, click on allow
    11. At the top left of vscode, click on Explorer
    12. Click on 'Open Folder' and open the folder you have unzipped
    13. Open explorer and right click on any file and click 'Open in Integrated Terminal'
    14. This will open the terminal, in here, look at the top right of it and locate a plus sign next to an arrow facing down.
    15. Click on that arrow and click on "Select Default Profile" and click on 'bash'. If bash isn't an option, then click on Ubuntu-WSL
    16. Click on the bin icon at the top right of the terminal
    17. Now re-do Step-13
    18. Refer to Part-11 to run the game

11. How To Run
----------------------
1. Open the Explorer in the top left
2. Right-click on any file and click on 'Open in Integrated Terminal', then type in one of the 3 commands below:

    *Standard Mode:
      make run

    *Dark Mode:
      make runDark

    *Debug Mode (with Valgrind):
      make valgrind

When you enter one of the above commands, you'll be presented with an option to create a name for the file you're writing to. This name does not matter and is purely so that a random map can be created. You do not need to worry about adding any extensions like "txt", just simply write the name and press enter.

    * For example "asd" would be a valid name
    * Do note that if you were to write "asd" the next time you play, that file will be overwritten and the previous map will be lost

Then enter how long you want the map to be, and how wide you want it to be

    * The minimum size is 8*8
    * The maximum size is 40*57
    * The recommended size is 30*30

Then enter either '1' or '2' depending on whether or not you want to temporarily or permanently create the map

    * 1 - Temporary - Creates a file with the map that is deleted when you lose, win or exit the game
    * 2 - Permanent - Creates a file with the map, and doesn't get deleted
  
To get the best experience, ensure that any tabs on the left are closed. You can close them by clicking on the icon of whatever is open.

    * If Explorer is highlighted and your folder is visible, just click on Explorer again to close it
    * When your terminal is open, look at the top right of it and click on the arrow facing up, inbetween the '...' and the 'X', to make your terminal fullscreen
  
12. Bugs
------------
A. Visual
    * You go invisible when you enter a tile where a player just died.
    * You go invisible when a snake goes on you when you have a shield.
    * 'Player's heart has been collected' message can appear if the snake kills you while your on its tile.

B. Gameplay
    * Since the map is randomly generated, it is susceptible to creating unplayable maps.
    * Players may not be generated onto the map.
    * If either of these occur, reload the game.
===================================
