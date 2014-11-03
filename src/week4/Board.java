package week4;

import com.algorithms.MinPQ;

public class Board {
	// private final int[][] initial;
	private final int[][] board;
	// private MinPQ<Board> pf = new MinPQ<Board>();
	// private final Node initial = new Node();
	private final int N;
	private final int[][] etalon;
	private int moves;
	private int priority;
	private int manhattan;
	private int hamming = -1;

	private void setMoves(int m) {
		this.moves = m;
	}

	private void setPriority(int p) {
		this.priority = p;
	}

	public Board(int[][] blocks) {
		board = blocks;
		// initial.privious = null;
		// initial.moves =0;
		this.N = blocks.length;

		int count = 1;
		etalon = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				this.etalon[i][j] = count;
				count++;

			}

	} // construct a board from an N-by-N array of blocks
		// (where blocks[i][j] = block in row i, column j)

	public int dimension() {
		return N;
	} // board dimension N

	public int hamming() {
		// int h = Node.
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] != 0) { // except 0
					System.out.println("etalon: " + etalon[i][j] + " == "
							+ board[i][j] + " board[i][j]");
					if (etalon[i][j] != board[i][j]) {
						count++;
					}

				}
		// this.hamming = count;
		return count;
		// number of blocks out of place
	}

	public int manhattan() {
		int curPos, etalonPos;
		int rezult = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++){
				curPos = i+j;
				etalonPos = board[i][j];
				if (curPos!=etalonPos){ //calculate
					rez = rez +;
				}
				
			}
		this.manhattan = rezult;
		return rezult;
		// sum of Manhattan distances between blocks and goal
	}

	public boolean isGoal() {
		if (hamming == -1)
			return hamming() == 0;
		else
			return hamming==0;
					
		// is this board the goal board?
	}

	public Board twin() {
		int[][] twin = board;
		return new Board(twin);
		// a boadr that is obtained by exchanging two adjacent blocks in the
		// same row
	}

	public boolean equals(Object y) {
		return board.equals(y);// does this board equal y?
	}

	public Iterable<Board> neighbors() {
		return null;
		// all neighboring boards
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", board[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
		// string representation of this board (in the output format specified
		// below)
	}

	public static void main(String[] args) {
		int N = 2;
		int[][] test = new int[N][N];
		int count = 1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				test[i][j] = i + j;
				count++;
			}
		count = 0;
		Board br = new Board(test);
		System.out.println(br.dimension());
		for (int[] ii : test)
			for (int jj : ii)
				System.out.println("element: " + jj);
		System.out.println("toString------------------");
		System.out.println(br.toString());
		System.out.println("Hamming (out of goal blocks): " + br.hamming());
		// unit tests (not graded)
	}
}
