import squint.SImage;

public class Encrypter {
    public static SImage encrypt(SImage baseImage, SImage mask) {
        if (baseImage.getHeight() < mask.getHeight() || baseImage.getWidth() < mask.getWidth()) {
            throw new IllegalArgumentException("Secret image cannot be larger than base image");
        }
        int[][] maskRedPixels = mask.getRedPixelArray();
        int[][] maskBluePixels = mask.getBluePixelArray();
        int[][] maskGreenPixels = mask.getGreenPixelArray();

        int[][] redPixels = baseImage.getRedPixelArray();
        int[][] greenPixels = baseImage.getGreenPixelArray();
        int[][] bluePixels = baseImage.getBluePixelArray();

        for (int r = 0; r < redPixels.length; r++)
            for (int c =  0; c < redPixels[0].length; c++) {
                if (r < maskRedPixels.length && c < maskRedPixels[r].length) {
                    redPixels[r][c] = redPixels[r][c]/2*2 + maskRedPixels[r][c]/128;
                    greenPixels[r][c] = greenPixels[r][c]/2*2 + maskGreenPixels[r][c]/128;
                    bluePixels[r][c] = bluePixels[r][c]/2*2 + maskBluePixels[r][c]/128;
                } else {
                    redPixels[r][c] = redPixels[r][c]/2*2;
                    greenPixels[r][c] = greenPixels[r][c]/2*2;
                    bluePixels[r][c] = bluePixels[r][c]/2*2;
                }
            }

        return new SImage(redPixels, greenPixels, bluePixels);
    }

    public static SImage encode(SImage baseImage, int[] arr) {
        int width = baseImage.getWidth();
        int height = baseImage.getHeight();
        int[][] redPixels = new int[width][height];
        int[][] greenPixels = new int[width][height];
        int[][] bluePixels = new int[width][height];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 8; j++) { // access each bit in the int
                int sigBit = arr[i]>>j & 1;
                redPixels[(8*i+j)/width][(8*i+j)%height] = 255*sigBit; //clearly display
                greenPixels[(8*i+j)/width][(8*i+j)%height] = 255*sigBit;
                bluePixels[(8*i+j)/width][(8*i+j)%height] = 255*sigBit;
            }
        }
        SImage sImage = new SImage(redPixels, greenPixels, bluePixels);
        return sImage;
    }

    public static SImage decrypt(SImage encryptedImage) {
        int[][] redPixels = encryptedImage.getRedPixelArray();
        int[][] greenPixels = encryptedImage.getGreenPixelArray();
        int[][] bluePixels = encryptedImage.getBluePixelArray();

        for (int r = 0; r < redPixels.length; r++)
            for (int c =  0; c < redPixels[0].length; c++) {
                redPixels[r][c] = redPixels[r][c]%2*255;
                greenPixels[r][c] = greenPixels[r][c]%2*255;
                bluePixels[r][c] = bluePixels[r][c]%2*255;
            }    

        return new SImage(redPixels, greenPixels, bluePixels);
    }

    public static int[] decode(SImage mask) {
        int width = mask.getWidth();
        int height = mask.getHeight();
        int[] asciiArr = new int[(width*height)/8];

        int[][] redPixels = mask.getRedPixelArray();
        for (int i = 0; i < asciiArr.length; i++) {
            int asciiVal = 0;
            for (int j = 7; j >=0 ; j--) {
                asciiVal = asciiVal << 1;
                int bit = redPixels[(8*i+j)/width][(8*i+j)%height]/255;
                asciiVal += bit;
            }
            asciiArr[i] = asciiVal;
        }
        return asciiArr;
    }
}