import java.util.ArrayList;

public class Recursive {
    // Function to print the fibonacci series
    static int fib(int n)
    {
        // Base Case
        if (n <= 1)
            return n;

        // Recursive call
        return fib(n - 1)
                + fib(n - 2);
    }

    // Driver Code
    public static void main(String args[])
    {
        // Given Number N
        int N = 10;
        ArrayList<Long> results = new ArrayList<>();

        for (int i = 0; i < 20; ++i) {
            Long start = System.nanoTime();
            fib(N);
            Long finish = System.nanoTime();
            results.add(finish-start);
        }
        long timeElapsed;
        long temp = 0;
        for (int i = 0; i < results.size(); ++i) {
            temp += results.get(i);
        }
        timeElapsed = temp / results.size();
        System.out.println("Time Elapsed: " + timeElapsed);
    }

}
