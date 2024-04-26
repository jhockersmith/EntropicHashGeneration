"""
This Python source file implements a program for generating entropy and hash keys based on natural phenomena.

MIT License

Copyright (c) 2024 Johnathan Hockersmith

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Check out the README.md for detailed information about the project.
"""

import cv2
import numpy as np
import secrets
import time
import sys
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes

def calculate_image_entropy(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    hist = cv2.calcHist([gray], [0], None, [256], [0, 256])
    hist /= hist.sum()
    entropy = -np.sum(hist * np.log2(hist + 1e-10))
    return entropy

def generate_crypto_key():
    return secrets.token_bytes(32)

def symmetric_encrypt(message, key):
    cipher = Cipher(algorithms.AES(key), modes.CFB(b'\0' * 16), backend=default_backend())
    encryptor = cipher.encryptor()
    ciphertext = encryptor.update(message) + encryptor.finalize()
    return ciphertext

def save_to_file(entropy, crypto_key, encrypted_message):
    with open("data.txt", "a") as file:
        file.write(f"{crypto_key.hex()}\n")

def process_image(image):
    try:
        entropy = calculate_image_entropy(image)

        crypto_key = generate_crypto_key()

        encrypted_message = symmetric_encrypt(bytes(str(entropy), 'utf-8'), crypto_key)

        print(f"Image Entropy: {entropy}")
        print(f"Generated Crypto Key: {crypto_key.hex()}")
        print(f"Encrypted Entropy: {encrypted_message.hex()}")

        save_to_file(entropy, crypto_key, encrypted_message)
    except Exception as e:
        print(f"Error processing image: {e}")

def process_project_images():
    try:
        while True:
            image_path = input("Enter the path to the project image file (or type 'exit' to stop): ")
            if image_path.lower() == 'exit':
                break

            try:
                image = cv2.imread(image_path)
                if image is not None:
                    process_image(image)
                else:
                    print("Error loading the image. Please make sure the file exists and is a valid image.")
            except Exception as e:
                print(f"Error processing image: {e}")

    except KeyboardInterrupt:
        print("\nProgram terminated by the user.")
        sys.exit()

def main():
    try:
        source_choice = input("Choose source (cv2/project): ").lower()

        if source_choice == "cv2":
            cap = cv2.VideoCapture(0)
            while True:
                ret, frame = cap.read()
                if not ret:
                    print("Error capturing frame.")
                    return

                process_image(frame)

                time.sleep(1)

        elif source_choice == "project":
            process_project_images()
        else:
            print("Invalid source choice. Please choose 'cv2' or 'project'.")
    except KeyboardInterrupt:
        print("\nProgram terminated by user.")
        sys.exit()

if __name__ == "__main__":
    main()
