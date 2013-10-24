package core;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 09:27
 */
public class Variable {
    private int index;
    private int value;

    public Variable(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public Variable() {
        this(0, 0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Variable clone() {
        Variable clone = new Variable();

        clone.setIndex(this.index);
        clone.setValue(this.value);

        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        Variable objVar = (Variable) obj;

        return (this.index == objVar.getIndex()) && (this.value == objVar.getValue());
    }
}
