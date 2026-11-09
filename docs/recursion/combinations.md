# Combinations

**Problem:** Given distinct characters, print every subset, including the empty subset. A combination ignores order: choosing A and B gives `[AB]` once, not also `[BA]`.

**Recursive idea:** For each character, choose it or skip it. Stop when there are no characters left to consider.

**Example for `ABC`:**

```text
[ABC]
[AB]
[AC]
[A]
[BC]
[B]
[C]
[]
```

**Inputs:** The implementations assume the input characters are distinct.

| Input | Expected result |
| --- | --- |
| `""` | One empty subset: `[]` |
| `"A"` | `[A]`, `[]` |
| `"ABC"` | 8 subsets |

**Understand:**

- [ ] Trace the choose and skip calls for `AB`.
- [ ] Explain why the empty subset is included.
- [ ] Explain why choosing A then B is the same combination as choosing B then A, so it is printed once.
- [ ] In `combination(prefix, str)`, explain what the prefix and remaining string represent.
- [ ] In `printCombinations`, explain why the `StringBuilder` is shortened before exploring the skip branch.

**Implementation comparison** — [Combinations.java](../../src/main/java/recursion/Combinations.java):

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| Prefix | `combination` | Pass chosen characters and remaining input as strings | O(n * 2^n), including output | O(n^2) live string data |
| Backtracking | `printCombinations` | Mutate and restore one `StringBuilder` | O(n * 2^n), including output | O(n) stack and builder |

The prefix version is direct to trace, but creates new strings at each call. The backtracking version reuses a mutable builder, so each branch must undo its change before the next branch.
