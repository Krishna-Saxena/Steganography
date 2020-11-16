import java.util.Scanner;

/**
 * @author Abhinav Nallapa, Krishna Saxena
 * Assignment: SortsA4: Binary Search (PP)
 * 
 * Tests binary search & recursive binary search methods
 */
public class SearchTester_NallapaSaxena {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Ask the user for search string
		Scanner myScan = new Scanner(System.in);
		System.out.println("Enter name: ");
        String input = myScan.nextLine();
        
        // Ask the user for method choice
        System.out.println("Enter 1 for binSearch() or 2 for binSearchWreck()");
        int method = myScan.nextInt();

        // Run binary search
        if (method == 1) {
            int index = binSearch( getList(), input );
            System.out.println(input + " found in list at index: " + index);
        } else if (method == 2) {
            int index = binSearchWreck( getList(), input );
            System.out.println(input + " found in list at index: " + index);
        } else {
            System.out.println("Not an option . . .");
        }
    }
    
	public static String[] getList() {
		String[] names = { "Adams", "Amarillas", "Baxter", "Eder", "Giradaux", 
                                             "Gonzalez", "Hansbrough", "Janda", "Kniffen", 
                                             "Lambert", "Mathurin", "McCrystal", "Molina", 
                                             "Preciado", "Reyerson", "Tam", "Ward", "Wolf", 
                                             "Wong", "Zabinski" };
		return names;
    }
    
    public static int binSearch(String[] arr, String s){
        int low = 0;
        int high = arr.length-1;

        while (low <= high) {
            int mid = (low + high)/2;
            if (s.compareTo(arr[mid]) < 0) {
                high = mid - 1;
            } else if (s.compareTo(arr[mid]) > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binSearchWreck(String[] arr, String s){
		return recHelper(arr, s, 0, arr.length-1);
    }

    private static int recHelper(String[] arr, String s, int lo, int hi) {
		if (hi < lo || lo < 0 || hi >= arr.length)
			return -1;
		int mid = (hi+lo)/2;
		if (arr[mid].equals(s))
			return mid;
		else if (arr[mid].compareTo(s) > 0) // look on left
			return recHelper(arr, s, lo, mid-1);
		else
			return recHelper(arr, s, mid+1, hi);
	}
}