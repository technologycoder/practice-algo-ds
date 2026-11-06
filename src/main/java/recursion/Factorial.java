package main.java.recursion;

public class Factorial {

    public static void main(String[] args) {

        System.out.println("Iterative factorial of 4 is: " + factorialIterative(4));
        System.out.println("Recursive factorial of 4 is: " + factorialRecursive(4));
        System.out.println("Tail-recursive factorial of 4 is: " + factorialTailRecursive(4));
    }

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

    static long factorialRecursive(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("num must be between 0 and 20");
        }

        if (num <= 1) {
            return 1;
        }

        return num * factorialRecursive(num - 1);
    }

    static long factorialTailRecursive(int num) {
        if (num < 0 || num > 20) {
            throw new IllegalArgumentException("num must be between 0 and 20");
        }

        return factorialTail(num, 1L);
    }

    private static long factorialTail(int num, long accumulated) {
        if (num <= 1) {
            return accumulated;
        }

        return factorialTail(num - 1, accumulated * num);
    }
}
