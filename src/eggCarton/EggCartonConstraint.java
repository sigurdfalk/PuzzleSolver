package eggCarton;

import core.Constraint;

/**
 * User: Sigurd
 * Date: 19.10.13
 * Time: 22:48
 */
public abstract class EggCartonConstraint extends Constraint {
    protected int k;

    public EggCartonConstraint(int k) {
        this.k = k;
    }

    protected int getRow(int index) {
        return index / k;
    }
}
