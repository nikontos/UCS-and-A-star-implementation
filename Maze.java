import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Maze {
   private Node[][] maze;
    double prob;

    /**
     * Maze constructor, creates a [][] object of type Node and initialises it
     * @param size This is the size of the [][]
     * @param blockProbability The propabilty a cell is blocked
     */
    public Maze (int size, double blockProbability)
    {
        Random rand = new Random();
        this.maze = new Node[size][size];
        this.prob = blockProbability;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                // if the cell is not blocked
                if ( rand.nextDouble() >= blockProbability ) {
                    this.maze[i][j] = new Node();
                    this.maze[i][j].nodeValue = rand.nextInt(4) + 1;
                    this.maze[i][j].row = i;
                    this.maze[i][j].column = j;
                    this.maze[i][j].isBlocked = false;
                }
                // if the cell is blocked
                else{
                    this.maze[i][j] = new Node();
                    this.maze[i][j].nodeValue = 100;
                    this.maze[i][j].isBlocked = true;
                }
            }
    }

    /**
     * This method is used to calculate the cost of moving between 2 neighbor cells.
     * @param first The first cell of type Node
     * @param second The second cell
     * @param isDiag Bolean if the movement is diagonial
     * @return
     */
    public double getMoveCost (Node first, Node second, boolean isDiag)
    {
        int firstRow = first.getRow();
        int firstCol = first.getColumn();
        int secondRow = second.getRow();
        int secondCol = second.getColumn();
        double val = abs(this.maze[firstRow][firstCol].getValue() - this.maze[secondRow][secondCol].getValue());
        //System.out.println("X: "+ firstRow + " Y: " + firstCol + this.maze[firstRow][firstCol].getValue());
        //System.out.println("X': "+ secondRow + " Y': " + secondCol + this.maze[secondRow][secondCol].getValue());
        if (isDiag )
            return  ( val + 0.5 );
        else
            return ( val + 1);
    }

    /**
     * This method returns an array list of type Node with all the neighboring Nodes of the given one.
     * @param node The node of which we want to find its neighbours
     * @return
     */
    public ArrayList<Node> getChildren ( Node node)
    {
        ArrayList<Node> children = new ArrayList<Node>();

        double moveCost;
        int row = node.getRow();
        int col = node.getColumn();
        Node child;

        // Diagonial top left
        if ( row > 0 && col > 0 ){
            child = new Node();
            child.isBlocked = this.maze[row - 1][col -1].isBlocked;
            child.row = row - 1;
            child.column = col - 1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row - 1][col -1], true);
            if(!child.isBlocked)
                children.add(child);
        }

        // Top Mid
        if ( row > 0 ){
            child = new Node();
            child.isBlocked = this.maze[row-1][col].isBlocked;
            child.row = row -1;
            child.column = col;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row-1][col], false);
            if(!child.isBlocked)
                children.add(child);
        }

        // Diagonial Top Right
        if ( row > 0 && col < this.maze.length - 1 ) {
            child = new Node();

            child.isBlocked = this.maze[row][col+1].isBlocked;
            child.row = row;
            child.column = col +1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row][col+1], true);
            if(!child.isBlocked)
                children.add(child);
        }

        // Mid Right
        if ( col < this.maze.length - 1 ){
            child = new Node();

            child.isBlocked = this.maze[row][col+1].isBlocked;
            child.row = row;
            child.column = col +1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row][col+1], false);
            if(!child.isBlocked)
                children.add(child);
        }

        // Digonial Bottom Right
        if ( row < this.maze.length - 1 && col < this.maze.length - 1 ) {
            child = new Node();

            child.isBlocked = this.maze[row+1][col+1].isBlocked;
            child.row = row + 1;
            child.column = col +1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row+1][col+1], true);
            if (!child.isBlocked)
                children.add(child);
        }

        // Middle Bottom
        if ( row < this.maze.length - 1 ) {
            child = new Node();

            child.isBlocked = this.maze[row+1][col].isBlocked;
            child.row = row + 1;
            child.column = col;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row+1][col], false);
            if(!child.isBlocked)
                children.add(child);
        }

        // Diagonial Bottom left
        if ( (row < this.maze.length - 1) && (col > 0) ) {
            child = new Node();

            child.isBlocked = this.maze[row+1][col-1].isBlocked;
            child.row = row + 1;
            child.column = col-1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row+1][col-1], true);
            if(!child.isBlocked)
                children.add(child);
        }

        // Middle left
        if ( col > 0 ) {
            child = new Node();

            child.isBlocked = this.maze[row][col-1].isBlocked;
            child.row = row;
            child.column = col-1;
            child.moveCostValue = getMoveCost(this.maze[row][col], this.maze[row][col-1], false);
            if(!child.isBlocked)
                children.add(child);
        }

        return children;
    }

    /**
     * This method returns the actual value of the node/cell of the maze
     * @param row
     * @param col
     * @return
     */
    public int getValue(int row, int col){
        return this.maze[row][col].nodeValue;
    }

    public boolean isBlocked(int x, int y){
        if (this.maze[x][y].isBlocked)
            return true;
        else
            return false;
    }

    public int getSize(Maze maze){
        return this.maze[0].length;
    }

    public void printMaze()
    {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze.length; j++) {
                System.out.print(this.maze[i][j].nodeValue + "\t");

            }
            System.out.println("");
        }
    }

    public double getprob (){
       return this.prob;
    }

    public double heuristicCost (Node start, Node first, Node second){
        double firstCost;
        double secondCost;
        double tmpX;
        double tmpY;
        int test = abs(start.row - first.row) ;
        int test2 = abs(start.column - second.column) ;

        //firstCost = Math.sqrt(firstCost) - test*0.5;
        firstCost = test*0.25 + test2*0.25;

        tmpX = start.row - second.row;
        tmpX = Math.pow(tmpX, 2);
        tmpY = start.column - second.column;
        tmpY = Math.pow(tmpY,2);
        secondCost = tmpX + tmpY;
        //secondCost = Math.sqrt(secondCost) - test2*0.5;
        secondCost = test2*0.25 + test*0.25;


        if (firstCost < secondCost)
            return firstCost;
        else
            return secondCost;

    }
}


