# ALGORITHMS_JAVA_Programming_Assignment
(i) Algorithm 2 (Dynamic Programming) 

We index the 𝒏 distinct food portions as 𝟏,…, 𝒏.  
Subproblem (Recurrence Relation): Let 𝑿[𝒌, 𝒑] represent the minimum caloric capacity 
required to construct a meal plan with a total protein content of at least 𝒑. For this reason, a 
loop for p = 1 to P_total is utilized, where 𝑷total is the cumulative protein sum of all available 
portions. The input is restricted exclusively to the first 𝒌 portions.  
--------------------------------------------------------------------------------------------------------
Boundary Conditions: 

• If we target a protein requirement of 0 using 0 portions, we do not need to select any 
item, meaning the minimum calories required is 0 (𝑿[𝟎,𝟎] = 𝟎).  
• When evaluating 0 items for a target protein quantity 𝒑 > 𝟎, we set 𝑿[𝟎,𝒑] = ∞ to 
facilitate the < min comparison, as it is impossible to satisfy a positive protein 
requirement with zero items.  
• Given the first 𝒌 available portions and a target of 𝒑 = 𝟎, the minimum calories 
required is trivially 0 (𝑿[𝒌,𝟎] = 𝟎). 
--------------------------------------------------------------------------------------------------------
When examining the 𝒌-th portion, we consider two main cases depending on its 
protein content 𝑷𝒌:

Case 1: 𝑷𝒌 <𝒑 (where 𝒑 ∈ [𝟏,…,𝑷total]). We can either:

• Include it: Collect the remaining 𝒑 − 𝑷𝒌 protein from the first 𝒌 − 𝟏 portions and add to 
min𝑿 the caloric cost 𝑪𝒌 of the 𝒌-th portion. 
• Exclude it: Collect the full protein quantity 𝒑 (where 𝒑 ∈ [𝟏,…,𝑷total]) solely from the 
first 𝒌 − 𝟏 portions.
--------------------------------------------------------------------------------------------------------
Case 2: 𝑷𝒌 ≥ 𝒑. We can either:

• Include it: Including the 𝒌-th portion immediately satisfies the target protein 
requirement of at least 𝒑. Thus, the remaining protein to be gathered from the first 
𝒌−𝟏 portions drops to zero, and we add 𝑪𝒌 to the cost (minX). 
• Exclude it: We must collect the full protein quantity 𝒑 from the first 𝒌− 𝟏 portions.
--------------------------------------------------------------------------------------------------------
Recurrence Equation: 

𝑿[𝒌,𝟎] = 0, if k = 𝟎 𝐚𝐧𝐝 𝐩 = 0

𝑿[𝒌,𝟎] = ∞, if P = 0

𝑿[𝒌,𝟎] = m𝐢𝐧{𝐗[𝐤− 𝟏,𝐩],𝐗[𝐤 −𝟏,𝐏𝐤] +𝐂𝐤}, i𝐟 𝐏𝐤 < p

𝑿[𝒌,𝟎] = m𝐢𝐧{𝐗[𝐤− 𝟏,𝐩],𝐗[𝐤 −𝟏,𝟎] + 𝐂𝐤}, i𝐟 𝐏𝐤 ≥ p 
--------------------------------------------------------------------------------------------------------
Pseudocode:

P_total = 0

for i in P:

P_total += i

X[0,0]=0

for p=1 to P_total:

X[0,p]=∞

for k=1 to n:

X[k,0]=0

for p=1 to P_total:

if  𝑃𝑘 < p:

X[k,p] = min{X[k-1,p],  X[k-1,p-𝑃𝑘] + 𝐶𝑘}

else:

X[k,p] = min{X[k-1,p],  X[k-1,0] + 𝐶𝑘}

while(p>=0 && calories > W):
            protein = p; 
            p--; 
            calories = X[n][p]; 
return protein, calories
--------------------------------------------------------------------------------------------------------
Complexity Analysis:

o 1 loop for i in P = Ο(n) 
 
o 1 loop for p=1 to P_total = Ο(n*Pmax) (since the maximum possible value for 𝑷total 
occurs when all portions contain the maximum individual protein value 𝑷𝐦𝐚𝐱) 
 
o 1 loop for k=1 to n=O(n)
1 n𝑒𝑠𝑡𝑒𝑑 loop for p=1 to Ptotal=𝑂(P_total) }𝑂(𝑛∗𝑃_𝑡𝑜𝑡𝑎𝑙) = O(n^2*Pmax) 
 
Overall time complexity:  Ο(n*P_total) = O(n^2 * Pmax)
--------------------------------------------------------------------------------------------------------
(iii) EXPERIMENTAL CONCLUSIONS AND SUMMARY FROM

ALGORITHMS EXECUTION

Time Complexity:

• Algorithm 1 (Dynamic Programming):

