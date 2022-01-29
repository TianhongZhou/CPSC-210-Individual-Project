package persistence;

import javafx.util.Pair;
import model.InitialGeneration;
import model.Block;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * CITATION: part of codes changed from provided code
 */
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            InitialGeneration testInitialGeneration = new InitialGeneration();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInitialGeneration() {
        try {
            InitialGeneration testInitialGeneration = new InitialGeneration();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInitialGeneration.json");
            writer.open();
            writer.write(testInitialGeneration);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyInitialGeneration.json");
            testInitialGeneration = reader.read();
            assertEquals(0, testInitialGeneration.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInitialGeneration() {
        try {
            InitialGeneration testInitialGeneration = new InitialGeneration();
            testInitialGeneration.addBlock(10, 11, 1);
            testInitialGeneration.addBlock(19, 10, 2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInitialGeneration.json");
            writer.open();
            writer.write(testInitialGeneration);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralInitialGeneration.json");
            testInitialGeneration = reader.read();
            assertEquals(2, testInitialGeneration.getSize());
            checkBlock(10, 11, 1, testInitialGeneration.getBlock(0));
            checkBlock(19, 10, 2, testInitialGeneration.getBlock(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
