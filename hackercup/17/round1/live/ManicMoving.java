package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ManicMoving {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }


    private static void solve(int caseNum, Scanner in, PrintStream out) {

//        System.out.println("case: " + caseNum);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        List<Integer> roadStarts = new ArrayList<>(m);
        List<Integer> roadEnds = new ArrayList<>(m);
        List<Integer> roadLenghts = new ArrayList<>(m);

        for (int i = 0; i < m; i++) {
            roadStarts.add(in.nextInt());
            roadEnds.add(in.nextInt());
            roadLenghts.add(in.nextInt());
        }

        List<Integer> taskStarts = new ArrayList<>(m);
        List<Integer> taskEnds = new ArrayList<>(m);
        for (int i = 0; i < k; i++) {
            taskStarts.add(in.nextInt());
            taskEnds.add(in.nextInt());
        }

        long[][] map = fw(n, roadStarts, roadEnds, roadLenghts);

//        printGrid(map, n);

        long[] empty = new long[k];
        long[] loaded = new long[k];

        empty[0] = map[0][taskStarts.get(0)-1] + map[taskStarts.get(0)-1][taskEnds.get(0)-1];

        if(k == 1) {
            out.println("Case #" + caseNum + ": " + printSolution(empty[0]));
            return;
        }


        loaded[0] = map[0][taskStarts.get(0)-1] + map[taskStarts.get(0)-1][taskStarts.get(1)-1] + map[taskStarts.get(1)-1][taskEnds.get(0)-1];

        for (int i = 1; i < k; i++) {
            empty[i] = Math.min(empty[i-1] + map[taskEnds.get(i-1)-1][taskStarts.get(i)-1] + map[taskStarts.get(i)-1][taskEnds.get(i)-1],
                    loaded[i-1] + map[taskEnds.get(i-1)-1][taskEnds.get(i)-1]);

            if(i < k-1) {
                loaded[i] = Math.min(empty[i-1] +
                                        map[taskEnds.get(i-1)-1][taskStarts.get(i)-1] +
                                        map[taskStarts.get(i)-1][taskStarts.get(i+1)-1] +
                                        map[taskStarts.get(i+1)-1][taskEnds.get(i)-1],
                                    loaded[i-1] +
                                            map[taskEnds.get(i-1)-1][taskStarts.get(i+1)-1] +
                                            map[taskStarts.get(i+1)-1][taskEnds.get(i)-1]);
            }

        }

        out.println("Case #" + caseNum + ": " + printSolution(empty[k-1]));
    }

    private static long printSolution(long value) {
        return value < Integer.MAX_VALUE ? value : -1;
    }

    private static long[][] fw(int n, List<Integer> roadStarts, List<Integer> roadEnds, List<Integer> roadLenghts) {

//        System.out.println("cities: " + n);
//        System.out.println("roadstarts: " + roadStarts);
//        System.out.println("roadends: " + roadEnds);
//        System.out.println("roadlens: " + roadLenghts);

        long[][] result = new long[n][];
        for (int i = 0; i < n; i++) {
            result[i] = new long[n];
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }

//        System.out.println("empty map");
//        printGrid(result, n );

        for (int i = 0; i < n; i++) {
            result[i][i] = 0;
        }

//        System.out.println("diagonal map");
//        printGrid(result, n );

        for (int i = 0; i < roadStarts.size(); i++) {
            result[roadStarts.get(i)-1][roadEnds.get(i)-1] =
                    Math.min(result[roadStarts.get(i)-1][roadEnds.get(i)-1], roadLenghts.get(i));
            result[roadEnds.get(i)-1][roadStarts.get(i)-1] =
                    Math.min(result[roadStarts.get(i)-1][roadEnds.get(i)-1], roadLenghts.get(i));
        }
//        System.out.println("edge map");
//        printGrid(result, n );

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(result[i][k] != Integer.MAX_VALUE && result[k][j] != Integer.MAX_VALUE &&
                            result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }

        return result;

    }

    public static void printGrid(long[][] a, int n)
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
