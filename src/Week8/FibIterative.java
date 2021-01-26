package Week8;

import java.util.ArrayList;

public class FibIterative {
    // Array to store the results of different test iterations.
    private static ArrayList<Long> results = new ArrayList<>();

    /* Dynamic Programming Approach to calculating the
     * Fibonacci Sequence.
     */
    public static void fib(int N)
    {
        int num1 = 0, num2 = 1;

        int counter = 0;

        // Iterate till counter is N
        while (counter < N) {
            // Swap
            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
            counter = counter + 1;
        }
    }

    // Add the timing results to Internal ArrayList
    public static void addToResults(long result) {
        results.add(result);
    }

    // Calculate the average of all the Timings.
    public static long averageResult() {
        long average = 0;
        for (Long result : results) {
            average += result;
        }
        return (average / results.size());
    }
}
