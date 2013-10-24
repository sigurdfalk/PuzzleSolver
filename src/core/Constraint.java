package core;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 09:27
 */
public abstract class Constraint {

    public abstract boolean satisfiesConstraint(State state, Variable variable);
}
