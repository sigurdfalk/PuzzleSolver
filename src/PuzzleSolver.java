import gui.PuzzleSolverUI;

import javax.swing.*;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 14:13
 */
public class PuzzleSolver {

    public static final void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PuzzleSolverUI puzzleSolverUI = new PuzzleSolverUI();
        puzzleSolverUI.createUI();
    }
}
