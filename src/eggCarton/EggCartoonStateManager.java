package eggCarton;

import core.LocalStateManager;
import core.State;
import gui.Viewer;

/**
 * User: Sigurd
 * Date: 19.10.13
 * Time: 22:30
 */
public class EggCartoonStateManager extends LocalStateManager {
    private int k;
    private Viewer eggCartonViewer;

    public EggCartoonStateManager(int rows, int columns, int k) {
        super(rows * k, columns);
        this.k = k;
        generateConstraints();
        this.eggCartonViewer = new Viewer(new EggCartonViewerPanel(rows, columns, k));

    }

    @Override
    public void generateConstraints() {
        constraints.add(new EggCartonColumnConstraint(k));
        constraints.add(new EggCartonRowConstraint(k));
        constraints.add(new EggCartonLRDiagonalConstraint(k));
        constraints.add(new EggCartonRLDiagonalConstraint(k));
    }

    @Override
    public void printState(State state) {
        eggCartonViewer.drawUI(state.getVariables());
    }

    @Override
    public void setMaxConflicts(State state) {
        maxConflicts = state.getVariables().size() * constraints.size();
    }
}
