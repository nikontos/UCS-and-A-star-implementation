/**
 * This is the main function of the program
 */

import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main (String[] args) {

         int mazeSize;
         Node initialState;
         Node firstTerminalState;
         Node secondTerminalState;
         double obstacleProb;
         Node startingState;
         ArrayList<Node> terminalStates= new ArrayList<Node>();
        Maze maze;
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;


        System.out.println("Provide the size N of the maze");
        mazeSize = scanner.nextInt();

        System.out.println("Define the probability a cell is blocked within [0,1]");
        // Make some checks that the probability is within [0,1]

        do {
                obstacleProb = scanner.nextDouble();
                if (obstacleProb > 1 || obstacleProb < 0)
                    System.out.println("Invalid propability, re-enter within [0,1]");
            }while (obstacleProb > 1 || obstacleProb < 0);


        // Create the maze
        maze = new Maze(mazeSize,obstacleProb);

        maze.printMaze();

        System.out.println("Define Starting State in X Y");
            do {
                if ((maze.isBlocked(x,y)))
                    System.out.println("Invalid starting state, it's blocked. Give a new one");
                x = scanner.nextInt();
                y = scanner.nextInt();
            } while (maze.isBlocked(x,y));
            startingState = new Node(x,y);

        System.out.println("Define 1st Terminal State in X Y");
        do {
            if ((maze.isBlocked(x,y)))
                System.out.println("Invalid starting state, it's blocked. Give a new one");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (maze.isBlocked(x,y));
        terminalStates.add(new Node(x,y));
        System.out.println("Define 2nd Terminal State in X Y");

        do {
            if ((maze.isBlocked(x,y)))
                System.out.println("Invalid terminal state, it's blocked. Give a new one");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (maze.isBlocked(x,y));
        terminalStates.add(new Node(x,y));



        Context c = new Context(maze);
        c.configureContext((Strategy) new UCS(startingState,terminalStates,maze));
        c.dowork();
        c.configureContext((Strategy) new aStar(startingState,terminalStates,maze));
        c.dowork();


    }







}
