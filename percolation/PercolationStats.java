/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double C = 1.96;
    private final double mean;
    private final double standardDev;
    private final int mTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] results = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                perc.open(row, col);
            }
            int openSites = perc.numberOfOpenSites();
            results[i] = (double) openSites / (n * n);
        }
        mean = StdStats.mean(results);
        standardDev = StdStats.stddev(results);
        mTrials = trials;
    }


    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return standardDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (C * standardDev / Math.sqrt(mTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (C * standardDev / Math.sqrt(mTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, t);
        StdOut.printf("mean                    = %f%n", percStats.mean());
        StdOut.printf("stddev                  = %f%n", percStats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]%n", percStats.confidenceLo(), percStats.confidenceHi());
        return;
    }
}
