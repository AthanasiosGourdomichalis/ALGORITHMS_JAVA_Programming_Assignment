import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.File;
public class main {
    public static int generate(int n, int[] P, int[] C, Random rand) {
            int sumC = 0;
            //initializing the arrays
            for (int i = 0; i < n; i++) {
                P[i] = rand.nextInt((n / 2) + 1) + n;
                C[i] = rand.nextInt((n / 2) + 1) + n;
                sumC += C[i];
            }

            //computing W
            int minW = (int) (0.4 * sumC);
            int maxW = (int) (0.7 * sumC);
            int W = rand.nextInt((maxW - minW) + 1) + minW;

            //Correction if some C[i] > W 
            for (int i = 0; i < n; i++) {
                if (C[i] > W) {
                    C[i] = rand.nextInt(W - n + 1) + n;
                }
            }

        return W; // return W to main
        }                           
    public static void wait(int n) {//it is not needed for the greedy algorithm because it allocates very little memory space
        if (n >= 500) { // for large n values 
            System.gc();//The garbage collector runs to release the memory allocated by the arrays
            try {
                Thread.sleep(300);//Thread.sleep() is executed to allow time for garbage collection.
            } catch (InterruptedException e) {
            }
        }
    }
    public static void main(String[] args) {
        long maxMem = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        System.out.println("=== JVM Active Memory: " + maxMem + " MB ===");
    
        if (maxMem < 2400) {
        System.out.println("The dynamic programming algorithms require over 2.4 GB of memory");
        }
        int[] n_array = {20, 50, 100, 250, 500, 800};
        Random rand = new Random();
        File directory = new File("../data");//Creation of the data directory for storing .txt files; the .. path segment places it in the root folder rather than src
        if (!directory.exists()) {
            directory.mkdir(); 
        }
        for (int n : n_array) {
            String filename = "../data/results_for_" + n + "_portions.txt";//Output .txt filename for each n
            try {
                FileWriter myWriter = new FileWriter(filename);//file creation
                System.out.println("\n==================================");
                myWriter.write("==================================\n");
                System.out.println("Results for n = " + n);
                myWriter.write("Results for n = " + n + "\n");
                System.out.println("==================================");
                myWriter.write("==================================\n");
                long sumTime1 = 0;
                long sumTime2 = 0;
                long sumTime3 = 0;
                double sumAvg = 0;
                for (int i = 1; i <= 10; i++) {
                    System.out.println("----------------------------------");
                    myWriter.write("----------------------------------\n");
                    System.out.println("Instance: " + i);
                    myWriter.write("Instance: " + i +"\n");
                    System.out.println("----------------------------------");
                    myWriter.write("----------------------------------\n");
                    // Arrays creation
                    int[] P = new int[n];
                    int[] C = new int[n];
                    long start;//timestamp before the algorithm execution
                    long end;//timestamp after the algorithm execution
                    int W = generate(n, P, C, rand);
                    
                    //Algorithm 1 execution
                    start = System.currentTimeMillis();
                    int[] res1 = Algorithm_1.findProtein(W, P, C);
                    end = System.currentTimeMillis();
                    sumTime1 = sumTime1 + (end - start);//computing execution time
                    wait(n);
                    System.out.println("Dynamic Algorithm 1: Protein= " + res1[0] + ", Calories= " + res1[1]);
                    myWriter.write("Dynamic Algorithm 1: Protein= " + res1[0] + ", Calories= " + res1[1]+"\n");

                    //Algorithm 2 execution
                    start = System.currentTimeMillis();
                    int[] res2 = Algorithm_2.findProtein(W, P, C);
                    end = System.currentTimeMillis();
                    wait(n);
                    sumTime2 = sumTime2 + (end - start);//computing execution time
                    System.out.println("Dynamic Algorithm 2: Protein= " + res2[0] + ", Calories= " + res2[1]);
                    myWriter.write("Dynamic Algorithm 2: Protein= " + res2[0] + ", Calories= " + res2[1]+"\n");

                    //Algorithm 3 execution
                    start = System.currentTimeMillis();
                    int[] res3 = Algorithm_3.findProtein(W, P, C);
                    end = System.currentTimeMillis();
                    sumTime3 = sumTime3 + (end - start);//computing execution time
                    System.out.println("Greedy Algorithm 3: Protein= " + res3[0] + ", Calories= " + res3[1]);
                    myWriter.write("Greedy Algorithm 3: Protein= " + res3[0] + ", Calories= " + res3[1]+"\n");

                    sumAvg += (double) res3[0] / res1[0];//fraction SOL(I)/OPT(I)
            
                
                }
                long avgTime1 = sumTime1/10;//average execution time for Algorithm 1
                long avgTime2 = sumTime2/10;//average execution time for Algorithm 2
                long avgTime3 = sumTime3/10;//average execution time for Algorithm 3
                
                System.out.println("\n==================================");
                myWriter.write("==================================\n");
                System.out.println("Execution time for each algorithm");
                myWriter.write("Execution time for each algorithm\n");
                System.out.println("==================================");
                myWriter.write("==================================\n");
                System.out.println("Average execution time Dynamic Algorithm 1: " + avgTime1 + " ms");
                myWriter.write("Average execution time Dynamic Algorithm 1: " + avgTime1 + " ms\n");
                System.out.println("Average execution time Dynamic Algorithm 2: " + avgTime2 + " ms");
                myWriter.write("Average execution time Dynamic Algorithm 2: " + avgTime2 + " ms\n");
                System.out.println("Average execution time Greedy Algorithm 3: " + avgTime3 + " ms");
                myWriter.write("Average execution time Greedy Algorithm 3: " + avgTime3 + " ms\n");
                System.out.println("\n==================================");
                myWriter.write("==================================\n");
                System.out.println("Average approximation ratio");
                myWriter.write("Average approximation ratio\n");
                System.out.printf("(SOL/OPT) =  %.4f\n", sumAvg/10);
                myWriter.write(String.format("(SOL/OPT) = %.4f\n", sumAvg / 10));
                System.out.println("==================================");
                myWriter.write("==================================\n");
                myWriter.close();//File closing
            }catch (IOException e) {
                System.out.println("File I/O exception occured.");
                e.printStackTrace();
            }
        }
    }
}