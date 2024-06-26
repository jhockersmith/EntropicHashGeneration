<p align="center">
  <img src="https://i.imgur.com/HefvQqQ.png">
</a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Author-Johnathan Hockersmith-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Maintained-Yes-brightgreen?style=for-the-badge">
</a>
</p>

<p align="center"><b>Develop robust cryptographic hash functions by analyzing the randomness in natural phenomena.</b></p>

##

### About
This repository explores the utilization of randomness derived from naturally occurring phenomena to generate entropic values, which in turn are employed for producing truly random hashes. By harnessing the inherent unpredictability found in observable natural processes, the project aims to advance the development of cryptographic hash functions with enhanced security and reliability.

### Dynamic Formations in Natural Phenomena
 - Oil-Water Interaction
   - Utilizes the entropic properties observed in the interaction between oil and water, comparable to the dynamic patterns seen in lava lamps. 
 - Cloud Formation
   - Uses observable differences in cloud formations to generate entropic values.
 - Flame Movement Dynamics
   - Determines entropic values by analyzing the dynamic and distinctive motion of flames, considering their potential interactions with external forces.

### Consideration of advantages and drawbacks
Compression of images can impact the randomness of the images. It reduces storage and computational costs but can potentially decrease the randomness of the hashes generated. The choice of compression algorithm should be carefully considered to minimize the impact on randomness while maintaining acceptable efficiency. Storing images securely is essential to prevent unauthorized access or manipulation, which could compromise the integrity of the hash generation process. Encryption, access control, and regular backups are essential security measures. However, these measures can also introduce performance overhead and potential vulnerabilities.
<details>

<summary>In Depth Comparative Analysis</summary>

### In-Depth Comparative Analysis
| Pros  | Cons |
| ------------- | ------------- |
| Enhanced Security: Utilizing entropic values from images can enhance the security of hash generation by introducing a significant level of randomness, making it more resistant to brute force and other cryptographic attacks. |Data Storage Requirements: Storing and managing large volumes of image data can be resource-intensive and may require significant storage capacity, leading to potential scalability issues and increased costs.  |
| Increased Uniqueness: Images provide a vast source of randomness, allowing for the generation of highly unique hash values, which reduces the likelihood of collisions and enhances data integrity.  | Privacy Concerns: The capture and storage of images for entropy generation raise privacy concerns, as sensitive or personal information may inadvertently be included in the images, requiring robust privacy measures and compliance with data protection regulations.  |
| Diverse Sources: Images can be captured from various natural phenomena, ensuring a diverse range of entropy sources and making the hash generation process more robust and reliable.  | Sensitivity to Environmental Factors: Image-based entropy generation is sensitive to environmental factors such as lighting conditions, camera settings, and image quality, which may introduce variability and unpredictability in the generated hash values.  |
| Transparency and Reproducibility: Image-based entropy generation offers a transparent and reproducible method, allowing for easy verification and validation of the generated hash values.  | Processing Overhead: Generating entropy from images requires computational resources for image capture, processing, and analysis, leading to increased processing overhead and potential performance impacts, especially in real-time applications.  |
| Flexibility and Adaptability: Image-based entropy generation can be easily integrated into existing systems and workflows, providing flexibility and adaptability for a wide range of applications and use cases.  | Security Risks: The security of the hash generation process is contingent on the integrity and confidentiality of the image data, making it susceptible to security risks such as unauthorized access, tampering, or interception, necessitating robust security measures and protocols for image capture, transmission, and storage.  |

</details>

### Entropy Assessment Framework
The entropy assessment framework provides a methodology and tools for quantifying the randomness of observable natural processes. It utilizes statistical techniques to analyze the distribution of values within a given data set and estimate its entropy, a measure of unpredictability.

#### Methodology
**The framework follows a three-step process:**
  1. Data Collection: The natural process under observation is captured and converted into a digital format, such as an image or a time series.
  2. Histogram Generation: A histogram is constructed to represent the frequency distribution of values within the data set.
  3. Entropy Calculation: The entropy of the data set is calculated using the histogram and the formula for Shannon entropy:
 
     $$H = -\sum_{x \in X} p(x) \log_2 p(x)$$
     where p(x) is the probability of occurrence of each value x in the data set.
#### Script Usage
The App.java program provided in the repository demonstrates the use of the framework. It takes a user-defined image and then calculates the entropy of the specified image.

#### Code Explanation
  1. Data Collection: The main method prompts the user to input the path to the image file.
  2. Entropy Calculation:
     - The calculateEntropy() method processes the image to calculate its entropy.
     - It constructs a histogram to count the frequency of each pixel intensity value in the image.
     - The entropy is then computed using the histogram and the Shannon entropy formula.
          ```
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
          ```
     
#### Output
The program outputs the calculated entropy value and the corresponding hash key.

#### Limitations
The accuracy of the entropy assessment depends on the quality and representativeness of the data collected. The framework assumes that the data set is sufficiently large and captures the underlying dynamics of the natural process.

#### Applications
The entropy assessment framework can be used in various applications, including:
  - Evaluating the randomness of naturally occurring phenomena
  - Developing random number generators based on natural processes
  - Assessing the security of cryptographic algorithms that rely on random inputs


#### Examples
Included in this repo are example images that are similar in subject, but different in formation (fire and clouds).

Using the App.java program, the hashes from fire-1.jpg and fire-2.png are the following respectively:

fire-1: 
```
484b3ef7adad422c2cfa7b792e8d0ce59301f08c6eb8e267c27416d231aefa32
```
fire-2: 
```
4e9aab7ea24fd404e98d4900f742a4ce51c7062f4462a9023853253b2138da43
```
Though the hashes are *similar*, they are not the same, proving that despite being the same natural occurrence, even the slightest change in feature can cause a dramatic shift in the calculated entropy.

### Cracking Hashes
Due to hashes being a fixed length, containing only letters and numbers, they can be guessed. This is why it is important to generate random hashes that do not have recognizable patterns, as computers can guess millions of hashes per second.

A desktop computer containing readily available hardware had an average hash rate of 1.5 million hashes per second. Given the hash from fire-2.png, the HashMatcher program would take roughly 82,313 hours or around nine calendar years to successfully guess the hash. 
With more powerful hardware, this rate increases significantly, but by capturing images with higher entropic values, the longer it would take to guess these hashes. 

In the HashMatcher.java, the program guesses between 0.00000000000000000001 and 20.0. This is roughly nine quintillion possible decimals for the program to guess. With higher entropic values, this number can significantly increase, allowing for much more secure hashes.
#### Check out my other projects:
 - [GPT-Jarvis](https://github.com/jhockersmith/GPT-Jarvis) - A real-life JARVIS using the OpenAI Whisper, Audio, and GPT 3.5 Turbo APIs

## Disclaimer
***The information provided in this project is for educational and informational purposes only. While every effort has been made to ensure the accuracy and reliability of the content, the project authors make no representations or warranties of any kind,***

