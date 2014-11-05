package week4;

//import java.util.Arrays;

import java.util.Iterator;

//import com.algorithms.MinPQ;
import com.algorithms.Stack;

public class Board {
	// private final int[][] initial;

	// private MinPQ<Board> pf = new MinPQ<Board>();
	// private final Node initial = new Node();
	private  short[][] board;
	private final int N;
	private final short[][] etalon;
	// private int moves;
	// private int priority; //manhattam+moves
//	private final int manhattan = this.manhattan();
//	private final int hamming = this.hamming();
	// private int twinCount = 0;
	// private int io; // position of zero
	// private int jo;
	private Stack<Integer> twinStack = new Stack<Integer>();

	// private Stack<Board> neibors = new Stack<Board>();

	public Board(int[][] blocks) {
//		this.board = blocks;
		// initial.privious = null;
		// initial.moves =0;
		this.N = blocks.length;
		board = new short[N][N];
		int count = 1;
		etalon = new short[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				this.board[i][j] = (short)blocks[i][j];
				this.etalon[i][j] = (short)count;
				count++;
			}
		this.etalon[N - 1][N - 1] = 0;

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
			}
		return rezult;
		// sum of Manhattan distances between blocks and goal
	}

	public boolean isGoal() {
		// if (hamming == -1)
		// return hamming() == 0;
		// else
		return (manhattan() == 0 && hamming() == 0 && board[N-1][N-1]==0 );

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
		if (twinStack.isEmpty()) {
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					if (j < N - 1)
						if (board[i][j] != 0 && board[i][j + 1] != 0)
							twinStack.push(i * N + j);
				}
		}
//		 System.out.println("tttttttStack "+twinStack.toString());
//		return swap(twinStack.pop(), 'r');
		int number = 0;
		if(board[0][0]!=0 && board[0][1]!=0 )
			number = 0;
		else
			number = N;
		return swap(number, 'r');
		
	} // a boadr that is obtained by exchanging two adjacent blocks in the
		// same row

	// private void getTwinCount() { // delete this
	// // find zero i and j position of zero;
	// for (int i = 0; i < N; i++)
	// for (int j = 0; j < N; j++) {
	// if (board[i][j] == 0) {
	// this.io = i;
	// this.jo = j;
	// break;
	// }
	// }
	// }
	private Board swap(int position, char side) {// left, right, up, down
		int i = position / N;
		int j = position - i * N;
		int second;
		// System.out.println("position i: "+i + " position j: "+j);
		int[][] temp = new int[N][N];
		arrayCopy(board, temp);
		int first = temp[i][j];
		switch (side) {
		case 'l':
			second = temp[i][j - 1];
			temp[i][j] = second;
			temp[i][j - 1] = first;
			break;
		case 'r':
			second = temp[i][j + 1];
			temp[i][j] = second;
			temp[i][j + 1] = first;
			break;
		case 'u':
			second = temp[i - 1][j];
			temp[i][j] = second;
			temp[i - 1][j] = first;
			break;
		case 'd':
			second = temp[i + 1][j];
			temp[i][j] = second;
			temp[i + 1][j] = first;
			break;
		default:
			break;
		}

		// System.out.println("f: " + first + " s: " + second);

		// this.twinCount += 1;
		return new Board(temp);
	}

	public Iterable<Board> neighbors() {
		int io = 0;
		int jo = 0;
		for (int i = 0; i < N; i++)
			// find zero coordinats
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					io = i;
					jo = j;
					break;
				}
			}
		// System.out.println("zero position" + io+" - i, j -"+ jo);
		Stack<Board> stack = new Stack<Board>();
		int position = io * N + jo;
		if (jo > 0) // left
			stack.push(swap(position, 'l'));
		if (jo < N - 1) // right
			stack.push(swap(position, 'r'));
		if (io > 0) // up
			stack.push(swap(position, 'u'));
		if (io < N - 1) // down
			stack.push(swap(position, 'd'));

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

	private int[][] arrayCopy(short[][] a, int[][] b) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				b[i][j] = a[i][j];
			}

		}
		return b;
	}

	public static void main(String[] args) {
		// int N = 3;
		// int[][] test = new int[N][N];
		// int count = 1;
		// count = 0;
//		 int[][] test = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
		int[][] test2 = { { 4, 1, 3 }, { 2, 0, 5 }, { 7, 8, 6 } };
//		int[][] test = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
//		int[][] etalonT = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		// for (int i = 0; i < N; i++)
		// for (int j = 0; j < N; j++) {
		// test[i][j] = i + j;
		// count++;
		// }

//		final Board br = new Board(test);
		Board br2 = new Board(test2);
//		Board br3 = new Board(etalonT);
		// Board br2 = br;
		// System.out.println(br.dimension());
		// for (int[] ii : test)
		// for (int jj : ii)
		// System.out.println("element: " + jj);

		// br2 = br.neighbors();

		System.out.println(br2.toString());
		System.out.println("toString------------------");
		// System.out.println("Hamming (out of goal blocks): " + br.hamming());
		// // unit tests (not graded)
//		 System.out.println(br.toString());
		// System.out.println(br.manhattan());
		// System.out.println(br.equals(br2));

		// for (int i = 0; i < 5; i++)
		// // br2 = br.twin();
		// System.out.println(br.twin().toString());
		System.out.println("end");

		// br2.twin();
		// br2 = br.twin();
		// System.out.println("toasaString: " + br.toString());
		// System.out.println(br.twin().toString());

		// System.out.println(br.getClass()==br2.getClass());
		// Stack<Board> temp = br.neighbors();

		 Iterator<Board> iterator = br2.neighbors().iterator();
		 while(iterator.hasNext()){
		 // System.out.println(iterator.toString());
		 System.out.println(iterator.next().toString());
		 }
//		System.out.println(br.isGoal());
////		for (int i = 0; i < br3.dimension(); i++)
////			for (int j = 0; j < br3.dimension(); j++) {
////				System.out.println(br3.etalon[i][j] + " ");
////			}
//		System.out.println(br.hamming());

	}
}
