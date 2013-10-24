package core;

/**
 * User: Sigurd
 * Date: 21.10.13
 * Time: 17:05
 */
public class Run {
    private int runNumber;
    private State result;
    private long duration;
    private double evaluation;
    private int numberOfSteps;
    private boolean isOptimal;

    public Run(State result, long duration, double evaluation, int numberOfSteps, boolean optimal) {
        this.result = result;
        this.duration = duration;
        this.evaluation = evaluation;
        this.numberOfSteps = numberOfSteps;
        isOptimal = optimal;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    public State getResult() {
        return result;
    }

    public void setResult(State result) {
        this.result = result;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public boolean isOptimal() {
        return isOptimal;
    }

    public void setOptimal(boolean optimal) {
        isOptimal = optimal;
    }

    @Override
    public String toString() {
        return "Run " + runNumber;
    }
}
