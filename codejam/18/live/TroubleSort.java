import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            List<Integer> even = new ArrayList<>();
            List<Integer> odd = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int v = in.nextInt();
                if(j%2 == 1) {
                    odd.add(v);
                } else {
                    even.add(v);
                }
            }
            Collections.sort(even);
            Collections.sort(odd);
            int result = -1;
            for (int j = 0; j < even.size(); j++) {
                if(j>0 && even.get(j) < odd.get(j-1)) {
                    result = j*2-1;
                    break;
                }
                if(odd.size() > j && even.get(j) > odd.get(j)) {
                    result = j*2;
                    break;
                }
            }
            if (result == -1) {
                System.out.println("Case #" + i + ": OK");
            } else {
                System.out.println("Case #" + i + ": " + result);
            }
        }
    }
}