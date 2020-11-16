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
		// Ask the user for search string
		Scanner myScan = new Scanner(System.in);
		System.out.println("Enter name: ");
		String input = myScan.nextLine();
		int index = binSearch( getList(), input );
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

    }

    public int binSearchWreck(String[] arr, String s){

    }
}