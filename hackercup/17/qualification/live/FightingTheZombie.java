package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;

public class FightingTheZombie {

    static final DecimalFormat df = new DecimalFormat("0.000000");
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }

    private static void solve(int caseNum, Scanner in, PrintStream out) {

        int h = in.nextInt();
        int s = in.nextInt();
        double maxProb = 0;
        for (int i = 0; i < s; ++i) {
            String dice = in.next("\\d+d\\d+((\\+|\\-)\\d+)?");
//            System.out.println("dice is: " + dice);
            int x, y, z = 0;
            if (dice.contains("-") || dice.contains("+")) {
                String[] strings = dice.split("(\\-|\\+)");
                z = Integer.valueOf(strings[1]);
                if (dice.contains("-")) {
                    z *= -1;
                }
                dice = strings[0];
            }
            String[] strings = dice.split("d");
            x = Integer.valueOf(strings[0]);
            y = Integer.valueOf(strings[1]);
//            System.out.println("x=" + x + " y=" + y + " z=" + z);

            double prob = probability(x, y, h - z);
            maxProb = Math.max(prob, maxProb);
        }

        out.println("Case #" + caseNum + ": " + df.format(maxProb));
    }

    private static double probability(int dice, int side, int sum) {
        double s = 0;
        for(int i = sum; i <= dice*side;++i) {
            double j = 0;
            for (int k = 0; k <= (i - dice) / side; ++k) {
                double x = k % 2 == 1 ? -1 : 1;
                x *= choose(dice, k);
                x *= choose(i - side * k - 1, dice - 1);
                j += x;
            }
//            System.out.println("j of rolling " + i + " with " + dice + " pieces of " + side + "-sided dice is " + j);
            s += j;
        }
        double p = s / Math.pow(side,dice);
//        System.out.println("p of rolling at least " + sum + " with " + dice + " pieces of " + side + "-sided dice is " + p);
        return p;
    }

    public static double choose(int x, int y) {
        if (y < 0 || y > x) return 0;
        if (y > x / 2) {
            y = x - y;
        }

        double answer = 1.0;
        for (int i = 1; i <= y; i++) {
            answer *= (x + 1 - i);
            answer /= i;
        }
        return answer;
    }

}
