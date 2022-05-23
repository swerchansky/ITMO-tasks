n = int(input())
a = list(map(int, input().split()))

a.sort()

summ = []
count = 0
summ.append(a[0] + a[1])
count += summ[-1]
# print(summ)

i = 2

while i < n - 1:
    if a[i] < summ[0] and a[i+1] < summ[0]:
        summ.append(a[i] + a[i+1])
        count += summ[-1]
        i += 2
    elif a[i] <= summ[0] or (len(summ) > 1 and a[i] <= summ[1]):
        summ.append(a[i] + summ.pop(0))
        count += summ[-1]
        i += 1
    elif len(summ) > 1 and summ[0] < a[i] and summ[1] < a[i]:
        summ.append(summ.pop(1) + summ.pop(0))
        count += summ[-1]
    else:
        summ.append(a[i] + summ.pop(0))
        count += summ[-1]
        i += 1
    # print(summ)

while i == n - 1:
    if a[i] <= summ[0] or (len(summ) > 1 and a[i] <= summ[1]):
        summ.append(a[i] + summ.pop(0))
        count += summ[-1]
        i += 1
    elif len(summ) > 1 and summ[0] < a[i] and summ[1] < a[i]:
        summ.append(summ.pop(1) + summ.pop(0))
        count += summ[-1]
    else:
        summ.append(a[i] + summ.pop(0))
        count += summ[-1]
        i += 1

while len(summ) > 1:
    summ.append(summ.pop(1) + summ.pop(0))
    count += summ[-1]

print(count)
