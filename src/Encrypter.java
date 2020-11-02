import squint.SImage;

public class Encrypter {
    public static SImage encrypt(SImage image, int[] arr) {
        int width = image.getWidth(), height = image.getHeight();
        int[][] redPixels = image.getPixelArray(SImage.RED);
        int[][] greenPixels = image.getPixelArray(SImage.GREEN);
        int[][] bluePixels = image.getPixelArray(SImage.BLUE);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 8; j++) { // access each bit in the int
                int sigBit = arr[i]>>j & 1;
                redPixels[(8*i+j)/width][(8*i+j)%height] =
                        redPixels[(8*i+j)/width][(8*i+j)%height]/2*2 + sigBit;
                greenPixels[(8*i+j)/width][(8*i+j)%height] =
                        greenPixels[(8*i+j)/width][(8*i+j)%height]/2*2 + sigBit;
                bluePixels[(8*i+j)/width][(8*i+j)%height] =
                        bluePixels[(8*i+j)/width][(8*i+j)%height]/2*2 + sigBit;
            }
        }
        SImage sImage = new SImage(redPixels, greenPixels, bluePixels);
        return sImage;
    }
    public static SImage encrypt(SImage image, SImage mask) {
        if (image.getHeight() != mask.getHeight() || image.getWidth() != mask.getWidth()) {
            throw new IllegalArgumentException("image and mask must have the same dimensions");
        }
        int[][] maskRedPixels = mask.getRedPixelArray();
        int[][] redPixels = image.getRedPixelArray();
        int[][] greenPixels = image.getGreenPixelArray();
        int[][] bluePixels = image.getBluePixelArray();

        for (int r = 0; r < redPixels.length; r++)
            for (int c =  0; c < redPixels[0].length; c++) {
                int lastBit = maskRedPixels[r][c] % 2;
                redPixels[r][c] = redPixels[r][c]/2*2+lastBit;
                greenPixels[r][c] = greenPixels[r][c]/2*2+lastBit;
                bluePixels[r][c] = bluePixels[r][c]/2*2+lastBit;
            }

        return new SImage(redPixels, greenPixels, bluePixels);
    }

    public static SImage stegMask(SImage image, int[] arr) {
        int width = image.getWidth(), height = image.getHeight();
        int[][] redPixels = new int[width][height];
        int[][] greenPixels = new int[width][height];
        int[][] bluePixels = new int[width][height];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 8; j++) { // access each bit in the int
                int sigBit = arr[i]>>j & 1;
                redPixels[(8*i+j)/width][(8*i+j)%height] = 128*sigBit; //clearly display
                greenPixels[(8*i+j)/width][(8*i+j)%height] = 128*sigBit;
                bluePixels[(8*i+j)/width][(8*i+j)%height] = 128*sigBit;
            }
        }
        SImage sImage = new SImage(redPixels, greenPixels, bluePixels);
        return sImage;
    }
}