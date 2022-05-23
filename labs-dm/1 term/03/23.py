str = input()

num = len(str) - 1
prev = list(str)

while num >= 0 and str[num] != '1':
    prev[num] = 1
    num -= 1
if num < 0:
    print('-')
else:
    prev[num] = 0
    print(*prev, sep='')

num = len(str) - 1
next = list(str)

while num >= 0 and str[num] != '0':
    next[num] = 0
    num -= 1
if num < 0:
    print('-')
else:
    next[num] = 1
    print(*next, sep='')
