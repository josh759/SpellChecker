// package MINIPROJECT;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class SpellChecker1 {
//     private TernarySearchTrie dictionaryTST;

//     public SpellChecker1() {
//         this.dictionaryTST = new TernarySearchTrie();
//     }

//     public void loadDictionary(String dictionaryFilePath) throws FileNotFoundException {
//         Scanner scanner = new Scanner(new File(dictionaryFilePath));
//         while (scanner.hasNext()) {
//             String word = scanner.next().toLowerCase();
//             dictionaryTST.put(word, 1);
//         }
//         scanner.close();
//     }

//     public void checkSpelling(String textFilePath) throws IOException {
//         FileInputStream fileStream = new FileInputStream(textFilePath);
//         BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
//         String line;
//         int lineNumber = 0; // Initialize line number counter

//         while ((line = reader.readLine()) != null) {
//             lineNumber++; // Increment line number with each new line read
//             String[] words = line.split("\\s+"); // Split each line into words by whitespace
//             for (String word : words) {
//                 word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove non-alphabetic characters and convert
//                                                                        // to lowercase
//                 if (!word.isEmpty() && !dictionaryTST.contains(word)) {
//                     System.out.println("Misspelled word: '" + word + "' found at line " + lineNumber); // Print each
//                                                                                                        // misspelled
//                                                                                                        // word along
//                                                                                                        // with the line
//                                                                                                        // number
//                 }
//             }
//         }
//         reader.close(); // Close the reader to ensure no resource leaks
//     }

//     public List<String> getSuggestions(String misspelledWord) {
//         List<String> suggestions = new ArrayList<>();
//         int minDistance = Integer.MAX_VALUE;
//         for (String word : dictionaryTST.getAllWords()) {
//             int dist = LevenshteinDistance.compute(misspelledWord, word);
//             if (dist < minDistance) {
//                 minDistance = dist;
//                 suggestions.clear();
//                 suggestions.add(word);
//             } else if (dist == minDistance) {
//                 suggestions.add(word);
//             }
//         }
//         return suggestions;
//     }

//     public static void main(String[] args) {
//         try {
//             String dictionaryPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\dictionary.txt";
//             String inputPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\Testing.txt";
//             SpellChecker1 spellChecker = new SpellChecker1();
//             spellChecker.loadDictionary(dictionaryPath);
//             spellChecker.checkSpelling(inputPath);
//         } catch (FileNotFoundException e) {
//             System.out.println("Dictionary file not found: " + e.getMessage());
//         } catch (IOException e) {
//             System.out.println("Error reading testing document: " + e.getMessage());
//         }
//     }

//     private static class TernarySearchTrie {
//         private Node root;
//         private List<String> allWords = new ArrayList<>();

//         private class Node {
//             char character;
//             Node left, middle, right;
//             Integer value;

//             public Node() {
//                 // default constructor
//             }
//         }

//         public void put(String key, Integer value) {
//             if (!contains(key)) {
//                 allWords.add(key);
//             }
//             root = put(root, key, value, 0);
//         }

//         private Node put(Node x, String key, Integer value, int d) {
//             if (key == null || key.isEmpty() || d >= key.length())
//                 return x;

//             if (x == null) {
//                 x = new Node();
//                 x.character = key.charAt(d);
//             }

//             char c = key.charAt(d);
//             if (c < x.character) {
//                 x.left = put(x.left, key, value, d);
//             } else if (c > x.character) {
//                 x.right = put(x.right, key, value, d);
//             } else if (d < key.length() - 1) {
//                 x.middle = put(x.middle, key, value, d + 1);
//             } else {
//                 x.value = (x.value == null) ? value : x.value + value;
//             }
//             return x;
//         }

//         public boolean contains(String key) {
//             return get(root, key, 0) != null;
//         }

//         private Node get(Node x, String key, int d) {
//             if (x == null || d >= key.length())
//                 return null;

//             char c = key.charAt(d);
//             if (c < x.character) {
//                 return get(x.left, key, d);
//             } else if (c > x.character) {
//                 return get(x.right, key, d);
//             } else if (d < key.length() - 1) {
//                 return get(x.middle, key, d + 1);
//             } else {
//                 return x;
//             }
//         }

//         public List<String> getAllWords() {
//             return allWords;
//         }
//     }

