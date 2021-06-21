# Algorithm-Princeton
## Hello

## Percolation
Create two UF(n * n + 1), the first one use site[0] as the top side, the other one use site[0] as the bottom site. Union sites in both two UFs, and the UFs records the connectivity between a site and the top or the bottom.  
1. If a site is connected to the top, it is full.
2. If a site is connected both to the top and the bottom, the matrix is percolated.

* You cannot use a UF(n * n + 1), because of this edge case:  
After you reach a percolatd path, every opened site at nth row will be regarded full because the site is unioned with bottom, the bottom is unioned with the top.  