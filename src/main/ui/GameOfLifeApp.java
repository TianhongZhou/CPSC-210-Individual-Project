package ui;

import model.Block;
import model.InitialGeneration;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * Game Of Life Application
 */
public class GameOfLifeApp {
    private static final String JSON_STORE = "./data/initialGeneration.json";
    private Block block;
    private InitialGeneration ig;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /*
     * EFFECTS: constructs and runs the game of life application
     */
    public GameOfLifeApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        ig = new InitialGeneration();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGameOfLife();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user input
     */
    private void runGameOfLife() {
        boolean keepGoing = true;
        String command;
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user command
     */
    private void processCommand(String command) {
        switch (command) {
            case "a":
                addBlock();
                break;
            case "d":
                deleteBlock();
                break;
            case "i":
                getInitialGeneration();
                break;
            case "l":
                loadInitialGeneration();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes accounts
     */
    private void init() {
        block = new Block(0, 0, 0);
        ig = new InitialGeneration();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    /*
     * EFFECTS: displays menu of options to user
     */
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add or edit block");
        System.out.println("\td -> delete block");
        System.out.println("\ti -> get initial generation");
        System.out.println("\tl -> load earlier generation from file");
        System.out.println("\tq -> quit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts adding block
     */
    private void addBlock() {
        addBlockValueX();
        addBlockValueY();
        addBlockState();
        int x = block.getValueX();
        int y = block.getValueY();
        int n = block.getState();
        ig.addOrEditBlock(x, y, n);
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts adding block's x value
     */
    private void addBlockValueX() {
        System.out.print("Enter x position of the block (an integer):");
        int x = input.nextInt();
        if (0 <= x & x <= 19) {
            block.changeValueX(x);
        } else {
            System.out.println("Please enter a number from 0 to 19\n");
            addBlockValueX();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts adding block's y value
     */
    private void addBlockValueY() {
        System.out.print("Enter y position of the block (an integer):");
        int y = input.nextInt();
        if (0 <= y & y <= 19) {
            block.changeValueY(y);
        } else {
            System.out.println("Please enter a number from 0 to 19\n");
            addBlockValueY();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts adding block's state
     */
    private void addBlockState() {
        System.out.print("Enter the state of the block (an integer):");
        int n = input.nextInt();
        if (0 <= n & n <= 2) {
            block.changeState(n);
        } else {
            System.out.println("Please enter a number from 0 to 2\n");
            addBlockState();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts deleting block
     */
    private void deleteBlock() {
        if (ig.getSize() == 0) {
            System.out.println("Can not delete a block from an empty initial generation\n");
        } else {
            deleteBlockValueX();
            deleteBlockValueY();
            int x = block.getValueX();
            int y = block.getValueY();
            if (ig.containsBlock(x, y, 1)) {
                ig.deleteBlock(x, y);
            } else {
                System.out.println("The block is not in the initial generation\n");
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts deleting block's x value
     */
    private void deleteBlockValueX() {
        System.out.print("Enter x position of the block (an integer):");
        int x = input.nextInt();
        if (0 <= x & x <= 19) {
            block.changeValueX(x);
        } else {
            System.out.println("Please enter a number from 0 to 19\n");
            deleteBlockValueX();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts deleting block's y value
     */
    private void deleteBlockValueY() {
        System.out.print("Enter y position of the block (an integer):");
        int y = input.nextInt();
        if (0 <= y & y <= 19) {
            block.changeValueY(y);
        } else {
            System.out.println("Please enter a number from 0 to 19\n");
            deleteBlockValueY();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts showing the initial generation
     */
    private void getInitialGeneration() {
        ig.fillInitialGenerationWithZeroBlocks();
        ig.sortInitialGeneration();
        System.out.println("The initial generation so far is:\n" + printInitialGeneration());
        displayMenuGetInitialGeneration();
        String command;
        command = input.next();
        command = command.toLowerCase();
        processCommandForI(command);
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user command for "i"
     */
    private void processCommandForI(String command) {
        switch (command) {
            case "e":
                break;
            case "s":
                System.out.println("The initial grid so far is:\n" + printGrid());
                int[][] grid = returnGrid();
                nextChoice(grid);
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: conducts for next step, showing the next generation or go back to menu
     */
    private void nextChoice(int[][] grid) {
        displayMenuGetNextGeneration();
        String command;
        command = input.next();
        command = command.toLowerCase();
        processCommandForS(command, grid);
    }

    /*
     * EFFECTS: displays menu of options to user for getInitialGeneration
     */
    private void displayMenuGetNextGeneration() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> next generation");
        System.out.println("\ts -> save current generation to file");
        System.out.println("\tb -> back to initial generation");
        System.out.println("\tc -> clear all");
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user command for "s"
     */
    private void processCommandForS(String command, int[][] grid) {
        switch (command) {
            case "b":
                break;
            case "c":
                ig = new InitialGeneration();
                break;
            case "n":
                System.out.println("The next generation is:\n" + printNextGrid(grid));
                nextChoice(returnNextGrid(grid));
                break;
            case "s":
                InitialGeneration currentGeneration = new InitialGeneration();
                currentGeneration.convertGridToInitialGeneration(grid);
                saveCurrentGeneration(currentGeneration);
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    /*
     * EFFECTS: return next grid
     */
    private int[][] returnNextGrid(int[][] grid) {
        return ig.nextGenerationGrid(grid);
    }

    /*
     * EFFECTS: convert the next generation as a string
     */
    private StringBuilder printNextGrid(int[][] grid) {
        StringBuilder printSoFar = new StringBuilder("[");
        int[][] newGrid = returnNextGrid(grid);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int n = ig.getIJ(newGrid, i, j);
                String block;
                if (i == 19 && j == 19) {
                    block = n + ",]";
                } else if (j == 19) {
                    block = n + ",\n";
                } else {
                    block = n + ",";
                }
                printSoFar.append(block);
            }
        }
        return printSoFar;
    }

    /*
     * EFFECTS: convert the initial generation as a string
     */
    private StringBuilder printGrid() {
        StringBuilder printSoFar = new StringBuilder("[");
        int[][] grid = ig.convertInitialGenerationToGrid();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int n = ig.getIJ(grid, i, j);
                String block;
                if (i == 19 && j == 19) {
                    block = n + ",]";
                } else if (j == 19) {
                    block = n + ",\n";
                } else {
                    block = n + ",";
                }
                printSoFar.append(block);
            }
        }
        return printSoFar;
    }

    /*
     * EFFECTS: return grid
     */
    private int[][] returnGrid() {
        return ig.convertInitialGenerationToGrid();
    }

    /*
     * EFFECTS: displays menu of options to user for getInitialGeneration
     */
    private void displayMenuGetInitialGeneration() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> edit the list");
        System.out.println("\ts -> show the grid");
    }

    /*
     * EFFECTS: convert the initial generation as a string
     */
    private StringBuilder printInitialGeneration() {
        StringBuilder printSoFar = new StringBuilder("[");
        int size = ig.getSize();
        for (int i = 0; i < size; i++) {
            Block b = ig.getBlock(i);
            int x = b.getValueX();
            int y = b.getValueY();
            int n = b.getState();
            String block;
            if (i % 20 == 0 & i != 0) {
                block = "(" + x + ", " + y + ", " + n + "),\n";
            } else {
                block = "(" + x + ", " + y + ", " + n + "), ";
            }
            printSoFar.append(block);
        }
        printSoFar.append("]");
        return printSoFar;
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
