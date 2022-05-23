import sys

str = input()

ind = 0

for i in range(len(str)):
    if str[i] == '=':
        ind = i+1
        break

a = list(map(int, str[ind:].split('+')))

if len(a) == 1:
    print("No solution")
    sys.exit(0)

for i in range(len(a)):
    a[-2] += 1
    a[-1] -= 1

    if a[-2] > a[-1]:
        a[-2] += a[-1]
        a.pop()
        break
    elif a[-2] * 2 <= a[-1]:
        while a[-2] * 2 <= a[-1]:
            tmp = a[-1] - a[-2]
            a[-1] = a[-2]
            a.append(tmp)
        break
    break

print(str[:ind], end='')
print(*a, sep='+')
