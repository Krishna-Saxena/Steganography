# Steganography
Graphical steganography application to hide secret text & images inside other images\
Handout: https://learncompsci.com/java/asgn/projects/steganography/stego-handout.pdf

### Setup
 - Unzip project folder (if using zip file) or clone from GitHub
 - Load project into your IDE of choice
 - Add squintV2.12.jar as a dependency following instructions from your IDE 
 - Run StegoTester_NallapaSaxena.java to open application
 
### Use
- Layout
  - Left Pane: Base Image
  - Middle Pane: Secret Text (Top) & Secret Image (Bottom)
  - Right Pane: Encrypted Image
- Encrypting:
  1. Use "Load Image" in left pane to load a base image
  1. Either:
     - Use "Load Image" in middle pane to load secret image
     - Type into text field in middle pane & select "Encode" to generate secret image
  1. Use "Encrypt" to generate encrypted image
- Decrypting:
  - Apply common sense to follow encrypting process in reverse order
