def count(str):
    global n
    if len(str) == n:
        print(str)
    else:
        count(str + '0')
        count(str + '1')


n = int(input())
count("")
