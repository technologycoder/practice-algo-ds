# Recursion

For each problem, identify the base case, explain how the input becomes smaller, trace a small example, implement it in Java, and work out time and stack space.

## What is recursion?

Recursion is when a method calls itself to solve a smaller version of the same problem. Each call has its own parameters and local variables, and waits for any calls it makes to finish before continuing.

A recursive solution needs two things:

- **Base case:** a simple input that can be answered without another recursive call.
- **Recursive case:** reduce the problem and call the method again, making progress toward the base case.

For example, factorial of 4 can be expressed as 4 times factorial of 3. The calls continue toward a base case, then return their results back up the call stack. Without a reachable base case, calls keep accumulating until the stack limit is exceeded.

## Tail recursion

### What is tail recursion?

A method is tail recursive when every recursive call is its final operation: after the call returns, the caller returns that result directly, with no computation or cleanup still pending.

```text
return solve(smallerInput, updatedResult)      → tail call
return 1 + solve(smallerInput, updatedResult)  → not a tail call
```

In the second form, the caller still has to add 1 after the recursive result arrives. A call being written on a return line does not by itself make it a tail call.

An **accumulator** is an argument that carries work already completed into the next call. It can move a calculation from after the recursive call to before it. Arguments are evaluated before entering the called method.

### Should we always aim for tail recursion?

No. Choose a clear, correct algorithm first.

- In languages with guaranteed tail-call elimination, a simple tail-recursive chain can reuse a stack frame and use constant stack space.
- Java does not guarantee that optimization. Tail recursion alone does not guarantee O(1) stack space or prevent StackOverflowError. See the [JVM specification on method frames](https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-2.html#jvms-2.6).
- For simple repeated updates, a loop often gives the clearest way to guarantee constant auxiliary space. Keep recursion when it naturally expresses the problem, such as tree traversal or Towers of Hanoi.
- Tail recursion and memoization address different concerns: tail position concerns work remaining in the caller, while memoization avoids recomputing results.

## Problems

- [Factorial](factorial.md)
- [Fibonacci](fibonacci.md)
- [Towers of Hanoi](towers-of-hanoi.md)
- [Combinations](combinations.md)
- [Permutations](permutations.md)
- [Binary search](binary-search.md)
- [Coin change](coin-change.md)
- [0/1 knapsack](knapsack.md)

For these introductory Big-O comparisons, count arithmetic and printing one fixed-size value or move as constant-cost operations. Include active recursive calls in space analysis; Java does not guarantee tail-call elimination.
