package ui;

import model.Block;
import model.Event;
import model.EventLog;
import model.InitialGeneration;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * Game Of Life Application
 */
public class GameOfLifeAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/initialGeneration.json";
    private static final int entireWidth = 600;
    private static final int entireHeight = 600;
    private static final int gridWidth = 500;
    private static final int gridHeight = 500;
    private static final int boxDim = 25;
    private InitialGeneration ig;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /*
     * EFFECTS: constructs and runs the game of life application
     */
    public GameOfLifeAppGUI() throws FileNotFoundException {
        super("Game of Life");

        ig = new InitialGeneration();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeGraphics();

        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
     * to manipulate this drawing
     *          print the event log after close the program
     * CITATIONS: part of codes changed from provided code
     */
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(entireWidth, entireHeight);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog theLog = ig.getTheLog();
                for (Event e : theLog) {
                    System.out.println(e.toString());
                }
                System.exit(0);
            }
        });
        centreOnScreen();
        flowLayout();
        showGrid();
        setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  layout the grid in the center of the plane
     */
    private void showGrid() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(gridWidth, gridHeight));
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
        while (true) {
            Graphics graphic = panel.getGraphics();
            graphicalInterface(returnGrid(), graphic);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the graphical interface of the grid
     */
    public void graphicalInterface(int[][] grid, Graphics graphic) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    graphic.setColor(Color.WHITE);
                } else if (grid[i][j] == 1) {
                    graphic.setColor(Color.BLACK);
                } else if (grid[i][j] == 2) {
                    graphic.setColor(Color.RED);
                }
                graphic.fillRect(i * boxDim, j * boxDim, boxDim, boxDim);
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the tools to flow layout
     */
    private void flowLayout() {
        FlowLayout experimentLayout = new FlowLayout();
        setLayout(experimentLayout);
        addTool();
        deleteTool();
        nextTool();
        saveTool();
        loadTool();
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the load tool
     */
    private void loadTool() {
        JPanel panel = new JPanel();
        JButton load = new JButton("Load");
        panel.add(load);
        getContentPane().add(BorderLayout.NORTH, panel);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadInitialGeneration();
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the save tool
     */
    private void saveTool() {
        JPanel panel = new JPanel();
        JButton save = new JButton("Save");
        panel.add(save);
        getContentPane().add(BorderLayout.NORTH, panel);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] grid = returnGrid();
                InitialGeneration currentGeneration = new InitialGeneration();
                currentGeneration.convertGridToInitialGeneration(grid);
                saveCurrentGeneration(currentGeneration);
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the next tool
     */
    private void nextTool() {
        JPanel panel = new JPanel();
        JButton next = new JButton("Next");
        panel.add(next);
        getContentPane().add(BorderLayout.NORTH, panel);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] grid = returnGrid();
                int[][] newGrid = returnNextGrid(grid);
                ig.convertGridToInitialGeneration(newGrid);
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the delete tool
     */
    private void deleteTool() {
        JPanel panel = new JPanel();
        JButton delete = new JButton("Delete");
        JLabel labelX = new JLabel("x-value (0-19)");
        JLabel labelY = new JLabel("y-value (0-19)");
        JTextField tf1 = new JTextField(3);
        JTextField tf2 = new JTextField(3);
        panel.add(labelX);
        panel.add(tf1);
        panel.add(labelY);
        panel.add(tf2);
        panel.add(delete);
        getContentPane().add(BorderLayout.NORTH, panel);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(tf1.getText());
                int y = Integer.parseInt(tf2.getText());
                ig.deleteBlock(x, y);
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS:  visual part of the add tool
     */
    private void visualAddTool(JPanel panel, JButton add, JTextField tf1, JTextField tf2, JTextField tf3) {
        JLabel labelX = new JLabel("x-value (0-19)");
        JLabel labelY = new JLabel("y-value (0-19)");
        JLabel state = new JLabel("state (0-2)");
        panel.add(labelX);
        panel.add(tf1);
        panel.add(labelY);
        panel.add(tf2);
        panel.add(state);
        panel.add(tf3);
        panel.add(add);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the add tool
     */
    private void addTool() {
        JPanel panel = new JPanel();
        JButton add = new JButton("Add");
        JTextField tf1 = new JTextField(3);
        JTextField tf2 = new JTextField(3);
        JTextField tf3 = new JTextField(3);
        visualAddTool(panel, add, tf1, tf2, tf3);
        getContentPane().add(BorderLayout.NORTH, panel);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(tf1.getText());
                int y = Integer.parseInt(tf2.getText());
                int n = Integer.parseInt(tf3.getText());
                ig.addOrEditBlock(x, y, n);
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS:  frame is centred on desktop
     * CITATIONS: part of codes changed from provided code
     */
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * EFFECTS: return next grid
     */
    private int[][] returnNextGrid(int[][] grid) {
        return ig.nextGenerationGrid(grid);
    }

    /*
     * EFFECTS: return grid
     */
    private int[][] returnGrid() {
        return ig.convertInitialGenerationToGrid();
    }

    /*
     * EFFECTS: saves the initial generation to file
     * CITATION: codes changed from provided code
     */
    private void saveCurrentGeneration(InitialGeneration currentGeneration) {
        try {
            jsonWriter.open();
            jsonWriter.write(currentGeneration);
            jsonWriter.close();
            System.out.println("Saved initial generation to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads the initial generation from file
     * CITATION: codes changed from provided code
     */
    private void loadInitialGeneration() {
        try {
            ig = jsonReader.read();
            System.out.println("Loaded initial generation from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

