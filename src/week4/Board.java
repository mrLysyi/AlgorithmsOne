package week4;

//import java.util.Arrays;

//import com.algorithms.MinPQ;
import com.algorithms.Stack;

public class Board {
	// private final int[][] initial;
	private final int[][] board;
	// private MinPQ<Board> pf = new MinPQ<Board>();
	// private final Node initial = new Node();
	private final int N;
	private final int[][] etalon;
	// private int moves;
	// private int priority; //manhattam+moves
	private int manhattan = this.manhattan();
	private int hamming = this.hamming();
	private int twinCount = 0;
	private int io; // position of zero
	private int jo;

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
					// System.out.println("etalon: " + etalon[i][j] + " == "
					// + board[i][j] + " board[i][j]");
					if (etalon[i][j] != board[i][j]) {
						count++;
					}

				}
		// this.hamming = count;
		return count;
		// number of blocks out of place
	}

	public int manhattan() {
		int curPos, etalonPos, ii, jj;
		int rezult = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				curPos = i * N + (j + 1);
				etalonPos = board[i][j];
				if (curPos != etalonPos && etalonPos != 0) { // calculate
					ii = (board[i][j] - 1) / N;
					jj = board[i][j] - 1 - ii * N;
					rezult = rezult + Math.abs(i - ii) + Math.abs(j - jj);

				}
				// System.out.println("i: " + i + " j: " + j + " rez: " +
				// rezult);

			}
		// this.manhattan = rezult;
		return rezult;
		// sum of Manhattan distances between blocks and goal
	}

	public boolean isGoal() {
		// if (hamming == -1)
		// return hamming() == 0;
		// else
		return (manhattan == 0 && hamming == 0);

		// is this board the goal board?
	}

	@Override
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;

		if (y.getClass() != this.getClass()) {
			// System.out.println(y.getClass() +" = " + this.getClass() +
			// "111111111111111111111111111111111111111111111111");
			return false;
		}
		Board that = (Board) y;
		if (this.N != that.N)
			return false;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (this.board[i][j] != that.board[i][j])
					return false;
			}
		return true;
		// does this board equal y?
	}

	public Board twin() {

		int[][] twin = board;
		// if (this.twinCount > 4)
		// this.twinCount = 0;
		int temp;
		for (int i = 0; i < N; i++)			// create counter, found ii and jj, exchange only one solution at time
			for (int j = 0; j < N; j++) {
				if (this.board[i][j] != 0) {
					if (j<N-1){
					temp = twin[i][j];
					twin[i][j] = twin[i][j+1];
					twin[i][j+1] = temp;
					}
					else if (i<N-1){
						temp = twin[i][j];
						twin[i][j] = twin[i+1][j];
						twin[i+1][j] = temp;						
					}
				}
			}
		// left exchange, right exchange, up, down
		this.twinCount++;

		return new Board(twin);
		// a boadr that is obtained by exchanging two adjacent blocks in the
		// same row
	}

	private void getTwinCount() { // delete this
		// find zero i and j position of zero;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					this.io = i;
					this.jo = j;
					break;
				}
			}

	}

	public Iterable<Board> neighbors() {
		Stack<Board> stack = new Stack<Board>();
		stack.push(this.twin());
		return stack;
		// all neighboring boards
	}

	@Override
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
		// int N = 3;
		// int[][] test = new int[N][N];
		// int count = 1;
		// count = 0;
		// int[][] test = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
		int[][] test2 = { { 4, 1, 3 }, { 0, 2, 5 }, { 7, 8, 6 } };
		int[][] test = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
		// for (int i = 0; i < N; i++)
		// for (int j = 0; j < N; j++) {
		// test[i][j] = i + j;
		// count++;
		// }

		Board br = new Board(test);
		Board br2 = new Board(test2);
		// Board br2 = br;
		System.out.println(br.dimension());
		for (int[] ii : test)
			for (int jj : ii)
				System.out.println("element: " + jj);

		// br2 = br.neighbors();
		System.out.println("toString------------------");
		System.out.println(br.toString());
		System.out.println("Hamming (out of goal blocks): " + br.hamming());
		// unit tests (not graded)
		System.out.println(br.toString());
		System.out.println(br.manhattan());
		System.out.println(br.equals(br2));

		// System.out.println(br.getClass()==br2.getClass());

	}
}
