import math


def count_num(res):
    global a, n, i, checked
    num = 0
    if res in checked:
        return 0
    if i == n-1:
        return 1
    for j in range(1, n+1):
        if j in checked:
            continue
        if j < res:
            num += math.factorial(n - i - 1)
        elif j == res:
            checked.append(a[i])
            i += 1
            return num + count_num(a[i])
        else:
            return 0


n = int(input())
a = list(map(int, input(). split()))
checked = []
i = 0
result = count_num(a[i])
print(result-1)

