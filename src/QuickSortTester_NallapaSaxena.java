import java.util.Arrays;

public class QuickSortTester_NallapaSaxena {

    private static final int ARRAY_LENGTH = 10000000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 1000000;
    private static final int PRINT_LENGTH = 30;
    
    public static void main (String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Trial " + i + "\n---------");
            int[] arr = genRandomArray();
            System.out.println(getStringEnds(arr));
            quickSort(arr,0,arr.length-1);
            System.out.println(getStringEnds(arr) + "\n");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition (arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr,i,j);
                i++;
            }
        }

        swap(arr,i,high);
        return i;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp  = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    //******************************************************** 
    //HELPER CLASSES JUST FOR TESTING
    //********************************************************
    public static int[] genRandomArray() {
        //create array with random length between MIN_SIZE & MAX_SIZE
        int[] arr = new int[ARRAY_LENGTH]; 
        
        //fill with random elements
        for (int i = 0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*MAX_VALUE + MIN_VALUE);
        }
        return arr;
    }

    public static String getStringEnds(int[] arr) {
        if (arr.length<=PRINT_LENGTH) {
            return Arrays.toString(arr);
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (; i<PRINT_LENGTH/2-1; i++){
            sb.append(arr[i] + ", ");
        }
        sb.append(arr[i] + ", ... , ");

        i = arr.length-PRINT_LENGTH/2;
        for (; i<arr.length-1; i++){
            sb.append(arr[i] + ", ");
        }
        sb.append(arr[i]);

        return sb.append("]").toString();
    }
}
