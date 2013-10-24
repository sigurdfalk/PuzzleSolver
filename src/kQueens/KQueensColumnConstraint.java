package kQueens;

import core.Constraint;
import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 13:53
 */
public class KQueensColumnConstraint extends Constraint {

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {

        int varRow = variable.getIndex();
        int varColumn = variable.getValue();

        for (Variable var : state.getVariables()) {
            if (varRow != var.getIndex()) {
                if (varColumn == var.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
