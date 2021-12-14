import sys

n = int(input())

nums = list(map(int, input(). split()))
compose = '0' * 33
bin_nums = ['0' * 33] * n

for i in range(n):
    num = bin(nums[i])
    num = num[2:]
    tmp = bin_nums[i][:33 - len(num)] + num
    bin_nums[i] = tmp

num = int(input())
num = bin(num)
num = num[2:]
tmp = compose[:33 - len(num)] + num
compose = tmp

print(bin_nums)
print(compose)

tt = [[0]*n for i in range(33)]

for i in range(n):
    for j in range(33):
        tt[j][i] = int(bin_nums[i][j])

print(tt)

check = []
check_tt = []

checker = True

for i in range(33):
    if not tt[i] in check:
        check.append(tt[i])
        check_tt.append(compose[i])
    else:
        ind = check.index(tt[i])
        if compose[i] != check_tt[ind]:
            print("Impossible")
            sys.exit()


print(check)
print(check_tt)

toj_0 = True

for i in range(33):
    if int(compose[i]) == 1:
        toj_0 = False
        break

if toj_0:
    print('1&~1')
    sys.exit()
else:
    result = ''
    for i in range(len(check)):
        if check_tt[i] == '1':
            if result != '':
                result += '|'
            result += '('
            if check[i][0] == 0:
                result += f'~{1}'
            else:
                result += f'{1}'
            for j in range(1, n):
                if check[i][j] == 0:
                    result += f'&~{j+1}'
                else:
                    result += f'&{j+1}'
            result += ')'

print(result)