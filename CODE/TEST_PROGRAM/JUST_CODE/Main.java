
import java.util.Scanner;
import java.util.Random;

public class Main {
    static long startTime, endTime, counter, startTimeofAll, endTimeofAll;
    static int local;
    static double time, timeofAll;
    public static void main(String[] args) {
        int operation = 0;
        long[] allKeys = new long[10_000_000];
        String[] allStrKeys = new String[10_000_000];
        int maxStringLength = 20;
        Scanner sc = new Scanner(System.in);
        while (operation != 5) {
            System.out.println("""
                    | TEST SPLAY TREE: '1' |
                    | TEST AVL TREE: '2' |
                    | TEST HASH TABLE WITH OPEN ADDRESSING: '3' |
                    | TEST HASH TABLE WITH SEPARATED CHAINS: '4' |
                    """);
            System.out.println();
            operation = Integer.parseInt(sc.nextLine());
            switch (operation) {
                case 1 -> {
                    startTimeofAll = System.nanoTime();
                    SplayTree splayTree = new SplayTree();
                    System.out.println("FIRST SCENARIO IN ACTION (INSERT->DELETE)");
                    startTime = System.nanoTime();
                    System.out.println("INSERT");
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        long randomKey = Math.round(Math.random() * 100_000_000);
                        allKeys[i] = randomKey;
                        splayTree.insert(randomKey, randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    counter = 0;
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    for (int j = 0; j < 1_000_000; j++) {
                        splayTree.delete(allKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    splayTree.clearTree();
                    System.out.println();
                    System.out.println("SECOND SCENARIO IN ACTION (INSERT->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        splayTree.insert(allKeys[i], randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    boolean existance = false;
                    for(int i = 0; i < 1_000_000; i++) {
                        existance = splayTree.search(allKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    splayTree.clearTree();
                    System.out.println();
                    System.out.println("THIRD SCENARIO IN ACTION (INSERT->DELETE->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        splayTree.insert(allKeys[i], randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 100_000; i++) {
                        splayTree.delete(allKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 100_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    existance = false;
                    for(int i = 0; i < 100_000; i++) {
                        existance = splayTree.search(allKeys[local]);
                        local += 7;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 100_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    endTimeofAll = System.nanoTime();
                    timeofAll = ((double)endTimeofAll - startTimeofAll)/1_000_000_000;
                    System.out.println();
                    System.out.println("All testing takes " + timeofAll + " seconds");
                    System.out.println("TRY ANOTHER ONE!");
                }
                case 2 -> {
                    AVLTree avlTree = new AVLTree();
                    System.out.println("FIRST SCENARIO IN ACTION (INSERT->DELETE)");
                    startTime = System.nanoTime();
                    System.out.println("INSERT");
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        long randomKey = Math.round(Math.random() * 100_000_000);
                        allKeys[i] = randomKey;
                        avlTree.insert(randomKey, randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    counter = 0;
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    for (int j = 0; j < 100_000; j++) {
                        avlTree.delete(allKeys[local]);
                        counter++;
                        local += 10;
                        if (counter == 1_000 || counter == 10_000 || counter == 100_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    avlTree.clearTree();
                    System.out.println();
                    System.out.println("SECOND SCENARIO IN ACTION (INSERT->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        avlTree.insert(allKeys[i], randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    for(int i = 0; i < 10_000; i++) {
                        avlTree.search(allKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 5_000 || counter == 10_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    avlTree.clearTree();
                    System.out.println();
                    System.out.println("THIRD SCENARIO IN ACTION (INSERT->DELETE->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString = generateRandomString(rand, maxStringLength);
                        avlTree.insert(allKeys[i], randomString);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 1_000_000; i++) {
                        avlTree.delete(allKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for(int i = 0; i < 10_000; i++) {
                        avlTree.search(allKeys[local]);
                        local += 7;
                        counter++;
                        if (counter == 1_000 || counter == 5_000 || counter == 10_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("TRY ANOTHER ONE!");
               }
                case 3 -> {
                    HTOpenAddressing htOpenAddressing = new HTOpenAddressing();
                    System.out.println("FIRST SCENARIO IN ACTION (INSERT->DELETE)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 1_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htOpenAddressing.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int j = 0; j < 10_000; j++) {
                        htOpenAddressing.delete(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 100 || counter == 1_000 || counter == 10_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println();
                    htOpenAddressing = new HTOpenAddressing();
                    System.out.println("SECOND SCENARIO IN ACTION (INSERT->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 1_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htOpenAddressing.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    for(int i = 0; i < 100_000; i++) {
                        htOpenAddressing.search(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 100_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }

                    }
                    System.out.println();
                    htOpenAddressing = new HTOpenAddressing();
                    System.out.println("THIRD SCENARIO IN ACTION (INSERT->DELETE->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 1_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htOpenAddressing.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int j = 0; j < 10_000; j++) {
                        htOpenAddressing.delete(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 100 || counter == 1_000 || counter == 10_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    for(int i = 0; i < 100_000; i++) {
                        htOpenAddressing.search(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 100_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                }
                case 4 -> {
                    HTSeparatedChains<String,String> htSeparatedChains = new HTSeparatedChains<>();
                    System.out.println("FIRST SCENARIO IN ACTION (INSERT->DELETE)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htSeparatedChains.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int j = 0; j < 50_000; j++) {
                        htSeparatedChains.delete(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 50_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println();
                    htSeparatedChains = new HTSeparatedChains<>();
                    System.out.println("SECOND SCENARIO IN ACTION (INSERT->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htSeparatedChains.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 100_000 || counter == 1_000_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    for(int i = 0; i < 1_000_000; i++) {
                        htSeparatedChains.search(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }

                    }
                    System.out.println();
                    htSeparatedChains = new HTSeparatedChains<>();
                    System.out.println("THIRD SCENARIO IN ACTION (INSERT->DELETE->SEARCH)");
                    System.out.println("INSERT");
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int i = 0; i < 10_000_000; i++) {
                        Random rand = new Random();
                        String randomString1 = generateRandomString(rand, maxStringLength);
                        allStrKeys[i] = randomString1;
                        String randomString2 = generateRandomString(rand, maxStringLength);
                        htSeparatedChains.insert(randomString1, randomString2);
                        counter++;
                        if (counter == 100_000 || counter == 1_000_000 || counter == 10_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Inserting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("DELETE");
                    local = 0;
                    startTime = System.nanoTime();
                    counter = 0;
                    for (int j = 0; j < 50_000; j++) {
                        htSeparatedChains.delete(allStrKeys[local]);
                        local += 10;
                        counter++;
                        if (counter == 1_000 || counter == 10_000 || counter == 50_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Deleting " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                    System.out.println("SEARCH");
                    local = 0;
                    counter = 0;
                    startTime = System.nanoTime();
                    for(int i = 0; i < 1_000_000; i++) {
                        htSeparatedChains.search(allStrKeys[local]);
                        local += 7;
                        counter++;
                        if (counter == 10_000 || counter == 100_000 || counter == 1_000_000) {
                            endTime = System.nanoTime();
                            time = ((double)endTime - startTime)/1_000_000_000;
                            System.out.println("Searching " + counter + " elements taking " + time + " seconds.");
                        }
                    }
                }
            }
        }
    }
    private static String generateRandomString(Random rand, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int length = rand.nextInt(maxLength) + 1;
        for (int i = 0; i < length; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
}