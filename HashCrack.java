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
        for (double decimalValue = 0.0000000000001; decimalValue <= 20.0; decimalValue += 0.0000000000001) {
            String hash = generateHash(decimalValue);
            if (hash.equals(userHash)) {
                matchingValues.add(decimalValue);
                break; // No need to continue if found
            }
            // Print progress every second
            long currentTime = System.currentTimeMillis();
            if (currentTime - startTime >= 1000) {
                long currentLine = Math.round((decimalValue - 0.0000000000001) / 0.0000000000001) + 1;
                System.out.printf("\rProgress: %d out of %d hashes", currentLine, totalLines);
                startTime = currentTime; // Update startTime
            }
            
            /* 
            Write the decimal value and its corresponding hash to a file. 
            Uncomment this line to enable logging, but be cautious as it may consume storage space. 
            Note that enabling logging significantly slows down the comparison process.
            */ 
            
            //printToFile(decimalValue, hash);
            
        }
        return matchingValues;
    }

    // Method to print decimal value and hash to a file
    public static void printToFile(double decimalValue, String hash) {
        try {
            FileWriter writer = new FileWriter("hashes.txt", true);
            writer.write(String.format("%.13f,%s\n", decimalValue, hash));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hash: ");
        String userHash = scanner.nextLine();
        scanner.close(); // Close the scanner after use
    
        // Calculate total number of lines
        long totalLines = Math.round((20.0 - 0.0000000000001) / 0.0000000000001);
    
        List<String> targetHashes = new ArrayList<>();
        List<Double> matchingValues = findMatchingHashes(targetHashes, userHash, totalLines);
        System.out.println("\nMatching decimal values:");
        for (Double value : matchingValues) {
            System.out.println(value);
        }
    }
}