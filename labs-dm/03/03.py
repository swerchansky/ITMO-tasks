import copy


def count(str, n):
    global a
    if len(str) == n:
        a.append(str)
    else:
        count(str + '0', n)
        count(str + '1', n)
        count(str + '2', n)


n = int(input())

a = []
count('', n-1)
for i in range(len(a)):
    a[i] = list(map(int, a[i]))
    a[i].insert(0, 0)
# print(a)

for i in range(1):
    now = copy.deepcopy(a)
    for j in range(len(a)):
        num = copy.deepcopy(a[j])
        print(*num, sep='')
        now.append(num)
        for k in range(len(a[j])):
            if now[-1][k] + 1 > 2:
                now[-1][k] = 0
            else:
                now[-1][k] = now[-1][k] + 1
        num = copy.deepcopy(now[-1])
        now.append(num)
        print(*num, sep='')
        for k in range(len(a[j])):
            if now[-1][k] + 1 > 2:
                now[-1][k] = 0
            else:
                now[-1][k] = now[-1][k] + 1
        print(*now[-1], sep='')

# print(now)


