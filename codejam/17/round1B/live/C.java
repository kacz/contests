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

        int n = in.nextInt();
        int q = in.nextInt();

        int[] e = new int[n];
        int[] s = new int[n];

        for (int i = 0; i < n; i++) {
            e[i] = in.nextInt();
            s[i] = in.nextInt();
        }

        int[][] map = new int[n][];

        for (int i = 0; i < n; i++) {
            map[i] = new int[n];
            for (int j = 0; j < n; j++) {
                map[i][j] = in.nextInt();
            }
        }

        int[] u = new int[q];
        int[] v = new int[q];

        for (int i = 0; i < q; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        List<Horse> horses = new LinkedList<>();

        double minTime = 0;
        for (int i = 0; i < n-1; i++) {
            if(!horses.isEmpty()) {
                minTime = horses.get(0).time;
                for (Horse h: horses) {
                    if(h.time < minTime) {
                        minTime = h.time;
                    }
                }
            }
            horses.add(new Horse(e[i],s[i],minTime));
            List<Horse> toremove = new LinkedList<>();
            for(Horse h: horses) {
                if(h.dist >= map[i][i+1]) {
                    h.dist -= map[i][i+1];
                    h.time += (double)map[i][i+1] / h.speed;
                } else {
                    toremove.add(h);
                }
            }
            horses.removeAll(toremove);
        }

        minTime = horses.get(0).time;
        for (Horse h: horses) {
            if(h.time < minTime) {
                minTime = h.time;
            }
        }

        out.println("Case #" + caseNum + ": " + minTime);
    }


    private static class Horse {
        int speed;
        int dist;
        double time;

        public Horse(int dist, int speed, double time) {
            this.dist = dist;
            this.speed = speed;
            this.time = time;
        }
    }
}
