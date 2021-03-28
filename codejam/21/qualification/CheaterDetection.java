import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CheaterDetection {
    private static Scanner in;
    public static void main(String[] args) throws FileNotFoundException {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        int p = in.nextInt();
        for (int testCase = 1; testCase <= t; ++testCase) {
            List<String> answers = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                answers.add(in.next());
            }

            List<List<Integer>> questionBuckets = new ArrayList<>(10);
            for (int bucket = 0; bucket < 10; bucket++) {
                questionBuckets.add(new ArrayList<>());
            }
            for (int question = 0; question < 10000; question++) {
                int correct = 0;
                for (int player = 0; player < 100; player++) {
                    if(answers.get(player).charAt(question) == '1') {
                        correct++;
                    }
                }
                int bucket = 10;
//                System.out.println("correct: " + correct);
                for (int i = 1; i < 10; i++) {
                    double difficulty = -3 + 0.6*i;
                    double limit = 0;
                    for (int j = 0; j < 10; j++) {
                        double skill = -2.7 + 0.6*j;
                        limit += 1/(1+Math.exp(difficulty-skill));
                    }
                    limit /= 10;
//                    System.out.println("limit:" + limit);
                    if(limit*100 <= correct) {
                        bucket = i;
                        break;
                    }
                }
                questionBuckets.get(bucket-1).add(question);
            }
//            System.out.println("bucket sizes: ");
//            for (int i = 0; i < 10; i++) {
//                System.out.println(questionBuckets.get(i).size());
//            }

            double[][] expectedScores = new double[10][10];
            for (int difficultyI = 0; difficultyI < 10; difficultyI++) {
                for (int skillI = 0; skillI < 10; skillI++) {
                    double difficulty = -2.7 + difficultyI*0.6;
                    double skill = -2.7 + skillI * 0.6;
                    double expected = 1/(1+Math.exp(difficulty-skill));
                    expectedScores[difficultyI][skillI] = expected;
                }
            }
            double[][] expectedCheaterScores = new double[10][10];
            for (int difficultyI = 0; difficultyI < 10; difficultyI++) {
                for (int skillI = 0; skillI < 10; skillI++) {
                    double difficulty = -2.7 + difficultyI*0.6;
                    double skill = -2.7 + skillI * 0.6;
                    double expected = 0.5 + 0.5/(1+Math.exp(difficulty-skill));
                    expectedCheaterScores[difficultyI][skillI] = expected;
                }
            }

            int biggestCheater = 0;
            double biggestCheatEvidence = 0;

            for (int player = 0; player < 100; player++) {
                double[] playerScores = new double[10];
                for (int i = 0; i < 10; i++) {
                    List<Integer> bucket = questionBuckets.get(i);
                    int correct = 0;
                    for(int question: bucket) {
                        if(answers.get(player).charAt(question) == '1') {
                            correct += 1;
                        }
                    }
                    double score = (double) correct / bucket.size();
                    playerScores[i] = score;
                }

                double minDiff = Double.MAX_VALUE;
                int closestSkill = 0;
                for (int skill = 0; skill < 10; skill++) {
                    double diff = 0;
                    for (int difficulty = 0; difficulty < 10; difficulty++) {
                        diff += Math.abs(expectedScores[difficulty][skill] - playerScores[difficulty]);
                    }
                    if(diff < minDiff) {
                        minDiff = diff;
                        closestSkill = skill;
                    }
                }
//                System.out.println("Player " + player +": closest genuine skill: " + closestSkill + " with diff:" + minDiff);

                double minCheatDiff = Double.MAX_VALUE;
                int closestCheatSkill = 0;
                for (int skill = 0; skill < 10; skill++) {
                    double diff = 0;
                    for (int difficulty = 0; difficulty < 10; difficulty++) {
                        diff += Math.abs(expectedCheaterScores[difficulty][skill] - playerScores[difficulty]);
                    }
                    if(diff < minCheatDiff) {
                        minCheatDiff = diff;
                        closestCheatSkill = skill;
                    }
                }
//                System.out.println("Player " + player +": closest cheater skill: " + closestCheatSkill + " with diff:" + minCheatDiff);
                double cheatEvidence = minDiff - minCheatDiff;
                if(cheatEvidence > biggestCheatEvidence) {
                    biggestCheatEvidence = cheatEvidence;
                    biggestCheater = player;
                }

            }
            System.out.println("Case #" + testCase + ": " + (biggestCheater+1));
        }
    }

}