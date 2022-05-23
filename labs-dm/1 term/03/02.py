import copy

n = int(input())

prev = ['0', '1']
now = copy.deepcopy(prev)

for i in range(1, n):
    for j in range(len(prev)-1, -1, -1):
        now.append(prev[j])
    for j in range(len(now)//2):
        now[j] = f'0{now[j]}'
    for j in range(len(now)//2, len(now)):
        now[j] = f'1{now[j]}'
    prev = copy.deepcopy(now)

for i in now:
    print(i)
