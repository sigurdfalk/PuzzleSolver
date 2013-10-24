package gui;

import core.*;
import eggCarton.EggCartoonStateManager;
import graphColor.GraphColorStateManager;
import kQueens.KQueensStateManager;
import latinSquare.LatinSquareStateManager;
import utilities.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * User: Sigurd
 * Date: 21.10.13
 * Time: 14:56
 */
public class PuzzleSolverUI extends JFrame {
    public static final int MIN_CONFLICTS = 1;
    public static final int SIMULATED_ANNEALING = 0;
    public static final int K_QUEENS = 0;
    public static final int GRAPH_COLOR = 1;
    public static final int EGG_CARTON = 2;
    public static final int LATIN_SQUARE = 3;

    private JPanel searchPanel;
    private JComboBox searchType;
    private JTextField numberOfRuns;
    private JTextField steps;
    private JCheckBox visualiseSearch;

    private JComboBox puzzleType;

    private JPanel kQueensPanel;
    private JTextField kQueensNumberOfQueens;

    private JPanel graphColorPanel;
    private JComboBox graphColorDifficulty;
    private JTextField graphColorNumberOfColors;

    private JPanel eggCartonPanel;
    private JTextField eggCartonNumberOfRows;
    private JTextField eggCartonNumberOfColumns;
    private JTextField eggCartonNumberOfEggs;

    private JPanel latinSquarePanel;
    private JTextField latinSquareNumberOfSymbols;

    private JButton search;
    private JButton stopSearch;

    private JTextField bestEvaluation;
    private JTextField averageEvaluation;
    private JTextField worstEvaluation;
    private JTextField standardDeviationEvaluation;

    private JTextField minSteps;
    private JTextField averageSteps;
    private JTextField maxSteps;
    private JTextField standardDeviationSteps;

    private JTextField optimalSolutions;
    private JTextField averageDuration;

    private JList runs;
    private JScrollPane listScroll;
    private JTextArea runResult;
    private JButton visualizeRun;

    private ArrayList<Run> runList;

    private LocalSearch localSearch;
    private LocalStateManager localStateManager;

    private Search searchTask;

    public PuzzleSolverUI() {
        super("General Puzzle Solver");
        searchPanel = new JPanel();
        searchType = new JComboBox(new String[]{"Simulated Annealing", "Min-Conflicts"});
        numberOfRuns = new JTextField(5);
        steps = new JTextField(5);
        steps.setText("10000");
        visualiseSearch = new JCheckBox();

        puzzleType = new JComboBox(new String[]{"K-Queens", "Graph Color", "Egg Carton", "Latin Square"});
        puzzleType.addActionListener(new PuzzleTypeListener());

        kQueensPanel = new JPanel();
        kQueensNumberOfQueens = new JTextField(5);

        graphColorPanel = new JPanel();
        graphColorDifficulty = new JComboBox(new String[]{"Easy", "Medium", "Hard"});
        graphColorNumberOfColors = new JTextField(5);

        eggCartonPanel = new JPanel();
        eggCartonNumberOfRows = new JTextField(5);
        eggCartonNumberOfColumns = new JTextField(5);
        eggCartonNumberOfEggs = new JTextField(5);

        latinSquarePanel = new JPanel();
        latinSquareNumberOfSymbols = new JTextField(5);

        search = new JButton("Search");
        search.addActionListener(new ButtonListener());
        stopSearch = new JButton("Stop search");
        stopSearch.addActionListener(new ButtonListener());
        stopSearch.setEnabled(false);

        bestEvaluation = new JTextField(5);
        averageEvaluation = new JTextField(5);
        worstEvaluation = new JTextField(5);
        standardDeviationEvaluation = new JTextField(5);
        standardDeviationEvaluation.setEditable(false);
        bestEvaluation.setEditable(false);
        averageEvaluation.setEditable(false);
        worstEvaluation.setEditable(false);

        minSteps = new JTextField(5);
        averageSteps = new JTextField(5);
        maxSteps = new JTextField(5);
        standardDeviationSteps = new JTextField(5);
        standardDeviationSteps.setEditable(false);
        minSteps.setEditable(false);
        averageSteps.setEditable(false);
        maxSteps.setEditable(false);

        optimalSolutions = new JTextField(5);
        optimalSolutions.setEditable(false);
        averageDuration = new JTextField(5);
        averageDuration.setEditable(false);

        runs = new JList();
        runs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        runs.addListSelectionListener(new ListListener());
        listScroll = new JScrollPane(runs);
        listScroll.setPreferredSize(new Dimension(150, 265));

        runResult = new JTextArea(10, 10);
        runResult.setEditable(false);
        runResult.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        visualizeRun = new JButton("Visualize run");
        visualizeRun.addActionListener(new ButtonListener());

        runList = new ArrayList<Run>();
    }

