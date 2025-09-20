# Splay Tree — Range Sum

## Overview
This project implements a **Splay Tree** (self-adjusting binary search tree) with support for:

- `add(x)` → insert an integer if it does not already exist  
- `del(x)` → delete an integer if it exists  
- `find(x)` → check if an integer exists (`true` / `false`)  
- `sum(l, r)` → return the sum of all integers in the inclusive range `[l, r]`  

All operations run in **amortized O(log n)** time.

---

## Input / Output Format

### Input
- First line: integer `n` — number of operations (`5 ≤ n ≤ 1e5`)  
- Next `n` lines: one operation per line:
  - `add x`
  - `del x`
  - `find x`
  - `sum l r`  
- Constraints: `-1e9 ≤ x, l, r ≤ 1e9`

### Output
- For each `find`, print `true` or `false`  
- For each `sum`, print the integer sum  


---

## How to Run

### Compile and run the Java program
```
javac SplayTree.java
java SplayTree < input.txt > output.txt
```

### Use the Python test generator
The repository also contains `testcase_generator.py`, which generates random test inputs, runs the Java program, and measures runtime performance.

Run it with:
```
python3 testcase_generator.py
```

This script will:
- Generate test cases with **uniform** and **Gaussian-distributed** inputs  
- Run them on the Splay Tree implementation  
- Record runtimes  
- Produce two plots (`Uniform Plot.png` and `Gaussian Plot.png`)  

---

## Outputs

### Uniform Plot
Runtime performance of the Splay Tree when input values are drawn from a **uniform distribution** across the range. 

![photo1688420527](https://github.com/user-attachments/assets/15a681e5-d0d9-4585-9dbe-40b5e2db5131)

### Gaussian Plot 
Runtime performance of the Splay Tree when input values are drawn from a **normal (Gaussian) distribution** centered around 50 with standard deviation 25.  
This tests performance when values are clustered instead of spread evenly.  

![photo1688420518](https://github.com/user-attachments/assets/8d92f17b-7315-4a93-8589-53f326513ee7)

Both graphs demonstrate the **amortized O(log n)** behavior of splay tree operations, showing near-linear growth in runtime as the number of inputs increases.

---

## Repository Structure
```
├── SplayTree.java # Java implementation of the splay tree
├── testcase_generator.py # Python script for test generation & benchmarking
└── README.md # Documentation
```
