package model;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitialGenerationTest {
    private InitialGeneration testInitialGeneration;

    @BeforeEach
    void setTestInitialGeneration() {
        testInitialGeneration = new InitialGeneration();
    }

    @Test
    void addBlockTestOnce() {
        testInitialGeneration.addBlock(1,2,0);
        Block b = new Block(1, 2, 0);
        assertEquals(1, testInitialGeneration.getSize());
        Block get = testInitialGeneration.getBlock(0);
        assertEquals(b.getValueX(), get.getValueX());
        assertEquals(b.getValueY(), get.getValueY());
        assertEquals(b.getState(), get.getState());
    }

    @Test
    void addBlockTestTwice() {
        testInitialGeneration.addBlock(1,2,0);
        Block b1 = new Block(1, 2, 0);
        assertEquals(1, testInitialGeneration.getSize());
        Block get0 = testInitialGeneration.getBlock(0);
        assertEquals(b1.getValueX(), get0.getValueX());
        assertEquals(b1.getValueY(), get0.getValueY());
        assertEquals(b1.getState(), get0.getState());

        testInitialGeneration.addBlock(10,11,1);
        Block b2 = new Block(10, 11, 1);
        assertEquals(2, testInitialGeneration.getSize());
        Block get1 = testInitialGeneration.getBlock(1);
        assertEquals(b1.getValueX(), get0.getValueX());
        assertEquals(b1.getValueY(), get0.getValueY());
        assertEquals(b1.getState(), get0.getState());
        assertEquals(b2.getValueX(), get1.getValueX());
        assertEquals(b2.getValueY(), get1.getValueY());
        assertEquals(b2.getState(), get1.getState());
    }

    @Test
    void getPositionsTest() {
        testInitialGeneration.addBlock(1,2,0);
        Pair<Integer, Integer> position1 = new Pair<>(1, 2);
        testInitialGeneration.addBlock(10,11,1);
        Pair<Integer, Integer> position2 = new Pair<>(10, 11);
        testInitialGeneration.addBlock(19,10,2);
        Pair<Integer, Integer> position3 = new Pair<>(19, 10);
        ArrayList<Pair<Integer, Integer>> positions = new ArrayList<>();
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
        assertEquals(positions, testInitialGeneration.getPositions());
    }

    @Test
    void  containsBlockTest() {
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(10,11,1);
        testInitialGeneration.addBlock(19,10,2);
        assertTrue(testInitialGeneration.containsBlock(1, 2, 0));
        assertTrue(testInitialGeneration.containsBlock(1, 2, 1));
        assertTrue(testInitialGeneration.containsBlock(10,11,1));
        assertTrue(testInitialGeneration.containsBlock(10,11,2));
        assertTrue(testInitialGeneration.containsBlock(19,10,2));
        assertTrue(testInitialGeneration.containsBlock(19,10,0));
        assertFalse(testInitialGeneration.containsBlock(1, 3, 0));
        assertFalse(testInitialGeneration.containsBlock(19, 9, 2));
        assertFalse(testInitialGeneration.containsBlock(11, 11, 0));
    }

    @Test
    void addOrEditBlockTestDoesNotExist() {
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(10,11,1);
        testInitialGeneration.addBlock(19,10,2);
        testInitialGeneration.addOrEditBlock(12, 13, 1);
        assertEquals(4, testInitialGeneration.getSize());
        assertEquals(1, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(0).getState());
        assertEquals(10, testInitialGeneration.getBlock(1).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(1).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(1).getState());
        assertEquals(19, testInitialGeneration.getBlock(2).getValueX());
        assertEquals(10, testInitialGeneration.getBlock(2).getValueY());
        assertEquals(2, testInitialGeneration.getBlock(2).getState());
        assertEquals(12, testInitialGeneration.getBlock(3).getValueX());
        assertEquals(13, testInitialGeneration.getBlock(3).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(3).getState());
    }

    @Test
    void addOrEditBlockTestExists() {
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(10,11,1);
        testInitialGeneration.addBlock(19,10,2);
        testInitialGeneration.addOrEditBlock(19, 10, 1);
        assertEquals(3, testInitialGeneration.getSize());
        assertEquals(1, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(0).getState());
        assertEquals(10, testInitialGeneration.getBlock(1).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(1).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(1).getState());
        assertEquals(19, testInitialGeneration.getBlock(2).getValueX());
        assertEquals(10, testInitialGeneration.getBlock(2).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(2).getState());
        testInitialGeneration.addOrEditBlock(10, 11, 0);
        assertEquals(3, testInitialGeneration.getSize());
        assertEquals(1, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(0).getState());
        assertEquals(10, testInitialGeneration.getBlock(1).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(1).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(1).getState());
        assertEquals(19, testInitialGeneration.getBlock(2).getValueX());
        assertEquals(10, testInitialGeneration.getBlock(2).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(2).getState());
        testInitialGeneration.addOrEditBlock(1, 2, 2);
        assertEquals(3, testInitialGeneration.getSize());
        assertEquals(1, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(2, testInitialGeneration.getBlock(0).getState());
        assertEquals(10, testInitialGeneration.getBlock(1).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(1).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(1).getState());
        assertEquals(19, testInitialGeneration.getBlock(2).getValueX());
        assertEquals(10, testInitialGeneration.getBlock(2).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(2).getState());
    }

    @Test
    void deleteBlockTest() {
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(10,11,1);
        testInitialGeneration.addBlock(19,10,2);
        testInitialGeneration.deleteBlock(19, 10);
        assertEquals(2, testInitialGeneration.getSize());
        assertEquals(1, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(0).getState());
        assertEquals(10, testInitialGeneration.getBlock(1).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(1).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(1).getState());
        testInitialGeneration.deleteBlock(1, 2);
        assertEquals(1, testInitialGeneration.getSize());
        assertEquals(10, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(0).getState());
        testInitialGeneration.deleteBlock(10, 12);
        assertEquals(1, testInitialGeneration.getSize());
        assertEquals(10, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(0).getState());
        testInitialGeneration.deleteBlock(9, 11);
        assertEquals(1, testInitialGeneration.getSize());
        assertEquals(10, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(0).getState());
        testInitialGeneration.deleteBlock(9, 12);
        assertEquals(1, testInitialGeneration.getSize());
        assertEquals(10, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(0).getState());
        testInitialGeneration.deleteBlock(10, 11);
        assertEquals(0, testInitialGeneration.getSize());
    }

    @Test
    void fillInitialGenerationWithZeroBlocksTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        testInitialGeneration.fillInitialGenerationWithZeroBlocks();
        assertEquals(400, testInitialGeneration.getSize());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++){
                assertTrue(testInitialGeneration.containsBlock(i, j, 0));
            }
        }
    }

    @Test
    void sortInitialGenerationTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        testInitialGeneration.fillInitialGenerationWithZeroBlocks();
        testInitialGeneration.sortInitialGeneration();
        assertEquals(0, testInitialGeneration.getBlock(0).getValueX());
        assertEquals(0, testInitialGeneration.getBlock(0).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(0).getState());
        assertEquals(1, testInitialGeneration.getBlock(22).getValueX());
        assertEquals(2, testInitialGeneration.getBlock(22).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(22).getState());
        assertEquals(10, testInitialGeneration.getBlock(211).getValueX());
        assertEquals(11, testInitialGeneration.getBlock(211).getValueY());
        assertEquals(1, testInitialGeneration.getBlock(211).getState());
        assertEquals(19, testInitialGeneration.getBlock(390).getValueX());
        assertEquals(10, testInitialGeneration.getBlock(390).getValueY());
        assertEquals(2, testInitialGeneration.getBlock(390).getState());
        assertEquals(19, testInitialGeneration.getBlock(399).getValueX());
        assertEquals(19, testInitialGeneration.getBlock(399).getValueY());
        assertEquals(0, testInitialGeneration.getBlock(399).getState());
    }

    @Test
    void removeAllTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        assertEquals(3, testInitialGeneration.getSize());
        testInitialGeneration.removeAll();
        assertEquals(0, testInitialGeneration.getSize());
    }

    @Test
    void convertInitialGenerationToArrayTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        assertEquals(20, grid.length);
        for (int i = 0; i < 20; i++) {
            assertEquals(20, grid[i].length);
        }
        assertEquals(0, grid[0][0]);
        assertEquals(0, grid[1][2]);
        assertEquals(1, grid[10][11]);
        assertEquals(2, grid[19][10]);
        assertEquals(0, grid[19][19]);
    }

    @Test
    void getIJTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        assertEquals(0, testInitialGeneration.getIJ(grid,0,0));
        assertEquals(0, testInitialGeneration.getIJ(grid,1,2));
        assertEquals(1, testInitialGeneration.getIJ(grid,10,11));
        assertEquals(2, testInitialGeneration.getIJ(grid,19,10));
    }

    @Test
    void getNeighbours00Test() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(1,0,1);
        testInitialGeneration.addBlock(1,1,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours00(grid);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighbours019Test() {
        testInitialGeneration.addBlock(0,18,1);
        testInitialGeneration.addBlock(1,18,1);
        testInitialGeneration.addBlock(1,19,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours019(grid);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighboursRow0Test() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(0,3,0);
        testInitialGeneration.addBlock(1,1,2);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighboursRow0(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighbours190Test() {
        testInitialGeneration.addBlock(18,0,1);
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(19,1,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours190(grid);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighbours1919Test() {
        testInitialGeneration.addBlock(18,18,1);
        testInitialGeneration.addBlock(18,19,2);
        testInitialGeneration.addBlock(19,18,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours1919(grid);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighboursRow19Test() {
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(18,2,2);
        testInitialGeneration.addBlock(18,3,1);
        testInitialGeneration.addBlock(19,1,1);
        testInitialGeneration.addBlock(19,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighboursRow19(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(2, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
        assertEquals(1, neighbours.get(3));
        assertEquals(0, neighbours.get(4));
    }

    @Test
    void getNeighboursColumn0Test() {
        testInitialGeneration.addBlock(1,0,1);
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(3,0,2);
        testInitialGeneration.addBlock(3,1,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighboursColumn0(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighboursColumn19Test() {
        testInitialGeneration.addBlock(1,18,1);
        testInitialGeneration.addBlock(1,19,0);
        testInitialGeneration.addBlock(2,18,2);
        testInitialGeneration.addBlock(3,18,2);
        testInitialGeneration.addBlock(3,19,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighboursColumn19(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighboursGeneralTest() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighboursGeneral(grid, 2, 2);
        assertEquals(8, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
        assertEquals(1, neighbours.get(5));
        assertEquals(0, neighbours.get(6));
        assertEquals(2, neighbours.get(7));
    }

    @Test
    void getNeighbours0JTestJEquals0() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(1,0,1);
        testInitialGeneration.addBlock(1,1,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours0J(grid, 0);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighbours0JTestJEquals19() {
        testInitialGeneration.addBlock(0,18,1);
        testInitialGeneration.addBlock(1,18,1);
        testInitialGeneration.addBlock(1,19,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours0J(grid, 19);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighbours0JTestJGeneral() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(0,3,0);
        testInitialGeneration.addBlock(1,1,2);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours0J(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighbours19JTestJEquals0() {
        testInitialGeneration.addBlock(18,0,1);
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(19,1,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours19J(grid, 0);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighbours19JTestJEquals19() {
        testInitialGeneration.addBlock(18,18,1);
        testInitialGeneration.addBlock(18,19,2);
        testInitialGeneration.addBlock(19,18,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours19J(grid, 19);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighbours19JTestJGeneral() {
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(18,2,2);
        testInitialGeneration.addBlock(18,3,1);
        testInitialGeneration.addBlock(19,1,1);
        testInitialGeneration.addBlock(19,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours19J(grid, 2);
        assertEquals(5, neighbours.size());
        assertEquals(2, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
        assertEquals(1, neighbours.get(3));
        assertEquals(0, neighbours.get(4));
    }

    @Test
    void getNeighboursTest00() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(1,0,1);
        testInitialGeneration.addBlock(1,1,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 0, 0);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighboursTest019() {
        testInitialGeneration.addBlock(0,18,1);
        testInitialGeneration.addBlock(1,18,1);
        testInitialGeneration.addBlock(1,19,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 0, 19);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(1, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
    }

    @Test
    void getNeighboursTest0J() {
        testInitialGeneration.addBlock(0,1,1);
        testInitialGeneration.addBlock(0,3,0);
        testInitialGeneration.addBlock(1,1,2);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 0, 2);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighboursTest190() {
        testInitialGeneration.addBlock(18,0,1);
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(19,1,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 19, 0);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighboursTest1919() {
        testInitialGeneration.addBlock(18,18,1);
        testInitialGeneration.addBlock(18,19,2);
        testInitialGeneration.addBlock(19,18,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 19, 19);
        assertEquals(3, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
    }

    @Test
    void getNeighboursTest19J() {
        testInitialGeneration.addBlock(18,1,2);
        testInitialGeneration.addBlock(18,2,2);
        testInitialGeneration.addBlock(18,3,1);
        testInitialGeneration.addBlock(19,1,1);
        testInitialGeneration.addBlock(19,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 19, 2);
        assertEquals(5, neighbours.size());
        assertEquals(2, neighbours.get(0));
        assertEquals(2, neighbours.get(1));
        assertEquals(1, neighbours.get(2));
        assertEquals(1, neighbours.get(3));
        assertEquals(0, neighbours.get(4));
    }

    @Test
    void getNeighboursTestI0() {
        testInitialGeneration.addBlock(1,0,1);
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(3,0,2);
        testInitialGeneration.addBlock(3,1,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 0);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighboursTestI19() {
        testInitialGeneration.addBlock(1,18,1);
        testInitialGeneration.addBlock(1,19,0);
        testInitialGeneration.addBlock(2,18,2);
        testInitialGeneration.addBlock(3,18,2);
        testInitialGeneration.addBlock(3,19,1);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 19);
        assertEquals(5, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
    }

    @Test
    void getNeighboursTestIJ() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        assertEquals(8, neighbours.size());
        assertEquals(1, neighbours.get(0));
        assertEquals(0, neighbours.get(1));
        assertEquals(2, neighbours.get(2));
        assertEquals(2, neighbours.get(3));
        assertEquals(1, neighbours.get(4));
        assertEquals(1, neighbours.get(5));
        assertEquals(0, neighbours.get(6));
        assertEquals(2, neighbours.get(7));
    }

    @Test
    void numberOfZeroTest() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int zeros = testInitialGeneration.numberOfZero(neighbours);
        assertEquals(2, zeros);
    }

    @Test
    void numberOfOneTest() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int ones = testInitialGeneration.numberOfOne(neighbours);
        assertEquals(3, ones);
    }

    @Test
    void numberOfTwoTest() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int twos = testInitialGeneration.numberOfTwo(neighbours);
        assertEquals(3, twos);
    }

    @Test
    void ruleOfGameTestS0Z2() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,2);
        testInitialGeneration.addBlock(2,1,2);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(1, newState);
    }

    @Test
    void ruleOfGameTestS0Z4() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS0Z6() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,0);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS0Z8() {
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,0);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(1, newState);
    }

    @Test
    void ruleOfGameTestS0O6() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(2, newState);
    }

    @Test
    void ruleOfGameTestS0O1() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,0);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(2, newState);
    }

    @Test
    void ruleOfGameTestS1O1() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,0);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS1O2() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,0);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(1, newState);
    }

    @Test
    void ruleOfGameTestS1Z2() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS1Z3() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,1);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(1, newState);
    }

    @Test
    void ruleOfGameTestS1T2() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,1);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,1);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS1T3() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,1);
        testInitialGeneration.addBlock(2,2,1);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(2, newState);
    }

    @Test
    void ruleOfGameTestS2O0() {
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(1, newState);
    }

    @Test
    void ruleOfGameTestS2O2() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,1);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,0);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(2, newState);
    }

    @Test
    void ruleOfGameTestS2O3() {
        testInitialGeneration.addBlock(1,1,1);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,1);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,0);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void ruleOfGameTestS2Z3() {
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(2, newState);
    }

    @Test
    void ruleOfGameTestS2Z4() {
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(1,2,0);
        testInitialGeneration.addBlock(1,3,1);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,0);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        ArrayList<Integer> neighbours = testInitialGeneration.getNeighbours(grid, 2, 2);
        int state = grid[2][2];
        int newState = testInitialGeneration.ruleOfGame(neighbours, state);
        assertEquals(0, newState);
    }

    @Test
    void nextGenerationTest() {
        testInitialGeneration.addBlock(1,1,0);
        testInitialGeneration.addBlock(1,2,2);
        testInitialGeneration.addBlock(1,3,0);
        testInitialGeneration.addBlock(2,1,0);
        testInitialGeneration.addBlock(2,2,2);
        testInitialGeneration.addBlock(2,3,2);
        testInitialGeneration.addBlock(3,1,1);
        testInitialGeneration.addBlock(3,2,1);
        testInitialGeneration.addBlock(3,3,2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        int[][] newGrid = testInitialGeneration.nextGenerationGrid(grid);
        assertEquals(0, newGrid[0][0]);
        assertEquals(0, newGrid[0][1]);
        assertEquals(0, newGrid[0][2]);
        assertEquals(0, newGrid[0][3]);
        assertEquals(0, newGrid[0][4]);
        assertEquals(0, newGrid[1][0]);
        assertEquals(0, newGrid[1][1]);
        assertEquals(1, newGrid[1][2]);
        assertEquals(0, newGrid[1][3]);
        assertEquals(0, newGrid[2][0]);
        assertEquals(0, newGrid[2][1]);
        assertEquals(2, newGrid[2][2]);
        assertEquals(1, newGrid[2][3]);
        assertEquals(0, newGrid[3][0]);
        assertEquals(0, newGrid[3][1]);
        assertEquals(2, newGrid[3][2]);
        assertEquals(1, newGrid[3][3]);
    }

    @Test
    void convertGridToInitialGenerationTest() {
        testInitialGeneration.addBlock(1, 2, 0);
        testInitialGeneration.addBlock(10, 11, 1);
        testInitialGeneration.addBlock(19, 10, 2);
        int[][] grid = testInitialGeneration.convertInitialGenerationToGrid();
        testInitialGeneration.removeAll();
        testInitialGeneration.convertGridToInitialGeneration(grid);
        assertEquals(400, testInitialGeneration.getSize());
        assertTrue(testInitialGeneration.containsBlock(1,  2,  0));
        assertTrue(testInitialGeneration.containsBlock(10, 11, 1));
        assertTrue(testInitialGeneration.containsBlock(19, 10, 2));
    }

    @Test
    void convertInitialGenerationToGridTestErrorInTry() {
        testInitialGeneration.addOrEditBlock(1, 2, 0);
        testInitialGeneration.set(0, null);
        try {
            testInitialGeneration.convertInitialGenerationToGrid();
        } catch (NullPointerException ignored) {

        }
    }

    @Test
    void addEventLogsWhenReadFileTest() {
        testInitialGeneration.addEventLogsWhenReadFile(1, 2, 0);
        assertTrue(testInitialGeneration.containsBlock(1,  2,  0));
    }

    @Test
    void getTheLogTest() {
        assertNotEquals(testInitialGeneration.getTheLog(), new EventLog());
    }
}
