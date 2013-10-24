package core;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 13.10.13
 * Time: 17:43
 */
public class State {
    private ArrayList<Variable> variables;

    public State(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public State clone() {
        ArrayList<Variable> variablesClone = new ArrayList<Variable>();

        for (int i = 0; i < variables.size(); i++) {
            variablesClone.add(i, variables.get(i).clone());
        }

        return new State(variablesClone);
    }
}
