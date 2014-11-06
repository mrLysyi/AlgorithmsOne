package week4;

import java.util.Iterator;

import com.algorithms.In;
import com.algorithms.StdOut;
import com.algorithms.MinPQ;

public class Solver {
	private final Board initial;

	private class Node implements Comparable<Node> {
		private Node privious;
		private Board board;
		private int manhattan;
		private int moves;
		private int priority = moves + manhattan;

		Node(Board board, int m) {
			this.moves = m;
			this.board = board;
			this.manhattan = board.manhattan();
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
	}

	public Solver(Board initial) {
		this.initial = initial;
		Node firstNode = new Node(initial, 0); // initial
		firstNode.privious = null;
		MinPQ<Node> pq = new MinPQ<Node>();
		pq.insert(firstNode);
		Node current = firstNode;
		int movesCount = 1;
		Board previous = firstNode.board;
		while (!current.board.isGoal()) {
			current = pq.delMin(); // del minimum

			Iterator<Board> iterator = current.board.neighbors().iterator();
			System.out.println(iterator.next().toString());
			while (iterator.hasNext()) {
				
				Node in = new Node(iterator.next(), movesCount).privious = current;
//				System.out.println(in.board.toString());
				if (previous.equals(in.board))	{		// critical optimization
					pq.insert(in); // add
//				System.out.println(in.board.toString());
				}
									// neighbours;
			}
			previous = current.board;
			// current.board.neighbors();
			movesCount++;
			// current = pq.
		}
		// this.initial.
	} // find a solution to the initial board (using the A* algorithm)

	public boolean isSolvable() {

		return true;
	} // is the initial board solvable?

	public int moves() {
		if (!isSolvable())
			return -1;
		else
			return initial.dimension();
		// min number of moves to solve initial board; -1 if unsolvable
	}

	public Iterable<Board> solution() {
		MinPQ<Board> bb = new MinPQ<Board>();
		// bb.
		return bb;
		// sequence of boards in a shortest solution; null if unsolvable
	}

	public static void main(String[] args) { // solve a slider puzzle (given
												// below)

		// create initial board from file
		// In in = new In(args[0]);

		In in = new In(
				"/home/hellow/workspaceJava/Algorithms_testFiles/Pazzle/puzzle03.txt");
		// In in = new In(
		// "/home/kokoko/workspace/Algorithms_sendfiles/puzzleTestFiles/puzzle03.txt");

		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
