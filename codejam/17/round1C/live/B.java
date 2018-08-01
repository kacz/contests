import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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
        out.println("Case #" + caseNum + ": ");
    }
}
