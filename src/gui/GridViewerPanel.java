package gui;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * User: Sigurd
 * Date: 18.10.13
 * Time: 18:17
 */
public class GridViewerPanel extends ViewerPanel {
    protected final int BOX_HEIGHT = 10;
    protected final int BOX_WIDTH = 10;

    private int numberOfRows;
    private int numberOfColumns;

    public GridViewerPanel(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(scale / 10, scale / 10);
        graphics2D.translate(moveX, moveY);

        for (int i = 0; i <= numberOfRows; i++) {
            int xStart = 0;
            int yStart = i * BOX_HEIGHT;
            int xEnd = numberOfColumns * BOX_WIDTH;
            int yEnd = yStart;
            graphics2D.draw(new Line2D.Double(xStart, yStart, xEnd, yEnd));
        }

        for (int i = 0; i <= numberOfColumns; i++) {
            int xStart = i * BOX_WIDTH;
            int yStart = 0;
            int xEnd = xStart;
            int yEnd = numberOfRows * BOX_HEIGHT;
            graphics2D.draw(new Line2D.Double(xStart, yStart, xEnd, yEnd));
        }
    }
}
