package main.java.recursion;

import java.util.Arrays;

public class Fibonacci {

    public static void main(String[] args) {
        int num = 10;
        System.out.println("Iterative Fibonacci of " + num + " is: " + fibonacciIterative(num));
        System.out.println("Recursive Fibonacci of " + num + " is: " + fibonacciRecursive(num));
        System.out.println("Memoized Fibonacci of " + num + " is: " + fibonacciMemoized(num));
    }

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

    // Use small inputs: repeated calculations make this version exponential.
    static long fibonacciRecursive(int num) {
        if (num < 0 || num > 92) {
            throw new IllegalArgumentException("num must be between 0 and 92");
        }

        if (num <= 1) {
            return num;
        }

        return fibonacciRecursive(num - 1) + fibonacciRecursive(num - 2);
    }

    static long fibonacciMemoized(int num) {
        if (num < 0 || num > 92) {
            throw new IllegalArgumentException("num must be between 0 and 92");
        }

        long[] memo = new long[num + 1];
        Arrays.fill(memo, -1L); // -1 means not computed; 0 is a valid result.
        return fibonacciMemoized(num, memo);
    }

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