//     private static class LevenshteinDistance {
//         public static int compute(String s1, String s2) {
//             if (s1 == null || s2 == null)
//                 return Integer.MAX_VALUE;

//             int[][] dp = new int[s1.length() + 1][s2.length() + 1];
//             for (int i = 0; i <= s1.length(); i++) {
//                 dp[i][0] = i;
//             }
//             for (int j = 0; j <= s2.length(); j++) {
//                 dp[0][j] = j;
//             }
//             for (int i = 1; i <= s1.length(); i++) {
//                 for (int j = 1; j <= s2.length(); j++) {
//                     int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
//                     dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
//                             dp[i - 1][j - 1] + cost);
//                 }
//             }
//             return dp[s1.length()][s2.length()];
//         }
//     }
// }

package MINIPROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker1 {
    private TernarySearchTrie dictionaryTST; // Data structure for storing dictionary words

    public SpellChecker1() {
        this.dictionaryTST = new TernarySearchTrie();
    }

    public void loadDictionary(String dictionaryFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dictionaryFilePath));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase(); // Convert word to lowercase before storing
            dictionaryTST.put(word, 1); // Insert each word with a placeholder value
        }
        scanner.close();
    }

    public void checkSpelling(String textFilePath) throws IOException {
        FileInputStream fileStream = new FileInputStream(textFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!word.isEmpty() && !dictionaryTST.contains(word)) {
                    System.out.println("Misspelled word: " + word);
                    List<String> suggestions = getSuggestions(word);
                    System.out.println("Did you mean: " + suggestions);
                }
            }
        }
        reader.close();
    }

    public List<String> getSuggestions(String misspelledWord) {
        List<String> suggestions = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (String word : dictionaryTST.getAllWords()) {
            int dist = LevenshteinDistance.compute(misspelledWord, word);
            if (dist < minDistance) {
                minDistance = dist;
                suggestions.clear();
                suggestions.add(word);
            } else if (dist == minDistance) {
                suggestions.add(word);
            }
        }
        System.out.println("Minimum distance: " + minDistance);
        return suggestions;

    }

    public static void main(String[] args) {
        try {
            String dictionaryPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\dictionary.txt";
            String inputPath = "C:\\Users\\joshu\\OneDrive\\Desktop\\PROJECT\\WINTER2023\\Spring2024\\MINIPROJECT\\Testing.txt";
            SpellChecker1 spellChecker = new SpellChecker1();
            spellChecker.loadDictionary(dictionaryPath);
            spellChecker.checkSpelling(inputPath);
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading testing document: " + e.getMessage());
        }
    }

    private static class TernarySearchTrie {
        private Node root;
        private List<String> allWords = new ArrayList<>();

        private class Node {
            char character;
            Node left, middle, right;
            Integer value;

        }

        public void put(String key, Integer value) {
            if (!contains(key)) {
                allWords.add(key);
            }
            root = put(root, key, value, 0);
        }

        private Node put(Node x, String key, Integer value, int d) {
            if (key == null || key.isEmpty() || d >= key.length())
                return x;

            if (x == null) {
                x = new Node();
                x.character = key.charAt(d);
            }

            char c = key.charAt(d);
            if (c < x.character) {
                x.left = put(x.left, key, value, d);
            } else if (c > x.character) {
                x.right = put(x.right, key, value, d);
            } else if (d < key.length() - 1) {
                x.middle = put(x.middle, key, value, d + 1);
            } else {
                if (x.value == null) {
                    x.value = value;
                } else {
                    x.value += value;
                }
            }
            return x;
        }

        public boolean contains(String key) {
            return get(root, key, 0) != null;
        }

        private Node get(Node x, String key, int d) {
            if (x == null || d >= key.length()) {
                return null;
            }

            char c = key.charAt(d);
            if (c < x.character) {
                return get(x.left, key, d);
            } else if (c > x.character) {
                return get(x.right, key, d);
            } else if (d < key.length() - 1) {
                return get(x.middle, key, d + 1);
            } else {
                return x;
            }
        }

        public List<String> getAllWords() {
            return allWords;
        }
    }

    private static class LevenshteinDistance {
        public static int compute(String s1, String s2) {
            if (s1 == null || s2 == null) {
                return Integer.MAX_VALUE;
            }

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= s2.length(); j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + cost);
                }
            }
            return dp[s1.length()][s2.length()];
        }
    }
}
