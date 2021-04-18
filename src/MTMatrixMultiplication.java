public class MTMatrixMultiplication {
    private int[][] mat1, mat2, product;

    public MTMatrixMultiplication(int size) {
        mat1 = new int[size][size];
        for (int r = 0; r < mat1.length; r++)
            for (int c = 0; c < mat1[0].length; c++)
                mat1[r][c] = (int)(Math.random()*size);
        mat2 = new int[size][size];
        for (int r = 0; r < mat2.length; r++)
            mat2[r][r] = 1;
        product = new int[size][size];
    }

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

    public int[][] calcProductWithRunnable() {
        for (int c = 0; c < mat1[0].length; c++)
            for (int r = 0; r < mat1.length; r++) {
                MatrixMultiplierRunnable matMultRunnable = new MatrixMultiplierRunnable(r, c);
                matMultRunnable.run();
            }
        return product;
    }

    public class MatrixMultiplierRunnable implements Runnable {
        int row1, col2;
        public MatrixMultiplierRunnable(int row1, int col2) {
            this.row1 = row1;
            this.col2 = col2;
        }

        @Override
        public void run() {
            int rowColDotProduct = 0;
            for (int i = 0; i < mat1.length; i++) {
                rowColDotProduct += mat1[row1][i]*mat2[i][col2];
            }
            product[row1][col2] = rowColDotProduct;
        }
    }

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
        MTMatrixMultiplication mtMatrixMultiplication = new MTMatrixMultiplication(750);
        Long startTime = System.currentTimeMillis();
        mtMatrixMultiplication.calcProductWithRunnable();
        Long endTime = System.currentTimeMillis();
        System.out.println("time taken runnable: " + (endTime-startTime) + "ms");
        startTime = System.currentTimeMillis();
        mtMatrixMultiplication.calcProductLinear();
        endTime = System.currentTimeMillis();
        System.out.println("time taken brute force: " + (endTime-startTime) + "ms");
    }
    // results
    // size runnable    brute
    // 5    1ms         0ms
    // 10   1ms         0ms
    // 50   3ms         4ms
    // 100  16ms        19ms
    // 500  707ms       776ms
    // 1000 5188ms      4354ms
}