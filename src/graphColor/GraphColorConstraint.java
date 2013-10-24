package graphColor;

import core.Constraint;
import core.State;
import core.Variable;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 20:57
 */
public class GraphColorConstraint extends Constraint {
    private int edgeEnd1;
    private int edgeEnd2;

    public GraphColorConstraint(int edgeEnd1, int edgeEnd2) {
        this.edgeEnd1 = edgeEnd1;
        this.edgeEnd2 = edgeEnd2;
    }

    public int getEdgeEnd1() {
        return edgeEnd1;
    }

    public void setEdgeEnd1(int edgeEnd1) {
        this.edgeEnd1 = edgeEnd1;
    }

    public int getEdgeEnd2() {
        return edgeEnd2;
    }

    public void setEdgeEnd2(int edgeEnd2) {
        this.edgeEnd2 = edgeEnd2;
    }

    @Override
    public boolean satisfiesConstraint(State state, Variable variable) {
        ArrayList<Variable> variables = state.getVariables();

        if (variable.getIndex() == edgeEnd1) {
            if (variables.get(edgeEnd2).getValue() == variable.getValue()) {
                return false;
            }
        } else if (variable.getIndex() == edgeEnd2) {
            if (variables.get(edgeEnd1).getValue() == variable.getValue()) {
                return false;
            }
        }

        return true;
    }
}
