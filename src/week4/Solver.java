package week4;

import com.algorithms.In;
import com.algorithms.StdOut;
import com.algorithms.MinPQ;

public class Solver {
private final Board initial;

// private class Node{
// Node privious;
// int[][] board;
// // int manhattan =
// //int priority =
// int moves;
// Node(){
//
// }
// }

// private void setBoard(int[][] in){
// board = in;
// }

	public Solver(Board initial) {
//		initial
		this.initial = initial;
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
		return null;
		// sequence of boards in a shortest solution; null if unsolvable
	}

	public static void main(String[] args) { // solve a slider puzzle (given
												// below)

		// create initial board from file
		// In in = new In(args[0]);

		In in = new In(
				"/home/hellow/workspaceJava/Algorithms_testFiles/Pazzle/puzzle03.txt");
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
