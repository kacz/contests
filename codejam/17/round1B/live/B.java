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
        int N = in.nextInt();
        int R = in.nextInt();
        int O = in.nextInt();
        int Y = in.nextInt();
        int G = in.nextInt();
        int B = in.nextInt();
        int V = in.nextInt();

        if (V > Y || G > R || O > B) {
            out.println("Case #" + caseNum + ": IMPOSSIBLE");
            return;
        }

        if ((V == Y && N > V + Y && V > 0) ||
                (B == O && N > B + O && B > 0) ||
                (R == G && N > R + G && R > 0)) {
            out.println("Case #" + caseNum + ": IMPOSSIBLE");
            return;
        }

        boolean hasY = Y > 0;
        boolean hasB = B > 0;
        boolean hasR = R > 0;

        StringBuffer stallsYV = new StringBuffer();
        for (int i = 0; i < V; i++) {
            stallsYV.append("YV");
        }
        Y -= V;
        V = 0;


        StringBuffer stallsBO = new StringBuffer();
        for (int i = 0; i < O; i++) {
            stallsBO.append("BO");
        }
        B -= O;
        O = 0;

        StringBuffer stallsRG = new StringBuffer();
        for (int i = 0; i < G; i++) {
            stallsRG.append("RG");
        }
        R -= G;
        G = 0;


        StringBuffer stallsBRY = new StringBuffer();
        boolean lastB = false;
        boolean lastR = false;
        boolean lastY = false;
        char first = Math.max(B, Math.max(R, Y)) == B ? 'B' : Math.max(B, Math.max(R, Y)) == R ? 'R' : 'Y';

        while (B > 0 && R > 0 && Y > 0) {
            int max = Math.max(B, Math.max(R, Y));
            if (max == B && !lastB) {
                stallsBRY.append("B");
                lastB = true;
                lastR = false;
                lastY = false;
                B--;
                continue;
            }
            if (max == R && !lastR) {
                stallsBRY.append("R");
                lastR = true;
                lastB = false;
                lastY = false;
                R--;
                continue;
            }
            if (max == Y && !lastY) {
                stallsBRY.append("Y");
                lastY = true;
                lastR = false;
                lastB = false;
                Y--;
                continue;
            }

            if (lastB) {
                max = Math.max(R, Y);
                if (max == R) {
                    stallsBRY.append("R");
                    lastB = false;
                    lastR = true;
                    R--;
                    continue;
                } else {
                    stallsBRY.append("Y");
                    lastB = false;
                    lastY = true;
                    Y--;
                    continue;
                }
            }

            if (lastR) {
                max = Math.max(B, Y);
                if (max == B) {
                    stallsBRY.append("B");
                    lastR = false;
                    lastB = true;
                    B--;
                    continue;
                } else {
                    stallsBRY.append("Y");
                    lastR = false;
                    lastY = true;
                    Y--;
                    continue;
                }
            }

            if (lastY) {
                max = Math.max(B, R);
                if (max == B) {
                    stallsBRY.append("B");
                    lastY = false;
                    lastB = true;
                    B--;
                    continue;
                } else {
                    stallsBRY.append("R");
                    lastY = false;
                    lastR = true;
                    R--;
                    continue;
                }
            }
        }

        StringBuffer stallsRemain = new StringBuffer();
        if (R > 0 && Y > 0) {
            int min = Math.min(R, Y);
            for (int i = 0; i < min; i++) {
                stallsRemain.append("RY");
            }
            R -= min;
            Y -= min;
            if (R > 0) {
                stallsRemain.append('R');
                R--;
                if (first == 'R') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (Y > 0) {
                stallsRemain.insert(0, 'Y');
                Y--;
                if (first == 'Y') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (first == 'Y') {
                stallsBRY.append(stallsRemain.reverse());
            } else {
                stallsBRY.append(stallsRemain);
            }
        }

        if (B > 0 && Y > 0) {
            int min = Math.min(B, Y);
            for (int i = 0; i < min; i++) {
                stallsRemain.append("BY");
            }
            B -= min;
            Y -= min;
            if (B > 0) {
                stallsRemain.append('B');
                B--;
                if (first == 'B') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (Y > 0) {
                stallsRemain.insert(0, 'Y');
                Y--;
                if (first == 'Y') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (first == 'Y') {
                stallsBRY.append(stallsRemain.reverse());
            } else {
                stallsBRY.append(stallsRemain);
            }
        }

        if (R > 0 && B > 0) {
            int min = Math.min(R, B);
            for (int i = 0; i < min; i++) {
                stallsRemain.append("RB");
            }
            R -= min;
            B -= min;
            if (R > 0) {
                stallsRemain.append('R');
                R--;
                if (first == 'R') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (B > 0) {
                stallsRemain.insert(0, 'B');
                B--;
                if (first == 'B') {
                    out.println("Case #" + caseNum + ": IMPOSSIBLE");
                    return;
                }
            }
            if (first == 'B') {
                stallsBRY.append(stallsRemain.reverse());
            } else {
                stallsBRY.append(stallsRemain);
            }
        }

        if(R>0 || B>0 || Y>0 || G>0 || V>0 || O>0) {
            out.println("Case #" + caseNum + ": IMPOSSIBLE");
            return;
        }

        if (hasB) {
            int pos = stallsBRY.indexOf("B");
            if (pos != -1) {
                stallsBRY.insert(pos, stallsBO);
            } else {
                stallsBRY.append(stallsBO);
            }
        }

        if (hasR) {
            int pos = stallsBRY.indexOf("R");
            if (pos != -1) {
                stallsBRY.insert(pos, stallsRG);
            } else {
                stallsBRY.append(stallsRG);
            }
        }

        if (hasY) {
            int pos = stallsBRY.indexOf("Y");
            if (pos != -1) {
                stallsBRY.insert(pos, stallsYV);
            } else {
                stallsBRY.append(stallsYV);
            }
        }

        out.println("Case #" + caseNum + ": " + stallsBRY);
    }
}
