package weekThree;
//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=858
//https://class.coursera.org/algs4partI-006/forum/thread?thread_id=760
import java.util.Arrays;

import com.algorithms.In;
import com.algorithms.StdDraw;

public class Fast {
	private static String filename = "/home/kokoko/workspace/Algorithms_sendfiles/Colinear_ex/input56.txt";
	public static void main(String[] args) {
		// read from the input
//		String filename = args[0];
		StdDraw.setXscale(0, 32768); // rescale coordinates and turn on
										// animation mode
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		
		In in = new In(filename);
		int N = in.readInt();
		// int countL = countLines(filename);
		int countL = N;

		Point[] parr = new Point[countL];
		Point[] parr2 = new Point[countL];
//		Point[] slopeArr = new Point[countL];
		
		for (int i = 0; i < N; i++) { // Create array of Points
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			parr[i] = p;
			parr2[i] = p;
			p.draw();
		}
		int count = 0;
		Arrays.sort(parr, 0, N);
		Point pA, p1 = new Point(0, 0);
		Point pB, p2 = new Point(1, 0);
		Point pC, p3 = new Point(0, 0);
		Point pD, p4,to = new Point(0, 0);
		System.out.println(p2.compareTo(p1));
//		boolean print = false;
		 for (Point s : parr) {
			 System.out.println(count + " : " + s.toString());
			 count++;
			 }
		
		for (int i=0; i<N; i++){
//			Arrays.sort(parr, 0, N);
			p1 = parr[i];
			Arrays.sort(parr2, p1.SLOPE_ORDER);	
			count=0;
			for(int j=0;j<N-1;j++){
				if (p1.SLOPE_ORDER.compare(parr2[j], parr2[j+1])==0){
					
					
				}
//					count++;
////					if (parr2[j].compareTo(parr2[j+1])==1)
//				
//					p1.drawTo(parr2[j]);
//				
//					
//					
////					p1.drawTo(to);
////					break;
////					System.out.println("lable");
//				}
//				else {
//					if(count > 2 ){
////						p1.drawTo(to);
////						parr2[j].drawTo(parr2[j+1]);
//					count = 0;	
//					
//					}
//					count = 0;
//					
//				}
				
				
			}
			
		
			
			
			
		}
		
		// Point to = new Point(0, 0);
//		 MY TEST
		 count = 0;
		 for (Point s : parr2) {
		 System.out.println(count + " : " + s.toString());
		 count++;
		 }
		StdDraw.show(0);
		// reset the pen radius
		StdDraw.setPenRadius();
//		 System.out.println(count);
	}
}
