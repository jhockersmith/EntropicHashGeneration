/*
 * This Java source file implements a program for generating entropy and hash keys based on natural phenomena.
 * 
 * MIT License
 * 
 * Copyright (c) 2024 Johna`than Hockersmith
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


 import java.io.File;
 import java.util.Scanner;
 import java.io.IOException;
 import javax.imageio.ImageIO;
 import java.security.MessageDigest;
 import java.awt.image.BufferedImage;
 import java.security.NoSuchAlgorithmException;
 
 
 public class App {
     public static void main(String[] args) {
     System.out.println("Entropy and Hash Key Generation:");
 
     // Get the path to the input image from the user
     System.out.print("Enter the path to the input image: ");
     Scanner scanner = new Scanner(System.in);
     
     String imagePath = scanner.nextLine();
 
     try {
         // Read the input image
         BufferedImage image = ImageIO.read(new File(imagePath));
         // Calculate entropy
         double entropy = calculateEntropy(image);
         // Generate hash key using entropy value
         String hashKey = generateHash(Double.toString(entropy));
 
         // Print entropy and hash key
         System.out.println("Entropy: " + entropy);
         System.out.println("Hash Key: " + hashKey);
     } catch (IOException e) {
         e.printStackTrace();
     } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
     } finally {
         scanner.close();
     }
 }
 
 // Function to calculate entropy of an image
 public static double calculateEntropy(BufferedImage image) {
     int[] histogram = new int[256];
     int totalPixels = image.getWidth() * image.getHeight();
 
     // Calculate frequency of each pixel intensity
     for (int y = 0; y < image.getHeight(); y++) {
         for (int x = 0; x < image.getWidth(); x++) {
             int pixel = image.getRGB(x, y);
             int intensity = (pixel >> 16) & 0xff; // Red component
             histogram[intensity]++;
         }
     }
 
     // Calculate entropy
     double entropy = 0;
     for (int i = 0; i < histogram.length; i++) {
         if (histogram[i] > 0) {
             double probability = (double) histogram[i] / totalPixels;
             entropy -= probability * (Math.log(probability) / Math.log(2));
         }
     }
     return entropy;
 }
 
 // Function to generate a secure hash using SHA-256
 public static String generateHash(String input) throws NoSuchAlgorithmException {
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] hashBytes = digest.digest(input.getBytes());
 
     // Convert byte array to hexadecimal string
     StringBuilder hexString = new StringBuilder();
     for (byte b : hashBytes) {
         String hex = Integer.toHexString(0xff & b);
         if (hex.length() == 1)
             hexString.append('0');
         hexString.append(hex);
     }
     return hexString.toString();
 }
 }
  