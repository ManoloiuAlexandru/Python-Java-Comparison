from flask import Flask, json, request

app = Flask(__name__)


class Server:
    def __init__(self, ip, port):
        self.ip = ip
        self.port = port


@app.route('/first/firstproblem', methods=['POST'])
def checked_data():
    result = request.json
    print(result.get("name"), "has age:", result.get("age"))
    return result


if __name__ == '__main__':
    server = Server(ip="127.0.0.1", port="8080")
    app.run(host=server.ip, port=server.port)
