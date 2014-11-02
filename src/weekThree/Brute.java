package weekThree;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.algorithms.In;
import com.algorithms.StdDraw;
import com.algorithms.StdOut;

public class Brute {

//	private static String filename = "/home/kokoko/workspace/Algorithms_sendfiles/Colinear_ex/input48.txt";

	private static String filename = "/home/hellow/workspaceJava/Algorithms_testFiles/collinear/input8.txt";
//	private static String filename = "/home/kokoko/workspace/Algorithms_sendfiles/Colinear_ex/horizontal5.txt";


	public static void main(String[] args) throws IOException {
		// read from the input
//		String filename = args[0];
		In in = new In(filename);
		StdDraw.setXscale(0, 32768); // rescale coordinates and turn on
										// animation mode
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		
		
		int N = in.readInt();
		// int countL = countLines(filename);
		int countL = N;

		Point[] parr = new Point[countL];		
		for (int i = 0; i < N; i++) { // Create array of Points
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			p.draw();
		}
		int count = 0;
		Arrays.sort(parr, 0, N);
		Point pA, p1 = new Point(0, 0);
		Point pB, p2 = new Point(0, 0);
		Point pC, p3 = new Point(0, 0);
		Point pD, p4 = new Point(0, 0);
		// Point to = new Point(0, 0);
	
		for (int a = 0; a < countL; a++) {		
			
			for (int b = a + 1; b < N; b++) {
				pA = parr[a];
				pB = parr[b];
				for (int c = b + 1; c < N; c++) {
					pC = parr[c];
					if (pA.slopeTo(pB) == pA.slopeTo(pC))
//						if (pB.slopeTo(pC) == pB.slopeTo(pA)) 
						{	
							for (int d = c + 1; d < N; d++) {
								pD = parr[d];
								if (pD.slopeTo(pA) == pD.slopeTo(pB))
									if (pD.slopeTo(pC) == pD.slopeTo(pB))
//									if (pD.slopeTo(pC)!=pD.slopeTo(parr[d+1]))
									{
//										p1 = pA;
//										p2 = pB;
//										p3 = pC;
//										p4 = pD;								
										pA.drawTo(pD);
										count++;
										
										StdOut.println(pA + " -> " + pB + " -> " + pC + " -> " + pD);
									
								}
							}
						}
				}
			}
		}

//		 MY TEST
//		 count = 0;
//		 for (Point s : parr) {
//		 System.out.println(count + " : " + s.toString());
//		 count++;
//		 }
		StdDraw.show(0);
		// reset the pen radius
		StdDraw.setPenRadius();
//		 System.out.println(count);
	}

	private static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

}
