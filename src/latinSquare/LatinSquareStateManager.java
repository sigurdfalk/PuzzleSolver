package latinSquare;

import core.LocalStateManager;
import core.State;
import gui.Viewer;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 14:39
 */
public class LatinSquareStateManager extends LocalStateManager {
    private Viewer latinSquareViewer;

    public LatinSquareStateManager(int n) {
        super(n * n, n);
        generateConstraints();
        latinSquareViewer = new Viewer(new LatinSquareViewerPanel(n, n));
    }

    @Override
    public void generateConstraints() {
        constraints.add(new LatinSquareColumnConstraint(domainSize));
        constraints.add(new LatinSquareRowConstraint(domainSize));
    }

    @Override
    public void printState(State state) {
        latinSquareViewer.drawUI(state.getVariables());
    }

    @Override
    public void setMaxConflicts(State state) {
        maxConflicts = numberOfVariables * constraints.size();
    }
}
