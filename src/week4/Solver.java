package week4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.algorithms.In;
import com.algorithms.MinPQ;
import com.algorithms.Stack;
import com.algorithms.StdOut;

//import com.algorithms.MinPQ;

public class Solver {
	private final Board initial;
	private boolean isSolvable = false;
	private Node rezultNode;
	private int movescount;
	// private Stack<Board> bb = new Stack<Board>();
//	private Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
//	private Map<Integer, Integer> hammingHashMap = new HashMap<Integer, Integer>();
//	private Map<Integer, Board> BoardMap = new HashMap<Integer, Board>();
	private Set<Board> closedSet = new HashSet<Board>();
	private Set<Board> NodeSet = new HashSet<Board>();
//	private Map<Integer, Board> containsBoardMap = new HashMap<Integer,Board>();

	private class Node implements Comparable<Node> {
		private Node priviousNode = null;
		 private Board board;
		private Integer boardHash;
		private Integer manhattan;
//		private Integer hamming;
		private int moves;
		private int priority;

		Node(Board board, int m) {
			this.moves = m;
			this.board = board;
			this.manhattan = board.manhattan();
			 this.priority = m + manhattan ;
			// this.board = board;
//			this.boardHash = board.hashCode(); // board into hashcode
//			if (!BoardMap.containsKey(boardHash))
//				BoardMap.put(boardHash, board);
//
//			 if (!hashMap.containsKey(boardHash)) { // manhman into Hash
//			 // Table
//			 this.manhattan = this.getBoard().manhattan();
//			 hashMap.put(boardHash, manhattan);
//			 } else {
//			 this.manhattan = (Integer) hashMap.get(boardHash);
//			
//			 }
			
//			if (!hammingHashMap.containsKey(boardHash)) { // manhman into Hash Table
//				this.hamming = this.getBoard().hamming();
//				hashMap.put(boardHash, hamming);
//			} else {
//				this.manhattan = (Integer) hashMap.get(boardHash);
//
//			}
//			this.priority = moves + hamming;
			// int hamman = (Integer) BoardMap.get(boardHash).hamming();
			// this.priority = moves + hamman ;

		}

		private Board getBoard() {
//			return (Board) BoardMap.get(boardHash);
			return this.board;
		}

		@Override
		public int compareTo(Node that) {
			int rezult = 0;
//			 rezult;
			if (this.priority > that.priority   ) 				//&& this.manhattan >= that.manhattan
				rezult = 1;
			else if (this.priority < that.priority  ) // && this.manhattan <= that.manhattan 
				rezult = -1;
			else if (this.priority == that.priority)
				if (this.manhattan > that.manhattan)
					return 1;
				else if (this.manhattan < that.manhattan)
					return -1;		
				else if (this.manhattan== that.manhattan){
					if (this.getBoard().hamming() > that.getBoard().hamming())
						return 1;
					else if (this.getBoard().hamming() < that.getBoard().hamming())
						return -1;	
					
				}
//			if (this.priority == that.priority && this.manhattan == that.manhattan)				
			return rezult;
		}

//		public int compare(Node a, Node b) {
//			if ((a.priority) < b.priority) {
//				return -1;
//			} else if (a.priority > b.priority) {
//				return 1;
//			}
//			// a.priority==b.priority
//			return 0;
//		}
	}

