import sys

n, k = map(int, input(). split())

a = list(map(int, input(). split()))

a.append(n+1)
fl = True

for i in range(k-1, -1, -1):
    if a[i+1] - a[i] >= 2:
        fl = False
        a[i] += 1
        for j in range(i+1, k):
            a[j] = a[j-1] + 1
        a.pop()
        break
if fl:
    print(-1)
    sys.exit(0)
print(*a)

