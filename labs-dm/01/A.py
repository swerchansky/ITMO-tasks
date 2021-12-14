n = int(input())

otn_1 = []
for i in range(n):
    otn_1.append(input().split())

otn_2 = []
for i in range(n):
    otn_2.append(input().split())

sim = False
anti_sim = True
ref = False
anti_ref = False
trans = True
count_sim = 0
count_ref = 0

#ref
for i in range(n):
    for j in range(n):
        if i == j and otn_1[i][j] == '1':
            count_ref += 1
            if count_ref == n:
                ref = True

#sim
for i in range(n):
    for j in range(n):
        if otn_1[i][j] == otn_1[j][i]:
            sim = True
        if otn_1[i][j] != otn_1[j][i]:
            sim = False
            break

#anti_sim
for i in range(n):
    for j in range(n):
        if otn_1[i][j] == '1' and otn_1[j][i] == '1' and i != j:
            anti_sim = False
            break

#anti_ref
if count_ref == 0:
    anti_ref = True

#trans
for i in range(n):
    for j in range(n):
        if otn_1[i][j] == '1':
            for m in range(n):
                if otn_1[j][m] == '1' and otn_1[i][m] == '0':
                    trans = False

result_1 = [int(ref), int(anti_ref), int(sim), int(anti_sim), int(trans)]

sim = False
anti_sim = True
ref = False
anti_ref = False
trans = True
count_sim = 0
count_ref = 0

#ref
for i in range(n):
    for j in range(n):
        if i == j and otn_2[i][j] == '1':
            count_ref += 1
            if count_ref == n:
                ref = True

#sim
for i in range(n):
    for j in range(n):
        if otn_2[i][j] == otn_2[j][i]:
            sim = True
        if otn_2[i][j] != otn_2[j][i]:
            sim = False
            break

# anti_sim
for i in range(n):
    for j in range(n):
        if otn_2[i][j] == '1' and otn_2[j][i] == '1' and i != j:
            anti_sim = False
            break

# anti_ref
if count_ref == 0:
    anti_ref = True

# trans
for i in range(n):
    for j in range(n):
        if otn_2[i][j] == '1':
            for m in range(n):
                if otn_2[j][m] == '1' and otn_2[i][m] == '0':
                    trans = False

result_2 = [int(ref), int(anti_ref), int(sim), int(anti_sim), int(trans)]

comp = []

for i in range(n):
    a = ['0'] * n
    for j in range(n):
        if otn_1[i][j] == '1':
            for m in range(len(a)):
                if otn_2[j][m] == '1':
                    a[m] = '1'
    comp.append(a)

print(*result_1)
print(*result_2)
for i in range(n):
    a = comp[i]
    print(*a)
