import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int d = in.nextInt();
            String p = in.next();
            int result = solve(d,p);
            if (result == -1) {
                System.out.println("Case #" + i + ": IMPOSSIBLE");
            } else {
                System.out.println("Case #" + i + ": " + result);
            }
        }
    }

    private static int solve(int d, String p) {
        List<Integer> shoots = new ArrayList<>();
        int power = 1;
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == 'C') {
                power *= 2;
            } else {
                shoots.add(power);
            }
        }
        Collections.sort(shoots);
        int sum = shoots.stream().mapToInt(Integer::intValue).sum();
        int counter = 0;
        while(sum > d) {
            int max = shoots.get(shoots.size()-1);
            if(max == 1) {
                return - 1;
            }
            max /= 2;
            shoots.set(shoots.size()-1, max);
            Collections.sort(shoots);
            sum -= max;
            counter ++;
        }
        return counter;
    }
}