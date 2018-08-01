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

        int r = in.nextInt();
        int c = in.nextInt();
        in.nextLine();
        byte[][] input = new byte[r][];
        for (int i = 0; i < r; ++i) {
            input[i] = in.nextLine().getBytes();
        }

//        Set<Byte> used = new HashSet<>();

//        System.out.println("input:");
//        printtable(input, System.out);

        byte[][] output = new byte[r][];
        for (int i = 0; i < r; ++i) {
            output[i] = input[i].clone();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (input[i][j] != '-') {
                    findMaxAndFill(i, j, input, output);
                }
            }
        }


//        System.out.println("output:");
//        printtable(output, System.out);

        out.println("Case #" + caseNum + ":");
        printtable(output, out);

    }

    private static void printtable(byte[][] output, PrintStream out) {
        for (int y = 0; y < output.length; y++) {
            for (int x = 0; x < output[0].length; x++) {
                out.print((char) output[y][x]);
            }
            out.println();
        }
    }

    private static void findMaxAndFill(int i, int j, byte[][] input, byte[][] output) {
        int height = input.length;
        int width = input[0].length;
        int minX = j;
        int minY = i;
        int maxX = width - 1;
        int maxY = i;
        byte c = ' ';

        for (int x = j; x < width; x++) {
            if (input[i][x] == '-') {
                maxX = x - 1;
                break;
            }
            if (output[i][x] != '?' && c != ' ') {
                maxX = x - 1;
                break;
            }
            if (output[i][x] != '?') {
                c = output[i][x];
            }
        }


        outer:
        for (int y = i + 1; y < height; y++) {
            boolean newColor = false;
            inner:
            for (int x = j; x < maxX + 1; x++) {
                if (output[y][x] != '?' && c != ' ') {
                    if (newColor) {
                        maxX = x - 1;
                        break inner;
                    }
                    break outer;
                }
                if (output[y][x] != '?') {
                    c = output[y][x];
                    newColor = true;
                }
            }
            maxY = y;
        }

        fill(output, minX, minY, maxX, maxY, c);
        fill(input, minX, minY, maxX, maxY, (byte) '-');

    }

    private static void fill(byte[][] table, int minX, int minY, int maxX, int maxY, byte c) {
        for (int y = minY; y <= maxY; ++y) {
            for (int x = minX; x <= maxX; ++x) {
                {
                    table[y][x] = c;
                }
            }
        }
    }

}
