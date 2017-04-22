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
        int d = in.nextInt();
        int n = in.nextInt();

        int[] k = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            k[i] = in.nextInt();
            s[i] = in.nextInt();
        }

        double maxT = 0;
        for (int i = 0; i < n; i++) {
            double t = (d - k[i]) / (double) s[i];
            if (t > maxT) {
                maxT = t;
            }
        }

        double result = d / maxT;

        out.println("Case #" + caseNum + ": " + result);
    }
}
