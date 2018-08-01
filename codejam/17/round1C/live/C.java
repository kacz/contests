import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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
        int k = in.nextInt();

        List<Pancake> pancakes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int r = in.nextInt();
            int h = in.nextInt();

            pancakes.add(new Pancake(r,r*r,2*r*h));
        }

        pancakes.sort(new Comparator<Pancake>() {
            @Override
            public int compare(Pancake o1, Pancake o2) {
                if(o1.r > o2.r) {
                    return 1;
                }
                if(o2.r > o1.r) {
                    return -1;
                }
                return 0;
            }
        });

        long surface = 0;
        for (int i = 0; i < n-k; i++) {
            long newSurface = computeSurface(pancakes,i,k);
            if(newSurface > surface) {
                surface = newSurface;
            }
        }

        double exposed = surface * Math.PI;

        out.println("Case #" + caseNum + ": " + exposed);
    }

    private static long computeSurface(List<Pancake> pancakes, int start, int k) {
        long surface = pancakes.get(start).surface;
        for (int i = start; i < start + k; i++) {
            surface += pancakes.get(i).side;
        }
        return surface;
    }

    public static class Pancake {
        int r;
        long surface;
        long side;

        public Pancake(int r,long surface, long side) {
            this.surface = surface;
            this.side = side;
        }
    }


}
