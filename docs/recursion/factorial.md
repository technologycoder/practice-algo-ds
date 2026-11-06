# Factorial

**Key lesson:** All three take **O(n) time**, but in Java, iteration uses **O(1) auxiliary space**, while both recursive forms use **O(n) stack space**.

**Problem:** Calculate n! = n × (n - 1) × … × 1, with 0! = 1. For example, 4! = 24.

**Inputs:** Accept 0–20 and return long; reject other inputs with IllegalArgumentException.

| Input | Expected result |
| --- | --- |
| 0 | 1 |
| 1 | 1 |
| 4 | 24 |
| 5 | 120 |
| 20 | 2432902008176640000 |
| -1 or 21 | IllegalArgumentException |

**Understand:**

- [ ] Explain why `factorialRecursive` is not tail recursive.
- [ ] Why the starting product and base-case result are 1.
- [ ] Why factorialRecursive(4) makes four calls with the implemented `num <= 1` base case.
- [ ] Why a missing base case or increasing argument prevents termination.
- [ ] Trace all three versions for 0, 1, and 4.

**Implementation comparison** — [Factorial.java](../../src/main/java/recursion/Factorial.java):

| Version | Method | Approach | Time | Auxiliary space |
| --- | --- | --- | --- | --- |
| Iterative | `factorialIterative` | Accumulate the product in a loop | O(n) | O(1) |
| Recursive | `factorialRecursive` | Multiply after the smaller call returns | O(n) | O(n) stack |
| Tail recursive | `factorialTailRecursive` | Carry the product in an accumulator argument | O(n) | O(n) stack in Java |
