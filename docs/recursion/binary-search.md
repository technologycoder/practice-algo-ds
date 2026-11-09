# Binary Search

**Problem:** Find a target in an array sorted in ascending order. Return its index, or `-1` if absent. With duplicates, any matching index is valid.

**Recursive idea:** Compare the target with the middle element. Search only the left or right half, excluding the middle. Stop when `left > right`: the search interval is empty.

**Inputs:** Non-null sorted array; sorting is the caller's responsibility. Bounds are inclusive, starting at `0` and `length - 1`.

| Array | Target | Expected result |
| --- | --- | --- |
| `[]` | 5 | -1 |
| `[5]` | 5 | 0 |
| `[2, 5, 8, 12, 16, 23]` | 12 | 3 |
| `[2, 5, 8, 12, 16, 23]` | 24 | -1 |
| `[2, 5, 5, 8]` | 5 | 1 or 2 |

**Understand:**

- [ ] Trace the search for a present and an absent target.
- [ ] Explain why the initial right bound is `length - 1`.
- [ ] Explain why `middle - 1` and `middle + 1` guarantee progress.
- [ ] Explain why `left + (right - left) / 2` avoids overflowing the sum of the bounds.
- [ ] Explain why memoization would not help this single search.

**Implementation comparison** — [BinarySearch.java](../../src/main/java/recursion/BinarySearch.java):

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| Iterative | `binarySearchIterative` | Update the bounds in a loop | O(log n) | O(1) |
| Recursive | `binarySearchRecursive` | Recurse into one half | O(log n) | O(log n) stack |
