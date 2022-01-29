package persistence;

import javafx.util.Pair;
import model.Block;
import model.InitialGeneration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * CITATION: part of codes changed from provided code
 * Represents a reader that reads initial generation from JSON data stored in file
 */
public class JsonReader {
    private String source;

    /*
     * EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads initial generation from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public InitialGeneration read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInitialGeneration(jsonObject);
    }

    /*
     * EFFECTS: reads source file as string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses InitialGeneration from JSON object and returns it
     */
    private InitialGeneration parseInitialGeneration(JSONObject jsonObject) {
        InitialGeneration ig = new InitialGeneration();
        addBlocks(ig, jsonObject);
        return ig;
    }

    /*
     * MODIFIES: ig
     * EFFECTS: parses blocks from JSON object and adds them to initial generation
     */
    private void addBlocks(InitialGeneration ig, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("blocks");
        for (Object json : jsonArray) {
            JSONObject nextBlock = (JSONObject) json;
            addBlock(ig, nextBlock);
        }
    }

    /*
     * MODIFIES: ig
     * EFFECTS: parses block from JSON object and adds it to initial generation
     */
    private void addBlock(InitialGeneration ig, JSONObject jsonObject) {
        int x = jsonObject.getInt("positionX");
        int y = jsonObject.getInt("positionY");
        int n = jsonObject.getInt("state");
        ig.addEventLogsWhenReadFile(x, y, n);
    }
}
