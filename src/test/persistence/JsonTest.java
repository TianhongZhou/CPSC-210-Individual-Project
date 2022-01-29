package persistence;

import javafx.util.Pair;
import model.Block;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    /*
     * CITATION: change from provided code
     */
    protected void checkBlock(Integer x, Integer y, Integer state, Block block) {
        Pair<Integer, Integer> position = block.getPosition();
        assertEquals(x, position.getKey());
        assertEquals(y, position.getValue());
        assertEquals(state, block.getState());
    }
}
