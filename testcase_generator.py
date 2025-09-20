import subprocess
import time
import numpy as np
from matplotlib import pyplot as plt


program_inputs = [i for i in range(0, 500000, 5000)]

uniform = []

for i in program_inputs:
    numbers = np.array([int(number) for number in np.random.uniform(low=1, high=100, size=i)])

    f = open('input.txt', 'w')
    f.write(f'{i * 2}\n')

    for _ in range(i):
        f.write(f'add {np.random.choice(numbers)}\n')
        f.write(f'find {np.random.choice(numbers)}\n')

    f.close()

    start_time = time.time()
    subprocess.run(['java', 'SplayTree.java'], capture_output=True, text=True)

    uniform.append(time.time() - start_time)

gaussian = []

for i in program_inputs:
    numbers = np.array([int(number) for number in np.random.normal(loc=50, scale=25, size=i)])

    f = open('input.txt', 'w')
    f.write(f'{i * 2}\n')

    for _ in range(i):
        f.write(f'add {np.random.choice(numbers)}\n')
        f.write(f'find {np.random.choice(numbers)}\n')

    start_time = time.time()
    subprocess.run(['java', 'SplayTree.java'], capture_output=True, text=True)

    gaussian.append(time.time() - start_time)

fig, ax = plt.subplots()
ax.plot(program_inputs, uniform, 'ro')
ax.set_xlabel('Program Inputs')
ax.set_ylabel('Runtime')
plt.savefig('Uniform Plot.png')
plt.show()

fig, ax = plt.subplots()
ax.plot(program_inputs, gaussian, 'bo')
ax.set_xlabel('Program Inputs')
ax.set_ylabel('Runtime')
plt.savefig('Gaussian Plot.png')
plt.show()
