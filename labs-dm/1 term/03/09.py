def count(str, open, close):
    global n, num
    if open + close == n * 2:
        print(num, str)
        num += 1
    else:
        if open < n:
            count(str + '(', open + 1, close)
        if open > close:
            count(str + ')', open, close + 1)


n = int(input())
open = 1
close = 0
num = 0
count("(", open, close)
