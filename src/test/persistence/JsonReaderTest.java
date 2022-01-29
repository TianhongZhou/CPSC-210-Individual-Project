package persistence;

import model.InitialGeneration;
import model.Block;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * CITATION: part of codes changed from provided code
 */
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            InitialGeneration testInitialGeneration = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInitialGeneration() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInitialGeneration.json");
        try {
            InitialGeneration testInitialGeneration = reader.read();
            assertEquals(0, testInitialGeneration.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInitialGeneration() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInitialGeneration.json");
        try {
            InitialGeneration testInitialGeneration = reader.read();
            assertEquals(2, testInitialGeneration.getSize());
            checkBlock(10, 11, 1, testInitialGeneration.getBlock(0));
            checkBlock(19, 10, 2, testInitialGeneration.getBlock(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
