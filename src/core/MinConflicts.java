package core;

import utilities.Util;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 15.10.13
 * Time: 10:10
 */
public class MinConflicts extends LocalSearch {

    public MinConflicts(LocalStateManager localStateManager, int iterations) {
        super(localStateManager, iterations);
    }

    public void search(State state, boolean visualize) {
        if (state == null) {
            state = localStateManager.generateInitialState();
        }

        localStateManager.setMaxConflicts(state);
        long startTime = System.currentTimeMillis();
        int numberOfSteps = 0;

        for (; (iterations == -1) ? true : numberOfSteps < iterations && !stop; numberOfSteps++) {
            if (visualize) {
                localStateManager.printState(state);
            }

            if (isSolution(state)) {
                long duration = System.currentTimeMillis() - startTime;
                result = new Run(state, duration, localStateManager.evaluateState(state), numberOfSteps, true);
                return;
            }

            Variable var = getConflictedVariable(state);
            int value = getMinimizingValue(state, var);

            if (value > -1) {
                state = localStateManager.updateState(state, var, value);
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        result = new Run(state, duration, localStateManager.evaluateState(state), numberOfSteps, false);
    }

    public boolean isSolution(State state) {
        for (Variable variable : state.getVariables()) {
            if (!localStateManager.satisfiesConstraints(state, variable)) {
                return false;
            }
        }

        return true;
    }

    public Variable getConflictedVariable(State state) {
        ArrayList<Variable> conflicted = new ArrayList<Variable>();

        for (Variable variable : state.getVariables()) {
            if (!localStateManager.satisfiesConstraints(state, variable)) {
                conflicted.add(variable);
            }
        }

        return conflicted.get(Util.generateRandomNumber(0, conflicted.size()));
    }

    private int getMinimizingValue(State state, Variable var) {
        ArrayList<Variable> candidates = new ArrayList<Variable>();
        int varConflicts = localStateManager.getConstraintViolations(state, var);

        for (int d : localStateManager.getDomain(var)) {
            Variable clone = var.clone();
            clone.setValue(d);
            int cloneConflicts = localStateManager.getConstraintViolations(state, clone);

            if (cloneConflicts < varConflicts) {
                varConflicts = cloneConflicts;
                candidates.clear();
                candidates.add(clone);
            } else if (cloneConflicts == varConflicts) {
                candidates.add(clone);
            }
        }

        return (candidates.size() > 0) ? candidates.get(Util.generateRandomNumber(0, candidates.size())).getValue() : -1;
    }
}
