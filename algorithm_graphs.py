import numpy as np
import matplotlib.pyplot as plt
n = [20, 50, 100, 250, 500, 800] 

# Average execution times in milliseconds (ms) for each algorithm
ms1 = [1, 0, 2, 25, 251, 1444] # Dynamic Programming Algorithm 1
ms2 = [1, 1, 2, 45, 425, 2344] # Dynamic Programming Algorithm 2
ms3 = [1, 0, 0, 0, 0, 1] # Greedy Algorithm 3

plt.xlabel('n values (Portions)')
plt.ylabel('Average Execution Time (ms)')
plt.title('Graphs Comparison')
plt.grid()

plt.plot(n, ms1, label = 'Dynamic Alg. 1')
plt.plot(n, ms2, label = 'Dynamic Alg. 2')
plt.plot(n, ms3, label = 'Greedy Alg.')

plt.legend()
plt.show()