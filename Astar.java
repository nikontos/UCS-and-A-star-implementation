import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Astar implements Strategy {
    Node startingState;
    ArrayList<Node> terminalStates;
    Maze maze;

    public Astar (Node startingState,ArrayList<Node> terminalStates,Maze maze){
        this.startingState = startingState;
        this.terminalStates = terminalStates;
        this.maze = maze;
    }

    @Override
    public void executeAlgorithm() {

        System.out.println("...A* Started...");
        System.out.println("S: [" + startingState.row + "," + startingState.column + "]");
        System.out.println("G1: [" + terminalStates.get(0).row + "," + terminalStates.get(0).column + "]");
        System.out.println("G2: [" + terminalStates.get(1).row + "," + terminalStates.get(1).column + "]");


        long start = System.currentTimeMillis();
        long finish;
        long timeElapsed;
        int depth = 0;
        ArrayList<Node> openSet = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();
        ArrayList<Node> children = new ArrayList<Node>();
        ArrayList<Node> path = new ArrayList<>();
        Node currentState;
        double distanceFromRoot = 0;
        startingState.distanceFromRoot = 0;
        openSet.add(startingState);
        boolean hasPath = false;


        while (!hasPath) {
            depth++;
            if ( openSet.isEmpty() ) {
                System.out.println("Path Not Found!!!");
                break;
            }
            bubbleSortHeuristic(openSet);
            currentState = openSet.get(0);
            openSet.remove(0);
            distanceFromRoot += currentState.moveCostValue;
            closedSet.add(currentState);

            if ( currentState.isEqual(terminalStates.get(0)) || currentState.isEqual(terminalStates.get(1)) ) {
                System.out.println("SUCCESS !!!");
                System.out.println("Path to terminal state [" + currentState.getRow() + "," + currentState.getColumn() + "] was found !");
                System.out.println("It took a wooping of " + depth + " expansions");
                System.out.println("Total Cost From Root : " + currentState.distanceFromRoot);
                hasPath = true;
                // System.out.println("PATH " + currentState.printPath(currentState));
                path = currentState.printPath(currentState);
                Collections.reverse(path);
                System.out.println("The path is : ");
                for (int i = 0; i < path.size(); i++)
                    path.get(i).printCords(path.get(i));
                System.out.println("");
                //maze.visualPath(path);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                double seconds = 0.001 * timeElapsed;
                System.out.println("A total of " + timeElapsed + " ms or " + seconds + " seconds was neeeded to calculate the path");
                System.out.println("Maze size was : " + maze.getSize(maze) + " Propability of blocked cell :" + maze.getprob());
            }

//            System.out.println("Current Node is ["+ currentState.getRow() + ","+ currentState.getColumn() + "]");
//            System.out.println("Node move cost : " + currentState.moveCostValue);
//            System.out.println("Total Cost from root : " + currentState.distanceFromRoot);
//            System.out.println("=======");
            children = maze.getChildren(currentState);

            for (int i = 0; i < children.size(); i++) {
                children.get(i).parent = currentState;
                children.get(i).distanceFromRoot = children.get(i).parent.distanceFromRoot + children.get(i).moveCostValue;
                children.get(i).heuristicDistanceFromRoot = children.get(i).parent.distanceFromRoot + children.get(i).moveCostValue + maze.heuristicCost(children.get(i), terminalStates.get(0), terminalStates.get(1));
            }

            ArrayList<Node> openSetCopy = openSet;
            ArrayList<Node> closedSetCopy = closedSet;
            ArrayList<Node> childrenCopy = children;


            if ( !closedSet.isEmpty() )
                for (int i = 0; i < children.size(); i++) {
                    //System.out.println("Child indedx: " + i + " Child size: " + children.size());
                    for (int j = 0; j < closedSet.size(); j++) {
                        //System.out.println("closed set index: " + j + " closed size: " + closedSet.size());
                        if ( !(i >= children.size()) )
                            if ( children.get(i).isEqual(closedSet.get(j)) ) {
                                if ( children.get(i).heuristicDistanceFromRoot >= closedSet.get(j).heuristicDistanceFromRoot ) {
                                    childrenCopy.remove(i);
                                } else
                                    childrenCopy.get(i).parent = currentState;
                                closedSetCopy.remove(j);
                            }
                    }
                }

            children = childrenCopy;


            for (int i = 0; i < children.size(); i++)
                for (int j = 0; j < openSet.size(); j++) {
                    if ( !(i >= children.size()) && !(j >= openSet.size()) )
                        if ( children.get(i).isEqual(openSet.get(j)) ) {
                            if ( children.get(i).heuristicDistanceFromRoot >= openSet.get(j).heuristicDistanceFromRoot ) {
                                childrenCopy.remove(i);
                            } else {
                                childrenCopy.get(i).parent = currentState;
                                openSetCopy.remove(j);
                            }
                        }
                }


            children = childrenCopy;
            openSet = openSetCopy;
            closedSet = closedSetCopy;

            openSet.addAll(children);
            bubbleSortHeuristic(openSet);
            children = new ArrayList<Node>();

        }

    }

    public void bubbleSortHeuristic( List<Node> list )
    {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if ( list.get(j).heuristicDistanceFromRoot > list.get(j + 1).heuristicDistanceFromRoot ) {
                    // swap arr[j+1] and arr[j]
                    Node temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }


    }
}
