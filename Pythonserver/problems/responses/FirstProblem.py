def first_problem_solution(name, age):
    if name is not None and age is not None:
        print(name, "has age:", age)
        return name + " has age:" + str(age)
    else:
        return "Invalid data"
