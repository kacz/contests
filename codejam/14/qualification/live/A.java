package kacz.codejam14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    static Scanner in;

    public static void main(String[] args) throws IOException {
	    System.setIn(new FileInputStream("A-small-attempt0.in"));
        System.setOut(new PrintStream("output"));
        in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 1; i <= T; ++i) {
            solve(i);
        }
    }

    private static void solve(int caseNum) {
        int row1 = in.nextInt();
        Set<Integer> first = new HashSet<Integer>();
        Set<Integer> second = new HashSet<Integer>();
        for(int i = 1; i <= 4; ++i) {
            List<Integer> l = new ArrayList<Integer>();
            for(int j = 0; j < 4; ++j) {
                l.add(in.nextInt());
            }
            if(i == row1) {
                first.addAll(l);
            }
        }
        int row2 = in.nextInt();
        for(int i = 1; i <= 4; ++i) {
            List<Integer> l = new ArrayList<Integer>();
            for(int j = 0; j < 4; ++j) {
                l.add(in.nextInt());
            }
            if(i == row2) {
                second.addAll(l);
            }
        }

        first.retainAll(second);
        if(first.size() == 1) {
            System.out.println("Case #" + caseNum + ": " + first.iterator().next());
        } else if(first.size() == 0) {
            System.out.println("Case #" + caseNum + ": Volunteer cheated!");
        } else {
            System.out.println("Case #" + caseNum + ": Bad magician!");
        }
    }
}
