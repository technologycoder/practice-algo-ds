package main.java.recursion;

public class Permutations {

    /** Prints every ordering of ABC. */
    public static void main(String[] args) {
        System.out.println("Prefix and remaining string:");
        permute("", "ABC");
        System.out.println("Swap and backtrack:");
        printPermutations("ABC");
    }

    /** Prints each ordering by adding one remaining character to the prefix. */
    public static void permute(String prefix, String str) {
        if (str.isEmpty()) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
        }
    }

    /**
     * Prints every ordering of the distinct characters in {@code input}.
     * Runs in O(n * n!) time including output.
     */
    public static void printPermutations(String input) {
        permute(input.toCharArray(), 0);
    }

    private static void permute(char[] characters, int index) {
        if (index == characters.length) {
            System.out.println(new String(characters));
            return;
        }

        for (int i = index; i < characters.length; i++) {
            swap(characters, index, i);
            permute(characters, index + 1);
            swap(characters, index, i);
        }
    }

    private static void swap(char[] characters, int first, int second) {
        char temp = characters[first];
        characters[first] = characters[second];
        characters[second] = temp;
    }
}
