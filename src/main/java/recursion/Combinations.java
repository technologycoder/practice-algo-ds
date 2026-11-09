package main.java.recursion;

public class Combinations {

    /** Prints every subset of ABC, including the empty subset. */
    public static void main(String[] args) {
        System.out.println("Prefix and remaining string:");
        combination("", "ABC");
        System.out.println("StringBuilder backtracking:");
        printCombinations("ABC");
    }

    /** Prints each subset by carrying the chosen prefix and remaining characters. */
    public static void combination(String prefix, String str) {
        if (str.isEmpty()) {
            System.out.println("[" + prefix + "]");
            return;
        }

        combination(prefix + str.charAt(0), str.substring(1));
        combination(prefix, str.substring(1));
    }

    /**
     * Prints every subset of the distinct characters in {@code input}.
     * Includes the empty subset; runs in O(n * 2^n) time including output.
     */
    public static void printCombinations(String input) {
        choose(input, 0, new StringBuilder());
    }

    private static void choose(String input, int index, StringBuilder selected) {
        if (index == input.length()) {
            System.out.println("[" + selected + "]");
            return;
        }

        selected.append(input.charAt(index));
        choose(input, index + 1, selected);
        selected.deleteCharAt(selected.length() - 1);
        choose(input, index + 1, selected);
    }
}
