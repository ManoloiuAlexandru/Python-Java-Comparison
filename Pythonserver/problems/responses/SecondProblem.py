from math import sqrt


def second_problem_solution(side1, side2):
    side3 = sqrt(pow(side1, 2) + pow(side2, 2))
    perimeter = side1 + side2 + side3
    area = side1 * side2 / 2
    print("Perimeter:", perimeter, " and area:", area)
    return perimeter, area
