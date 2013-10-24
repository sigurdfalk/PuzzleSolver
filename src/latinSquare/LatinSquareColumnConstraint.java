package latinSquare;

import core.State;
import core.Variable;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 14:47
 */
public class LatinSquareColumnConstraint extends LatinSquareConstraint {
    public LatinSquareColumnConstraint(int n) {
        super(n);
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        int varRow = getRow(variable.getIndex());
        int varColumn = getColumn(variable.getIndex());

        ArrayList<Variable> variables = state.getVariables();

        for (int i = varColumn; i < variables.size(); i += n) {
            Variable var = variables.get(i);

            if (varRow != getRow(var.getIndex()) && variable.getValue() == var.getValue()) {
                return false;
            }
        }

        return true;
    }
}
