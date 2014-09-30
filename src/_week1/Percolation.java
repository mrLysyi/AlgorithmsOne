package _week1;

import com.algorithms.*;

public class Percolation {
	// private QuickUnionUF massive;
	private WeightedQuickUnionUF massive;
	private int N;
	private final int perc;
	// private int count;
	private boolean[] id; // indicate current state of PERCOLATION node,
							// 0-blocked;
							// 1-open;2-Full.

	public Percolation(int N) {
		if (N <= 1)
			throw new IndexOutOfBoundsException("N must be > 1");
		this.N = N;
		perc = N * N; // connecting point of percolation ( out of range)
		// this.count = N * N;
		// QuickUnionUF masiveJ;
		// massive = new QuickUnionUF(N * N);
		massive = new WeightedQuickUnionUF(N * N + 1);
		id = new boolean[N * N];
		for (int i = 1; i < N * N; i++) {
			id[i] = false;
		}
	} // create N-by-N grid, with all sites blocked (zero 0 )

	public void open(int i, int j) {
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");
		int el = element(i, j); // element in QuickUNION line
		// if (massive.count()==N*N+1){
		// massive.union(el, N*N);
		// }
		//
		// if (id[el] == 0)
		// id[el] = 1; // set current item as OPEN (1)
		// connect elements, if they are open or FILL
		id[el] = true;
		if (i == 1) { // left			Y	X
			if (j == 1) { // UPPER left CORNER
				massive.union(el, perc);
				right(el);
				down(el);
			} else if (j == N) { // down left CORNER				
//				up(el);
				right(el);
			} else if (j > 1 && j < N) { // LEFT side
				massive.union(el, perc);
				down(el);
				right(el);
//				up(el);
			}

		} else if (i == N) { // RIGHT
			if (j == 1) { // up right CORNER	
				massive.union(el, perc);
				down(el);
				left(el);
			} else if (j == N) { // DOWN right CORNER
				left(el);
				up(el);
//			} else if (j > 1 && j < N){ // RIGHT middle
//				left(el);
//				down(el);
//				up(el);
			}

		} else if (i > 1 && i < N) {
			if (j == 1) { // up side
				
				left(el);
				right(el);
				down(el);
			}else if (j == N) { // down side
				left(el);
				right(el);
				up(el);
			} else if (j > 1 && j < N) { // in the middle
				left(el);
				right(el);
				up(el);
				down(el);
			}
		}
	} // open site (row i, column j) if it is not already

	public boolean isOpen(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N)
			throw new IndexOutOfBoundsException("i,j out of bounds");
		int el = element(i, j);
		// if (massive.count()==count && massive.connected(el, count))
		// // if ((id[el] == 1))
		// return true;
		// else if(!(massive.find(el)==el))
		// return true;
		// else if()
		return id[el];
		// else
		// return false;
	} // is site (row i, column j) open?

	public boolean isFull(int i, int j) {		
		if (isOpen(i, j)) {
			int el = element(i, j);
			return (massive.connected(el, perc));
		}

		// return true;
		// if (i == 1 && id[el] != 0) {
		// id[el] = 2;
		// return true;
		// }
		// for (int k = 0; k < N * N - 1; k = k + N - 1) {
		// if (id[el] == true) {
		// if (massive.connected(k, el)) {
		// // id[el] = 2;
		// return true;
		// }
		// }
		// }
		return false;
	} // is site (row i, column j) full?

	public boolean percolates() {
		int n = N - 1;
		for (int up = 0; up < n * n; up = up + N)
			for (int k = n * n; k > 0; k = k - n) {
				// System.out.println("k: " + k);
				if (massive.connected(up, k))
					return true;
			}
		return false;
	} // does the system percolate?

	private int element(int i, int j) {
		return ((i - 1)*N + (j - 1));
	}

	private void left(int el) {
		if ((id[el - 1] == true)) {// LEFT
			massive.union(el, el - 1);
		}
	}

	private void right(int el) {
		if ((id[el + 1] == true)) { // RIGHT
			massive.union(el, el + 1);
		}
	}

	private void up(int el) {
		if ((id[el - N-1] == true)) { // UPPER
			massive.union(el, el - N);
		}
	}

	private void down(int el) {
		if ((id[el + N-1] == true)) // DOWN
			massive.union(el, el + N);
	}

	public static void main(String[] args) {
		// int N = 3;
		// // int T = 3;
		// // int[] counT;
		// // Percolation per = new Percolation(10);
		// Percolation percolation;
		//
		// percolation = new Percolation(N);
		// if (percolation.massive.count() == 10)
		// System.out.println("yes");
		// percolation.open(1, 1);
		// percolation.open(1, 2);
		// percolation.open(1, 3);
		// System.out.println(percolation.percolates());
		// percolation.open(1, 1);
		// percolation.open(2, 1);
		// percolation.open(3, 1);
		// percolation.open(2, 2);
		// percolation.open(2, 3);
		// percolation.open(3, 3);
		// System.out.println(percolation.percolates());
		// System.out.println(percolation.isFull(3, 3));
		// counT = new int[N];
		// for (int i = 0; i < T; i++) { //run open() T times with two random
		// positions
		// int randI, randJ ;
		// int count=0;
		//
		// percolation = new Percolation(N);
		// while (!percolation.percolates()) {
		// randI = StdRandom.uniform(N ) + 1;
		// randJ = StdRandom.uniform(N ) + 1;
		// System.out.println(randI +" randJ= " + randJ);
		// percolation.open(randI, randJ);
		// if (percolation.percolates()) break;
		// count++;
		// }
		// System.out.println(counT[i]);
		// counT[i] = count;
		// }
	}// test client, optional
}
