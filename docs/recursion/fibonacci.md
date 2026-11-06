# Fibonacci

**Problem:** Return the nth Fibonacci number, using F(0) = 0 and F(1) = 1. Every later value is the sum of the previous two.

**Sequence:** 0, 1, 1, 2, 3, 5, 8, 13, …

**Recursive idea:** For n ≥ 2, calculate F(n - 1) and F(n - 2), then add them. Return n directly when n is 0 or 1.

```text
F(4) = F(3) + F(2)
F(3) = F(2) + F(1)
F(2) = F(1) + F(0)
```

Notice that F(2) is requested more than once. This repeated work makes the uncached recursive version slow.

Keep “not computed” distinguishable from the valid cached result F(0) = 0.

All three methods should accept 0 through 92 and reject other inputs with IllegalArgumentException; 92 is the largest index whose result fits in long. Start the uncached recursive version with n ≤ 30; larger valid inputs become impractical.

| Input | Expected result |
| --- | --- |
| 0 | 0 |
| 1 | 1 |
| 2 | 1 |
| 4 | 3 |
| 7 | 13 |
| 10 | 55 |
| 92, optimized versions | 7540113804746346429 |
| -1 or 93 | IllegalArgumentException |

**Understand:**

- [ ] Why two base cases are needed.
- [ ] Draw the complete call tree for F(4): nine total calls, but at most four active calls at once.
- [ ] Why total calls and maximum stack depth are different.
- [ ] How memoization avoids recomputing the same result.
- [ ] Why the iterative version needs only two previous values.

### Are these versions tail recursive?

**None of our three versions is tail recursive:**

- **Iterative:** uses a loop, so there is no recursion.
- **Recursive:** adds the two results after the recursive calls return.
- **Memoized recursive:** adds the results and stores the sum after the recursive calls return. Memoization alone does not make a method tail recursive.

A separate tail-recursive version is possible by passing the previous two values as accumulator arguments and returning the recursive call directly. In Java, that still does not guarantee constant stack space.

**Implementation comparison** — [Fibonacci.java](../../src/main/java/recursion/Fibonacci.java):

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| Iterative | `fibonacciIterative` | Loop using the previous two values | O(n) | O(1) |
| Recursive | `fibonacciRecursive` | Calculate both smaller values on every call | O(2^n) upper bound | O(n) stack |
| Memoized recursive | `fibonacciMemoized` | Cache computed values in an array | O(n) | O(n) cache and stack |
