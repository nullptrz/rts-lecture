package Week8;

public class FibBenchmark {
    public static void main(String[] args) {
        // Fibonacci number to be calculated.
        int N = 25;
        int numberOfIterations = 100;

        for (int i = 0; i < numberOfIterations; ++i) {
            long start = System.nanoTime();
            FibDynamicProgramming.fib(N);
            long finish = System.nanoTime();
            FibDynamicProgramming.addToResults(finish-start);
        }

        for (int i = 0; i < numberOfIterations; ++i) {
            long start = System.nanoTime();
            FibRecursive.fib(N);
            long finish = System.nanoTime();
            FibRecursive.addToResults(finish-start);
        }

        for (int i = 0; i < numberOfIterations; ++i) {
            long start = System.nanoTime();
            FibIterative.fib(N);
            long finish = System.nanoTime();
            FibIterative.addToResults(finish-start);
        }



        System.out.println("Average Time Result for Dynamic Programming: " + FibDynamicProgramming.averageResult());
        System.out.println("Average Time Result for Recursive Approach: " + FibRecursive.averageResult());
        System.out.println("Average Time Result for Iterative Approach: " + FibIterative.averageResult());
    }
}
