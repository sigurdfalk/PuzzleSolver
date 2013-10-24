package eggCarton;

import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 00:34
 */
public class EggCartonRowConstraint extends EggCartonConstraint {
    public EggCartonRowConstraint(int k) {
        super(k);
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        int varRow = getRow(variable.getIndex());

        for (int i = (varRow * k); i < (varRow * k) + k; i++) {
            Variable var = state.getVariables().get(i);

            if (var.getIndex() != variable.getIndex() && var.getValue() == variable.getValue()) {
                return false;
            }
        }

        return true;
    }
}
