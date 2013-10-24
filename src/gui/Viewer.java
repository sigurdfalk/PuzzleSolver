package gui;

import core.Variable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 17.10.13
 * Time: 00:45
 */
public class Viewer extends JFrame implements ChangeListener {
    private ViewerPanel viewerPanel;
    private JSlider scaleSlider;

    private double scale = 20;
    private double moveX = 0;
    private double moveY = 0;

    private ArrayList<Variable> variables;

    public Viewer(ViewerPanel viewerPanel) {
        this.viewerPanel = viewerPanel;
        scaleSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
        scaleSlider.addChangeListener(this);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(new JLabel("Scale"));
        jPanel.add(scaleSlider);

        container.add(jPanel, BorderLayout.PAGE_END);
        container.add(viewerPanel, BorderLayout.CENTER);

        addMouseMotionListener(new MouseListener());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Viewer");
        setSize(900, 900);
    }

    public void drawUI(ArrayList<Variable> variables) {
        drawUI(variables, scale, moveX, moveY);
    }

    public void drawUI(ArrayList<Variable> variables, double scale, double moveX, double moveY) {
        this.variables = variables;

        viewerPanel.update(variables, scale, moveX, moveY);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        scale = (double) scaleSlider.getValue();

        viewerPanel.update(variables, scale, moveX, moveY);

    }

    private class MouseListener extends MouseInputAdapter {
        private double x;
        private double y;

        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            moveX += (e.getX() - x);
            moveY += (e.getY() - y);

            x = e.getX();
            y = e.getY();

            viewerPanel.update(variables, scale, moveX, moveY);
        }
    }
}