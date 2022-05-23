str = input()

alphabet = "abcdefghijklmnopqrstuvwxyz"

result = ''

for i in range(len(str)):
    ind = alphabet.index(str[i])
    result += f"{ind + 1} "
    alphabet = f"{str[i]}{alphabet[0:ind]}{alphabet[ind + 1 : ]}"

print(result)
