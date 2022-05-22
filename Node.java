import java.util.ArrayList;

public class Node {

    int row;
    int column;
    int nodeValue;
    double moveCostValue;
    double distanceFromRoot;
    boolean visited;
    boolean isBlocked;
    Node parent = null;
    double heuristicDistanceFromRoot;

////////////////////////////////////////////

    public Node (){

    }
    public Node (int row, int column,int nodeValue, double distanceFromRoot)
    {
        this.row = row;
        this.column = column;
        this.nodeValue = nodeValue;
        this.distanceFromRoot = distanceFromRoot;
    }

    public Node( int x, int y ) {
        this.row = x;
        this.column = y;
    }

    public void printNode()
    {
        System.out.println("("+row+","+column+")"+" With Value: "+ this.nodeValue);
    }

    public int getRow ()
    {
        return this.row;
    }

    public int getColumn ()
    {
        return this.column;
    }

    public int getValue ()
    {
        return this.nodeValue;
    }

    public double getmoveCostValueValue ()
    {
        return this.moveCostValue;
    }

    public boolean isEqual (Node node){
        if (this.row == node.row && this.column == node.column)
            return true;
        else
            return false;
    }

    /**
     * This method returns an Array list with the minimum path from the Starting State to the Terminal State
     * @param node
     * @return
     */
    public ArrayList printPath ( Node node){
        ArrayList<Node> path = new ArrayList<>();
        Node tmp;
        path.add(node);
        while (node.parent!=null)
        {
            path.add(node.parent);
            tmp = node.parent;
            node.parent = tmp.parent;
        }
        return path;
    }
    public void printCords (Node node){
        System.out.print(" ["+node.row+","+node.column+"] = " + node.moveCostValue );
    }


}
