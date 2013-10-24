package graphColor;

import core.Constraint;
import core.LocalStateManager;
import core.State;
import core.Variable;
import gui.Viewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 16.10.13
 * Time: 20:56
 */
public class GraphColorStateManager extends LocalStateManager {
    public static final int GRAPH_COLOR_EASY = 0;
    public static final int GRAPH_COLOR_MEDIUM = 1;
    public static final int GRAPH_COLOR_HARD = 2;
    private int difficulty;
    private ArrayList<GraphColorCoordinate> coordinates;
    private Viewer graphViewer;

    public GraphColorStateManager(int numberOfColors, int difficulty) {
        super(0, numberOfColors);
        this.difficulty = difficulty;
        this.coordinates = new ArrayList<GraphColorCoordinate>();
        readGraphData();
        this.graphViewer = new Viewer(new GraphColorViewerPanel(constraints, coordinates));
    }

    @Override
    public void generateConstraints() {
    }

    @Override
    public void printState(State state) {
        graphViewer.drawUI(state.getVariables());
    }

    @Override
    public void setMaxConflicts(State state) {
        int i = 0;

        for (Variable variable : state.getVariables()) {
            for (Constraint constraint : constraints) {
                GraphColorConstraint gc = (GraphColorConstraint) constraint;

                if (variable.getIndex() == gc.getEdgeEnd1() || variable.getIndex() == gc.getEdgeEnd2()) {
                    i++;
                }
            }
        }

        maxConflicts = i;
    }

    private void readGraphData() {
        try {
            File file = new File(getFileName(difficulty));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));

            String line = null;

            for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
                String[] val = line.split(" ");
                if (i == 0) {
                    numberOfVariables = Integer.parseInt(val[0]);
                    int numberOfEdges = Integer.parseInt(val[1]);
                } else if (i > 0 && i < (numberOfVariables + 1)) {
                    int index = Integer.parseInt(val[0]);
                    double x = Double.parseDouble(val[1]) * 7;
                    double y = Double.parseDouble(val[2]) * 7;
                    coordinates.add(new GraphColorCoordinate(x, y));
                } else if (i >= (numberOfVariables + 1)) {
                    int i1 = Integer.parseInt(val[0]);
                    int i2 = Integer.parseInt(val[1]);
                    constraints.add(new GraphColorConstraint(i1, i2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(int difficulty) {
        switch (difficulty) {
            case GRAPH_COLOR_EASY:
                return "graph-color-2.txt";
            case GRAPH_COLOR_MEDIUM:
                return "graph-color-1.txt";
            case GRAPH_COLOR_HARD:
                return "graph-color-3.txt";
            default:
                throw new IllegalArgumentException("No such difficulty");
        }
    }
}
