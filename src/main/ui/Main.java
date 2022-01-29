package ui;

import java.io.FileNotFoundException;

/*
 * The main class for the application
 * CITATION: codes changed from provided code
 */
public class Main {
    public static void main(String[] args) {
        try {
            new GameOfLifeAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
