import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class ScoreManager {

    private static final String FILE_NAME = "highscore.txt";

    //Save a score to the file
    public static void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(String.valueOf(score));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load scores into an array
    public static int[] loadHighScores() {
        Queue<Integer> scores = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    scores.add(parseInt(line)); // Convert each line to an integer
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores.stream().mapToInt(Integer::intValue).toArray();
    }

    //Reverse the array
    private static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // Swap elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    //Sort the array using insertion sort and save to file
    public static void sortAndSaveScores() {
        int[] scores = loadHighScores();

        // Sort the scores
        for (int i = 1; i < scores.length; ++i) {
            int key = scores[i];
            int j = i - 1;

            while (j >= 0 && scores[j] > key) {
                scores[j + 1] = scores[j];
                j = j - 1;
            }
            scores[j + 1] = key;
        }

        //Save sorted scores back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int score : scores) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}