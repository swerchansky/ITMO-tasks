str = input()

a = []
a.append(str)

for i in range(1, len(str)):
    a.append(a[i-1][1:] + a[i-1][0])

a.sort()
result = ''

for i in range(len(str)):
    result += a[i][-1:]

print(result)