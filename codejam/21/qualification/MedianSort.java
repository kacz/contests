import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MedianSort {
    private static Scanner in;
//    private static PrintStream debug;
    public static void main(String[] args) throws FileNotFoundException {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        debug = new PrintStream(new FileOutputStream("debug"));
        int t = in.nextInt();
        int n = in.nextInt();
        int q = in.nextInt();
        for (int testCase = 1; testCase <= t; ++testCase) {
            System.out.println("1 2 3");
            int median = in.nextInt();
            List<Integer> array;
//            debug.println("first median: " + median);
            switch (median) {
                case 1: array = new ArrayList<>(Arrays.asList(2, 1, 3)); break;
                case 2: array = new ArrayList<>(Arrays.asList(1, 2, 3)); break;
                case 3: array = new ArrayList<>(Arrays.asList(2, 3, 1)); break;
                default: return;
            }
            for (int i = 4; i <= n; i++) {
                int startpos = -1;
                int endpos = array.size();
                add(array, i, startpos, endpos);
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                if(i > 0) sb.append(' ');
                sb.append(array.get(i));
            }
            System.out.println(sb.toString());
//            debug.println("solution: " + sb.toString());

            int result = in.nextInt();
            if (result != 1) {
                return;
            }

        }
    }

    private static void add(List<Integer> array, int num, int startpos, int endpos) {
        int diff = endpos - startpos;
        if (diff == 1) {
            array.add(endpos, num);
            return;
        } else {
            int q1pos = startpos + diff / 3;
            int q2pos = startpos + (2*diff) / 3;

            if(q1pos == -1 && q2pos == 0) {
                q1pos = 0;
                q2pos = 1;
            }
            if(q2pos == array.size()) {
                array.add(num);
                return;
            }

            int q1 = array.get(q1pos);
            int q2 = array.get(q2pos);

            System.out.println(q1 + " " + q2 + " " + num);
//            debug.println("question: " + q1 + " " + q2 + " " + num);
            int median = in.nextInt();
//            debug.println("answer: " + median);

            if(median == q1) {
                add(array, num, startpos, q1pos);
            } else if(median == q2) {
                add(array, num, q2pos, endpos);
            } else {
                add(array, num, q1pos, q2pos);
            }
        }
    }

}