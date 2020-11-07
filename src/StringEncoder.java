//Import(s)
import squint.SImage;

/**
 * Utility class to convert between String & ASCII array values
 */
public class StringEncoder {
    /**
     * Converts String into an array of ASCII values
     * @param baseImage image that determines maximum size of string
     * @param str String to convert to array of ASCII values
     * @return array of ASCII values representing given string
     * @throws IllegalArgumentException if String too long for image
     */
    public static int[] convertToASCII(SImage baseImage, String str) throws IllegalArgumentException{
        int width = baseImage.getWidth(); 
        int height = baseImage.getHeight();
        if (str.length()*8 > width*height)
            throw new IllegalArgumentException("String too long for image");
        
        int[] asciiArr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            asciiArr[i] = str.charAt(i);
        }
        return asciiArr;
    }

    /**
     * Converts array of ASCII values into a String
     * @param asciiArr array of ASCII values to convert to String
     * @return string represented by given array of ASCII values
     */
    public static String convertToString(int[] asciiArr) {
        String result = "";
        for (int i : asciiArr){
            result += Character.toString(i);
        }
        return result;
    }
}
