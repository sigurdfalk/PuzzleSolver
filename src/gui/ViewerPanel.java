package gui;

import core.Variable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 18.10.13
 * Time: 18:07
 */
public abstract class ViewerPanel extends JPanel {
    protected double scale = 0;
    protected double moveX = 0;
    protected double moveY = 0;

    protected ArrayList<Variable> variables;

    public void update(ArrayList<Variable> variables, double scale, double moveX, double moveY) {
        this.variables = variables;
        this.scale = scale;
        this.moveX = moveX;
        this.moveY = moveY;
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
