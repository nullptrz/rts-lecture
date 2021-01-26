package Week8;

import java.util.ArrayList;

public class FibDynamicProgramming {
    // Array to store the results of different test iterations.
    private static ArrayList<Long> results = new ArrayList<>();

    /* Dynamic Programming Approach to calculating the
     * Fibonacci Sequence.
     */
    public static int fib(int n)
    {

        // Declare an array to store
        // Fibonacci numbers.
        // 1 extra to handle case, n = 0
        int f[] = new int[n + 2];

        int i;

        // 0th and 1st number of
        // the series are 0 and 1
        f[0] = 0;
        f[1] = 1;

        for (i = 2; i <= n; i++) {

            // Add the previous 2 numbers
            // in the series and store it
            f[i] = f[i - 1] + f[i - 2];
        }

        // Nth Fibonacci Number
        return f[n];
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
