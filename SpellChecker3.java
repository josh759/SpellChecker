
package MINIPROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SpellChecker3 {
    private ArrayList<String> dictionary;

    // Constructor initializes the ArrayList for storing dictionary words
    public SpellChecker3() {
        this.dictionary = new ArrayList<>();
    }

    // Loads words from the dictionary file, converts them to lowercase, and sorts
    // the list for binary search
    public void loadDictionary(String dictionaryPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dictionaryPath));
        while (scanner.hasNext()) {
            dictionary.add(scanner.next().toLowerCase());
        }
        scanner.close();
        Collections.sort(dictionary); // Sort the dictionary to enable binary search
    }

    // Checks spelling and prints the misspelled word along with the line number
    // using binary search
    public void checkSpelling(String textFilePath) throws IOException {
        FileInputStream fileStream = new FileInputStream(textFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        String line;
        int lineNumber = 0; // Initialize a line number counter

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number for each new line read
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove punctuation and convert to lower case
                if (!word.isEmpty() && Collections.binarySearch(dictionary, word) < 0) {
                    System.out.println("Misspelled word: " + word + " on line " + lineNumber);
                }
            }
        }

        reader.close();
    }

    public static void main(String[] args) {
        try {
            String dictionaryPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\dictionary.txt";
            String inputPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\Testing.txt";

            SpellChecker3 spellChecker = new SpellChecker3();
            spellChecker.loadDictionary(dictionaryPath); // Load and sort dictionary words
            spellChecker.checkSpelling(inputPath); // Check spelling using binary search
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading testing document: " + e.getMessage());
        }
    }
}
