import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reversesort {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int testCase = 1; testCase <= t; ++testCase) {
            int nums = in.nextInt();
            List<Integer> array = new ArrayList<>(nums);
            for (int i = 0; i < nums; i++) {
                array.add(in.nextInt());
            }

            int result = 0;
            for (int i = 1; i < nums; i++) {
                int j = array.indexOf(i);
                result += j - i + 2;
//                System.out.println("step " + i + ": " + (j - i + 2));
                reverse(array, i-1, j);
            }
            System.out.println("Case #" + testCase + ": " + result);
        }
    }

    private static void reverse(List<Integer> array, int from, int to) {
        while (from < to) {
            int tmp = array.get(to);
            array.set(to, array.get(from));
            array.set(from,tmp);
            from++;
            to--;
        }
    }
}