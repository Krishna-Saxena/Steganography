import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args) {
        String[] arr = {"nr", "nn", "px", "og", "cx", "db", "rj"};
        quicksort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void quicksort(Comparable[] arr) {
        quicksortHelper(arr, 0, arr.length-1);
    }

    private static void quicksortHelper(Comparable[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int pivotIdx = (int)(Math.random()*(hi-lo+1))+lo;
        Comparable pivotVal = arr[pivotIdx];
        swap(arr, lo, pivotIdx);
        int lt = lo;
        int gt = lt+1;
        while (gt <= hi) {
            if (arr[gt].compareTo(pivotVal) < 0)
                swap(arr, ++lt, gt);
            gt++;
        }
        swap(arr, lo, lt);
        quicksortHelper(arr, lo, lt-1);
        quicksortHelper(arr, lt+1, hi);
    }
    private static void swap(Comparable[] arr, int a, int b) {
        Comparable comparable = arr[a];
        arr[a] = arr[b];
        arr[b] = comparable;
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] arr = new String[arrLen];
        for (int i = 0; i < arrLen; i++)
            arr[i] = generateRandomString(strLen);
        return arr;
    }

    private static String generateRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) ((int)(Math.random()*26)+97));
        }
        return sb.toString();
    }
}
