package core;

import utilities.Util;

import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 13.10.13
 * Time: 17:43
 */
public class SimulatedAnnealing extends LocalSearch {
    private double maxTemp;

    public SimulatedAnnealing(LocalStateManager localStateManager, int iterations, double maxTemp) {
        super(localStateManager, iterations);
        this.maxTemp = maxTemp;
    }

    public void search(State state, boolean visualize) {
        if (state == null) {
            state = localStateManager.generateInitialState();
        }

        localStateManager.setMaxConflicts(state);
        long startTime = System.currentTimeMillis();
        int numberOfSteps = 0;
        double temp = maxTemp;

        for (; (iterations == -1) ? true : numberOfSteps < iterations && !stop; numberOfSteps++) {
            if (visualize) {
                localStateManager.printState(state);
            }

            double stateEvaluation = localStateManager.evaluateState(state);

            if (stateEvaluation >= 1.0) {
                long duration = System.currentTimeMillis() - startTime;
                result = new Run(state, duration, localStateManager.evaluateState(state), numberOfSteps, true);
                return;
            }

            ArrayList<State> successors = localStateManager.generateSuccessorStates(state);

            double bestSuccessorEvaluation = Double.MIN_VALUE;
            State bestSuccessor = null;

            for (State successor : successors) {
                double successorEvaluation = localStateManager.evaluateState(successor);

                if (successorEvaluation > bestSuccessorEvaluation) {
                    bestSuccessor = successor;
                    bestSuccessorEvaluation = successorEvaluation;
                }
            }

            double q = (bestSuccessorEvaluation - stateEvaluation) / stateEvaluation;
            double p = Math.min(1, Math.pow(Math.E, (-1 * q) / temp));
            double x = Util.generateRandomNumber(0, 2);

            if (x > p) {
                state = bestSuccessor;
            } else {
                state = successors.get(Util.generateRandomNumber(0, successors.size()));
            }

            temp = schedule(temp);
        }

        long duration = System.currentTimeMillis() - startTime;
        result = new Run(state, duration, localStateManager.evaluateState(state), numberOfSteps, false);
    }

    private double schedule(double t) {
        return t * 0.999;
    }
}
