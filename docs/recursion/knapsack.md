# Knapsack

**Problem:** Each item has a weight and value. Choose items with the greatest total value without exceeding capacity.

**Recursive idea:** For each item, compare skipping it with taking it if it fits. Stop when capacity is zero or no items remain.

The key difference between the two versions is the index in the take branch:

- **0/1 knapsack:** take moves to the next item, so each item is used at most once.
- **Unbounded knapsack:** take stays at the same index, so the item can be reused. Positive weights ensure capacity decreases and recursion terminates.

**Fractional knapsack** allows taking part of an item. Sort by value/weight from highest to lowest and take as much as possible from each item. This greedy strategy is optimal for fractional knapsack; it does not guarantee the 0/1 optimum.

**Inputs:** Equal-length weight/value arrays, positive weights, nonnegative values, and nonnegative capacity. Invalid values throw `IllegalArgumentException`; null arrays throw `NullPointerException`. The recursive methods return `long`; fractional knapsack returns `double`. Uncached recursion is intended for small inputs. The memoized method caches repeated 0/1 states.

| Weights | Values | Capacity | 0/1 result | Reusable result | Fractional result |
| --- | --- | --- | --- | --- | --- |
| `[10, 20, 30]` | `[60, 100, 120]` | 50 | 220 | 300 | 240 |
| `[1, 3, 4, 5]` | `[1, 4, 5, 7]` | 7 | 9 | 9 | 9 |
| `[2]` | `[5]` | 4 | 5 | 10 | 10 |
| `[5]` | `[10]` | 3 | 0 | 0 | 6 |
| `[1, 3]` | `[2, 4]` | 0 | 0 | 0 | 0 |
| `[]` | `[]` | 7 | 0 | 0 | 0 |

**Understand:**

- [ ] Draw take/skip calls for a one-item input.
- [ ] Explain why both branches advance the index in 0/1 knapsack.
- [ ] Explain why only the take branch keeps the same index in unbounded knapsack.
- [ ] Predict how reusing the item changes the result for weight `[2]`, value `[5]`, capacity 4.
- [ ] Explain why sorting by value/weight ratio cannot guarantee the 0/1 optimum.
- [ ] Explain why sorting by value/weight is optimal for fractional knapsack.

**Implementation comparison:** [Knapsack.java](../../src/main/java/recursion/Knapsack.java) and [FractionalKnapsack.java](../../src/main/java/recursion/FractionalKnapsack.java).

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| 0/1 recursive | `knapsackRecursive` | Take and skip; both advance to the next item | O(2^n) upper bound | O(n) stack |
| 0/1 memoized | `knapsackMemoized` | Cache each item and remaining capacity | O(n * (W + 1)) | O(n * (W + 1)) cache and stack |
| Unbounded recursive | `knapsackRecursiveDuplicatesAllowed` | Take keeps the index; skip advances | Exponential upper bound | O(n + W / w) stack |
| Fractional greedy | `FractionalKnapsack.fractionalKnapsackGreedy` | Sort by value/weight; take full or partial items | O(n log n) | O(n) index array |

Here `n` is the number of items, `W` is capacity, and `w` is the smallest item weight when the array is nonempty. The unbounded version has a loose time bound of O(2^(n + W / w)). The memoized 0/1 method caches the same take/skip recurrence. The unbounded version remains uncached so the index rule is easy to compare.
