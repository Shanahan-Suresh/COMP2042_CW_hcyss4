# Brick Breaker (COMP2042)
- This is a remake of the Brick Destroy game made by FilippoRanza ([link](https://github.com/FilippoRanza/Brick_Destroy)) for
Software Maintanence (COMP2042) coursework, Fall 2021
- This coursework is done by Shanahan Suresh, Student ID 20319174.

# About the Game
The goal of the game is to break all the bricks in a level by bouncing a ball with a 
rectangular paddle, similar to Pong.

The controls are as follows :

|     Keys      | Action                    |
| ------------- |---------------------------|
|`SPACE`| Start/Pause Game          |
|`A`| Moves the Paddle Left     |
|`D`| Moves the Paddle Right    |
|`ESC`| Enter/Exit the Pause Menu |
|`ALT+SHIFT+F1`| Open the Debug Console    |

### Table Of Contents:
1. Refactoring Activities
2. Additions
3. Version Control
4. IDE and JAVA Versions
5. Credits
6. License
7. How to run


## 1. Refactoring Activities
- **Packages and organisation:** Initially, all the classes were used in the project were located in the same directory
resulting in unorganized classes that were hard to work with. The classes have since been grouped together in related packages
  (Balls, Bricks, Walls etc) to enhance maintainability and organisation of the project.
 

- **General refactoring activities:** The following activities were applied to many if not all classes
  - Renaming variables and methods with descriptive names
  - Initializing variables
  - Reordering methods in a class according to related methods for the purpose of easier location
  - Separating huge blocks of code into various methods, including constructor methods
  - Including getter and setter methods to access variables
  - Removing unused variables, methods and import statements
  - Applying Guard Causes in if-else statements (Brick class)
  - Pushing up methods in child classes (Brick class)
  - Breaking apart complicated lambda expressions into seperate methods (GameBoard class)
  - General code formatting
  

- **Extracting classes out of a single class:** Certain classes within the code held too many responsibilities
 for a single class, these classes were then fragmented and split off into separate classes for the purpose of
maintainability and encapsulation. The new classes are : Crack class from Brick, LevelCreate from Wall
- **Design Patterns:** The Factory Design Pattern was applied in the creation of Brick objects and Sound objects
- **MVC Pattern (Incomplete):** Initially the project was planned to apply the Model, View,
Controller design, but it proved to be too complicated for my current capability. As such they are only organized in the
MVC style. At the very least, I believe the Model package is correct and incorporates the all 
classes that are meant to be in Model.
- **JUnit Tests:** JUnit tests were created for various different classes to ensure that the
project will still function as intented following any future modifications. The classes are
located in a designated test folder.
- **Javadocs:** Javadoc comments were added to all public variables and methods, they were
also added to certain protected and private methods. The generated Javadocs is in its own
dedicated folder.



## 2. Additions
1. Background images to menus
2. Instruction menu
3. High Score function and menu
4. Game timer
5. Sound effects
7. Made window resizeable
8. New Brick type (Diamond) and new level


## 3. Version Control
- Git is used as the version control system for this project, the remote repository for this project
is located here -  ([link](https://github.com/Shanahan-Suresh/COMP2042_CW_hcyss4))
- Branching and merging were conducted for specific additional features as shown below:
  <img src="PDF and Screenshots/Use of branching in Git.PNG" style="width:300px;"/>
- Every commit message was intended to be short and meaningful. The commit messages can be
expanded for further details.

## 4. IDE and JAVA Version
IntelliJ IDEA 2021.3 (Community Edition)
<br/>Runtime version: 11.0.13

Java(TM) SE Runtime Environment (build 15.0.2)


## 5. Credits
The background images were created in Paint.<br/>The audio files were sourced from previous personal projects.

## 6. License
This game is copyright free.

## 7. How to run
1. Download the whole ZIP file
2. Import the project into an IDE
3. Run the GraphicsMain class
