def count(res):
    global n
    if len(res) == n:
        print(*res)
    else:
        for i in range(1, n+1):
            if f'{i}' not in res:
                count(res + f'{i}')


n = int(input())

for i in range(1, n+1):
    count(f'{i}')
