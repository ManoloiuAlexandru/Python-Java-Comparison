"""
The Flask server
"""
from flask import Flask, json, request, redirect, url_for, render_template

from ITschool_projects.problems.responses.FirstProblem import first_problem_solution
from ITschool_projects.problems.responses.SecondProblem import second_problem_solution

app = Flask(__name__)


class Server:
    def __init__(self, ip, port):
        """
        The flaks server where we set the ip and the port that we want to use
        :param ip: the ip of the server
        :param port: the port of the server
        """
        self.ip = ip
        self.port = port


solution = None  # global variable that we use to safe the result of the problems


@app.route('/first/secondproblem', methods=['POST'])
def second_problem():
    """
    Function that will be bind to the URL /first/secondproblem and we tell the route that we
    will receive data from the user using post
    :return: a redirect to the show_result method that has the solution as parameter
    """
    global solution
    result = request.json
    solution = second_problem_solution(result.get('side1'), result.get('side2'))
    return redirect(url_for('show_result', date=solution))


@app.route('/first/firstproblem', methods=['POST'])
def first_problem():
    """
    Function that will be bind to the URL /first/firstproblem and we tell the route that we
    will receive data from the user using post
    :return: a redirect to the show_result method that has the solution as parameter
    """
    global solution
    result = request.json
    solution = first_problem_solution(result.get('name'), result.get('age'))
    return redirect(url_for('show_result', date=solution))


@app.route('/result')
def show_result():
    """
    Function that sends the data to the front-end part
    :return: a jason to the react part
    """
    return {"result": solution}


if __name__ == '__main__':
    """
    Main function
    """
    server = Server(ip="127.0.0.1", port="8080")
    app.run(host=server.ip, port=server.port)
