package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BeachUmbrellas {

    public static final int MODULO = 1000000007;
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }


    private static void solve(int caseNum, Scanner in, PrintStream out) {

        System.out.println("case: " + caseNum);

        int n = in.nextInt();
        int m = in.nextInt();

        long umbrellaspace = 0;
        ArrayList<Integer> umbrellas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int r = in.nextInt();
            umbrellas.add(r);
            umbrellaspace += 2*r;
        }

        long combinations = combinatins(n-2);
//        System.out.println("combinations = " + combinations);

        long solution = 0;

        if(n == 1) {
            out.println("Case #" + caseNum + ": " + m);
            return;
        }
        //choose the two side umbrellas
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int extraspace = umbrellas.get(i) + umbrellas.get(j);
                int fullspace = m - 1 + extraspace;
//                System.out.println("dividing " + (fullspace - umbrellaspace) + " meters into " + (n+1) + " sections");
                long subsolution = chooseRepeat(fullspace - umbrellaspace, n+1);
//                System.out.println("subsolution = " + subsolution);
                solution += subsolution;
                solution %= MODULO;
            }
        }

        solution *= combinations;
        solution %= MODULO;

        out.println("Case #" + caseNum + ": " + solution);
    }

    private static long chooseRepeat(long n, int k) {
        return choose(n+k-1, k-1);
    }

    public static long choose(long x, long y) {
        if (y < 0 || y > x) return 0;
        if (y > x / 2) {
            y = x - y;
        }

        double answer = 1.0;
        for (int i = 1; i <= y; i++) {
            answer *= (x + 1 - i);
            answer /= i;
            answer %= MODULO;
        }
        return (long) answer;
    }

    private static long combinatins(int i) {
        long result = 2;
        for (int j = 2; j <= i; j++) {
            result *= j;
            result %= MODULO;
        }
        return result;
    }

}
