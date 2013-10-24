package eggCarton;

import core.Variable;
import gui.GridViewerPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 03:10
 */
public class EggCartonViewerPanel extends GridViewerPanel {
    private int k;

    public EggCartonViewerPanel(int numberOfRows, int numberOfColumns, int k) {
        super(numberOfRows, numberOfColumns);
        this.k = k;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLUE);

        for (int i = 0; i < variables.size(); i++) {
            Variable variable = variables.get(i);
            int y = ((i / k) * BOX_WIDTH) + (BOX_WIDTH / 2);
            int x = (variable.getValue() * BOX_HEIGHT) + (BOX_HEIGHT / 2);
            graphics2D.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
        }
    }
}
