from flask import Flask, json, request, redirect, url_for, render_template

from ITschool_projects.problems.responses.FirstProblem import first_problem_solution

app = Flask(__name__)


class Server:
    def __init__(self, ip, port):
        self.ip = ip
        self.port = port


@app.route('/first/firstproblem', methods=['POST'])
def first_problem():
    result = request.json
    solution = first_problem_solution(result.get('name'), result.get('age'))
    return redirect(url_for('show_result', date=solution))


@app.route('/result')
def show_result():
    data = request.args.get('date', None)
    return render_template('result.html', date=data)


if __name__ == '__main__':
    server = Server(ip="127.0.0.1", port="8080")
    app.run(host=server.ip, port=server.port)
