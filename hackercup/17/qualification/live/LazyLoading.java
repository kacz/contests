package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class LazyLoading {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }

    private static void solve(int caseNum, Scanner in, PrintStream out) {

        //vector1: 0 1

        int n = in.nextInt();
        LinkedList<Integer> items = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            int w = in.nextInt();
            items.add(w);
        }
        Collections.sort(items);

        System.out.println("sorted elements: " + items);
        int solution = 0;

        while (!items.isEmpty()) {
            int max = items.removeLast();
            System.out.println("max: " + max);
            int pieces = 50 / max;
            if (50 % max != 0) {
                pieces++;
            }
            pieces--;


            System.out.println("pieces needed: " + pieces);
            if (items.size() < pieces)
                break;

            for (int i = 0; i < pieces; i++) {
                int piece = items.removeFirst();
                System.out.print(piece + " ");
            }
            System.out.println("");
            solution++;
        }
        if (solution == 0) {
            solution++;
        }

        out.println("Case #" + caseNum + ": " + solution);
    }
}
