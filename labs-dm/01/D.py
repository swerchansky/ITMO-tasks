import sys

n = int(input())

result = []
and_list = []

toj_0 = True

for j in range(n):
    result.append(f"1 {j + 1}")

count_one = 0

for i in range(2**n):
    x, truth = input(). split()
    if truth == '1':
        toj_0 = False
        count_one += 1
        ch = False
        for j in range(n-1):
            if n > 2 and ch:
                j_2 = x[j + 1]
                if j_2 == '1':
                    j_2 = j + 2
                else:
                    j_2 = n + 2 + j
                result.append(f"2 {j_2} {n + len(result)}")
                if j == n-2:
                    and_list.append(n + len(result))
                continue
            j_1 = x[j]
            j_2 = x[j+1]
            if j_1 == '1':
                j_1 = j+1
            else:
                j_1 = n+1+j
            if j_2 == '1':
                j_2 = j+2
            else:
                j_2 = n+2+j
            result.append(f"2 {j_1} {j_2}")
            if n <= 2:
                and_list.append(n + len(result))
            ch = True

count = 0
length = len(result) + n+1
prev = 0

for i in range(1, len(and_list)):
    if i == 1:
        result.append(f"3 {and_list[i-1]} {and_list[i]}")
    else:
        result.append(f"3 {and_list[i]} {len(result)+n}")
    count += 1

# print(result)

if toj_0:
    result = ["1 1", f"2 1 {n+1}"]
    print(n + len(result))
    for i in range(len(result)):
        print(result[i])
    sys.exit()


print(n+len(result))
for i in range(len(result)):
    print(result[i])

