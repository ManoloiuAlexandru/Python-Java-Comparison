a = int(input())
b = int(input())
c = int(input())

d = (a - b if b < a else
     b - c if b < c else
     a + b + c if ((a < b) and (c != b)) or ((b + c) != a and not True)
     else
     0)

print(d)
