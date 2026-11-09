package main.java.recursion;

import java.util.Arrays;
import java.util.Objects;

public class FractionalKnapsack {

    /** Maximizes value by taking items in descending value/weight order, including a fraction if needed. */
    public static double fractionalKnapsackGreedy(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        Integer[] order = new Integer[weights.length];
        for (int index = 0; index < order.length; index++) {
            order[index] = index;
        }
        Arrays.sort(order, (left, right) -> Double.compare(
                (double) values[right] / weights[right],
                (double) values[left] / weights[left]));

        int remaining = capacity;
        double totalValue = 0.0;
        for (int index : order) {
            if (remaining == 0) {
                break;
            }
            int weightTaken = Math.min(remaining, weights[index]);
            totalValue += (double) values[index] * weightTaken / weights[index];
            remaining -= weightTaken;
        }
        return totalValue;
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
