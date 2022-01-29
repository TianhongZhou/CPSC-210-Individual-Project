package model;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    private Block testBlock;
    private int valueX;
    private int valueY;
    private int state;


    @BeforeEach
    void setTestBlock() {
        testBlock = new Block(0, 0, 0);
        valueX = testBlock.getValueX();
        valueY = testBlock.getValueY();
        state = testBlock.getState();
    }

    @Test
    void changeValueXTestMaxNotSameAsOrigin() {
        int newValueX = 19;
        testBlock.changeValueX(newValueX);
        Pair<Integer, Integer> p = new Pair<>(newValueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(newValueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeValueXTestMiddleNotSameAsOrigin() {
        int newValueX = 10;
        testBlock.changeValueX(newValueX);
        Pair<Integer, Integer> p = new Pair<>(newValueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(newValueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeValueXTestMinSameAsOrigin() {
        int newValueX = 0;
        testBlock.changeValueX(newValueX);
        Pair<Integer, Integer> p = new Pair<>(newValueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(newValueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeValueYTestMaxNotSameAsOrigin() {
        int newValueY = 19;
        testBlock.changeValueY(newValueY);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, newValueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(newValueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeValueYTestMiddleNotSameAsOrigin() {
        int newValueY = 10;
        testBlock.changeValueY(newValueY);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, newValueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(newValueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeValueYTestMinSameAsOrigin() {
        int newValueY = 0;
        testBlock.changeValueY(newValueY);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, newValueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(newValueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeStateTestMaxNotSameAsOrigin() {
        int state = 2;
        testBlock.changeState(state);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeStateTestMiddleNotSameAsOrigin() {
        int state = 1;
        testBlock.changeState(state);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }

    @Test
    void changeStateTestMinSameAsOrigin() {
        int state = 0;
        testBlock.changeState(state);
        Pair<Integer, Integer> p = new Pair<>(this.valueX, this.valueY);
        assertEquals(p, testBlock.getPosition());
        assertEquals(valueX, testBlock.getValueX());
        assertEquals(valueY, testBlock.getValueY());
        assertEquals(state, testBlock.getState());
    }
}