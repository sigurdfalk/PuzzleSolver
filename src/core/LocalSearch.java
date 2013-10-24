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

    public LocalSearch(LocalStateManager localStateManager, int iterations) {
        this.localStateManager = localStateManager;
        this.iterations = iterations;
        this.result = null;
    }

    public Run getResult() {
        return result;
    }

    public void stopSearch() {
        this.stop = true;
    }

    public abstract void search(State state, boolean visualize);
}
