package latinSquare;

import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 14:46
 */
public class LatinSquareRowConstraint extends LatinSquareConstraint {
    public LatinSquareRowConstraint(int n) {
        super(n);
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        int varRow = getRow(variable.getIndex());
        int varColumn = getColumn(variable.getIndex());

        for (int i = (varRow * n); i < (varRow * n) + n; i++) {
            Variable var = state.getVariables().get(i);

            if (varColumn != getColumn(var.getIndex()) && variable.getValue() == var.getValue()) {
                return false;
            }
        }

        return true;
    }
}
