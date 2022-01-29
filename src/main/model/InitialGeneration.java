package model;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
 * Represents the initial generation of the game, having a list of block
 */
public class InitialGeneration implements Writable {
    private ArrayList<Block> initialG;   // a list of block that indicates the initial generation
    private EventLog theLog;

    /*
     * EFFECTS: initialGeneration is set to an empty list.
     */
    public InitialGeneration() {
        initialG = new ArrayList<>();
        theLog = EventLog.getInstance();
    }

    public EventLog getTheLog() {
        return theLog;
    }

    /*
     * EFFECTS: return the size of initialG
     */
    public int getSize() {
        return initialG.size();
    }

    /*
     * EFFECTS: set a block into the initial generation
     */
    public void set(int n, Block block) {
        initialG.set(n, block);
    }

    /*
     * REQUIRES : 0 <= n < initialG.getSize()
     * EFFECTS: return the nth block in the initialG
     */
    public Block getBlock(int n) {
        return initialG.get(n);
    }

    /*
     * REQUIRES: 0 <= x, y <= 19; 0 <= n <= 2
     * MODIFIES: this
     * EFFECTS: add the block with x-value = x, y-value = y, state = n to the initialG
     *          add a new event to the event log
     */
    public void addBlock(int x, int y, int n) {
        Block b = new Block(x, y, n);
        initialG.add(b);
        Event e = new Event("Add a block at " + x + ", " + y + " with state " + n);
        theLog.logEvent(e);
    }

    /*
     * REQUIRES: initialG is not empty
     * EFFECTS: return a list of the positions of all the blocks in the initialG
     */
    public ArrayList<Pair<Integer, Integer>> getPositions() {
        ArrayList<Pair<Integer, Integer>> positions = new ArrayList<>();
        for (Block b : initialG) {
            Pair<Integer, Integer> position = b.getPosition();
            positions.add(position);
        }
        return positions;
    }

    /*
     * REQUIRES: 0 <= x, y <= 19; 0 <= n <= 2; ig is not empty
     * EFFECTS: return whether there is a block with the same position in the ig as the added one
     */
    public boolean containsBlock(int x, int y, int n) {
        Block b = new Block(x, y, n);
        Pair<Integer, Integer> p = b.getPosition();
        ArrayList<Pair<Integer, Integer>> ps = getPositions();
        return ps.contains(p);
    }

    /*
     * REQUIRES: 0 <= x, y <= 19; 0 <= n <= 2; ig is not empty
     * MODIFIES: this
     * EFFECTS: If there is already a block with the same position as the new block, change the current block
     *          to the new block;
     *          if the new block is not in the initialGeneration, then add it into the initialGeneration.
     *          Add a new event to the event log
     */
    public void addOrEditBlock(int x, int y, int n) {
        Block b = new Block(x, y, n);
        int size = getSize();
        if (containsBlock(x, y, n)) {
            for (int k = 0; k < size; k++) {
                Block i = getBlock(k);
                if (b.getValueX() == i.getValueX() && b.getValueY() == i.getValueY()) {
                    i.changeState(n);
                }
            }
            Event e = new Event("Edit the block at " + x + ", " + y + " to state " + n);
            theLog.logEvent(e);
        } else {
            addBlock(x, y, n);
        }
    }

    /*
     * REQUIRES: 0 <= x, y <= 19; ig is not empty; the block that want to delete is in the initialGeneration
     * MODIFIES: this
     * EFFECTS: Change the state of the block that want to delete to 0, and then remove it from the
     *          initialGeneration ig.
     *          Add a new event to the event log
     */
    public void deleteBlock(int x, int y) {
        int size = getSize();
        for (int k = 0; k < size; k++) {
            Block i = getBlock(k);
            if (x == i.getValueX() && y == i.getValueY()) {
                i.changeState(0);
                initialG.remove(i);
                break;
            }
        }
        Event e = new Event("Delete the block at " + x + ", " + y);
        theLog.logEvent(e);
    }

