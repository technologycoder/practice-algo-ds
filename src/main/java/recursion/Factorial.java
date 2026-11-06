package main.java.recursion;

public class Factorial {

    /** Demonstrates the same factorial result using all three approaches. */
    public static void main(String[] args) {

        System.out.println("Iterative factorial of 4 is: " + factorialIterative(4));
        System.out.println("Recursive factorial of 4 is: " + factorialRecursive(4));
        System.out.println("Tail-recursive factorial of 4 is: " + factorialTailRecursive(4));
    }

    /**
     * Calculates num! with a running product; 0! and 1! both return 1.
     * Accepts 0-20, rejecting other inputs because factorial must fit in long.
     * O(n) time and O(1) auxiliary space.
     */
    static long factorialIterative(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("num must be between 0 and 20");
        }

        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates num! by multiplying num by the factorial of num - 1.
     * Accepts 0-20. Multiplication remains after the call, so this is not tail recursive.
     * O(n) time and O(n) stack space.
     */
    static long factorialRecursive(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("num must be between 0 and 20");
        }

        if (num <= 1) {
            return 1;
        }

        return num * factorialRecursive(num - 1);
    }

    /**
     * Validates an input from 0-20 and starts tail recursion with a product of 1.
     * O(n) time and O(n) stack space in Java; tail-call elimination is not guaranteed.
     */
    static long factorialTailRecursive(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("num must be between 0 and 20");
        }

        return factorialTail(num, 1L);
    }

    /**
     * Carries the completed product in accumulated; accumulated * num! stays constant.
     * Updates the product before recursing, leaving no calculation after the call.
     */
    private static long factorialTail(int num, long accumulated) {
        if (num <= 1) {
            return accumulated;
        }

        return factorialTail(num - 1, accumulated * num);
    }
}
