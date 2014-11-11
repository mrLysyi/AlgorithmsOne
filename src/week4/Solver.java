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
	private Node rezultNode = null;
	private int movescount;
	// private Stack<Board> bb = new Stack<Board>();
//	private Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
//	private Map<Integer, Integer> hammingHashMap = new HashMap<Integer, Integer>();
//	private Map<Integer, Board> BoardMap = new HashMap<Integer, Board>();
//	private Set<Board> closedSet = new HashSet<Board>();
//	private Set<Board> NodeSet = new HashSet<Board>();
//	private Map<Integer, Board> containsBoardMap = new HashMap<Integer,Board>();

	private class Node implements Comparable<Node> {
		private Node priviousNode = null;
		 private Board board;
//		private Integer boardHash;
		private Integer manhattan;
//		private Integer hamming;
		private int moves;
		private int priority;

		Node(Board board, int m) {
			this.moves = m;
			this.board = board;
			this.manhattan = board.manhattan();
			 this.priority = m + manhattan ;
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
		pq.insert(firstNode);
		pqTwin.insert(firstNodeTwin);
		Node current = firstNode;		
		Node twin = firstNodeTwin;		
		Node priviousSearchNode = firstNode;
		Node priviousSearchNodeTwin  = firstNodeTwin;
		int movesCount = 0;
		int insertCount =0;
		while (!pq.isEmpty()) { // || !isGoalTwin
			current = pq.delMin(); // del minimum
			twin = pqTwin.delMin();
			movesCount++;
			if (current.manhattan == 0){	// if initial puzzle is already solved
				this.rezultNode =current;
				isSolvable = true;
				return;
			}
			
			if (twin.manhattan == 1){					
				isSolvable = false;
				return;					
			}	
			
			if (current.manhattan == 1){
				Iterator<Board> iterator2 = current.getBoard().neighbors()
						.iterator();
				while (iterator2.hasNext()) {
					Board temp2 = iterator2.next();
					Node in2 = new Node(temp2, current.moves+1);
					if(in2.manhattan==0){
					in2.priviousNode = current;	//					if (rezultNode==null)
						this.rezultNode = in2;
						isSolvable = true;
						return;
						}					 
					 }
			}		
			
			Iterator<Board> iterator = current.getBoard().neighbors()
						.iterator();		
						
			while (iterator.hasNext()) {
				Board temp = iterator.next();//				
				Node in = new Node(temp, current.moves+1);	
				in.priviousNode = current; 				
			
				if (movesCount <= current.getBoard().dimension()-1) {	//current.getBoard().dimension()-2 //3
					insertCount++;
					pq.insert(in);

				} else if (!in.getBoard().equals(current.priviousNode.getBoard())&&
						   !in.getBoard().equals(priviousSearchNode.getBoard()) 
						  )   { //  && in.manhattan <= current.getBoard().dimension()+priviousSearchNode.manhattan						
					pq.insert(in);
					insertCount++;		
				}
			}
			if (movesCount >1)
				priviousSearchNode = current.priviousNode; //currentBoard.previousBoard.previousBoard
			
		Iterator<Board> iteratorTwin = twin.getBoard().neighbors()	//TWIN__TWIN
				.iterator();
		
		while (iteratorTwin.hasNext()) {					
			Node inT = new Node(iteratorTwin.next(), twin.moves+1);	
			inT.priviousNode = twin; 				
//		
			if (movesCount <= twin.getBoard().dimension()-1) {	//current.getBoard().dimension()-2 //3
////				insertCount++;
				pqTwin.insert(inT);
//
			} else if (!inT.getBoard().equals(twin.priviousNode.getBoard())&&
					   !inT.getBoard().equals(priviousSearchNodeTwin.getBoard()) 
					  )   { //  && in.manhattan <= current.getBoard().dimension()+priviousSearchNode.manhattan						
				pqTwin.insert(inT);
//				insertCount++;		
			}
		}
		if (movesCount >1)
			priviousSearchNodeTwin = twin.priviousNode; //currentBoard.previousBoard.previousBoard
		
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
			return rezultNode.moves;
		// min number of moves to solve initial board; -1 if unsolvable
	}

	public Iterable<Board> solution() {
		if (!isSolvable) {
			return null;
		}
		Stack<Board> bb = new Stack<Board>();
		Node current = null;
		current = rezultNode;
		int movescount = 0;
		while (current.priviousNode != null) {
			bb.push(current.getBoard());
			current = current.priviousNode;
			movescount++;
		}
//		this.movescount = movescount;
		bb.push(initial);
		
		return bb;
		// sequence of boards in a shortest solution; null if unsolvable
	}

	public static void main(String[] args) { // solve a slider puzzle (given
												// below)
		// create initial board from file
		// In in = new In(args[0]);
		In in = new In(
		 "/home/hellow/workspaceJava/Algorithms_testFiles/Pazzle/puzzle00.txt");
		// In in = new In(
//				"/home/kokoko/workspace/Algorithms_sendfiles/puzzleTestFiles/puzzle34.txt");
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
