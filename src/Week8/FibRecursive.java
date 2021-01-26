package Week8;

import java.util.ArrayList;

public class FibRecursive {
    // Array to store the results of different test iterations.
    private static ArrayList<Long> results = new ArrayList<>();

    /* Recursive Approach to calculating the
     * Fibonacci Sequence.
     */
    public static int fib(int n)
    {
        // Base Case
        if (n <= 1)
            return n;

        // Recursive call
        return fib(n - 1)
                + fib(n - 2);
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
