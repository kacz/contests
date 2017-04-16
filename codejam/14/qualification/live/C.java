package kacz.codejam14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    private static class Table {
        char[] data;
        int sizeX;
        int sizeY;
        Table(int x, int y) {
            data = new char[x*y];
            sizeX = x;
            sizeY = y;
            for(int i = 0; i < x*y;++i) {
                data[i] = '*';
            }
        }
        public char get(int x, int y) {
            return data[(y-1)*sizeX+x-1];
        }
        public void set(int x, int y, char val) {
            data[(y-1)*sizeX+x-1] = val;
        }
    }

    static Scanner in;

    public static void main(String[] args) throws IOException {
	    System.setIn(new FileInputStream("input"));
        System.setOut(new PrintStream("output"));
        in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 1; i <= T; ++i) {
            System.out.println("Case #" + i + ": ");
            solve();
        }
    }

    private static void solve() {
        int R = in.nextInt();
        int C = in.nextInt();
        int M = in.nextInt();
        int size = R*C;
        int clear = size - M;

        int min = Math.min(R,C);
        int max = Math.max(R, C);

        if(min != 1 && (clear == 2 || clear == 3 || clear == 5 || clear == 7)) {
            System.out.println("Impossible");
            return;
        }
        if(min == 2 && clear%2 == 1 && clear != 1) {
            System.out.println("Impossible");
            return;
        }

        Table t = createTable(min,max,M);

        if(C == min) {
            printTable(t);
        } else {
            printTranspose(t);
        }

    }

    private static Table createTable(int min, int max, int m) {
        Table t = new Table(min,max);

        int clear = min*max-m;
        if(clear != 1) {
            if(min == 1) {
                for(int i = 1; i <= clear; ++i) {
                    t.set(1,i,'.');
                }
            } else if(clear < 2*min+2){
                int half = clear / 2;
                for(int i = 1; i <= half; ++i) {
                    t.set(i,1,'.');
                    t.set(i,2,'.');
                }
                if(clear%2 == 1) {
                    t.set(half,1,'*');
                    t.set(half,2,'*');
                    t.set(1,3,'.');
                    t.set(2,3,'.');
                    t.set(3,3,'.');
                }
            } else {
                int row = 1;
                while(clear > min) {
                    for(int i = 1; i <= min; ++i) {
                        t.set(i,row,'.');
                    }
                    clear -= min;
                    ++row;
                }
                if(clear == 1) {
                    t.set(min,row-1,'*');
                    ++clear;
                }
                for(int i = 1; i <= clear; ++i) {
                    t.set(i,row,'.');
                }
            }
        }
        t.set(1,1,'c');
        return t;
    }

    private static void printTranspose(Table t) {
        for(int x = 1; x <= t.sizeX; ++x) {
            for(int y = 1; y <= t.sizeY; ++y) {
                System.out.print(t.get(x,y));
            }
            System.out.println();
        }
    }

    private static void printTable(Table t) {
        for(int y = 1; y <= t.sizeY; ++y) {
            for(int x = 1; x <= t.sizeX; ++x) {
                System.out.print(t.get(x,y));
            }
            System.out.println();
        }
    }
}
