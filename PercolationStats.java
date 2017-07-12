import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percolationThresholds;
    private final int trials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        percolationThresholds = new double[trials];
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        for (int numOfTrials = 0; numOfTrials < trials; numOfTrials++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int tempRow = StdRandom.uniform(n) + 1;
                int tempCol = StdRandom.uniform(n) + 1;
                if (!(p.isOpen(tempRow, tempCol))) {
                    p.open(tempRow, tempCol);
                }
            }
        percolationThresholds[numOfTrials] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationThresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationThresholds);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, trials);
        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        System.out.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");

    }

}
