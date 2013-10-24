package kQueens;

import core.Constraint;
import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 13:54
 */
public class KQueensLRDiagonalConstraint extends Constraint {

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {

        int varRow = variable.getIndex();
        int varColumn = variable.getValue();

        for (Variable var : state.getVariables()) {
            if (varRow != var.getIndex()) {
                if (var.getValue() == (varColumn + Math.abs(varRow - var.getIndex()))) {
                    return false;
                }
            }
        }

        return true;
    }
}
