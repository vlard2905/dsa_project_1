
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        HTSeparatedChains<String, String> hashtable = new HTSeparatedChains<>();
        Scanner sc = new Scanner(System.in);
        //String[] allKeys = new String[10_000_000];
        ArrayList<String> allKeys = new ArrayList<>();
        int operation = 0;
        while (operation != 6) {
            System.out.println("If you want to insert one element press \'1\' \n" +
                    "If you want to search the element press \'2\' \n" +
                    "If you want to delete the element press \'3\' \n" +
                    "If you want to insert random #yourcount elements press \'4\' \n" +
                    "If you want to print all the elements press \'5\' \n");
            operation = Integer.parseInt(sc.nextLine());
            switch (operation) {
                case 1 -> {
                    //System.out.println("Enter string of key: ");
                    String inputKey = sc.nextLine();
                    //System.out.println("Enter string of data: ");
                    String inputData = sc.nextLine();
                    hashtable.insert(inputKey, inputData);
                }
                case 3 -> {
                    long inputDelete = sc.nextLong();
                    sc.nextLine();
                    long startTime2 = System.nanoTime();
                    for (int i = 0; i < inputDelete; i++) {
                        hashtable.delete(allKeys.get(i));
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");
                }
                case 2 -> {
                    long inputDelete = sc.nextLong();
                    sc.nextLine();
                    long startTime2 = System.nanoTime();
                    for (int i = 0; i < inputDelete; i++) {
                        hashtable.search(allKeys.get(i));
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");
                }
                case 4 -> {
                    System.out.println("Write count of random insertions you want to do: ");
                    int numStrings = Integer.parseInt(sc.nextLine());
                    Random rand = new Random();
                    int maxStringLength = 20;
                    long startTime2 = System.currentTimeMillis();

                    for (int i = 0; i < numStrings; i++) {
                        String str = generateRandomString(rand, maxStringLength);
                        String str2 = generateRandomString(rand, maxStringLength);
                        allKeys.add(str2);
                        hashtable.insert(str2, str);
                    }

                    long endTime2 = System.currentTimeMillis();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");

                    System.out.println("Finished inserting " + numStrings + " random strings.");
                }
                case 5 -> {
                    hashtable.printHashTable();
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