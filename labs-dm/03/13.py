import math


def count(res):
    global n, i, checked, k
    if len(res) == n:
        return res
    for i in range(1, n+1):
        num = math.factorial(n - len(res) - 1)
        if i in checked:
            continue
        if k >= num:
            k -= num
        elif k < num:
            checked.append(i)
            res.append(i)
            return count(res)


n, k = map(int, input(). split())
checked = []
j = 0
result = []

for i in range(1, n+1):
    if math.factorial(n - 1) <= k:
        k -= math.factorial(n - 1)
    else:
        checked.append(i)
        result = count([i])
        break

print(*result)
