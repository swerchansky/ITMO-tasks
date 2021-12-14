str = input()

d = {"a": 0, "b": 1, "c": 2, "d": 3, "e": 4, "f": 5, "g": 6, "h": 7, "i": 8, "j": 9, "k": 10, "l": 11, "m": 12, "n": 13,
     "o": 14, "p": 15, "q": 16, "r": 17, "s": 18, "t": 19, "u": 20, "v": 21, "w": 22, "x": 23, "y": 24, "z": 25}

result = ''
count = 25
index = 0

while index < len(str):
    num = str[index]
    if index == len(str) - 1:
        result += f"{d[num]} "
        break
    while num in d and index + 1 < len(str):
        index += 1
        num += str[index]
    if num in d:
        result += f"{d[num]} "
        break
    count += 1
    d[num] = count
    result += f"{d[num[:-1]]} "

print(result)
