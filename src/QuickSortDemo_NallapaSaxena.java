import java.awt.*;
import javax.swing.*;

/**
 * Graphical application to visualize quicksort algorithm
 */
public class QuickSortDemo_NallapaSaxena extends JComponent implements Runnable {
    
    //General Graphics Settings
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final int FPS = 240;
    private static final int DELAY = (1000/FPS);
    private static final int PAUSE = 3000; //Delay between sorts

    //Bar Graph Settings
    private static final int BAR_WIDTH = 5;
    private static final int ARRAY_LENGTH = screen.width/BAR_WIDTH-4;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = screen.height-100;

    private JFrame frame;
    private int[] arr;
    private long lastUpdateTime;
    private int greenIndex; //Pivot
    private int redIndex; //Left Pointer
    private int blueIndex; //Right Pointer

    public static void main (String[] args) {
        QuickSortDemo_NallapaSaxena quickSortDemo = new QuickSortDemo_NallapaSaxena();
        Thread t = new Thread(quickSortDemo);
        t.start();
    }

    /**
     * Sets up & displays GUI
     */
    public QuickSortDemo_NallapaSaxena() {
        this.setFocusable(true);
        frame = new JFrame("Quick Sort Demo");
        frame.setSize(screen.width, screen.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(BACKGROUND_COLOR); 
        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //-----------------------------------------------------
    // QUICKSORT CODE
    //-----------------------------------------------------

    /**
     * Main quickSort recursive method.
     * @param arr array to sort
     * @param low index to start sorting at 
     * @param high index to stop sorting at
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) { //Run until given index range has no values
            int partitionIndex = partition(arr, low, high); //Sorts array around pivot & stores pivot index
            greenIndex = partitionIndex;
            quickSort(arr, low, partitionIndex - 1); //Re-run quicksort for values < pivot
            quickSort(arr, partitionIndex + 1, high); //Re-run quicksort for values > pivot
        }
    }

    /**
     * quickSort helper method that sorts portion of an array around a chosen pivot
     * Uses last element pivot
     * @param arr array to sort
     * @param low index to start at 
     * @param high index to stop at
     * @return final index of pivot
     */
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; //Set pivot to last element
        int i = low; //Left Index for values less than pivot
        redIndex = i;

        for (int j = low; j < high; j++) { //Right index for values greater pivot
            blueIndex = j;
            if (arr[j] < pivot) {  //If right index finds value < pivot
                swap(arr,i,j); //Swap value with i & increment i
                i++;
                //Ensures that all values before index i are less than pivot,
                //and all values between after i and before j are greater than pivot
            }
        }
        swap(arr,i,high); //Swap the pivot to the correct place in array
        return i;
    }

    /**
     * Swaps two elements in array
     */
    public void swap(int[] arr, int a, int b) {
        int temp  = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

        //Call to repaint graphics after every swap
        try {
            //Delay to maintain stable FPS
            Thread.sleep(Math.max(2,DELAY-(System.currentTimeMillis() - lastUpdateTime)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            repaint();
            lastUpdateTime = System.currentTimeMillis();
        }
    }

    //-----------------------------------------------------
    // CODE FOR DEMO
    //-----------------------------------------------------

    /**
     * Updates graphics & displays bar graph for array
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        g2.setFont(new Font("Monospaced Bold",Font.PLAIN,18));
        g2.drawString("Quick Sort by Abhinav & Krishna",10,20);
        g2.drawString("Green = Pivot | Red = Left Pointer | Blue = Right Pointer",10,50);
        synchronized(arr) { 
            //Display all array elements
            for (int i = 0; i<arr.length; i++) {
                //Display colored bars for index, left pointer, right pointer
                if (i==greenIndex)
                    g2.setColor(Color.GREEN);
                else if (i==redIndex)
                    g2.setColor(Color.RED);
                else if (i==blueIndex)
                    g2.setColor(Color.BLUE);
                else   
                    g2.setColor(Color.WHITE);
                //Display bars for other array elements
                g2.fillRect(i*BAR_WIDTH+BAR_WIDTH*2,MAX_VALUE-arr[i],BAR_WIDTH,arr[i]); //x,y,width,height
                
            }
        }
    }

    /**
     * Main application thread. Endlessly runs sorthing algorithm
     */
    @Override
    public void run() {
        while(true) {
            arr = genRandomArray(); //generate random array
            repaint(); //update graphics
            lastUpdateTime = System.currentTimeMillis();
            quickSort(arr,0,arr.length-1); //sort array
            
            //Pause between sorts
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a randomly filled array of length ARRAY_LENGTH with MIN_VALUE<value<MAX_VALUE
     * @return randomly filled array
     */
    public static int[] genRandomArray() {
        int[] arr = new int[ARRAY_LENGTH]; 
        for (int i = 0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*MAX_VALUE + MIN_VALUE);
        }
        return arr;
    }
}
