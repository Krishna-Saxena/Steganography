import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

public class QuickSortDemoTester_NallapaSaxena {    
    public static void main (String[] args) {
        QuickSortDemo quickSortDemo = new QuickSortDemo();
        Thread t = new Thread(quickSortDemo);
        t.start();
    }
}

class QuickSortDemo extends JComponent implements Runnable {
    // private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension screen = new Dimension(1800,1000);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final int FPS = 240;
    private static final int DELAY = (1000/FPS);
    private static final int PAUSE = 3000;

    private int[] arr;
    private static final int BAR_WIDTH = 5;
    private static final int ARRAY_LENGTH = screen.width/BAR_WIDTH-(BAR_WIDTH*4);
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = screen.height-50;

    private JFrame frame;

    int green; //Green = Pivot
    int red; //Red = Left Pointer
    int blue; //Blue = Right Pointer

    public QuickSortDemo() {
        this.setFocusable(true);
        frame = new JFrame("Quick Sort Demo");
        frame.setSize(screen.width, screen.height);
        // frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(BACKGROUND_COLOR); 
        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        g2.setFont(new Font("Monospaced Bold",Font.PLAIN,18));
        g2.drawString("Quick Sort by Abhinav & Krishna",10,20);
        g2.drawString("Green = Pivot | Red = Left Pointer | Blue = Right Pointer",10,50);
        synchronized(arr) { 
            for (int i = 0; i<arr.length; i++) {
                if (i==green)
                    g2.setColor(Color.GREEN);
                else if (i==red)
                    g2.setColor(Color.RED);
                else if (i==blue)
                    g2.setColor(Color.BLUE);
                else   
                    g2.setColor(Color.WHITE);
                g2.fillRect(i*BAR_WIDTH+BAR_WIDTH*2,screen.height-arr[i]-50,BAR_WIDTH,arr[i]); //x,y,width,height
                
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            arr = genRandomArray();
            repaint();
            quickSort(arr,0,arr.length-1); 
            
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition (arr, low, high);
            green = partitionIndex;
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        red = i;

        long startTime, timeDiff, sleepTime;

        for (int j = low; j < high; j++) {
            blue = j;
   
            startTime = System.currentTimeMillis();

            if (arr[j] < pivot) {
                swap(arr,i,j);
                i++;
            }

            timeDiff = System.currentTimeMillis() - startTime;
            sleepTime = Math.max(2,DELAY-timeDiff);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        swap(arr,i,high);
        return i;
    }

    public void swap(int[] arr, int a, int b) {
        int temp  = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        repaint();
    }

    public static int[] genRandomArray() {
        int[] arr = new int[ARRAY_LENGTH]; 
        for (int i = 0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*MAX_VALUE + MIN_VALUE);
        }
        return arr;
    }
}
