import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        HTOpenAddressing hashtable = new HTOpenAddressing();
        /*String path = "/home/vladchesnykov/Desktop/STU BA FIIT/DSA_B/Project_1_122474/txtFile.odt";
        WriteFile writeFile = new WriteFile(path, true);
        Random rand1 = new Random();
        int maxStringLength1 = 20;
        for (int i = 0; i < 10000000; i++) {
            String str = generateRandomString(rand1, maxStringLength1);
            writeFile.writeToFile(str);
            writeFile.writeToFile(" ");
        }*/
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        String[] allKeys = new String[3_000_000];
        while (operation != 7) {
            System.out.println("If you want to insert one element press \'1\' \n" +
                    "If you want to search the element press \'2\' \n" +
                    "If you want to delete the element press \'3\' \n" +
                    "If you want to insert random #yourcount elements press \'4\' \n" +
                    "If you want to print all the elements press \'5\' \n" +
                    "If you want to count all the elements press \'6\' \n" +
                    "If you want to stop program press \'7\'\n");
            operation = Integer.parseInt(sc.nextLine());
            switch (operation) {
                case 1 -> {
                    System.out.println("Enter string of key: ");
                    String inputKey = sc.nextLine();
                    System.out.println("Enter string of data: ");
                    String inputData = sc.nextLine();
                    hashtable.insert(inputKey, inputData);
                }
                case 3 -> {
                    long numStrings = sc.nextLong();
                    long startTime2 = System.nanoTime();
                    sc.nextLine();
                    for (int i = 0; i < numStrings; i++) {
                        hashtable.delete(allKeys[i]);
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");
                }
                case 2 -> {
                    long numStrings = sc.nextLong();
                    long startTime2 = System.nanoTime();
                    sc.nextLine();
                    for (int i = 0; i < numStrings; i++) {
                        hashtable.search(allKeys[i]);
                    }
                    long endTime2 = System.nanoTime();
                    double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
                    System.out.println("Elapsed time: " + elapsedTime2 + " seconds");
                }
                case 4 -> {
                    System.out.println("Write count of random insertions you want to do: ");
                    int numStrings = Integer.parseInt(sc.nextLine());
                    long startTime = System.currentTimeMillis();
                    Random rand = new Random();
                    int maxStringLength = 20;

                    for (int i = 0; i < numStrings; i++) {
                        String str = generateRandomString(rand, maxStringLength);
                        String str2 = generateRandomString(rand, maxStringLength);
                        allKeys[i] = str2;
                        hashtable.insert(str2, str);
                    }
                    long endTime = System.currentTimeMillis();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    System.out.println("Elapsed time: " + elapsedTime + " seconds");
                    //System.out.println("Finished inserting " + numStrings + " random strings.");
                }
                case 5 -> {
                    hashtable.printHashTable();
                }
                case 6 -> {
                    System.out.println(hashtable.counter());
                }
                case 7 -> {
                    System.out.println("Program is stopped.");
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