    public void createUI() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BorderLayout());

        topLeftPanel.add(getSearchPanel(), BorderLayout.PAGE_START);

        JPanel puzzleTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        puzzleTypePanel.add(new JLabel("Puzzle type"));
        puzzleTypePanel.add(puzzleType);
        puzzleTypePanel.add(getKQueensPanel());
        puzzleTypePanel.add(getGraphColorPanel());
        puzzleTypePanel.add(getEggCartonPanel());
        puzzleTypePanel.add(getLatinSquarePanel());

        topLeftPanel.add(puzzleTypePanel, BorderLayout.PAGE_END);

        JPanel topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.add(search, BorderLayout.PAGE_START);
        topRightPanel.add(stopSearch, BorderLayout.PAGE_END);
        topRightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(topLeftPanel, BorderLayout.CENTER);
        topPanel.add(topRightPanel, BorderLayout.LINE_END);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel listPanel = new JPanel();
        listPanel.add(listScroll);

        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(listPanel, BorderLayout.LINE_START);
        mainPanel.add(getResultPanel(), BorderLayout.CENTER);
        mainPanel.add(getResultTotals(), BorderLayout.LINE_END);

        c.add(mainPanel, BorderLayout.PAGE_START);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750, 390);
        this.setVisible(true);
    }

    private JPanel getSearchPanel() {
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        searchPanel.add(new JLabel("Search type"));
        searchPanel.add(searchType);
        searchPanel.add(new JLabel("Number of steps"));
        searchPanel.add(steps);
        searchPanel.add(new JLabel("Number of runs"));
        searchPanel.add(numberOfRuns);
        searchPanel.add(new JLabel("Visualize search"));
        searchPanel.add(visualiseSearch);

        return searchPanel;
    }

    private JPanel getKQueensPanel() {
        kQueensPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        kQueensPanel.add(new JLabel("Number of queens"));
        kQueensPanel.add(kQueensNumberOfQueens);

        return kQueensPanel;
    }

    private JPanel getGraphColorPanel() {
        graphColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        graphColorPanel.add(new JLabel("Difficulty"));
        graphColorPanel.add(graphColorDifficulty);
        graphColorPanel.add(new JLabel("Number of colors"));
        graphColorPanel.add(graphColorNumberOfColors);
        graphColorPanel.setVisible(false);

        return graphColorPanel;
    }

    private JPanel getEggCartonPanel() {
        eggCartonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        eggCartonPanel.add(new JLabel("Number of rows"));
        eggCartonPanel.add(eggCartonNumberOfRows);
        eggCartonPanel.add(new JLabel("Number of columns"));
        eggCartonPanel.add(eggCartonNumberOfColumns);
        eggCartonPanel.add(new JLabel("Number of eggs"));
        eggCartonPanel.add(eggCartonNumberOfEggs);
        eggCartonPanel.setVisible(false);

        return eggCartonPanel;
    }

    private JPanel getLatinSquarePanel() {
        latinSquarePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        latinSquarePanel.add(new JLabel("Number of symbols"));
        latinSquarePanel.add(latinSquareNumberOfSymbols);
        latinSquarePanel.setVisible(false);

        return latinSquarePanel;
    }

    private JPanel getResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout());

        JScrollPane runResultScroll = new JScrollPane(runResult);

        resultPanel.add(runResultScroll, BorderLayout.CENTER);
        resultPanel.add(visualizeRun, BorderLayout.PAGE_END);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return resultPanel;
    }

    private JPanel getResultTotals() {

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel evaluationPanel = new JPanel(new GridLayout(0, 2));

        evaluationPanel.add(new JLabel(" Best "));
        evaluationPanel.add(bestEvaluation);
        evaluationPanel.add(new JLabel(" Worst "));
        evaluationPanel.add(worstEvaluation);
        evaluationPanel.add(new JLabel(" Average deviation "));
        evaluationPanel.add(averageEvaluation);
        evaluationPanel.add(new JLabel(" Standard deviation "));
        evaluationPanel.add(standardDeviationEvaluation);
        evaluationPanel.setBorder(BorderFactory.createTitledBorder("Evaluation"));

        JPanel stepsPanel = new JPanel(new GridLayout(0, 2));

        stepsPanel.add(new JLabel(" Min "));
        stepsPanel.add(minSteps);
        stepsPanel.add(new JLabel(" Max "));
        stepsPanel.add(maxSteps);
        stepsPanel.add(new JLabel(" Average deviation "));
        stepsPanel.add(averageSteps);
        stepsPanel.add(new JLabel(" Standard deviation "));
        stepsPanel.add(standardDeviationSteps);
        stepsPanel.setBorder(BorderFactory.createTitledBorder("Steps"));

        JPanel otherPanel = new JPanel(new GridLayout(0, 2));

        otherPanel.add(new JLabel(" Optimal solutions "));
        otherPanel.add(optimalSolutions);
        otherPanel.add(new JLabel(" Average duration "));
        otherPanel.add(averageDuration);
        otherPanel.setBorder(BorderFactory.createTitledBorder("Other"));

        rightPanel.add(evaluationPanel);
        rightPanel.add(stepsPanel);
        rightPanel.add(otherPanel);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return rightPanel;
    }

    private void clearFields() {
        runList.clear();
        runs.setListData(runList.toArray());
        runResult.setText("");
        bestEvaluation.setText("");
        worstEvaluation.setText("");
        averageEvaluation.setText("");
        maxSteps.setText("");
        minSteps.setText("");
        averageSteps.setText("");
        optimalSolutions.setText("");
        averageDuration.setText("");
        standardDeviationEvaluation.setText("");
        standardDeviationSteps.setText("");
    }

    private void search() {
        clearFields();

        try {
            int gameType = puzzleType.getSelectedIndex();

            if (gameType == K_QUEENS) {
                int k = Integer.parseInt(kQueensNumberOfQueens.getText());
                localStateManager = new KQueensStateManager(k);
            } else if (gameType == GRAPH_COLOR) {
                int difficulty = graphColorDifficulty.getSelectedIndex();
                int k = Integer.parseInt(graphColorNumberOfColors.getText());
                localStateManager = new GraphColorStateManager(k, difficulty);
            } else if (gameType == EGG_CARTON) {
                int rows = Integer.parseInt(eggCartonNumberOfRows.getText());
                int columns = Integer.parseInt(eggCartonNumberOfColumns.getText());
                int k = Integer.parseInt(eggCartonNumberOfEggs.getText());
                localStateManager = new EggCartoonStateManager(rows, columns, k);
            } else if (gameType == LATIN_SQUARE) {
                int n = Integer.parseInt(latinSquareNumberOfSymbols.getText());
                localStateManager = new LatinSquareStateManager(n);
            }

            int typeSearch = searchType.getSelectedIndex();
            int numberOfSteps = Integer.parseInt(steps.getText());

            if (typeSearch == MIN_CONFLICTS) {
                localSearch = new MinConflicts(localStateManager, numberOfSteps);
            } else if (typeSearch == SIMULATED_ANNEALING) {
                localSearch = new SimulatedAnnealing(localStateManager, numberOfSteps, 100);
            }

            performSearch();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void performSearch() {
        int intNumberOfRuns = Integer.parseInt(numberOfRuns.getText());
        boolean visualize = visualiseSearch.isSelected();

        searchTask = new Search(intNumberOfRuns, visualize);
        searchTask.execute();
    }

    private void showResult(Run run) {
        runResult.setText("");
        runResult.append("Is optimal solution: " + run.isOptimal());
        runResult.append("\nEvaluation: " + run.getEvaluation());
        runResult.append("\nNumber of steps: " + run.getNumberOfSteps());
        runResult.append("\nDuration: " + (run.getDuration() / 1000.0)  + " seconds");
    }

    private void visualizeRun(Run run) {
        localStateManager.printState(run.getResult());
    }

    private class Search extends SwingWorker<Void, Void> {
        private double evaluationBest = Double.MIN_VALUE;
        private double evaluationWorst = Double.MAX_VALUE;
        private double evaluationTotal = 0;

        private int stepsMax = Integer.MIN_VALUE;
        private int stepsMin = Integer.MAX_VALUE;
        private int stepsTotal = 0;

        private int optimalSolutionCount = 0;
        private long totalDuration = 0;

        private int numberOfRuns;
        private boolean visualize;

        public Search(int numberOfRuns, boolean visualize) {
            this.numberOfRuns = numberOfRuns;
            this.visualize = visualize;
        }

        @Override
        protected Void doInBackground() throws Exception {
            stopSearch.setEnabled(true);
            search.setEnabled(false);

            for (int i = 0; i < numberOfRuns && !isCancelled(); i++) {
                localSearch.search(null, visualize);
                Run run = localSearch.getResult();

                    run.setRunNumber(i + 1);
                    runList.add(run);
                    runs.setListData(runList.toArray());

                    double runEvaluation = run.getEvaluation();

                    if (runEvaluation > evaluationBest) {
                        evaluationBest = runEvaluation;
                    }
                    if (runEvaluation < evaluationWorst) {
                        evaluationWorst = runEvaluation;
                    }
                    evaluationTotal += runEvaluation;

                    int runSteps = run.getNumberOfSteps();

                    if (runSteps > stepsMax) {
                        stepsMax = runSteps;
                    }
                    if (runSteps < stepsMin) {
                        stepsMin = runSteps;
                    }
                    stepsTotal += runSteps;

                    optimalSolutionCount += run.isOptimal() ? 1 : 0;
                    totalDuration += run.getDuration();

            }

            return null;
        }

        @Override
        protected void done() {
            stopSearch.setEnabled(false);
            search.setEnabled(true);

            bestEvaluation.setText(Double.toString(evaluationBest));
            bestEvaluation.setCaretPosition(0);
            worstEvaluation.setText(Double.toString(evaluationWorst));
            worstEvaluation.setCaretPosition(0);
            averageEvaluation.setText(Double.toString(evaluationTotal / (double) numberOfRuns));
            averageEvaluation.setCaretPosition(0);

            minSteps.setText(Integer.toString(stepsMin));
            minSteps.setCaretPosition(0);
            maxSteps.setText(Integer.toString(stepsMax));
            maxSteps.setCaretPosition(0);
            averageSteps.setText(Integer.toString(stepsTotal / numberOfRuns));
            averageSteps.setCaretPosition(0);

            optimalSolutions.setText(optimalSolutionCount + "/" + runList.size());
            averageDuration.setText(Double.toString((totalDuration / (double) numberOfRuns) / 1000.0) + " seconds");
            averageDuration.setCaretPosition(0);

            ArrayList<Double> evaluations = new ArrayList<Double>();
            ArrayList<Double> steps = new ArrayList<Double>();

            for (Run run : runList) {
                evaluations.add(run.getEvaluation());
                steps.add((double) run.getNumberOfSteps());
            }

            standardDeviationEvaluation.setText(Double.toString(Util.getStdDev(evaluations)));
            standardDeviationSteps.setText(Double.toString(Util.getStdDev(steps)));
            standardDeviationEvaluation.setCaretPosition(0);
            standardDeviationSteps.setCaretPosition(0);
        }


    }

    private class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (runs.getSelectedIndex() != -1) {
                showResult(runList.get(runs.getSelectedIndex()));
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(search)) {
                search();
            } else if (e.getSource().equals(visualizeRun)) {
                visualizeRun(runList.get(runs.getSelectedIndex()));
            } else if (e.getSource().equals(stopSearch)) {
                localSearch.stopSearch();
                searchTask.cancel(true);
            }
        }
    }

    private class PuzzleTypeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            kQueensPanel.setVisible(false);
            graphColorPanel.setVisible(false);
            eggCartonPanel.setVisible(false);
            latinSquarePanel.setVisible(false);

            int selectedIndex = ((JComboBox) e.getSource()).getSelectedIndex();

            switch (selectedIndex) {
                case K_QUEENS:
                    kQueensPanel.setVisible(true);
                    break;
                case GRAPH_COLOR:
                    graphColorPanel.setVisible(true);
                    break;
                case EGG_CARTON:
                    eggCartonPanel.setVisible(true);
                    break;
                case LATIN_SQUARE:
                    latinSquarePanel.setVisible(true);
                    break;
            }
        }
    }
}
