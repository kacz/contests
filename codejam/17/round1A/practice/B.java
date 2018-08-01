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

        int n = in.nextInt();
        int p = in.nextInt();

        List<Integer> r = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            r.add(in.nextInt());
        }

        List<LinkedList<Pack>> q = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            LinkedList<Pack> qi = new LinkedList<>();
            for (int j = 0; j < p; j++) {
                Pack pack = new Pack(in.nextInt(), r.get(i));
                if(pack.max < pack.min) {
                    continue;
                }
                qi.add(pack);
            }

            qi.sort((o1, o2) -> o1.size < o2.size ? -1 : o1.size > o2.size ? 1 : 0);
//            for(Pack pack: qi) {
//                System.out.println("Pack size:" + pack.size + " req:" + r.get(i) + " min:" + pack.min + " max:" + pack.max);
//            }
//            System.out.println();
            q.add(qi);
        }

        boolean finish = false;
        int servings = 1;
        int kits = 0;

        for(List<Pack> ingredient: q) {
            if(ingredient.isEmpty()) {
                out.println("Case #" + caseNum + ": " + kits);
                return;
            }
        }

        outer:
        while(!finish) {
            for(LinkedList<Pack> ingredient: q) {
                //remove packs too small
                while(ingredient.get(0).max < servings) {
                    ingredient.pop();
                    if(ingredient.isEmpty()) {
                        finish=true;
                        break outer;
                    }
                }

                //increase servings size if packs are too big
                if(ingredient.get(0).min > servings) {
                    servings = ingredient.get(0).min;
                    continue outer;
                }
            }

            //if we got here, a new kit can be created. lets remove the packs from the queues
            kits++;
            for(LinkedList<Pack> ingredient: q) {
                ingredient.pop();
                if(ingredient.isEmpty()) {
                    finish = true;
                    break outer;
                }
            }
        }

        out.println("Case #" + caseNum + ": " + kits);


    }

    private static class Pack {
        int min, max, size;

        public Pack(int size, int required) {
            this.size = size;
            max = (int) Math.floor((10*size) / (required * 9));
            min = (int) Math.ceil(size / (required * 1.1));
        }
    }
}
