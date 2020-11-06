import time
import hashlib
import requests
import json

SERVER_URL = "https://api.wayhome.zhangtory.com/address"
USER_NAME = "YOUR_USER_NAME"
KEY_NAME = "YOUR_KEY_NAME"
SECRET_KEY = "YOUR_SECRET_KEY"

PROTOCOL = "http"
PORT = 8123
PATH = ""


def build_params_str():
    params_str = "keyName" + KEY_NAME
    if PATH != "":
        params_str = params_str + "path" + PATH
    params_str = params_str + "port" + str(PORT)
    params_str = params_str + "protocol" + PROTOCOL
    timestamp = str(int(round(time.time() * 1000)))
    params_str = params_str + "timestamp" + timestamp
    params_str = params_str + "username" + USER_NAME
    md5str = hashlib.md5((params_str + SECRET_KEY).encode(encoding="UTF-8")).hexdigest().upper()
    params = {'username': USER_NAME, 'keyName': KEY_NAME,  'path': PATH, 'port': PORT, 'protocol': PROTOCOL, 'timestamp': timestamp, 'sign': md5str}
    return params


def post_address():
    params = build_params_str()
    headers = {'Content-Type': 'application/x-www-form-urlencoded', 'User-Agent': 'way-home-client-py 1.2', 'Connection': 'Keep-Alive'}
    rep = requests.post(SERVER_URL, data=params, headers=headers)
    print(rep.text)


if __name__ == "__main__":
    print("start wayhome-client 1.2...")
    while True:
        post_address()
        time.sleep(1)
