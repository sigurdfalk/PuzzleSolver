package core;

/**
 * User: Sigurd
 * Date: 13.10.13
 * Time: 17:43
 */
public abstract class LocalSearch {
    protected LocalStateManager localStateManager;
    protected int iterations;
    protected Run result;
    protected boolean stop;
    protected State initialState;
    protected boolean visualize;

    public LocalSearch(LocalStateManager localStateManager, int iterations) {
        this.localStateManager = localStateManager;
        this.iterations = iterations;
        this.result = null;
    }

    public void initializeSearch(State initialState, int iterations, boolean visualize) {
        this.initialState = initialState;
        this.iterations = iterations;
        this.visualize = visualize;
    }

    public Run getResult() {
        return result;
    }

    public void stopSearch() {
        this.stop = true;
    }

    public abstract void search(State state, boolean visualize);
}
