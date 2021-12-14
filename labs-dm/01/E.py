n = int(input())
x = []
fun = []

for i in range(2 ** n):
    a, b = map(str, input().split())
    x.append(a)
    fun.append(int(b))

result = []
result.append(fun[0])

# triangle method
for j in range(len(fun) - 1):
    for i in range(len(fun) - 1 - j):
        if fun[i] != fun[i + 1]:
            fun[i] = 1
        else:
            fun[i] = 0
    result.append(fun[0])

for i in range(2 ** n):
    print(x[i], result[i])
