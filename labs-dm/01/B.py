def nol_1(f):
    nol_1 = True
    if f[0] != 0:
        nol_1 = False
    return nol_1


def odin_1(f):
    odin_1 = True
    if f[len(f) - 1] != 1:
        odin_1 = False
    return odin_1


def sam_1(f):
    sam_1 = True
    for i in range(len(f)):
        if f[i] == f[len(f) - i - 1]:
            sam_1 = False
            break
    return sam_1


def mon_1(f):
    if len(f) == 1:
        return True
    half = len(f) // 2
    for i in range(len(f) // 2):
        if f[i + half] < f[i]:
            return False
    left = []
    right = []
    for i in range(len(f) - half):
        left.append(f[i])
        right.append(f[i + half])
    return mon_1(left) and mon_1(right)


def lin_1(f):
    odin_polinom = 1
    for j in range(1, len(f)):
        a = f[j]
        if a == 1:
            if j != odin_polinom:
                return False
            odin_polinom *= 2
        if j == odin_polinom and a == 0:
            odin_polinom *= 2
    return True


n = int(input())

odin = True
nol = True
sam = True
mon = True
lin = True

for i in range(n):
    x, a = map(str, input().split())
    f = []
    for j in range(len(a)):
        f.append(int(a[j]))
    if odin:
        odin = odin_1(f)
    if nol:
        nol = nol_1(f)
    if sam:
        sam = sam_1(f)
    if mon:
        mon = mon_1(f)

    result = []
    result.append(f[0])
    for j in range(len(f) - 1):
        for m in range(len(f) - 1 - j):
            if f[m] != f[m + 1]:
                f[m] = 1
            else:
                f[m] = 0
        result.append(f[0])
    if lin:
        lin = lin_1(result)


if nol or odin or sam or mon or lin:
    print("NO")
else:
    print("YES")

print(nol)
print(odin)
print(sam)
print(mon)
print(lin)
