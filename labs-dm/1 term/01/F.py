n, k = map(int, input().split())

input_list = []

for i in range(k):
    a = input().split()
    input_list.append(list(map(int, a)))

skip = [False] * k

check_out = False
while True:
    check = True
    for i in range(k):
        if skip[i]:
            continue
        count = 0
        ind_j = 0
        for j in range(n):
            if input_list[i][j] != -1:
                count += 1
                ind_j = j
        if count == 1:
            check = False
            skip[i] = True
            for j in range(k):
                if input_list[j][ind_j] != -1 and not skip[j]:
                    count_neg = 0
                    count_none = 0
                    check_l = input_list[i][ind_j]
                    for m in range(n):
                        if m == ind_j:
                            check_l = input_list[i][ind_j]
                        else:
                            check_l = -1
                        if check_l == input_list[j][m]:
                            if input_list[j][m] == -1:
                                count_none += 1
                                continue
                            skip[j] = True
                            break
                        if check_l != -1 and check_l != input_list[j][m]:
                            if input_list[j][m] == -1:
                                count_none += 1
                                continue
                            count_neg += 1
                    if count_neg + count_none == n:
                        check_out = True
                        if check_out:
                            break
                    input_list[j][ind_j] = -1
        if check_out:
            break
    if check:
        break
    if check_out:
        break

if check_out:
    print("YES")
else:
    print("NO")
