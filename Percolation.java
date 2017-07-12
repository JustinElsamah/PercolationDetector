import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] mPercolationTableBlockOpen;
    private int mNumOfOpenSites;
    private final WeightedQuickUnionUF mUnionFindArray;
    private final int n;

    public Percolation(int n) {
        mNumOfOpenSites = 0;
        this.n = n;

        if (this.n < 1) {
            throw new IllegalArgumentException();
        }

        mPercolationTableBlockOpen = new boolean[this.n][this.n];

        for (int i = 0; i < this.n; i++) {
            for (int y = 0; y < this.n; y++) {
                mPercolationTableBlockOpen[i][y] = false;
            }

        }

        mUnionFindArray = new WeightedQuickUnionUF(this.n * this.n + 2);

        for (int i = 1; i <= this.n; i++) {
            mUnionFindArray.union(0, i);
        }

        for (int i = 0; i < this.n; i++) {
            mUnionFindArray.union(this.n * this.n + 1, this.n * this.n - i);
        }
    }

    public void open(int row, int col) {
        if (isOutOfBounds(row, col)) {
            throw new IllegalArgumentException();
        }

        if (!(isOpen(row, col))) {
            int[] arr1 = new int[]{0, 0, -1, 1};
            int[] arr2 = new int[]{-1, 1, 0, 0};
            mPercolationTableBlockOpen[row - 1][col - 1] = true;
            mNumOfOpenSites++;
            for (int i = 0; i < arr1.length; i++) {
                if (!(isOutOfBounds(row + arr1[i], col + arr2[i]))) {
                    if (this.isOpen(row + arr1[i], col + arr2[i])) {
                        mUnionFindArray.union(twoDToOneD(row, col), twoDToOneD(row + arr1[i], col + arr2[i]));
                    }
                }
            }
        }
    }



    public boolean isOpen(int row, int col) {
        if (isOutOfBounds(row, col)) {
            throw new IllegalArgumentException();
        }

        if (mPercolationTableBlockOpen[row-1][col-1]) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        if (isOutOfBounds(row, col)) {
            throw new IllegalArgumentException();
        }

        if (this.isOpen(row, col) && mUnionFindArray.connected(0, twoDToOneD(row, col))) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return mNumOfOpenSites;
    }

    public boolean percolates() {
        if (mUnionFindArray.connected(0, this.n * this.n + 1)) {
            return true;
        }
        return false;
    }

    private int twoDToOneD(int row, int col) {
        return (row - 1) * this.n + col;
    }

    private boolean isOutOfBounds(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            return true;
        }
        return false;
    }

}
