n = int(input())

result = "((A0|B0)|(A0|B0))"

for i in range(n):
    if i == 0:
        result = f"((A{i}|B{i})|(A{i}|B{i}))"
    else:
        result = f"(({result}|((A{i}|A{i})|(B{i}|B{i})))|(A{i}|B{i}))"
print(result)
