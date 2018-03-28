import threading

from flask import Flask
from kafka import KafkaConsumer
from time import sleep

import datetime

app = Flask(__name__)

map = {}


class ConsumerThread(object):
    def __init__(self, interval=1):
        self.interval = interval
        thread = threading.Thread(target=self.run, args=())
        thread.daemon = True
        thread.start()

    def run(self):
        for mes in consumer:
            print(mes)
            map[mes] = datetime.datetime.now()

            sleep(self.interval)


consumer = KafkaConsumer('test_topic',
                         bootstrap_servers=['kafka:9092'], api_version=(0, 10, 1))

ConsumerThread()


@app.route("/stats")
def stats():
    return str(map)


if __name__ == "__main__":
    app.run(debug=True, host='stats')
