# Towers of Hanoi

**Problem:** Move n differently sized disks from peg A to peg C, using peg B as temporary storage.

**Rules:** Move one disk at a time, take only the top disk from a peg, and never put a larger disk on a smaller disk. Initially, all disks are on A, smallest on top.

**Recursive idea:** To move n disks from a source to a destination:

1. Move the top n - 1 disks from the source to the spare peg.
2. Move disk n from the source to the destination.
3. Move the n - 1 disks from the spare peg to the destination.

Stop at n = 0 without printing a move. The source, destination, and spare roles change between recursive calls; the physical peg labels do not change.

**Implement:** `void hanoi(int n, char source, char destination, char spare)`. Call `hanoi(n, 'A', 'C', 'B')`. Print each move as `Move disk 1 from A to C`, substituting the disk and peg labels. Assume distinct peg labels; reject negative n. Begin with 0 through 3 disks.

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

- [ ] Trace all moves for 2 disks, then 3 disks, checking every move against the rules.
- [ ] Explain why the largest disk cannot move until all smaller disks are on the spare peg.
- [ ] Identify the correct argument order for each recursive call.
- [ ] Distinguish printed moves from method calls: for n = 2, there are three moves but seven calls with the n = 0 base case.
- [ ] Derive the move recurrence M(n) = 2M(n - 1) + 1, with M(0) = 0.

**Big-O:** Exactly 2^n - 1 moves, O(2^n) time, and O(n) recursive stack space. With the stated n = 0 base case, total calls are 2^(n + 1) - 1 and maximum active depth is n + 1. Space excludes the printed output; storing every move would require exponential output storage.
