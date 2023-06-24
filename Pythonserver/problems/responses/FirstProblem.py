def first_problem_solution(name, age):
    if name != None and age != None:
        print(name, "has age:", age)
        return name + " has age:" + str(age)
    else:
        return "Invalid data"
