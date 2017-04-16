import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
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
	
	public static long nextPalindrome(long a) {
		String num = new StringBuffer().append(a).toString();
		boolean even = (num.length() % 2 == 0);
		String base = num.substring(0,(int)Math.ceil(((double)num.length())/2));
		
		String tail = new StringBuffer().append(base.substring(0, (even ? base.length() : base.length()-1))).toString();
//		if(tail == null) tail = "";
		String newnum = base + tail;
//		System.out.println("vajon:" + newnum + " base:" + base + " tail:" + tail);
		long pal = Long.parseLong(newnum);
		if(pal > a) return pal;
		
		long basenum = Long.parseLong(num.substring(0,(int)Math.ceil(((double)num.length())/2)));
		String newbase = "" + (basenum+1);
		if(newbase.length() > base.length()) 
		{
			even = !even;
			if(even) newbase = newbase.substring(0,newbase.length()-1);
		}
		
		
		
		tail = new StringBuffer().append(newbase.substring(0, (even ? newbase.length() : newbase.length()-1))).toString();
		if(tail == null) tail = "";
//		System.out.println("vajon:" + base + tail);
		pal = Long.parseLong(newbase + tail);
		
		return pal;
	}
	
	public static boolean isPalindrome(long a) {
		String num = new StringBuffer().append(a).reverse().toString();
		long r = Long.parseLong(num);
		return (a-r)==0;
	}

	public static void solve(Scanner in, PrintStream out,int iteration) {
		long a = in.nextLong();
		long b = in.nextLong();
		
		long result = 0;
		
		long aRoot = (long)Math.ceil(Math.sqrt(a));
		long bRoot = (long)Math.floor(Math.sqrt(b));
		
		long root = aRoot -1;
		while(root <= bRoot) {
			long root2 = nextPalindrome(root);
//			out.println("next to " + root + " is " + root2);
			root = root2;
			if(root > bRoot) break;
//			out.println(root*root + " is " + isPalindrome(root*root));
			if(isPalindrome(root*root)){
				result++;
			}
			
		}
		
		out.println("Case #" + iteration + ": " + result);

	}
}
