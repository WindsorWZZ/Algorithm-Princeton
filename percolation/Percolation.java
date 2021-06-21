/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int side;
    private final WeightedQuickUnionUF topUf;
    private final WeightedQuickUnionUF botUf;
    private boolean[][] matrix;
    private int openSites;
    private boolean isPercolated = false;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        side = n;

        topUf = new WeightedQuickUnionUF(n * n + 1); // 0 represents top
        botUf = new WeightedQuickUnionUF(n * n + 1); // 0 represents bottom

        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = false;
            }
        }

        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        int index = side * (row - 1) + col;
        int i = row - 1;
        int j = col - 1;
        if (matrix[i][j]) {
            return;
        }
        matrix[i][j] = true;
        openSites++;

        // union with neighbor sites
        if (i - 1 >= 0 && matrix[i - 1][j]) {
            topUf.union(index, index - side);
            botUf.union(index, index - side);
        }
        if (i + 1 < side && matrix[i + 1][j]) {
            topUf.union(index, index + side);
            botUf.union(index, index + side);
        }
        if (j - 1 >= 0 && matrix[i][j - 1]) {
            topUf.union(index, index - 1);
            botUf.union(index, index - 1);
        }
        if (j + 1 < side && matrix[i][j + 1]) {
            topUf.union(index, index + 1);
            botUf.union(index, index + 1);
        }
        // union with top/bottom node
        if (row == 1) {
            topUf.union(index, 0);
        }
        if (row == side) {
            botUf.union(index, 0);
        }

        // record percolation
        if (topUf.find(index) == topUf.find(0) && botUf.find(index) == botUf.find(0)) {
            isPercolated = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return matrix[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        int index = side * (row - 1) + col;
        return topUf.find(index) == topUf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return isPercolated;
    }

    // validate row & col
    private void validate(int row, int col) {
        if (row <= 0 || row > side || col <= 0 || col > side) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        // p.open(1, 1);
        // p.open(2, 1);
        // p.open(3, 1);
        // p.open(4, 1);
        // p.open(5, 1);
        StdOut.println(p.percolates());
    }
}
