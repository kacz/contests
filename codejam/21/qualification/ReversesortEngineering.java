import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReversesortEngineering {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int testCase = 1; testCase <= t; ++testCase) {
            int nums = in.nextInt();
            int cost = in.nextInt();
            if (cost < nums -1 || cost > nums*(nums+1)/2-1) {
                System.out.println("Case #" + testCase + ": IMPOSSIBLE");
            } else {
                List<Integer> array = new ArrayList<>();
                array.add(nums);
                int costLeft = cost;
                for (int numsLeft = nums-1; numsLeft > 0; numsLeft--) {
                    if (costLeft > numsLeft)  {
                        if(costLeft - numsLeft > array.size()) {
                            array.add(0, numsLeft);
                            reverse(array, 0, array.size()-1);
                            costLeft -= array.size();
                        } else {
                            array.add(0, numsLeft);
                            reverse(array, 0, costLeft - numsLeft);
                            costLeft -= costLeft - numsLeft + 1;
                        }
                    } else {
                        array.add(0, numsLeft);
                        costLeft--;
                    }
                }


                System.out.print("Case #" + testCase + ":");
                for (int i = 0; i < array.size(); i++) {
                    System.out.print(" " + array.get(i));
                }
                System.out.println();
//                reversesort(new ArrayList<Integer>(array), cost);
            }
        }
    }

    private static void reversesort(ArrayList<Integer> array, int cost) {
        int result = 0;

        for (int i = 1; i < array.size(); i++) {
            int j = array.indexOf(i);
            result += j - i + 2;
//                System.out.println("step " + i + ": " + (j - i + 2));
            reverse(array, i - 1, j);
        }
        System.out.println("requested cost: " + cost + "actual cost: " + result);
    }

    private static void reverse(List<Integer> array, int from, int to) {
        while (from < to) {
            int tmp = array.get(to);
            array.set(to, array.get(from));
            array.set(from,tmp);
            from++;
            to--;
        }
    }
}