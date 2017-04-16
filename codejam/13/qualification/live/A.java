import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
			try {
				in.nextLine();
			} catch(NoSuchElementException e) {
				
			}
			
		}

	}

	public static void solve(Scanner in, PrintStream out,int iteration) {
		List<String> s = new ArrayList<String>();
		
		for(int i=0;i<4;++i ) {
			s.add(in.nextLine());
		}
		
		char result = 'D';
		
		for(int i = 0; i < 4;++i) {
			if(s.get(i).contains(".")) result = '?';
		}
		
		for(int i = 0; i < 4;++i) {
			if(!s.get(i).contains(".")) {
				if(!s.get(i).contains("X")) {
					result = 'O';
					break;
				}
				else if(!s.get(i).contains("O")) {
					result = 'X';
					break;
				}
			}
		}
		
		for(int i = 0; i < 4;++i) {
			
			String a = "" + s.get(0).charAt(i) +s.get(1).charAt(i) + s.get(2).charAt(i) + s.get(3).charAt(i); 
			
			if(!a.contains(".")) {
				if(!a.contains("X")) {
					result = 'O';
					break;
				}
				else if(!a.contains("O")) {
					result = 'X';
					break;
				}
			}
		}
		
		String d1 = "" + s.get(0).charAt(0) +s.get(1).charAt(1) + s.get(2).charAt(2) + s.get(3).charAt(3); 
		
		if(!d1.contains(".")) {
			if(!d1.contains("X")) {
				result = 'O';
			}
			else if(!d1.contains("O")) {
				result = 'X';
			}
		}
		
		String d2 = "" + s.get(0).charAt(3) +s.get(1).charAt(2) + s.get(2).charAt(1) + s.get(3).charAt(0); 
		
		if(!d2.contains(".")) {
			if(!d2.contains("X")) {
				result = 'O';
			}
			else if(!d2.contains("O")) {
				result = 'X';
			}
		}
		
		
		out.print("Case #" + iteration + ": ");
		
		switch(result) {
			case 'X': out.println("X won"); break;
			case 'O': out.println("O won"); break;
			case 'D': out.println("Draw"); break;
			case '?': out.println("Game has not completed"); break;
		}
	}
}
