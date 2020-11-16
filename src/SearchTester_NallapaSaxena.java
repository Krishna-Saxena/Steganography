import java.util.Scanner;

/**
 * @author Abhinav Nallapa, Krishna Saxena
 * Assignment: SortsA4: Binary Search (PP)
 */
public class SearchTester_NallapaSaxena {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchTester_NallapaSaxena st = new SearchTester_NallapaSaxena();
		// Ask the user for search string
		Scanner myScan = new Scanner(System.in);
		System.out.println("Enter name: ");
		String input = myScan.nextLine();
		int index = st.binSearchWreck( getList(), input );
		System.out.println(input + " found in list at index: " + index);

    }
    
	public static String[] getList() {
		String[] names = { "Adams", "Amarillas", "Baxter", "Eder", "Giradaux", 
                                             "Gonzalez", "Hansbrough", "Janda", "Kniffen", 
                                             "Lambert", "Mathurin", "McCrystal", "Molina", 
                                             "Preciado", "Reyerson", "Tam", "Ward", "Wolf", 
                                             "Wong", "Zabinski" };
		return names;
		
    }
    
    public int binSearch(String[] arr, String s){
		return -1;
    }

    public int binSearchWreck(String[] arr, String s){
		return recHelper(arr, s, 0, arr.length-1);
    }

    private int recHelper(String[] arr, String s, int lo, int hi) {
		if (hi < lo || lo < 0 || hi >= arr.length)
			return -1;
		int mid = lo+(hi-lo)/2;
		if (arr[mid].equals(s))
			return mid;
		else if (arr[mid].compareTo(s) > 0) // look on left
			return recHelper(arr, s, lo, mid-1);
		else
			return recHelper(arr, s, mid+1, hi);
	}
}