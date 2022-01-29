package model;

import javafx.util.Pair;
import org.json.JSONObject;
import persistence.Writable;
import ui.GameOfLifeApp;

/*
 * Represents a block having x-value, y-value (position of it), and state
 */
public class Block implements Writable {
    private Pair<Integer, Integer> position;    // the position of the block
    private int state;                          // the state of the block

    /*
     * REQUIRES: 0 <= x, y <= 19; 0 <= n <= 2
     * EFFECTS: valueX is set to a positive integer x between 0 and 19;
     *          valueY is set to a positive integer y between 0 and 19;
     *          state is set to a positive integer n between 0 and 2.
     */
    public Block(int x, int y, int n) {
        position = new Pair<>(x, y);
        state = n;
    }

    /*
     * EFFECTS: return the position of the block
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /*
     * EFFECTS: return the x-value of the block
     */
    public int getValueX() {
        return position.getKey();
    }

    /*
     * EFFECTS: return the y-value of the block
     */
    public int getValueY() {
        return position.getValue();
    }

    /*
     * EFFECTS: return the state of the block
     */
    public int getState() {
        return state;
    }

    /*
     * REQUIRES: 0 <= x <= 19
     * MODIFIES: this
     * EFFECTS: valueX is set to a new positive integer x between 0 and 19;
     *          no matter whether the x is same as original value or not,
     *          the valueX of the block will always be set to the new value.
     */
    public void changeValueX(int x) {
        int y = position.getValue();
        position = new Pair<>(x, y);
    }

    /*
     * REQUIRES: 0 <= y <= 19
     * MODIFIES: this
     * EFFECTS: valueY is set to a new positive integer y between 0 and 19;
     *          no matter whether the y is same as original value or not,
     *          the valueY of the block will always be set to the new value.
     */
    public void changeValueY(int y) {
        int x = position.getKey();
        position = new Pair<>(x, y);
    }

    /*
     * REQUIRES: 0 <= n <= 2
     * MODIFIES: this
     * EFFECTS: valueX is set to a new positive integer n between 0 and 2;
     *          no matter whether the n is same as original value or not,
     *          the state of the block will always be set to the new value.
     */
    public void changeState(int n) {
        state = n;
    }

    /*
     * CITATION: codes changed from provided code
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positionX", getValueX());
        json.put("positionY", getValueY());
        json.put("state", state);
        return json;
    }
}

