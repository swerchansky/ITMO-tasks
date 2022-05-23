import math

mode = int(input())
str = input()

if mode == 1:
    print(len(str))
    result = ''
    j = 1
    count = 0
    while count != len(str):
        if (j & j - 1) == 0:
            result += ' '
            j += 1
        else:
            result += str[count]
            j += 1
            count += 1
    k = math.ceil(math.log2(len(result)))
    binn = [''] * (len(result) + 1)
    for j in range(1, len(result)+1):
        tmp = bin(j)[2:]
        binn[j] = '0' * (k-len(tmp)) + tmp
    k = 1
    i = 1
    while i < len(result) + 1:
        count = 0
        for j in range(i+1, len(result)+1):
            if binn[j][-k] == '1' and (i & i - 1) == 0:
                count += int(result[j-1])
        result = result.replace(' ', f'{count % 2}', 1)
        k += 1
        i = 2 ** (k-1)
    print(result)

elif mode == 2:
    k = math.ceil(math.log2(len(str)))
    binn = [''] * (len(str) + 1)
    for i in range(1, len(str) + 1):
        tmp = bin(i)[2:]
        binn[i] = '0' * (k-len(tmp)) + tmp
    k = 1
    i = 1
    res = 0
    while i < len(str) + 1:
        count = 0
        for j in range(i + 1, len(str) + 1):
            if binn[j][-k] == '1':
                count += int(str[j - 1])
        if int(str[i-1]) != (count % 2):
            res += i
        k += 1
        i = 2 ** (k - 1)
    result = ''
    j = 1
    count = 0
    for i in range(len(str)):
        if ((i+1) & i) == 0:
            continue
        elif i + 1 == res:
            if str[i] == '1':
                result += '0'
            else:
                result += '1'
        else:
            result += str[i]
    print(result)
