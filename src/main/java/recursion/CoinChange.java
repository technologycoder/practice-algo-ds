package main.java.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CoinChange {

    /** Demonstrates both coin-use rules and the minimum-coin question. */
    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 4};
        int amount = 5;
        System.out.println("Ways, each coin once: " + countWaysEachCoinOnce(coins, amount));
        System.out.println("Ways, unlimited reuse: " + countWaysUnlimited(coins, amount));
        System.out.println("Ways, memoized: " + countWaysUnlimitedMemoized(coins, amount));
        System.out.println("Combinations, each coin once: " + listCombinationsEachCoinOnce(coins, amount));
        System.out.println("Combinations, unlimited reuse: " + listCombinationsUnlimited(coins, amount));
        System.out.println("Minimum coins, unlimited reuse: " + minCoinsUnlimited(coins, amount));
        System.out.println("Minimum coins, memoized: " + minCoinsUnlimitedMemoized(coins, amount));
    }

    /** Counts combinations when each denomination can be selected at most once. */
    public static long countWaysEachCoinOnce(int[] coins, int amount) {
        validate(coins, amount);
        return countOnce(coins, amount, 0);
    }

    /** Counts unordered combinations when each denomination can be reused. */
    public static long countWaysUnlimited(int[] coins, int amount) {
        validate(coins, amount);
        return countUnlimited(coins, amount, 0);
    }

    /** Counts reusable-coin combinations while caching each (index, amount) state. */
    public static long countWaysUnlimitedMemoized(int[] coins, int amount) {
        validate(coins, amount);
        long[][] memo = new long[coins.length][amount + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1L);
        }
        return countUnlimited(coins, amount, 0, memo);
    }

    /** Lists each combination that uses each denomination at most once. */
    public static List<List<Integer>> listCombinationsEachCoinOnce(int[] coins, int amount) {
        validate(coins, amount);
        List<List<Integer>> results = new ArrayList<>();
        collectOnce(coins, amount, 0, new ArrayList<>(), results);
        return results;
    }

    /** Lists unordered combinations when each denomination can be reused. */
    public static List<List<Integer>> listCombinationsUnlimited(int[] coins, int amount) {
        validate(coins, amount);
        List<List<Integer>> results = new ArrayList<>();
        collectUnlimited(coins, amount, 0, new ArrayList<>(), results);
        return results;
    }

    /** Returns the fewest coins with unlimited reuse, or -1 if the amount is impossible. */
    public static int minCoinsUnlimited(int[] coins, int amount) {
        validate(coins, amount);
        return minimum(coins, amount, 0);
    }

    private static long countOnce(int[] coins, int remaining, int index) {
        if (remaining == 0) {
            return 1L;
        }
        if (remaining < 0 || index == coins.length) {
            return 0L;
        }
        return Math.addExact(
                countOnce(coins, remaining - coins[index], index + 1),
                countOnce(coins, remaining, index + 1));
    }

    private static long countUnlimited(int[] coins, int remaining, int index) {
        return countUnlimited(coins, remaining, index, null);
    }

    private static long countUnlimited(int[] coins, int remaining, int index, long[][] memo) {
        if (remaining == 0) {
            return 1L;
        }
        if (remaining < 0 || index == coins.length) {
            return 0L;
        }
        if (memo != null && memo[index][remaining] != -1L) {
            return memo[index][remaining];
        }

        long result = Math.addExact(
                countUnlimited(coins, remaining - coins[index], index, memo),
                countUnlimited(coins, remaining, index + 1, memo));
        if (memo != null) {
            memo[index][remaining] = result;
        }
        return result;
    }

    private static void collectOnce(int[] coins, int remaining, int index,
                                    List<Integer> selected, List<List<Integer>> results) {
        if (remaining == 0) {
            results.add(new ArrayList<>(selected));
            return;
        }
        if (remaining < 0 || index == coins.length) {
            return;
        }

        selected.add(coins[index]);
        collectOnce(coins, remaining - coins[index], index + 1, selected, results);
        selected.remove(selected.size() - 1);
        collectOnce(coins, remaining, index + 1, selected, results);
    }

    private static void collectUnlimited(int[] coins, int remaining, int index,
                                         List<Integer> selected, List<List<Integer>> results) {
        if (remaining == 0) {
            results.add(new ArrayList<>(selected));
            return;
        }
        if (remaining < 0 || index == coins.length) {
            return;
        }

        selected.add(coins[index]);
        collectUnlimited(coins, remaining - coins[index], index, selected, results);
        selected.remove(selected.size() - 1);
        collectUnlimited(coins, remaining, index + 1, selected, results);
    }

    /** Returns the fewest reusable coins while caching each (index, amount) state. */
    public static int minCoinsUnlimitedMemoized(int[] coins, int amount) {
        validate(coins, amount);
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -2); // -2 is uncomputed; -1 is a computed impossible result.
        }
        return minimum(coins, amount, 0, memo);
    }

    private static int minimum(int[] coins, int remaining, int index) {
        return minimum(coins, remaining, index, null);
    }

    private static int minimum(int[] coins, int remaining, int index, int[][] memo) {
        if (remaining == 0) {
            return 0;
        }
        if (index == coins.length) {
            return -1;
        }
        if (memo != null && memo[index][remaining] != -2) {
            return memo[index][remaining];
        }

        int take = -1;
        if (coins[index] <= remaining) {
            int smaller = minimum(coins, remaining - coins[index], index, memo);
            if (smaller >= 0) {
                take = smaller + 1;
            }
        }
        int skip = minimum(coins, remaining, index + 1, memo);
        int result;
        if (take == -1) {
            result = skip;
        } else if (skip == -1) {
            result = take;
        } else {
            result = Math.min(take, skip);
        }
        if (memo != null) {
            memo[index][remaining] = result;
        }
        return result;
    }

    private static void validate(int[] coins, int amount) {
        Objects.requireNonNull(coins, "coins");
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be nonnegative");
        }
        Set<Integer> seen = new HashSet<>();
        for (int coin : coins) {
            if (coin <= 0 || !seen.add(coin)) {
                throw new IllegalArgumentException("coins must be positive and distinct");
            }
        }
    }
}
