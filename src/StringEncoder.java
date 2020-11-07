import squint.SImage;

public class StringEncoder {
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

    public static String convertToString(int[] asciiArr) {
        String result = "";
        for (int i : asciiArr){
            result += Character.toString(i);
        }
        return result;
    }
}
