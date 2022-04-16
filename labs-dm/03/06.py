def count(str):
    global n, result
    if len(str) == n:
        result.append(str)
    else:
        count(str + '0')
        if len(str) > 0 and str[-1] != '1':
            count(str + '1')
        if len(str) == 0:
            count(str + '1')


n = int(input())
result = []
count("")

print(len(result))

for i in result:
    print(i)
