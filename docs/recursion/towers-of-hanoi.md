# Towers of Hanoi

**Problem:** Move `n` differently sized disks from one peg to another, using a third peg as temporary storage.

**Rules:** Move one disk at a time, take only the top disk from a peg, and never put a larger disk on a smaller disk. Initially, all disks are on the source peg, smallest on top.

To move `n` disks from source to destination:

1. Move the top `n - 1` disks from source to spare.
2. Move disk `n` from source to destination.
3. Move the `n - 1` disks from spare to destination.

The recursive method `hanoi` is in `main.java.recursion.TowerOfHanoi`. Call `hanoi(n, 'A', 'C', 'B')`. The base case is `n = 0`, which prints no move. Peg labels must be distinct; negative disk counts are rejected.

**Example for 2 disks:**

```text
Move disk 1 from A to B
Move disk 2 from A to C
Move disk 1 from B to C
```

| Disks | Expected moves |
| --- | --- |
| 0 | 0; no output |
| 1 | 1 |
| 2 | 3 |
| 3 | 7 |
| 4 | 15 |

**Understand:**

- [ ] Trace all moves for 2 disks, then 3 disks, checking each against the rules.
- [ ] Explain why the largest disk cannot move until all smaller disks are on the spare peg.
- [ ] Identify the argument order in each recursive call.
- [ ] Derive `M(n) = 2M(n - 1) + 1`, with `M(0) = 0`.

**Big-O:** Exactly `2^n - 1` moves, `O(2^n)` time, and `O(n)` recursive stack space.
