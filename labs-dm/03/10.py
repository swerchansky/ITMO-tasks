def count(n, i, str):
    if n == 0:
        print(str)
    else:
        for j in range(i, n + 1):
            count(n-j, j, f'{str}+{j}')


n = int(input())

for i in range(1, n+1):
    count(n-i, i, f'{i}')
