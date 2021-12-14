def to_bin(f):
    if f == 1:
        return '1.0'
    else:
        frac = f
        k, m = f.as_integer_ratio()
        return f'0.{k:0{m.bit_length() - 1}b}'


n = int(input())
str = input()

d = {"a": 0, "b": 0, "c": 0, "d": 0, "e": 0, "f": 0, "g": 0, "h": 0, "i": 0, "j": 0, "k": 0, "l": 0, "m": 0, "n": 0,
     "o": 0, "p": 0, "q": 0, "r": 0, "s": 0, "t": 0, "u": 0, "v": 0, "w": 0, "x": 0, "y": 0, "z": 0}

d_nums = {0: 'a', 1: 'b', 2: 'c', 3: 'd', 4: 'e', 5: 'f', 6: 'g', 7: 'h', 8: 'i', 9: 'j', 10: 'k', 11: 'l', 12: 'm',
          13: 'n',
          14: 'o', 15: 'p', 16: 'q', 17: 'r', 18: 's', 19: 't', 20: 'u', 21: 'v', 22: 'w', 23: 'x', 24: 'y', 25: 'z'}

d_keys = {"a": 0, "b": 1, "c": 2, "d": 3, "e": 4, "f": 5, "g": 6, "h": 7, "i": 8, "j": 9, "k": 10, "l": 11, "m": 12,
          "n": 13,
          "o": 14, "p": 15, "q": 16, "r": 17, "s": 18, "t": 19, "u": 20, "v": 21, "w": 22, "x": 23, "y": 24, "z": 25}

for i in str:
    d[i] += 1

count = []

for i in range(n):
    count.append(d[d_nums[i]])

frequency = []

for i in range(n):
    frequency.append(count[i] / len(str))

line = [frequency[0]]

for i in range(1, n):
    line.append(frequency[i] + line[i - 1])

line_new = list(line)
# print(line_new)

l_cur = 0.0
r_cur = 1.0
l = l_cur
r = r_cur

for i in str:
    ind = d_keys[i]
    if ind > 0:
        tmp = (l_cur)
        l_cur = (l + (r_cur - l_cur) * line[ind - 1])
        r_cur = (l + (r_cur - tmp) * line[ind])
    else:
        r_cur = l + (r_cur - l_cur) * line[ind]
    l = (l_cur)
    r = (r_cur)
    # if ind > 0:
    #     l_cur = line[ind-1]
    #     r_cur = line[ind]
    # else:
    #     r_cur = line[ind]
    # for j in range(n):
    #     if j > 0:
    #         line[j] = l + (r_cur - l_cur) * line_new[j]
    #     else:
    #         line[j] = l + (r_cur - l_cur) * line_new[j]
    # print(line)
    print(l_cur, r_cur)
#
# print(l_cur, r_cur)
# print(count)
# print(frequency)
# print(line)

print(to_bin(l_cur))
l = to_bin(l_cur)
# tmp = f'{r_cur}'
# result = Fraction(tmp)
# print(r_cur)
print(to_bin(r_cur))
r = to_bin(r_cur)
# 0.01101001001
# 0.01101001011

i = 0

while l[i] == r[i]:
    i += 1
    if i == len(l):
        break

print(n)
print(*count)
if i == 0:
    print(0)
else:
    print(r[2:i + 1])
