package main.java.recursion;

import java.util.Objects;

public class BinarySearch {

    /** Searches the same sorted array with a loop and with recursion. */
    public static void main(String[] args) {
        int[] numbers = {2, 5, 8, 12, 16, 23};
        System.out.println("Iterative: " + binarySearchIterative(numbers, 12));
        System.out.println("Recursive: " + binarySearchRecursive(numbers, 12));
    }

    /**
     * Returns a matching index, or -1 if absent. Input must be sorted ascending.
     * With duplicates, any matching index is valid. O(log n) time, O(1) space.
     */
    public static int binarySearchIterative(int[] numbers, int target) {
        Objects.requireNonNull(numbers, "numbers");
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (numbers[middle] == target) {
                return middle;
            }
            if (target < numbers[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    /** Same contract as the iterative version; O(log n) time and stack space. */
    public static int binarySearchRecursive(int[] numbers, int target) {
        Objects.requireNonNull(numbers, "numbers");
        return search(numbers, target, 0, numbers.length - 1);
    }

    private static int search(int[] numbers, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = left + (right - left) / 2;
        if (numbers[middle] == target) {
            return middle;
        }
        if (target < numbers[middle]) {
            return search(numbers, target, left, middle - 1);
        }
        return search(numbers, target, middle + 1, right);
    }
}
