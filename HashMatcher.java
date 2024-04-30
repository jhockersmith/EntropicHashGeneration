/*
 * This Java source file implements a program for comparing hash keys to all possible hashes of decimal values between 0.0000000000001.and 20
 * 
 * MIT License
 * 
 * Copyright (c) 2024 Johnathan Hockersmith
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * Check out the README.md for detailed information about the project.
 */
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCrack {
    // Method to generate hash from a decimal value
    public static String generateHash(double decimalValue) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(Double.toString(decimalValue).getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Method to find matching hash in a list of hashes
    public static List<Double> findMatchingHashes(List<String> targetHashes, String userHash, long totalLines) {
        List<Double> matchingValues = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        long lastUpdateTime = startTime;
        double startValue = 0.00000000000000000001;
        double endValue = 20.0;
        double increment = 0.00000000000000000001;

        for (double decimalValue = startValue; decimalValue <= endValue; decimalValue += increment) {
            String hash = generateHash(decimalValue);
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime >= 1000) { // Check if half a second has elapsed
                long elapsedTime = currentTime - startTime;
                long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
                long minutes = (elapsedTime / (1000 * 60)) % 60;
                long seconds = (elapsedTime / 1000) % 60;
                long currentLine = Math.round((decimalValue - startValue) / increment) + 1;
                System.out.printf("\rProgress: %d out of %d hashes | Elapsed Time: %02d:%02d:%02d",
                                    currentLine, totalLines, hours, minutes, seconds);
                lastUpdateTime = currentTime; // Update lastUpdateTime
            }

            if (hash.equals(userHash)) {
                matchingValues.add(decimalValue);
                break; // No need to continue if found
            }
            /* 
            Write the decimal value and its corresponding hash to a file. 
            Uncomment this line to enable logging, but be cautious as it may consume storage space. 
            Note that enabling logging significantly slows down the comparison process.
            */ 
            // printToFile(decimalValue, hash);
        }
        return matchingValues;
    }

    // Method to print decimal value and hash to a file
    public static void printToFile(double decimalValue, String hash) {
        try {
            FileWriter writer = new FileWriter("hashes.txt", true);
            writer.write(String.format("%.20f,%s\n", decimalValue, hash)); // Ensure consistent decimal length
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hash: ");
        String userHash = scanner.nextLine();
        scanner.close();
    
        // Calculate total number of lines
        long totalLines = Math.round((20.0 - 0.00000000000000000001) / 0.00000000000000000001);
    
        List<String> targetHashes = new ArrayList<>();
        List<Double> matchingValues = findMatchingHashes(targetHashes, userHash, totalLines);
        System.out.println("\nMatching decimal values:");
        for (Double value : matchingValues) {
            System.out.println(value);
        }
    }
}
