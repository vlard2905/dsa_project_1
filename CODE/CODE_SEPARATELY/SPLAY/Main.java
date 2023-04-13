
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        Scanner sc = new Scanner(System.in);
        long[] allKeys = new long[10_000_000];
        int operation = 0;
        while (operation != 6) {
            System.out.println("If you want to insert one element press \'1\' \n" +
                    "If you want to search the #yourcount elements press \'2\' \n" +
                    "If you want to delete the #yourcount element press \'3\' \n" +
                    "If you want to insert random #yourcount elements press \'4\' \n");
            operation = Integer.parseInt(sc.nextLine());
            switch (operation) {
                case 1 -> {
                    System.out.println("Enter long int as key: ");
                    long inputKey = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Enter string as data for key " + inputKey + ": ");
                    String inputData = sc.nextLine();
                    tree.insert(inputKey, inputData);
                }
                case 3 -> {
                    long numStrings = sc.nextLong();
                    sc.nextLine();
                    long startTime2 = System.nanoTime();
                    for (int i = 0; i < numStrings; i++) {
                        tree.delete(allKeys[i]);
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");

                    System.out.println("Finished inserting " + numStrings + " random strings.");
                }
                case 2 -> {
                    long numStrings = sc.nextLong();
                    sc.nextLine();
                    long startTime2 = System.nanoTime();
                    for (int i = 0; i < numStrings; i++) {
                        tree.search(allKeys[i]);
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");

                    System.out.println("Finished inserting " + numStrings + " random strings.");
                }
                case 4 -> {
                    System.out.println("Write count of random insertions you want to do: ");
                    int numStrings = Integer.parseInt(sc.nextLine());
                    Random rand = new Random();
                    int maxStringLength = 20;
                    long startTime2 = System.nanoTime();
                    for (int i = 0; i < numStrings; i++) {
                        String strData = generateRandomString(rand, maxStringLength);
                        long randomKey = Math.round(Math.random() * 100000000);
                        allKeys[i] = randomKey;
                        tree.insert(randomKey, strData);
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");

                    System.out.println("Finished inserting " + numStrings + " random strings.");
                }
            }
        }

    }

    private static String generateRandomString(Random rand, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int length = rand.nextInt(maxLength) + 1;
        for (int i = 0; i < length; i++) {
            char c = (char)(rand.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
}