import copy


def count(res):
    global n, k, pos
    if len(res) == k:
        print(f'{pos}: {res}')
        pos += 1
        return
    for j in range(1, n+1):
        if j > int(res[-1]) and n - j >= k - len(res) - 1:
            res_2 = copy.deepcopy(res)
            res_2.append(j)
            count(res_2)


n, k = map(int, input(). split())
pos = 0;
for i in range(1, n+1):
    count([i])
