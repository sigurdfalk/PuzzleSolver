package eggCarton;

import core.State;
import core.Variable;

/**
 * User: Sigurd
 * Date: 19.10.13
 * Time: 22:44
 */
public class EggCartonColumnConstraint extends EggCartonConstraint {
    public EggCartonColumnConstraint(int k) {
        super(k);
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        int varColumn = variable.getValue();

        int eggCount = 0;

        for (Variable var : state.getVariables()) {
            if (varColumn == var.getValue()) {
                eggCount++;
            }
        }

        return eggCount <= k;
    }
}
