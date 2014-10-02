package _week1;

import com.algorithms.*;
//Use standard random from our standard libraries to generate random numbers; 
//use standard statistics to compute the sample mean and standard deviation

public class PercolationStats {
	private int T;
	private double[] counT; // WTF
	private double mean;
	private double stdev;
	private double confidenceLo = 0.0;
	private double confidenceHi = 0.0;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException("N<=0 or T<=0");
		this.T = T;
		counT = new double[T];
		Percolation percolation;
		for (int i = 0; i < T; i++) { // run open() T times with two random
			int randI = 0, randJ = 0;
			int count = 0;
			percolation = new Percolation(N);
			while (!percolation.percolates()) { // positions
				randI = StdRandom.uniform(N) + 1;
				randJ = StdRandom.uniform(N) + 1;
				if (!percolation.isOpen(randI, randJ)) {
					percolation.open(randI, randJ);
					count++;
				}
				this.counT[i] = count;
			}
		}
		double t1;
		double sum = 0.0, sum2 = 0.0;
		this.confidenceLo = counT[0];
		for (int j = 0; j < T; j++) { // mean
			t1 = counT[j] / ((double) (N * N));
			if (t1 > confidenceHi)
				this.confidenceHi = t1;
			if (t1 < confidenceLo)
				this.confidenceLo = t1;
			sum = sum + t1;
			// System.out.println(counT[i]+" t1= "+t1);
		}
		this.mean = sum / T;
		for (int j = 0; j < T; j++) { // stdev
			t1 = counT[j] / ((double) (N * N));
			sum2 = sum2 + (t1 * t1 - 2 * mean * t1 + mean * mean);
		}
		this.stdev = Math.sqrt((sum2 / (T - 1)));
	} // perform T independent computational experiments on an N-by-N grid

	public double mean() {
		return mean;
	} // sample mean of percolation threshold

	public double stddev() {
		if (T == 1)
			return Double.NaN;
		// return StdStats.stddev(counT);
		// return rezult;
		return stdev;
	} // sample standard deviation of percolation threshold

	public double confidenceLo() {
		// double rezult = 0.0;
		return confidenceLo;

	} // returns lower bound of the 95% confidence interval

	public double confidenceHi() {
		// double rezult = 0.0;

		return confidenceHi;
	} // returns upper bound of the 95% confidence interval

	public static void main(String[] args) {

		PercolationStats ps = new PercolationStats(200, 100);
		System.out.println("mean = " + ps.mean());
		System.out.println("stdev = " + ps.stddev());
		System.out.println("Lo: " + ps.confidenceLo + ";  Hi: "
				+ ps.confidenceHi);
	} // test client, described below
}