	public Solver(Board initial) {
		this.initial = initial;
		Node firstNode = new Node(initial, 0); // initial
		Node firstNodeTwin = new Node(initial.twin(), 0);
		firstNode.priviousNode = null;
		firstNodeTwin.priviousNode = null;
		MinPQ<Node> pq = new MinPQ<Node>();
		MinPQ<Node> pqTwin = new MinPQ<Node>();
//		closedSet.add(firstNode.getBoard());
		pq.insert(firstNode);
		pqTwin.insert(firstNodeTwin);

		Node current = firstNode;
		
		Node twin = firstNodeTwin;
		int movesCount = 0;
		Node priviousSearchNode = firstNode;
		int insertCount =0;
		while (!pq.isEmpty()) { // || !isGoalTwin
			current = pq.delMin(); // del minimum
//			closedSet.add(current.getBoard());
//			twin = pqTwin.delMin();
			movesCount++;
			if (current.manhattan == 1){
				Iterator<Board> iterator2 = current.getBoard().neighbors()
						.iterator();
				while (iterator2.hasNext()) {
					Node in2 = new Node(iterator2.next(), movesCount);
					if(in2.manhattan==0)
//						System.out.println("WIN");
					in2.priviousNode = current.priviousNode;
						rezultNode = in2;
						isSolvable = true;
						return;
				}
			}
//			if (current.manhattan == 0 ){
//				rezultNode = current;
//				isSolvable = true;
//				System.out.println(movesCount);
//				System.out.println(insertCount+" inCount");
//				return;
//			} else if (twin.manhattan == 0) {
//				isSolvable = false;
//				return;
//			} 
			if (current.manhattan == 1 || current.manhattan == 1)
				System.out.println(movesCount);
				//			else if(current.board.hamming() == 1 && current.manhattan == 2){
//				isSolvable = false;
//				return;
//			}
			// prcount = current.priority + 1;
		
			Iterator<Board> iterator = current.getBoard().neighbors()
						.iterator();
			
//			Iterator<Board> iteratorTwin = twin.getBoard().neighbors()
//					.iterator();
			// System.out.println(iterator.next().toString());
			
			while (iterator.hasNext()) {
				Node in = new Node(iterator.next(), movesCount);	
				in.priviousNode = current; 
				
				if (in.manhattan == 1 ){
					Iterator<Board> iterator2 = in.getBoard().neighbors()
							.iterator();
					while (iterator2.hasNext()) {
						Node in2 = new Node(iterator2.next(), movesCount);
						if(in2.manhattan==0)
//							System.out.println("WIN");
						in2.priviousNode = in;
							rezultNode = in2;
							isSolvable = true;
							return;
					}
					System.out.println("neigbours " + movesCount);
					System.out.println(insertCount+" insertion Count");	
				}
				
//				if (closedSet.contains(in.getBoard()))
//					continue;
				
				if (movesCount <=current.getBoard().dimension()-2) {	
					insertCount++;
					pq.insert(in);
//					closedSet.add(in.getBoard());
				} else if (!in.getBoard().equals(current.priviousNode.getBoard())&&!in.getBoard().equals(priviousSearchNode.getBoard()) && in.manhattan <= 1+priviousSearchNode.manhattan)   { // && in.compareTo(current.priviousNode)<1  && in.manhattan <= 1+priviousSearchNode.manhattan
					pq.insert(in);
					insertCount++;
//					closedSet.add(in.getBoard());			
				}

			}
			if (movesCount >1)
				priviousSearchNode = current.priviousNode; //currentBoard.previousBoard.previousBoard
//			else
//				priviousSearchNode = current;
		}
		

	} // find a solution to the initial board (using the A* algorithm)

	public boolean isSolvable() {

		return isSolvable;
//		 return true;
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

		Node current = rezultNode;
		int movescount = 0;
		while (current.priviousNode != null) {
			bb.push(current.getBoard());
			current = current.priviousNode;
			movescount++;
		}
		this.movescount = movescount;
		bb.push(initial);
		
		return bb;
		// sequence of boards in a shortest solution; null if unsolvable
	}

	public static void main(String[] args) { // solve a slider puzzle (given
												// below)

		// create initial board from file
		// In in = new In(args[0]);

		In in = new In(
		// "/home/hellow/workspaceJava/Algorithms_testFiles/Pazzle/puzzle16.txt");
		// In in = new In(
				"/home/kokoko/workspace/Algorithms_sendfiles/puzzleTestFiles/puzzle34.txt");
		// puzzle2x2-unsolvable1.txt puzzle03.txt puzzle3x3-unsolvable1.txt

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
