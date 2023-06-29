from math import sqrt

"""Calculate the area and perimeter of a right triangle that has the 2 sides given.
Ex: side1=3 side2=4 => side3=5 
Perimeter=12 and area=6
"""


def second_problem_solution(side1, side2):
    """
    :param side1: one side of the triangle
    :param side2: second side of the triangle
    :return: Perimeter and area of the triangle
    """
    side3 = sqrt(pow(side1, 2) + pow(side2, 2))
    perimeter = side1 + side2 + side3
    area = side1 * side2 / 2
    print("Perimeter:", perimeter, " and area:", area)
    return {"Perimeter": perimeter, "area": area}
