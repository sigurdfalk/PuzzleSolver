package eggCarton;

import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 19.10.13
 * Time: 22:44
 */
public class EggCartonLRDiagonalConstraint extends EggCartonConstraint {
    public EggCartonLRDiagonalConstraint(int k) {
        super(k);
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        int varRow = getRow(variable.getIndex());
        int varColumn = variable.getValue();
        int constraint = varRow - varColumn;

        int eggCount = 0;

        for (Variable var : state.getVariables()) {
            if ((getRow(var.getIndex()) - var.getValue()) == constraint) {
                eggCount++;
            }
        }

        return eggCount <= k;
    }
}
