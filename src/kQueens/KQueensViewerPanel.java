package kQueens;

import core.Variable;
import gui.GridViewerPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 03:04
 */
public class KQueensViewerPanel extends GridViewerPanel {
    public KQueensViewerPanel(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLUE);

        for (int i = 0; i < variables.size(); i++) {
            Variable variable = variables.get(i);
            int x = (i * BOX_WIDTH) + (BOX_WIDTH / 2);
            int y = (variable.getValue() * BOX_HEIGHT) + (BOX_HEIGHT / 2);
            graphics2D.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
        }
    }
}
