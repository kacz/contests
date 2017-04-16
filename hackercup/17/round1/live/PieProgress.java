package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PieProgress {

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

        int[] boughtCount = new int[n];
        Arrays.fill(boughtCount, 0);

        int overallPrice = 0;
        for (int i = 0; i < n; i++) {
            int bestDay = -1;
            int bestPrice = Integer.MAX_VALUE;
            for (int j = 0; j <= i; j++) {
                if(boughtCount[j] < m) {
                    int overPrice = (boughtCount[j] + 1) * (boughtCount[j] + 1) - boughtCount[j] * boughtCount[j];
                    int fullPrice = pies.get(j).get(boughtCount[j]) + overPrice;
                    if (fullPrice < bestPrice) {
                        bestPrice = fullPrice;
                        bestDay = j;
                    }
                }
            }
            boughtCount[bestDay]++;
            overallPrice += bestPrice;
        }

        out.println("Case #" + caseNum + ": " + overallPrice);
    }

    private static long distance(Star s, Star t) {
        return (s.x - t.x) * (s.x - t.x) + (s.y - t.y) * (s.y - t.y);
    }
}
