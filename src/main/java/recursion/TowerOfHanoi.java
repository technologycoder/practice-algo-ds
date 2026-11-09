package main.java.recursion;

public class TowerOfHanoi {

    /** Demonstrates moving three disks from peg A to peg C. */
    public static void main(String[] args) {
        hanoi(3, 'A', 'C', 'B');
    }

    /**
     * Prints the moves to transfer {@code disks} disks from source to destination.
     * Peg labels must be distinct and disks must be nonnegative.
     * O(2^n) time and O(n) recursive stack space.
     */
    public static void hanoi(int disks, char source, char destination, char spare) {
        if (disks < 0) {
            throw new IllegalArgumentException("disks must be nonnegative");
        }
        if (source == destination || source == spare || destination == spare) {
            throw new IllegalArgumentException("peg labels must be distinct");
        }

        moveDisks(disks, source, destination, spare);
    }

    private static void moveDisks(int disks, char source, char destination, char spare) {
        if (disks == 0) {
            return;
        }

        moveDisks(disks - 1, source, spare, destination);
        System.out.println("Move disk " + disks + " from " + source + " to " + destination);
        moveDisks(disks - 1, spare, destination, source);
    }
}
