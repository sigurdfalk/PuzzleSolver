package core;

import utilities.Util;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 13.10.13
 * Time: 17:43
 */
public abstract class LocalStateManager {
    public static final double CONSTRAINT_SUCCESSOR_GENERATION = 1;

    protected int numberOfVariables;
    protected int domainSize;
    protected int maxConflicts;
    protected ArrayList<Constraint> constraints;

    public LocalStateManager(int numberOfVariables, int domainSize) {
        this.numberOfVariables = numberOfVariables;
        this.domainSize = domainSize;
        this.constraints = new ArrayList<Constraint>();
    }

    public State generateInitialState() {
        ArrayList<Variable> variables = new ArrayList<Variable>();

        for (int i = 0; i < numberOfVariables; i++) {
            int value = Util.generateRandomNumber(0, domainSize);

            variables.add(new Variable(i, value));
        }

        return new State(variables);
    }

    public State updateState(State state, Variable var, int value) {
        state.getVariables().get(var.getIndex()).setValue(value);
        return state;
    }

    public State updateState(State state, Variable var) {
        int[] domain = getDomain(var);
        int value = domain[Util.generateRandomNumber(0, domain.length)];
        return updateState(state, var, value);
    }

    public int getConstraintViolations(State state, Variable var) {
        int varConflicts = 0;

        for (Constraint constraint : constraints) {
            if (!constraint.satisfiesConstraint(state, var)) {
                varConflicts++;
            }
        }

        return varConflicts;
    }

    public boolean satisfiesConstraints(State state, Variable variable) {
        for (Constraint constraint : constraints) {
            if (!constraint.satisfiesConstraint(state, variable)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<State> generateSuccessorStates(State state) {
        int numberOfSuccessors = (int)((numberOfVariables * domainSize) * CONSTRAINT_SUCCESSOR_GENERATION);
        ArrayList<State> successors = new ArrayList<State>();

        for (int i = 0; i < numberOfSuccessors; i++) {
            State clone = state.clone();
            ArrayList<Variable> variables = clone.getVariables();
            int n = Util.generateRandomNumber(0, variables.size());
            Variable variable = variables.get(n);
            State newState = updateState(clone, variable);
            successors.add(newState);
        }

        return successors;
    }

    public double evaluateState(State state) {
        double conflicts = 0.0;

        for (Variable variable : state.getVariables()) {
            conflicts += getConstraintViolations(state, variable);
        }

        double max = (double) maxConflicts;
        return  (max - conflicts) / max;
    }

    public int[] getDomain(Variable variable) {
        int[] domain = new int[domainSize - 1];

        for (int i = 0, j = 0; i < domain.length; j++) {
            if (j != variable.getValue()) {
                domain[i] = j;
                i++;
            }
        }

        return domain;
    }

    public abstract void generateConstraints();

    public abstract void printState(State state);

    public abstract void setMaxConflicts(State state);
}
