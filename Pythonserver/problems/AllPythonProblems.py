"""
The Flask server
"""
from flask import Flask, json, request, redirect, url_for, render_template

from ITschool_projects.problems.responses.AccountManagement import add_data_to_db
from ITschool_projects.problems.responses.CurrencyConvertor import currency_converting
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
currency_dict = {}


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


@app.route('/currencys')
def get_currency():
    """
    Function that returns the dictionary of the currency exchange for RON
    :return:a dictionary with the exchange rates for RON
    """
    global currency_dict
    currency_dict = currency_converting()
    return currency_dict


@app.route('/currencyconvertor', methods=['POST'])
def currency_convertor():
    """
    Function that converts the amount you send to the amount you want the function returns the
    result in string form. The result is obtained by doing by multiplying the amount with the
    exchange rate:currencyToConvert/currencyToConvertTo
    :return: a string representing the result
    """
    global solution
    result = request.json
    solution = result.get("currency") + " " + str(round(float(currency_dict.get(result.get("currency"))) / float(
        currency_dict.get(result.get("currencyToConvertTo"))) * float(result.get("amount")),
                                                        2)) + " = " + result.get(
        "currencyToConvertTo")
    return redirect(url_for('show_result', date=solution))


@app.route('/accountsmanagemnt', methods=['POST'])
def accounts_management():
    """
    Function that sends data from the front end part to the data base in order to create the account
    :return: a message to show if data was send
    """
    global solution
    result = request.json
    solution = add_data_to_db(result)
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
