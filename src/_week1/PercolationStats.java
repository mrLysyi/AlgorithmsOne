package _week1;

import com.algorithms.*;

//Use standard random from our standard libraries to generate random numbers; 
//use standard statistics to compute the sample mean and standard deviation

public class PercolationStats {
	private int T;
//	private int N;
	private int counT[];
	private double mean, confidenceLo=0.0, confidenceHi=0.0;
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException("N<=0 or T<=0");
//		this.N = N;
		this.T = T;
		counT = new int[T];
		
		 Percolation percolation ;
//		Percolation percolation = new Percolation(N);
		for (int i = 0; i < T; i++) { // run open() T times with two random										
			int randI=0, randJ=0;
			int count = 0;
			percolation = new Percolation(N);
			while (!percolation.percolates()) {	// positions
				randI = StdRandom.uniform(N) + 1;
				randJ = StdRandom.uniform(N) + 1;
				// System.out.println(randI +" randJ= " + randJ);
				if (!percolation.isOpen(randI, randJ)) {
					percolation.open(randI, randJ);
					count++;
				}
				
			}
			this.counT[i] = count;
			{
				double t1, sum=0.0;
				this.confidenceLo = counT[0]/((double) (N*N));
				for (int j=0; j<T; j++){
					t1 = counT[i]/((double) (N*N));
					if (t1 > confidenceHi) this.confidenceHi = t1;
					if (t1 < confidenceLo) this.confidenceLo = t1;
					sum = sum +t1;
//					System.out.println(counT[i]+" t1= "+t1);
				}
				this.mean = sum/T;
			}
			// System.out.println(counT[i]);
		}
	} // perform T independent computational experiments on an N-by-N grid

	public double mean() {
//		mean = StdStats.mean(counT)/((double)(N*N));
//		mean = StdStats.sum(counT)
//		this.mean = StdStats.mean(counT);
		
//		if(confidenceLo)
		return mean;
	} // sample mean of percolation threshold

	public double stddev() {
		if (T == 1)
			return Double.NaN;
		double rezult = StdStats.stddev(counT)/T;
		return rezult;
	} // sample standard deviation of percolation threshold

	public double confidenceLo() {
//		double rezult = 0.0;
		return confidenceLo;

	} // returns lower bound of the 95% confidence interval

	public double confidenceHi() {
//		double rezult = 0.0;
		return confidenceHi;
	} // returns upper bound of the 95% confidence interval

	public static void main(String[] args) {

		PercolationStats ps = new PercolationStats(20, 100);
		System.out.println("mean= " + ps.mean());
		System.out.println("stdev= " + ps.stddev());
		System.out.println("Lo: "+ ps.confidenceLo + ";  Hi: "+ ps.confidenceHi);
		
//		for (int i : ps.counT){
//			System.out.println(i);
//		}
	} // test client, described below
}