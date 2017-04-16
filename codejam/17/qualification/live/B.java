package kacz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class B {

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
        char[] line = in.nextLine().toCharArray();

        int lastChange = -1;

        if(line.length > 1) {
            for (int i = line.length - 2; i >= 0; --i) {
                if(line[i] > line[i+1]) {
                    --line[i];
                    lastChange = i;
                }
            }

            if(lastChange != -1) {
                for (int i = lastChange + 1; i < line.length; ++i) {
                    line[i] = '9';
                }
            }

            if(line[0] == '0') {
                line = Arrays.copyOfRange(line, 1, line.length);
            }
        }

        out.print("Case #" + caseNum + ": ");
        out.println(line);
    }

}
