package kQueens;

import core.LocalStateManager;
import core.State;
import gui.Viewer;

/**
 * User: Sigurd
 * Date: 13.10.13
 * Time: 17:44
 */
public class KQueensStateManager extends LocalStateManager {
    private Viewer kQueensViewer;

    public KQueensStateManager(int k) {
        super(k, k);
        generateConstraints();
        this.kQueensViewer = new Viewer(new KQueensViewerPanel(k, k));
    }

    @Override
    public void generateConstraints() {
        constraints.add(new KQueensColumnConstraint());
        constraints.add(new KQueensLRDiagonalConstraint());
        constraints.add(new KQueensRLDiagonalConstrain());
    }

    @Override
    public void printState(State state) {
        kQueensViewer.drawUI(state.getVariables());
    }

    @Override
    public void setMaxConflicts(State state) {
        maxConflicts = state.getVariables().size() * constraints.size();
    }

}
