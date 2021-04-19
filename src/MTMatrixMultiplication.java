/**
 * Multithreading Demo Class using Matrix Multiplication
 * Assignment: SP-A3: Demo Program
 * @author Krishna Saxena
 * @author Abhinav Nallapa
 * 
 * Results:
 * size runnable    brute
 * 5    1ms         0ms
 * 10   1ms         0ms
 * 50   3ms         4ms
 * 100  16ms        19ms
 * 500  707ms       776ms
 * 500  312ms       352ms
 * 500  313ms       348ms
 * 1000 3147ms      2991ms
 * 1000 3417ms      3102ms
 * 1000 5188ms      4354ms
 * 2000 195018ms    220127ms
 * 2000 208893ms    207294ms
 * 
 * As you can see, blind use of multithreading can improve performance, but not necessarily in all cases.
 * Multithreading typically improved performane for the 50, 100, and 500 size matrices. In the 1000 and 2000 
 * size matrices, the excessive creation of threads likely just created memory bottlenecks and slowed the 
 * computation. The multithreading code design could be improved to excel with any test case by optimizing 
 * the max number of threads used.
 */
public class MTMatrixMultiplication {
    private int[][] mat1, mat2, product;

    /**
     * Constructor generates matrices for testing
     */
    public MTMatrixMultiplication(int size) {
        //Fill mat1 with random values
        mat1 = new int[size][size];
        for (int r = 0; r < mat1.length; r++)
            for (int c = 0; c < mat1[0].length; c++)
                mat1[r][c] = (int)(Math.random()*size);

        //Fill mat2 with 1s
        mat2 = new int[size][size];
        for (int r = 0; r < mat2.length; r++)
            mat2[r][r] = 1;

        //Create product array
        product = new int[size][size];
    }

    /**
     * Multiplies matrices in a single thread
     * Each index (r,c) in product is calculated as dot product of row r in mat 1 and col c in mat 2
     * 
     * @return product of matrices
     */
    public int[][] calcProductLinear() {
        for (int c = 0; c < mat1[0].length; c++)
            for (int r = 0; r < mat1.length; r++) {
                int rowColDotProduct = 0;
                for (int i = 0; i < mat1.length; i++) {
                    rowColDotProduct += mat1[r][i]*mat2[i][c];
                }
                product[r][c] = rowColDotProduct;
            }
        return product;
    }

    /**
     * Multiplies matrices using multithreading
     * Creates a new thread to compute each index in product
     * Each index (r,c) in product is calculated as dot product of row r in mat 1 and col c in mat 2
     * 
     * @return product of matrices
     */
    public int[][] calcProductWithRunnable() {
        for (int c = 0; c < mat1[0].length; c++)
            for (int r = 0; r < mat1.length; r++) {
                MatrixMultiplierRunnable matMultRunnable = new MatrixMultiplierRunnable(r, c);
                matMultRunnable.run();
            }
        return product;
    }

    /**
     * Runnable class to compute value of single index in product in new thread
     */
    public class MatrixMultiplierRunnable implements Runnable {
        int row1, col2;

        public MatrixMultiplierRunnable(int row1, int col2) {
            this.row1 = row1;
            this.col2 = col2;
        }

        @Override
        /**
         * run() method of Runnable runs in seperate thread
         */
        public void run() {
            int rowColDotProduct = 0;
            for (int i = 0; i < mat1.length; i++) {
                rowColDotProduct += mat1[row1][i]*mat2[i][col2];
            }
            product[row1][col2] = rowColDotProduct;
        }
    }

    /**
     * Prints a matrix
     * @param mat matrix to print
     */
    public static void printMat(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                sb.append(mat[r][c]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        MTMatrixMultiplication mtMatrixMultiplication = new MTMatrixMultiplication(50);

        //Test multithreaded matrix multiplication
        Long startTime = System.currentTimeMillis();
        mtMatrixMultiplication.calcProductWithRunnable();
        Long endTime = System.currentTimeMillis();
        System.out.println("time taken runnable: " + (endTime-startTime) + "ms");

        //Test single threaded matrix multiplication
        startTime = System.currentTimeMillis();
        mtMatrixMultiplication.calcProductLinear();
        endTime = System.currentTimeMillis();
        System.out.println("time taken brute force: " + (endTime-startTime) + "ms");
    }
}