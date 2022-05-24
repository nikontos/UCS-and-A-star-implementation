# UCS-and-A-star-implementation
Implementing UCS and A* using the strategy pattern

## Uniform Cost Search (UCS)
Calculates the minimum path within the maze, from a Starting state to the closest Final State.

## A-Star (A*)
Similar to UCS but in order to find the miminum path uses a heuristic function.

## Execution
Clone the repository <br>
Open the terminal

To compile :

> javac Main.java Astar.java UCS.java Context.java Maze.java Node.java Strategy.java

To execute the program :

> java Main


### Astar.java

This is the class that implements the A* algorithm

### UCS.java

This class implements the UCS algorithm

### Maze.java

This class implenets various functions of the maze. The maze is populated with Node objects. <br>
Also in this class some other functions are also implemented like calculating legal moves and the heuristic function.

### Node.java

The type of object we populate our maze with, it contains information about each cell.

### Strategy.java Context.java

Bot classes are used to implement the strategy pattern.

### Main.java

The main class, requires some user input.
