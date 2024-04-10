
  # **Advancing Genuine Random Hash Generation through Entropy Assessment of Observable Natural Processes.**

<p align="center">
  <img src="">
  <img src="">
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
   - Utilizes the entropic properties observed in the interaction between oil and water, comprable to the dynamic patterns seen in lava lamps. 
 - Cloud Formation
   - Uses observable differences in cloud formations to generate entropic values.
 - Flame Movement Dynamics
   - Determines entropic values by analyzing the dynamic and distinctive motion of flames, considering their potential interactions with external forces.

### Consideration of advantages and drawbacks

<details>

<summary>In Depth Comparative Analysis</summary>

### In Depth Comparative Analysis
| Pros  | Cons |
| ------------- | ------------- |
| Enhanced Security: Utilizing entropic values from images can enhance the security of hash generation by introducing a significant level of randomness, making it more resistant to brute-force and other cryptographic attacks. |Data Storage Requirements: Storing and managing large volumes of image data can be resource-intensive and may require significant storage capacity, leading to potential scalability issues and increased costs.  |
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
The CalculateEntropy script provided in the repository demonstrates the use of the framework. It captures an image using a Python script and then calculates the entropy of the captured image.

#### Code Explanation
  1. Data Collection (Python): The capture_image.py script is executed to capture an image.
  2. Entropy Calculation (Java):
     - The calculateEntropy method reads the captured image from the specified file path.
     - A histogram is generated to count the frequency of each pixel intensity value in the image.
     - The entropy is calculated using the histogram and the Shannon entropy formula.
 
#### Check out my other projects:
 - [GPT-Jarvis](https://github.com/jhockersmith/GPT-Jarvis) - A real-life JARVIS using the OpenAI Whisper, Audio, and GPT 3.5 Turbo APIs

## Disclaimer
***The information provided in this project is for educational and informational purposes only. While every effort has been made to ensure the accuracy and reliability of the content, the project authors make no representations or warranties of any kind,***

