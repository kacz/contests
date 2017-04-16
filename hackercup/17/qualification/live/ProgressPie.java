package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgressPie {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }

    static class Star {
        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    private static void solve(int caseNum, Scanner in, PrintStream out) {

        //vector1: 0 1

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> pies = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> dayPies = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int c = in.nextInt();
                dayPies.add(c);
            }
            dayPies.sort(Integer::compareTo);
            pies.add(dayPies);
        }

        System.out.println(pies);



//        out.println("Case #" + caseNum + ": " + solution);
    }

    private static long distance(Star s, Star t) {
        return (s.x - t.x) * (s.x - t.x) + (s.y - t.y) * (s.y - t.y);
    }
}
