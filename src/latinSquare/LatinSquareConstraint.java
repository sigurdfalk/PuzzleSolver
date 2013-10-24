package latinSquare;

import core.Constraint;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 14:47
 */
public abstract class LatinSquareConstraint extends Constraint {
    protected int n;

    public LatinSquareConstraint(int n) {
        this.n = n;
    }

    protected int getRow(int index) {
        return index / n;
    }

    protected int getColumn(int index) {
        return index % n;
    }
}
