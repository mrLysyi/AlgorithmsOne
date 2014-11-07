package week4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.algorithms.In;
import com.algorithms.Stack;
import com.algorithms.StdOut;
import com.algorithms.MinPQ;

public class Solver {
	private final Board initial;
	private boolean isSolvable = false;
	private Node rezultNode;
	private int movescount;
	// private Stack<Board> bb = new Stack<Board>();
	private Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	private Map<Integer, Board> BoardMap = new HashMap<Integer, Board>();

	private class Node implements Comparable<Node> {
		private Node priviousNode = null;
		// private Board board;
		private Integer boardHash;
		private Integer manhattan;
		private int moves;
		private int priority;

		Node(Board board, int m) {
			this.moves = m;
			// this.board = board;
			this.boardHash = board.hashCode(); // board into hashcode
			if (!BoardMap.containsKey(boardHash))
				BoardMap.put(boardHash, board);

			if (!hashMap.containsKey(board.hashCode())) { // manhman into Hash
															// Table
				this.manhattan = this.getBoard().manhattan();
				hashMap.put(boardHash, manhattan);
			} else {
				this.manhattan = (Integer) hashMap.get(board.hashCode());

			}
			this.priority = moves + manhattan;

		}

		private Board getBoard() {
			return (Board) BoardMap.get(boardHash);
		}

		@Override
		public int compareTo(Node that) {
			int rezult;
			if (this.priority < that.priority)
				rezult = -1;
			else if (this.priority == that.priority)
				rezult = 0;
			else
				// >
				rezult = 1;
			return rezult;
		}

		public int compare(Node a, Node b) {
			if ((a.priority) < b.priority) {
				return -1;
			} else if (a.priority > b.priority) {
				return 1;
			}
			// a.priority==b.priority
			return 0;

		}
	}

	public Solver(Board initial) {
		this.initial = initial;
		Node firstNode = new Node(initial, 0); // initial
		Node firstNodeTwin = new Node(initial.twin(), 0);
		firstNode.priviousNode = null;
		firstNodeTwin.priviousNode = null;
		MinPQ<Node> pq = new MinPQ<Node>();
		MinPQ<Node> pqTwin = new MinPQ<Node>();

		pq.insert(firstNode);
		pqTwin.insert(firstNodeTwin);

		Node current = firstNode;
		Node twin = firstNodeTwin;
		int movesCount = 0;
		Board previousBoard = null;
		Board previousBoardTwin = null;
		boolean isGoal = false;
		boolean isGoalTwin = false;

		while (!isGoal) { // || !isGoalTwin
			current = pq.delMin(); // del minimum
			twin = pqTwin.delMin();
			movesCount++;
			Iterator<Board> iterator = current.getBoard().neighbors()
					.iterator();
			Iterator<Board> iteratorTwin = twin.getBoard().neighbors()
					.iterator();
			// System.out.println(iterator.next().toString());

			while (iterator.hasNext()) {
				Node in = new Node(iterator.next(), movesCount);
				in.priviousNode = current; //
				if (movesCount == 1) {
					pq.insert(in);
					// System.out.println("mvoes_first:" + movesCount);
					// System.out.println(in.board.toString());
				} else if (!previousBoard.equals(in.getBoard())) { // critical
																	// optimization
					pq.insert(in); // add//
				}
			}
			while (iteratorTwin.hasNext()) { // twin loop for iterator
				Node in = new Node(iteratorTwin.next(), movesCount);
				in.priviousNode = twin; //
				if (movesCount == 1) {
					pqTwin.insert(in);
				} else if (!previousBoardTwin.equals(in.getBoard())) {
					pqTwin.insert(in); // add//
				}
			}

			previousBoard = current.getBoard();
			previousBoardTwin = twin.getBoard();
			// current.board.neighbors();
			isGoal = current.getBoard().isGoal();
			isGoalTwin = twin.getBoard().isGoal();
			if (isGoal) {
				rezultNode = current;
				isSolvable = true;
				return;
			} else if (isGoalTwin) {
				isSolvable = false;
				return;
			}
			iterator = null;
			iteratorTwin = null;
			// current = pq.
		}
		// this.initial.
	} // find a solution to the initial board (using the A* algorithm)

	public boolean isSolvable() {

		return isSolvable;
		// return true;
	} // is the initial board solvable?

	public int moves() {
		solution();
		if (!isSolvable())
			return -1;
		else
			return movescount;
		// min number of moves to solve initial board; -1 if unsolvable
	}

	public Iterable<Board> solution() {
		if (!isSolvable) {
			return null;
		}
		Stack<Board> bb = new Stack<Board>();
		bb.push(initial);
		Node current = rezultNode;
		int movescount = 0;
		while (current.priviousNode != null) {
			bb.push(current.getBoard());
			current = current.priviousNode;
			movescount++;
		}
		this.movescount = movescount;
		return bb;
		// sequence of boards in a shortest solution; null if unsolvable
	}

	public static void main(String[] args) { // solve a slider puzzle (given
												// below)

		// create initial board from file
		// In in = new In(args[0]);

		In in = new In(
				"/home/hellow/workspaceJava/Algorithms_testFiles/Pazzle/puzzle16.txt");
		// In in = new In(
		// "/home/kokoko/workspace/Algorithms_sendfiles/puzzleTestFiles/puzzle03.txt");
		// puzzle2x2-unsolvable1.txt puzzle03.txt

		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);
		//
		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
		// --------------------------------
		// System.out.println("original " + initial.toString());
		// System.out.println(initial.neighbors().toString());
		// solver.Node
		//
	}

}
