public class Context {
    private Strategy strategy;
    private Maze maze;

    public Context(Maze maze){
        this.maze = maze;
    }

    public void configureContext(Strategy strategy){
        this.strategy = strategy;
    }

    public void dowork(){
        //
        //
        //
        strategy.executeAlgorithm(f);
        //
        //
        //
    }
}
