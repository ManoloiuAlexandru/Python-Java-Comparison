"""
Given a name and the age of a person show on screen: 'name' has age:'age'.
"""


def first_problem_solution(name, age):
    """
    :param name: the name of the person
    :param age: the age of the person
    :return: a string containing the 'name' has age:'age'
    or invalid data if one of the parameters is none
    """
    if name is not None and age is not None:
        print(name, "has age:", age)
        return name + " has age:" + str(age)
    else:
        return "Invalid data"
