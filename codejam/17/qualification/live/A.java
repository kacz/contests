package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class A {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("D:\\dev\\in.txt"));
        PrintStream out = new PrintStream("D:\\dev\\out.txt");
        int cases = in.nextInt();
        in.nextLine();
        for (int i = 0; i < cases; ++i) {
            solve(i + 1, in, out);
        }
    }

    private static void solve(int caseNum, Scanner in, PrintStream out) {
        String[] line = in.nextLine().split(" ");
        char[] pancakes = line[0].toCharArray();
        int l = pancakes.length;
        int k = Integer.valueOf(line[1]);
        int result = 0;
        for(int i = 0; i < l-k+1; ++i) {
            if(pancakes[i] == '-') {
                flip(pancakes,i,k);
                ++result;
            }
        }
        for(int i=0;i<k;++i) {
            if(pancakes[l-i-1] == '-') {
                result = -1;
                break;
            }
        }
        out.print("Case #" + caseNum + ": ");
        if(result == -1) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(result);
        }
    }

    private static void flip(char[] pancakes, int start, int len) {
        for(int i = start; i < start+len; ++i) {
            if(pancakes[i] == '-') {
                pancakes[i] = '+';
            } else {
                pancakes[i] = '-';
            }
        }
    }

}
