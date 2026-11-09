# Coin Change

**Problem:** With distinct positive denominations, count the combinations for an amount, list those combinations, and find the fewest coins when denominations can be reused.

**Recursive idea:** At each denomination, take it or skip it. The key difference is the next index:

- If each denomination can be used **once**, both take and skip advance to the next index.
- If a denomination can be **reused**, take stays at the same index; skip advances.

Stop with one solution when the remaining amount is zero. Stop with no solution when it becomes negative or no denominations remain.

**Inputs:** Distinct positive denominations and a nonnegative amount. Invalid values throw `IllegalArgumentException`; null throws `NullPointerException`. Counts use `long` and throw `ArithmeticException` if they overflow. The plain recursive methods are intended for small inputs. Memoized methods cache repeated states while keeping the same recursive choices.

| Coins | Amount | Ways, once | Ways, reusable | Fewest reusable |
| --- | --- | --- | --- | --- |
| `[1, 2, 3, 4]` | 5 | 2 | 6 | 2 |
| `[1, 3, 4]` | 6 | 0 | 4 | 2 |
| `[2]` | 3 | 0 | 0 | -1 |
| `[]` | 0 | 1 | 1 | 0 |
| `[]` | 3 | 0 | 0 | -1 |

For `[1, 2, 3, 4]` and amount 5, the six reusable combinations are `[1,1,1,1,1]`, `[1,1,1,2]`, `[1,1,3]`, `[1,2,2]`, `[1,4]`, and `[2,3]`.

**Understand:**

- [ ] Trace take/skip calls for coins `[1, 2]` and amount 3.
- [ ] Explain why `[1, 2]` and `[2, 1]` count as one combination.
- [ ] Explain how the take branch's index prevents reuse in the once version and allows reuse in the unlimited version.
- [ ] Explain why each listed solution is copied before recursion backtracks and removes a coin.
- [ ] Explain why taking the largest coin first fails for `[1, 3, 4]`, amount 6: taking 4 needs two more 1s, while 3 + 3 uses fewer coins.
- [ ] Distinguish zero ways from an impossible minimum (`-1`).

**Implementation comparison** — [CoinChange.java](../../src/main/java/recursion/CoinChange.java):

| Method | Question | Recursive choices | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| `countWaysEachCoinOnce` | Count combinations, once | Take next coin or skip to next | O(2^k) | O(k) stack |
| `countWaysUnlimited` | Count combinations, reusable | Take same coin or skip to next | Exponential in `k + A / c` | O(k + A / c) stack |
| `countWaysUnlimitedMemoized` | Count combinations, reusable | Cache each index and amount | O(k * (A + 1)) | O(k * (A + 1)) cache plus stack |
| `listCombinationsEachCoinOnce` | List combinations, once | Take next coin or skip to next | Proportional to search plus output | O(k) stack, plus output |
| `listCombinationsUnlimited` | List combinations, reusable | Take same coin or skip to next | Proportional to search plus output | O(k + A / c) stack, plus output |
| `minCoinsUnlimited` | Minimum count, reusable | Take same coin or skip to next | Exponential in `k + A / c` | O(k + A / c) stack |
| `minCoinsUnlimitedMemoized` | Minimum count, reusable | Cache each index and amount | O(k * (A + 1)) | O(k * (A + 1)) cache plus stack |

Here `k` is the number of denominations, `A` is the amount, and `c` is the smallest denomination when coins are present. The take branch reduces the amount; the skip branch reduces the number of denominations. The memoized methods return the same results as their uncached counterparts but avoid solving each reachable state repeatedly. They are recursive with caching (top-down dynamic programming), not bottom-up table-filling.
