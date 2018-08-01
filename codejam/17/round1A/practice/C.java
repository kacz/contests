import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class C {

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

        long result = 0;

        long hd = in.nextLong();
        long ad = in.nextLong();
        long hk = in.nextLong();
        long ak = in.nextLong();
        long b = in.nextLong();
        long d = in.nextLong();

        // if knight is too strong:

        if (ak -d>hd) {}

        if (2*ak - 3*d >hd){
            out.println("Case #" + caseNum + ": IMPOSSIBLE");
        }

        optimizeAttacks(hk,ad,b);

        out.println("Case #" + caseNum + ": " + result);
    }

    private static void optimizeAttacks(long health, long attack, long buff) {
        n+health/(attack+n*buff)
    }
}
