import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoonsAndUmbrellas {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int testCase = 1; testCase <= t; ++testCase) {
            int x = in.nextInt();
            int y = in.nextInt();
            String mural = in.next();
            int result = 0;
            for (int before = 0; before < mural.length() - 1; ) {
                int after = before+1;
                while (mural.charAt(after) == '?' && after < mural.length()-1) {
                    after++;
                }
                // i is the last char before ?s or 0 if o=?
                // iter is the first char after ?s or last if last=?
                if(mural.charAt(before) != '?' && mural.charAt(after) != '?') {
                    result += minimize(mural.charAt(before), mural.charAt(after), after-before-1,x,y);
                } else if(mural.charAt(before) == '?' && mural.charAt(after) != '?') {
                    int cost1 = minimize('C', mural.charAt(after), after-before-1,x,y);
                    int cost2 = minimize('J', mural.charAt(after), after-before-1,x,y);
                    result += Math.min(cost1,cost2);
                } else if(mural.charAt(before) != '?' && mural.charAt(after) == '?') {
                    int cost1 = minimize(mural.charAt(before), 'C', after-before-1,x,y);
                    int cost2 = minimize(mural.charAt(before), 'J', after-before-1,x,y);
                    result += Math.min(cost1,cost2);
                } else {
                    int cost = minimize('C', 'C', after-before-1,x,y);
                    cost = Math.min(cost, minimize('C', 'J', after-before-1,x,y));
                    cost = Math.min(cost, minimize('J', 'C', after-before-1,x,y));
                    cost = Math.min(cost, minimize('J', 'J', after-before-1,x,y));
                    result += cost;
                }
                before = after;
            }
            System.out.println("Case #" + testCase + ": " + result);
        }
    }

    private static int minimize(char before, char after, int length, int x, int y) {
        if (length == 0) {
            if (before == 'C' && after == 'J') {
                return x;
            }
            if(before == 'J' && after == 'C') {
                return y;
            }
            return 0;
        }
        if(before == after) {
            if(x+y < 0) {
                return (x+y)*(length/2+1);
            } else {
                return 0;
            }
        }
        if(before == 'C') {
            return x +minimize('J', 'J', length-1,x,y);
        } else {
            return y + minimize('C', 'C', length-1,x,y);
        }
    }
}