    /*
     * MODIFIES: this
     * EFFECTS: fill the initial generation with Block(i, j, 0) for i = 0 to 20 and j = 0 to 20 if initial generation
     *          does not contain Block(i, j) so far.
     */
    public void fillInitialGenerationWithZeroBlocks() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (!containsBlock(i, j, 0)) {
                    Block block = new Block(i, j, 0);
                    initialG.add(block);
                }
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: sort the initial generation to be a list in the order like [(0,0,n), (0,1,n), ..., (1,0,n), (1,1,n)
     *          ..., (19,18,n), (19,19,n)]
     */
    public void sortInitialGeneration() {
        InitialGeneration empty = new InitialGeneration();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                empty.addBlock(i, j, 0);
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int idx = 20 * i + j;
                Block block = getBlock(idx);
                empty.addOrEditBlock(block.getValueX(), block.getValueY(), block.getState());
            }
        }
        removeAll();
        int size = empty.getSize();
        for (int i = 0; i < size; i++) {
            Block b = empty.getBlock(i);
            addBlock(b.getValueX(), b.getValueY(), b.getState());
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: delete all block in the initial generation
     */
    public void removeAll() {
        initialG.clear();
    }

    /*
     * REQUIRES: initial generation should be fillInitialGenerationWithZeroBlocks() and sortInitialGeneration() before
     *           convertInitialGenerationToArray()
     * EFFECTS: convert the initial generation to a matrix
     */
    public int[][] convertInitialGenerationToGrid() {
        int[][] grid = new int[20][20];
        int size = getSize();
        try {
            for (int k = 0; k < size; k++) {
                Block i = getBlock(k);
                int x = i.getValueX();
                int y = i.getValueY();
                int n = i.getState();
                grid[x][y] = n;
            }
        } catch (NullPointerException e) {
            System.out.println("");
        }
        return grid;
    }

    /*
     * MODIFIES: this
     * EFFECTS: convert a matrix to the initial generation
     */
    public void convertGridToInitialGeneration(int[][] grid) {
        removeAll();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int n = getIJ(grid, i, j);
                addBlock(i, j, n);
            }
        }
    }

    /*
     * EFFECTS: return the item of ith row and jth column of grid
     */
    public int getIJ(int[][] grid, int i, int j) {
        return grid[i][j];
    }


    /*
     * EFFECTS: get a list of all the neighbours' state of grid[i][j]
     */
    public ArrayList<Integer> getNeighbours(int[][] grid, int i, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        if (i == 0) {
            neighbours.addAll(getNeighbours0J(grid, j));
        } else if (i == 19) {
            neighbours.addAll(getNeighbours19J(grid, j));
        } else if (j == 0) {
            neighbours.addAll(getNeighboursColumn0(grid, i));
        } else if (j == 19) {
            neighbours.addAll(getNeighboursColumn19(grid, i));
        } else {
            neighbours.addAll(getNeighboursGeneral(grid, i, j));
        }
        return neighbours;
    }

    /*
     * REQUIRES: 0 <= j <= 19
     * EFFECTS: all situations for getting a list of all the neighbours' state of grid[19][j]
     */
    public ArrayList<Integer> getNeighbours19J(int[][] grid, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        if (j == 0) {
            neighbours.addAll(getNeighbours190(grid));
        } else if (j == 19) {
            neighbours.addAll(getNeighbours1919(grid));
        } else {
            neighbours.addAll(getNeighboursRow19(grid, j));
        }
        return neighbours;
    }

    /*
     * REQUIRES: 0 <= j <= 19
     * EFFECTS: all situations for getting a list of all the neighbours' state of grid[0][j]
     */
    public ArrayList<Integer> getNeighbours0J(int[][] grid, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        if (j == 0) {
            neighbours.addAll(getNeighbours00(grid));
        } else if (j == 19) {
            neighbours.addAll(getNeighbours019(grid));
        } else {
            neighbours.addAll(getNeighboursRow0(grid, j));
        }
        return neighbours;
    }

    /*
     * EFFECTS: get a list of all the neighbours' state of grid[0][0]
     */
    public ArrayList<Integer> getNeighbours00(int[][] grid) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,0,1));
        neighbours.add(getIJ(grid,1,0));
        neighbours.add(getIJ(grid,1,1));
        return neighbours;
    }

    /*
     * EFFECTS: get a list of all the neighbours' state of grid[0][19]
     */
    public ArrayList<Integer> getNeighbours019(int[][] grid) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,0,18));
        neighbours.add(getIJ(grid,1,18));
        neighbours.add(getIJ(grid,1,19));
        return neighbours;
    }

    /*
     * REQUIRES: 0 < j < 19
     * EFFECTS: get a list of all the neighbours' state of grid[0][j]
     */
    public ArrayList<Integer> getNeighboursRow0(int[][] grid, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,0,j - 1));
        neighbours.add(getIJ(grid,0,j + 1));
        neighbours.add(getIJ(grid,1,j - 1));
        neighbours.add(getIJ(grid,1,j));
        neighbours.add(getIJ(grid,1,j + 1));
        return neighbours;
    }

    /*
     * EFFECTS: get a list of all the neighbours' state of grid[19][0]
     */
    public ArrayList<Integer> getNeighbours190(int[][] grid) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,18,0));
        neighbours.add(getIJ(grid,18,1));
        neighbours.add(getIJ(grid,19,1));
        return neighbours;
    }

    /*
     * EFFECTS: get a list of all the neighbours' state of grid[19][19]
     */
    public ArrayList<Integer> getNeighbours1919(int[][] grid) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,18,18));
        neighbours.add(getIJ(grid,18,19));
        neighbours.add(getIJ(grid,19,18));
        return neighbours;
    }

    /*
     * REQUIRES: 0 < j < 19
     * EFFECTS: get a list of all the neighbours' state of grid[19][j]
     */
    public ArrayList<Integer> getNeighboursRow19(int[][] grid, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,18,j - 1));
        neighbours.add(getIJ(grid,18,j));
        neighbours.add(getIJ(grid,18,j + 1));
        neighbours.add(getIJ(grid,19,j - 1));
        neighbours.add(getIJ(grid,19,j + 1));
        return neighbours;
    }

    /*
     * REQUIRES: 0 < i < 19
     * EFFECTS: get a list of all the neighbours' state of grid[i][0]
     */
    public ArrayList<Integer> getNeighboursColumn0(int[][] grid, int i) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,i - 1,0));
        neighbours.add(getIJ(grid,i - 1,1));
        neighbours.add(getIJ(grid,i,1));
        neighbours.add(getIJ(grid,i + 1,0));
        neighbours.add(getIJ(grid,i + 1,1));
        return neighbours;
    }

    /*
     * REQUIRES: 0 < i < 19
     * EFFECTS: get a list of all the neighbours' state of grid[i][19]
     */
    public ArrayList<Integer> getNeighboursColumn19(int[][] grid, int i) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,i - 1,18));
        neighbours.add(getIJ(grid,i - 1,19));
        neighbours.add(getIJ(grid,i,18));
        neighbours.add(getIJ(grid,i + 1,18));
        neighbours.add(getIJ(grid,i + 1,19));
        return neighbours;
    }

    /*
     * REQUIRES: 0 < i, j < 19
     * EFFECTS: get a list of all the neighbours' state of grid[i][j]
     */
    public ArrayList<Integer> getNeighboursGeneral(int[][] grid, int i, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(getIJ(grid,i - 1,j - 1));
        neighbours.add(getIJ(grid,i - 1,j));
        neighbours.add(getIJ(grid,i - 1,j + 1));
        neighbours.add(getIJ(grid,i,j - 1));
        neighbours.add(getIJ(grid,i,j + 1));
        neighbours.add(getIJ(grid,i + 1,j - 1));
        neighbours.add(getIJ(grid,i + 1,j));
        neighbours.add(getIJ(grid,i + 1,j + 1));
        return neighbours;
    }

    /*
     * REQUIRES: neighbours is not empty and consists of 0 or 1 or 2
     * EFFECTS: get the number of zeros in the given list
     */
    public int numberOfZero(ArrayList<Integer> neighbours) {
        int count = 0;
        for (Integer i : neighbours) {
            if (i == 0) {
                count = count + 1;
            }
        }
        return count;
    }

    /*
     * REQUIRES: neighbours is not empty and consists of 0 or 1 or 2
     * EFFECTS: get the number of ones in the given list
     */
    public int numberOfOne(ArrayList<Integer> neighbours) {
        int count = 0;
        for (Integer i : neighbours) {
            if (i == 1) {
                count = count + 1;
            }
        }
        return count;
    }

    /*
     * REQUIRES: neighbours is not empty and consists of 0 or 1 or 2
     * EFFECTS: get the number of twos in the given list
     */
    public int numberOfTwo(ArrayList<Integer> neighbours) {
        int count = 0;
        for (Integer i : neighbours) {
            if (i == 2) {
                count = count + 1;
            }
        }
        return count;
    }

    /*
     * REQUIRES: 0 <= state <= 2
     * EFFECTS: apply the rule of the game to an item in the matrix, and return a new state it will be.
     */
    public int ruleOfGame(ArrayList<Integer> n, int s) {
        int output = 0;
        if (s == 0) {
            if (numberOfZero(n) < 3 | numberOfZero(n) > 6) {
                output =  1;
            } else if (numberOfOne(n) > 3) {
                output =  2;
            }
        } else if (s == 1) {
            if (numberOfOne(n) == 2 | numberOfZero(n) == 3) {
                output = 1;
            } else if (numberOfTwo(n) > 2) {
                output =  2;
            }
        } else {
            if (numberOfOne(n) < 2) {
                output =  1;
            } else if (numberOfOne(n) == 2 | numberOfZero(n) == 3) {
                output =  2;
            }
        }
        return output;
    }

    /*
     * MODIFIES: grid
     * EFFECTS: return the grid with next generation
     */
    public int[][] nextGenerationGrid(int[][] grid) {
        int[][] newGrid = new int[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ArrayList<Integer> neighbours = getNeighbours(grid, i, j);
                int state = getIJ(grid, i, j);
                int newState = ruleOfGame(neighbours, state);
                newGrid[i][j] = newState;
            }
        }
        return newGrid;
    }

    /*
     * CITATION: codes changed from provided code
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("blocks", blocksToJson());
        json.put("name", "Initial Generation");
        return json;
    }

    /*
     * EFFECTS: returns blocks in this initial generation as a JSON array
     */
    private JSONArray blocksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Block b : initialG) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }

    /*
     * REQUIRES: 0 <= x, y <= 19; 0 <= n <= 2
     * MODIFIES: this
     * EFFECTS: add a new event to the event log when read the file
     *          add the block to the initialG
     */
    public void addEventLogsWhenReadFile(int x, int y, int n) {
        Event e = new Event("Add a block from saved file to " + x + ", " + y + " with state " + n);
        theLog.logEvent(e);
        addBlock(x, y, n);
    }
}
