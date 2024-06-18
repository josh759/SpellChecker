
package MINIPROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SpellChecker2 {
    private TernarySearchTrie dictionaryTST; // Data structure for storing dictionary words

    public SpellChecker2() {
        this.dictionaryTST = new TernarySearchTrie(); // Initialize the ternary search trie
    }

    public void loadDictionary(String dictionaryFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dictionaryFilePath));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase(); // Read and convert each word to lowercase
            dictionaryTST.put(word, 1); // Insert the word into the trie with a placeholder value
        }
        scanner.close(); // Close the scanner to free resources
    }

    public void checkSpelling(String textFilePath) throws IOException {
        FileInputStream fileStream = new FileInputStream(textFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        String line;
        int lineNumber = 0; // Initialize line number counter

        while ((line = reader.readLine()) != null) {
            lineNumber++; // Increment line number with each new line read
            String[] words = line.split("\\s+"); // Split each line into words by whitespace
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove non-alphabetic characters and convert
                                                                       // to lowercase
                if (!word.isEmpty() && !dictionaryTST.contains(word)) {
                    System.out.println("Misspelled word: '" + word + "' found at line " + lineNumber); // Print each
                                                                                                       // misspelled
                                                                                                       // word along
                                                                                                       // with the line
                                                                                                       // number
                }
            }
        }
        reader.close(); // Close the reader to ensure no resource leaks
    }

    public static void main(String[] args) {
        try {
            String dictionaryPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\dictionary.txt";
            String inputPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\Testing.txt";

            SpellChecker2 spellChecker = new SpellChecker2();
            spellChecker.loadDictionary(dictionaryPath); // Load words into the trie
            spellChecker.checkSpelling(inputPath); // Check spelling in the specified document
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading testing document: " + e.getMessage());
        }
    }
}
