package main.java.recursion;

import java.util.Arrays;

public class Fibonacci {

    /** Demonstrates the same Fibonacci result using all three approaches. */
    public static void main(String[] args) {
        int num = 10;
        System.out.println("Iterative Fibonacci of " + num + " is: " + fibonacciIterative(num));
        System.out.println("Recursive Fibonacci of " + num + " is: " + fibonacciRecursive(num));
        System.out.println("Memoized Fibonacci of " + num + " is: " + fibonacciMemoized(num));
    }

    /**
     * Calculates F(num), with F(0) = 0 and F(1) = 1, keeping the previous two values.
     * Accepts 0-92, the range whose Fibonacci results fit in long.
     * O(n) time and O(1) auxiliary space.
     */
    static long fibonacciIterative(int num) {
        if (num < 0 || num > 92) {
            throw new IllegalArgumentException("num must be between 0 and 92");
        }

        if (num <= 1) {
            return num;
        }

        long previous = 0;
        long current = 1;
        for (int i = 2; i <= num; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    /**
     * Calculates F(num) directly from F(num - 1) + F(num - 2), without caching.
     * Accepts 0-92, but use small inputs (about 30 or less) because work repeats.
     * O(2^n) time upper bound and O(n) stack space; addition makes it non-tail-recursive.
     */
    static long fibonacciRecursive(int num) {
        if (num < 0 || num > 92) {
            throw new IllegalArgumentException("num must be between 0 and 92");
        }

        if (num <= 1) {
            return num;
        }

        return fibonacciRecursive(num - 1) + fibonacciRecursive(num - 2);
    }

    /**
     * Calculates F(num) using recursion and a fresh cache shared by that call's helpers.
     * Accepts 0-92. Each non-base result is computed once; separate calls start fresh.
     * O(n) time and O(n) auxiliary space for the cache and recursion stack.
     */
    static long fibonacciMemoized(int num) {
        if (num < 0 || num > 92) {
            throw new IllegalArgumentException("num must be between 0 and 92");
        }

        long[] memo = new long[num + 1];
        Arrays.fill(memo, -1L); // -1 means not computed; 0 is a valid result.
        return fibonacciMemoized(num, memo);
    }

    /**
     * Reuses cached results; -1 marks entries not yet computed.
     * Addition and cache assignment follow the recursive calls, so this is not tail recursive.
     */
    private static long fibonacciMemoized(int num, long[] memo) {
        if (num <= 1) {
            return num;
        }

        if (memo[num] != -1L) {
            return memo[num];
        }

        memo[num] = fibonacciMemoized(num - 1, memo) + fibonacciMemoized(num - 2, memo);
        return memo[num];
    }
}
