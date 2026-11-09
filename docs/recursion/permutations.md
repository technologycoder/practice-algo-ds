# Permutations

**Problem:** Given distinct characters, print every possible ordering. Changing the order creates a different permutation.

**Recursive idea:** Choose one remaining character for the next position, then recursively arrange the rest. Stop when no characters remain.

**Example for `ABC`:**

```text
ABC
ACB
BAC
BCA
CBA
CAB
```

**Inputs:** The implementations assume the input characters are distinct.

| Input | Expected number of permutations |
| --- | --- |
| `""` | 1 (the empty permutation) |
| `"A"` | 1 |
| `"ABC"` | 6 |

**Understand:**

- [ ] Trace the recursive calls for `AB`.
- [ ] Explain why `AB` and `BA` are different permutations.
- [ ] In `permute(prefix, str)`, explain how choosing each character for the next position produces every ordering.
- [ ] In `printPermutations`, explain why the array must be restored after each recursive call.
- [ ] Predict how many permutations are printed for `n` distinct characters.

**Implementation comparison** — [Permutations.java](../../src/main/java/recursion/Permutations.java):

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| Prefix | `permute` | Add a character to the prefix and remove it from the remaining string | O(n * n!), including output | O(n^2) live string data |
| Backtracking | `printPermutations` | Swap each candidate into place, recurse, then undo the swap | O(n * n!), including output | O(n) array and stack |

The prefix version is direct to trace, but creates new strings and substrings at each call. The swap version reuses one character array, so each recursive branch must restore its swap before trying another choice.
