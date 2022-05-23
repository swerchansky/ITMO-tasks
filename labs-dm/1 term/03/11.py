import copy


def count(res):
    print(*res)
    for j in range(1, n+1):
        if res[-1] < j:
            res_2 = copy.deepcopy(res)
            res_2.append(j)
            count(res_2)


n = int(input())
print()
for i in range(1, n+1):
    count([i])
