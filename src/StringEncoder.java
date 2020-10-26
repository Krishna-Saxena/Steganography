import squint.SImage;

public class StringEncoder {
    public static int[] encode(SImage image, String str) throws IllegalArgumentException{
        int width = image.getWidth(); int height = image.getHeight();
        int[] arr = encodeString(str);
        if (arr.length*8 > width*height)
            throw new IllegalArgumentException("String too long for image");
        return arr;
    }
    private static int[] encodeString(String str) {
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        return arr;
    }
}
