package _week1;

import com.algorithms.*;

public class Percolation {
	private WeightedQuickUnionUF massive;
	private WeightedQuickUnionUF massiveTwo;
	private int N;
	private boolean perlocates = false;
	private final int perc;
//	private final int percTwo;
	private boolean[] id; // indicate current state of PERCOLATION node (Open or
							// not)
//	private boolean[] idB; // connected to bottom or not?;

	public Percolation(int N) {
		if (N < 1)
			throw new IllegalArgumentException("N must be > 1");
		this.N = N;
		perc = N * N; // connecting point of percolation ( out of range)		
		massive = new WeightedQuickUnionUF(N * N + 2);
		massiveTwo = new WeightedQuickUnionUF(N * N + 2);
		id = new boolean[N * N + 1]; // open?
//		idB = new boolean[N * N]; // connected to bottom
		for (int i = 0; i < N * N; i++) {
			id[i] = false;
//			idB[i] = false;
		}
		// if (N == 1) {
		// perlocates = true;
		// return;
		// }

	} // create N-by-N grid, with all sites blocked (zero 0 )

	public void open(int i, int j) {
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");
		int el = element(i, j); // element in QuickUNION line
//		 System.out.println("el find BEFORE " + massive.find(el));
		if (!id[el])
			id[el] = true;
		if (N == 1) {
			massive.union(el, perc);
			perlocates = true;
			return;
		}
		if (i == 1) { // left Y X
			if (j == 1) { // UPPER left CORNER
				massive.union(el, perc);
				massiveTwo.union(el, perc);
				right(el);
				down(el);
			} else if (j == N) { // down left CORNER
				massive.union(el, perc);
				massiveTwo.union(el, perc);
				down(el);
				left(el);
			} else if (j > 1 && j < N) { // LEFT side
				massive.union(el, perc);
				massiveTwo.union(el, perc);
				down(el);
				right(el);
				left(el);
			}
		} else if (i == N) { // DOWN
			if (j == 1) { // down left CORNER
				massiveTwo.union(el, perc+1); // connected to bottom;
				up(el);
				right(el);
			} else if (j == N) { // DOWN right CORNER
				massiveTwo.union(el, perc+1); // connected to bottom
				left(el);
				up(el);
			} else { // DOWN middle
				massiveTwo.union(el, perc+1); // connected to bottom(el);
				right(el);
				left(el);
				up(el);
			}
		} else if (i > 1 && i < N) {
			if (j == 1) { // left side
				up(el);
				right(el);
				down(el);
			} else if (j == N) { // right side
				left(el);
				down(el);
				up(el);
			} else if (j > 1 && j < N) { // in the middle
				left(el);
				right(el);
				up(el);
				down(el);
			}
		}
//		 System.out.println("el find AFTER " + massive.find(el));
	} // open site (row i, column j) if it is not already

	public boolean isOpen(int i, int j) {
		return id[element(i, j)];
	} // is site (row i, column j) open?

	public boolean isFull(int i, int j) {
		int el = element(i, j);
		if (isOpen(i, j)) 
		return (massive.connected(el, perc));
		else return false;
	} // is site (row i, column j) full?

	public boolean percolates() {
		if (massiveTwo.connected(perc, perc+1))
			perlocates = true;
		return perlocates;
	} // does the system percolate?

	private int element(int i, int j) { // ELEMENT
		if (i <= 0 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");
		return ((i - 1) * N + (j - 1));
	}

	private void left(int el) {
		if (id[el - 1] == true){ // LEFT
			massive.union(el, el - 1);
			massiveTwo.union(el, el - 1);
		}
//		if (idB[el - 1]) {
//			idB[el] = true;
//		
//			if (massive.connected(el, perc) || massive.connected(el - 1, perc))
//				perlocates = true;
//		}
	}

	private void right(int el) {
		if (id[el + 1] == true){ // RIGHT
			massive.union(el, el + 1);
			massiveTwo.union(el, el + 1);
		}
//		if (idB[el + 1]) {
//			idB[el] = true;
//			
//			if (massive.connected(el, perc) || massive.connected(el + 1, perc))
//				perlocates = true;
//		}
	}

	private void up(int el) {
		if (id[el - N] == true){ // UPPER
			massive.union(el, el - N);
			massiveTwo.union(el, el - N);
		}
//		if (idB[el - N]) {
//			idB[el] = true;
//		
//			if (massive.connected(el, perc) || massive.connected(el - N, perc))
//				perlocates = true;
//		}
	}

	private void down(int el) {
		if (id[el + N] == true){ // DOWN
			massive.union(el, el + N);
			massiveTwo.union(el, el + N);
		}
//		if (idB[el + N]) {
//			idB[el] = true;
//			
//			if (massive.connected(el, perc) || massive.connected(el + N, perc))
//				perlocates = true;
//		}
	}

	public static void main(String[] args) {
		// for (int i = 10 * 10 - 10; i < 100; i++)
		// System.out.println(i);
		int N = 3;
		int i = 3;
		int j = 1;
		int el = ((i - 1) * N + (j - 1));
		Percolation perc = new Percolation(N);
		perc.open(1, 1);
		perc.open(2, 1);
		int el2 = ((2 - 1) * N + (1 - 1));
		System.out.println(el2 + " " + perc.massive.find(el2));
		perc.open(3, 1);
		System.out.println(el + " " + perc.massive.find(el));
		System.out.println(perc.isFull(1, 2) + " " + perc.isOpen(1, 2));
	}// test client, optional
}
