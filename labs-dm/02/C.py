str = input()

a = [''] * len(str)

for i in range(len(str)):
    for j in range(len(str)):
        a[j] = f"{str[j]+a[j]}"
    a.sort()

print(a[0])
