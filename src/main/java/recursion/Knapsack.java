package main.java.recursion;

import java.util.Arrays;
import java.util.Objects;

public class Knapsack {

    /** Compares 0/1, unbounded, and fractional knapsack on one example. */
    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        System.out.println("Each item once, recursive: " + knapsackRecursive(weights, values, capacity));
        System.out.println("Each item once, memoized: " + knapsackMemoized(weights, values, capacity));
        System.out.println("Unlimited reuse: "
                + knapsackRecursiveDuplicatesAllowed(weights, values, capacity));
        System.out.println("Fractional, greedy: "
                + FractionalKnapsack.fractionalKnapsackGreedy(weights, values, capacity));
    }

    /** Returns the greatest value when each item can be selected at most once. */
    public static long knapsackRecursive(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        return bestValueOnce(weights, values, capacity, 0);
    }

    /** Caches the 0/1 result for each (item index, remaining capacity) state. */
    public static long knapsackMemoized(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        long[][] memo = new long[weights.length][capacity + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1L);
        }
        return bestValueOnce(weights, values, capacity, 0, memo);
    }

    /** Returns the greatest value when each item can be reused without limit. */
    public static long knapsackRecursiveDuplicatesAllowed(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        return bestValueReusable(weights, values, capacity, 0);
    }

    private static long bestValueOnce(int[] weights, int[] values, int remaining, int index) {
        return bestValueOnce(weights, values, remaining, index, null);
    }

    private static long bestValueOnce(int[] weights, int[] values, int remaining, int index, long[][] memo) {
        if (remaining == 0 || index == weights.length) {
            return 0L;
        }
        if (memo != null && memo[index][remaining] != -1L) {
            return memo[index][remaining];
        }

        long skip = bestValueOnce(weights, values, remaining, index + 1, memo);
        long take = 0L;
        if (weights[index] <= remaining) {
            take = values[index] + bestValueOnce(weights, values, remaining - weights[index], index + 1, memo);
        }
        long result = Math.max(take, skip);
        if (memo != null) {
            memo[index][remaining] = result;
        }
        return result;
    }

    private static long bestValueReusable(int[] weights, int[] values, int remaining, int index) {
        if (remaining == 0 || index == weights.length) {
            return 0L;
        }

        long skip = bestValueReusable(weights, values, remaining, index + 1);
        long take = 0L;
        if (weights[index] <= remaining) {
            take = values[index]
                    + bestValueReusable(weights, values, remaining - weights[index], index);
        }
        return Math.max(take, skip);
    }

    private static void validate(int[] weights, int[] values, int capacity) {
        Objects.requireNonNull(weights, "weights");
        Objects.requireNonNull(values, "values");
        if (capacity < 0 || weights.length != values.length) {
            throw new IllegalArgumentException("capacity must be nonnegative and arrays must have equal lengths");
        }
        for (int index = 0; index < weights.length; index++) {
            if (weights[index] <= 0 || values[index] < 0) {
                throw new IllegalArgumentException("weights must be positive and values nonnegative");
            }
        }
    }
}
