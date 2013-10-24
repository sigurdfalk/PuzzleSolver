package graphColor;

import core.Constraint;
import core.Variable;
import gui.ViewerPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 18.10.13
 * Time: 18:06
 */
public class GraphColorViewerPanel extends ViewerPanel {
    public static final int COLOR_RED = 0;
    public static final int COLOR_BLUE = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_YELLOW = 3;
    public static final int COLOR_CYAN = 4;
    public static final int COLOR_MAGENTA = 5;
    public static final int COLOR_ORANGE = 6;
    public static final int COLOR_PINK = 7;

    private ArrayList<Constraint> graphColorConstraints;
    private ArrayList<GraphColorCoordinate> coordinates;

    public GraphColorViewerPanel(ArrayList<Constraint> graphColorConstraints, ArrayList<GraphColorCoordinate> coordinates) {
        this.graphColorConstraints = graphColorConstraints;
        this.coordinates = coordinates;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(scale / 10, scale / 10);
        graphics2D.translate(moveX, moveY);

        for (Constraint constraint : graphColorConstraints) {
            GraphColorConstraint graphColorConstraint = (GraphColorConstraint) constraint;
            GraphColorCoordinate c1 = coordinates.get(graphColorConstraint.getEdgeEnd1());
            GraphColorCoordinate c2 = coordinates.get(graphColorConstraint.getEdgeEnd2());
            graphics2D.draw(new Line2D.Double(c1.getX(), c1.getY(), c2.getX(), c2.getY()));
        }

        for (Variable variable : variables) {
            GraphColorCoordinate coordinate = coordinates.get(variable.getIndex());
            graphics2D.setColor(getColor(variable.getValue()));
            graphics2D.fill(new Ellipse2D.Double(coordinate.getX() - 2, coordinate.getY() - 2, 4, 4));
        }
    }

    private Color getColor(int value) {
        switch (value) {
            case COLOR_RED:
                return Color.RED;
            case COLOR_BLUE:
                return Color.BLUE;
            case COLOR_GREEN:
                return Color.GREEN;
            case COLOR_YELLOW:
                return Color.YELLOW;
            case COLOR_MAGENTA:
                return Color.MAGENTA;
            case COLOR_CYAN:
                return Color.CYAN;
            case COLOR_PINK:
                return Color.PINK;
            case COLOR_ORANGE:
                return Color.ORANGE;
            default:
                return Color.GRAY;
        }
    }
}

