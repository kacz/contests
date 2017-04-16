import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {

	static String abcTo;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//debug
		if(args.length > 0) {
			try {
				System.setIn(new FileInputStream("/home/kacz/hack/input"));
				System.setOut(new PrintStream(new FileOutputStream("/home/kacz/hack/output")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		in.nextLine();
		for (int c = 1; c <= t; c++) {
			solve(in,System.out,c);
			
		}

	}

	public static void solve(Scanner in, PrintStream out,int iteration) {
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] lawn = new int[n][m];
		
		int[] colsMin = new int[m];
		Arrays.fill(colsMin, 101);
		
		int[] rowsMin = new int[n];
		Arrays.fill(rowsMin, 101);
		
		for(int i = 0;i<n;++i) {
			for(int j = 0;j<m;++j) {
				lawn[i][j] = in.nextInt();
			}
		}
		
		int[][] fromLeft = new int[n][m];
		for(int i = 0;i<n;++i) {
			int max = lawn[i][0];
			for(int j = 0;j<m;++j) {
				fromLeft[i][j] = max;
				if(max < lawn[i][j])
					max = lawn[i][j];
			}
		}
		
		int[][] fromRight = new int[n][m];
		for(int i = 0;i<n;++i) {
			int max = lawn[i][m-1];
			for(int j = m-1;j>=0;--j) {
				fromRight[i][j] = max;
				if(max < lawn[i][j])
					max = lawn[i][j];
			}
		}
		
		int[][] fromTop = new int[n][m];
		for(int j = 0;j<m;++j) {
			int max = lawn[0][j];
			for(int i = 0;i<n;++i) {
				fromTop[i][j] = max;
				if(max < lawn[i][j])
					max = lawn[i][j];
			}
		}
		
		int[][] fromBottom = new int[n][m];
		for(int j = 0;j<m;++j) {
			int max = lawn[n-1][j];
			for(int i = n-1;i>=0;--i) {
				fromBottom[i][j] = max;
				if(max < lawn[i][j])
					max = lawn[i][j];
			}
		}
		
		boolean ok = true;
		
		for(int i = 0;i<n;++i) {
			for(int j = 0;j<m;++j) {
//				if(lawn[i][j] < fromTop[i][j] && lawn[i][j] < fromBottom[i][j] && lawn[i][j] < fromRight[i][j] && lawn[i][j] < fromLeft[i][j] )
//				{
//					ok = false;
//					break;
//				}
				if(! ( (lawn[i][j] >= fromTop[i][j] && lawn[i][j] >= fromBottom[i][j]) || 
						(lawn[i][j] >= fromLeft[i][j] && lawn[i][j] >= fromRight[i][j]) ))
				{
					ok = false;
					break;
				}
			}
		}
//		
//		out.println("TOP");
//		for(int i = 0;i<n;++i) {
//			for(int j = 0;j<m;++j) {
//				out.print(fromTop[i][j] + " ");
//			}
//			out.println();
//		}
//		out.println("BOTTOM");
//		for(int i = 0;i<n;++i) {
//			for(int j = 0;j<m;++j) {
//				out.print(fromBottom[i][j] + " ");
//			}
//			out.println();
//		}
//		out.println("LEFT");
//		for(int i = 0;i<n;++i) {
//			for(int j = 0;j<m;++j) {
//				out.print(fromLeft[i][j] + " ");
//			}
//			out.println();
//		}
//		out.println("RIGHT");
//		for(int i = 0;i<n;++i) {
//			for(int j = 0;j<m;++j) {
//				out.print(fromRight[i][j] + " ");
//			}
//			out.println();
//		}
//		
		
		out.print("Case #" + iteration + ": ");
		
		if(ok)
			out.println("YES");
		else 
			out.println("NO");
	}
}
