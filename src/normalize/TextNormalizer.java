/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package normalize;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextNormalizer {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            StringBuilder normalizedText = new StringBuilder();
            String line;
            boolean newSentence = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    if (newSentence) {
                        line = line.substring(0, 1).toUpperCase() + line.substring(1);
                        newSentence = false;
                    }

                    line = line.replaceAll("\\s+", " ");
                    line = line.replaceAll(" ,", ",");
                    line = line.replaceAll(" \\.", ".");
                    line = line.replaceAll(" :", ":");
                    if (line.startsWith("\"")) {
                        line = line.replaceAll("\" ", "\"");
                    }
                    if (line.endsWith("\".")) {
                        line = line.replaceAll(" \"\\.", "\".");
                    }
                    if (line.endsWith(".")) {
                        newSentence = true;
                    }
                    normalizedText.append(line).append(" ");
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(normalizedText.toString().trim() + ".");
            writer.close();
            System.out.println("Text normalization completed. Check 'output.txt' for the result.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
