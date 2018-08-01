import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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
        int n = in.nextInt();
        int k = in.nextInt();

        List<Pancake> pancakes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int r = in.nextInt();
            int h = in.nextInt();

            pancakes.add(new Pancake(r,h));
        }

        pancakes.sort(new Comparator<Pancake>() {
            @Override
            public int compare(Pancake o1, Pancake o2) {
                if(o1.side > o2.side) {
                    return -1;
                }
                if(o2.side > o1.side) {
                    return 1;
                }
                return 0;
            }
        });

        double surface = 0;
        for (int i = 0; i < n; i++) {
            Pancake bottom = pancakes.get(i);

            List<Pancake> deck = new ArrayList<>();
            Collections.copy(pancakes,deck);
            deck.remove(bottom);

            double newSurface = computeSurface(deck,r,k-1);
            newSurface += bottom.side + bottom.surface;
            if(newSurface > surface) {
                surface = newSurface;
            }
        }

        double exposed = surface * Math.PI;

        out.println("Case #" + caseNum + ": " + exposed);
    }

    private static double computeSurface(List<Pancake> pancakes, int start, int k) {
        double surface = 0;
        for (int i = start; i < start + k; i++) {
            surface += pancakes.get(i).side;
        }
        return surface;
    }

    public static class Pancake {
        long r;
        double surface;
        double side;

        public Pancake(long r,long h) {
            this.r = r;
            this.surface = r*r;
            this.side = 2*r*h;
        }
    }


}
