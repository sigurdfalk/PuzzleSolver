package latinSquare;

import core.Variable;
import gui.GridViewerPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * User: Sigurd
 * Date: 20.10.13
 * Time: 15:26
 */
public class LatinSquareViewerPanel extends GridViewerPanel {
    private int n;
    private Color[] colors;

    public LatinSquareViewerPanel(int numberOfRows, int numberOfColumns) {
        super(numberOfRows, numberOfColumns);
        this.n = numberOfColumns;
        this.colors = setColors(n);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < variables.size(); i++) {
            Variable variable = variables.get(i);
            int x = (variable.getIndex() / n) * BOX_HEIGHT;
            int y = (variable.getIndex() % n) * BOX_WIDTH;
            graphics2D.setColor(colors[variable.getValue()]);
            graphics2D.fill(new Rectangle2D.Double(x + 0.5, y + 0.5, BOX_WIDTH - 1, BOX_HEIGHT - 1));
        }
    }

    private Color[] setColors(int numberOfColors) {
        Color[] output = new Color[numberOfColors];

        float interval = 360 / numberOfColors;
        for (float x = 0, i = 0; x < 360 && i < output.length; x += interval, i++) {
            output[(int)i] = Color.getHSBColor((x / 360), 1, 1);
        }

        return output;
    }
}
