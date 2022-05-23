import copy

n, k = map(int, input().split())

prev = []

for i in range(k):
    prev.append(i)

now = copy.deepcopy(prev)

for i in range(1, n):
    num = len(prev)
    for m in range(k-1):
        if m % 2 == 0:
            for j in range(num-1, -1, -1):
                now.append(prev[j])
        else:
            for j in range(num):
                now.append(prev[j])
    ind = len(now) // k
    for j in range(k):
        for m in range(ind * j, ind * (j+1)):
            now[m] = f'{j}{now[m]}'
    prev = copy.deepcopy(now)

for i in now:
    print(i)
