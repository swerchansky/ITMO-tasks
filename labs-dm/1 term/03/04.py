n = int(input())

d = {}

prev = '0' * n
print(prev)
d[prev] = 1

while True:
    str = f'{prev[1:]}1'
    if str in d:
        str = f'{prev[1:]}0'
        if str in d:
            break
        else:
            d[str] = 1
            print(str)
            prev = str
    else:
        d[str] = 1
        print(str)
        prev = str