This approach has a time complexity of 𝑶(𝒏𝑾), 
which is pseudo-polynomial (exponential relative to the input bit-length of 𝑾, since if 𝒃 
bits store 𝑾, then 𝑾 = 𝟐𝒃). Experimentally, as 𝒏 (# of food portions) increases, the 
target capacity 𝑾 scales alongside it, causing the execution time to grow rapidly. For 
small values of 𝒏, execution takes only 1 to 2 milliseconds, but once 𝒏 approaches 𝟓𝟎𝟎, 
the runtime jumps sharply to around ~𝟐𝟓𝟎 ms, as shown by the blue line in the chart.
--------------------------------------------------------------------------------------------------------
• Algorithm 2 (Dynamic Programming):

With a time complexity of 𝑶(𝒏^2*𝑷𝐦𝐚𝐱), this 
algorithm behaves similarly to Algorithm 1 because 𝑷𝐦𝐚𝐱 depends on the numeric scale 
of the input bits values. As shown by the orange line, the runtime for small inputs is near 
zero, but it shoots up even faster than Algorithm 1, reaching approximately ~𝟒𝟓𝟎 ms 
when 𝒏 nears 𝟓𝟎𝟎.
--------------------------------------------------------------------------------------------------------
• Algorithm 3 (Greedy): 

With a time complexity of 𝑶(𝒏 𝐥𝐨𝐠𝒏), the primary cost here is 
sorting the 𝒏 portions. This is a true polynomial-time algorithm, resulting in a highly 
stable performance. On the graph, its runtime appears as a flat line hovering near 0 ms 
(the green line of the graph staying between 𝟎 and 𝟏 ms), showing only a negligible 1 ms 
increase even for massive values of 𝒏.
--------------------------------------------------------------------------------------------------------
Time complexity summary

As the number of portions (𝒏) scales up, the dynamic programming approaches face an 
aggressive increase in execution time, with the second algorithm being the slowest. On 
the other hand, the greedy heuristic maintains near-instantaneous execution times 
close to 0 ms even under high workloads
--------------------------------------------------------------------------------------------------------
Image from: https://github.com/AthanasiosGourdomichalis/ALGORITHMS_JAVA_Programming_Assignment/blob/main/algorithm_graphs.py
Check "algorithm_graphs.py"

Check "README.pdf"
https://github.com/AthanasiosGourdomichalis/ALGORITHMS_JAVA_Programming_Assignment/blob/main/README.pdf
--------------------------------------------------------------------------------------------------------
Space Complexity:

Algorithms 1 & 2 (Dynamic Programming):

With space complexities of 𝑶(𝒏*𝑾) and 
𝑶(𝒏*𝑷total) respectively, their memory usage grows directly alongside their average 
execution time scaling.  
For a small input size (𝒏 = 𝟐𝟎) 
Algorithm 1 (with 𝐦𝐚𝐱(𝑾) = 𝟒𝟐𝟎) 
• Matrix size: (n + 1) x (W + 1) = 21 x 421 = 8.841 cells. 
• Max Memory: 8.841 x 4 bytes = 35.364 bytes 
Algorithm 2 (with 𝐦𝐚𝐱(𝑷total) = 𝟔𝟎𝟎) 
• Matrix size: (n + 1) x (Ptotal + 1) = 21 x 601 = 12.621 cells. 
• Max Memory: 12.621 x 4 bytes = 50.484 bytes 
--------------------------------------------------------------------------------------------------------
For a large input size (𝒏 = 𝟏𝟎𝟎𝟎): 
Algorithm 1 (with 𝐦𝐚𝐱(𝑾) = 𝟏.𝟎𝟓𝟎.𝟎𝟎𝟎)  
• Matrix size: (n + 1) x (W + 1) = 1001 x 1.050.001 = 1.051.051.001 cells 
• Max Memory: 1.051.051.001 x 4 bytes = 4.204.204.004 bytes = 4,20 GB 
Algorithm 2: (with 𝐦𝐚𝐱(𝑷total) = 𝟏.𝟓𝟎𝟎.𝟎𝟎𝟎)  
• Matrix size: (n + 1) x (Ptotal + 1) = 1001 x 1.500.001 = 1.501.501.001 cells 
• Max Memory: 1.501.501.001 x 4 bytes = 6.006.004.004 bytes = 6,01 GB\
--------------------------------------------------------------------------------------------------------
• Algorithm 3 (Greedy):

Possesses a space complexity of 𝑶(𝒏). It only uses a linear array 
structure (Java ArrayList) to sort the entries, keeping memory allocation minimal (a few 
kilobytes) even for huge 𝒏 values. 
Space complexity summary  
Memory allocation proved to be the primary bottleneck during our tests. For very large 
scales (e.g., 𝒏 > 𝟗𝟓𝟎), the massive multi-gigabyte table allocations of the exact 
dynamic programming algorithms 1 & 2 triggered out-of-memory heap space 
exceptions, crashing the program. The greedy algorithm completely bypassed this 
limitation due to its minimal footprint.
--------------------------------------------------------------------------------------------------------
Approximation Ratio (𝑺𝑶𝑳/𝑶𝑷𝑻) and Trade-offs:

In theory, greedy heuristics do not guarantee an optimal solution compared to exact dynamic 
programming models. However, our benchmarks show that under realistic performance limits, 
exact models can easily overload standard system memory. Looking closely at the greedy 
algorithm's approximation ratio (𝑺𝑶𝑳/𝑶𝑷𝑻), the solution quality steadily converges to 1 as the 
problem scale grows. At 𝒏 = 𝟖𝟎𝟎, the ratio reaches 𝟎.𝟗𝟗𝟖𝟖, meaning the greedy solution is 
practically indistinguishable from the absolute mathematical optimum.
--------------------------------------------------------------------------------------------------------
Final Conclusion: 

For real-world software where an absolute, mathematically perfect optimum is not strictly 
mandatory -such as this meal planner- the greedy algorithm is a significantly better 
architectural choice. While dynamic programming algorithms consume immense system 
memory for negligible gains in solution quality, the greedy approach provides an ideal balance 
by offering blazing-fast performance and minimal resource costs with a nearly perfect 
approximation of the optimal result.
--------------------------------------------------------------------------------------------------------
