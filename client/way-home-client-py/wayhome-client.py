import time
import hashlib
import requests
import json

SERVER_URL = "https://wayhome.zhangtory.com/api/address"
KEY_ID = "YOUR_KEY_ID"
SECRET_KEY = "YOUR_SECRET_KEY"

PROTOCOL = "http"
PORT = 8123
PATH = ""


def build_params_str():
    params_str = "keyId=" + KEY_ID
    if PATH != "":
        params_str = params_str + "&path=" + PATH
    params_str = params_str + "&port=" + str(PORT)
    params_str = params_str + "&protocol=" + PROTOCOL
    timestamp = str(int(round(time.time() * 1000)))
    params_str = params_str + "&timestamp=" + timestamp
    md5str = hashlib.md5((params_str + "&secretKey=" + SECRET_KEY).encode(encoding="UTF-8")).hexdigest().upper()
    params = {'keyId': KEY_ID, 'path': PATH, 'port': PORT, 'protocol': PROTOCOL, 'timestamp': timestamp, 'sign': md5str}
    return params


def post_address():
    params = build_params_str()
    print(params)
    headers = {'Content-Type': 'application/json;charset=utf-8', 'User-Agent': 'way-home-client-py', 'Connection': 'Keep-Alive'}
    data = json.dumps(params)
    rep = requests.post(SERVER_URL, data=data, headers=headers)
    print(rep.text)


if __name__ == "__main__":
    print("start wayhome-client...")
    while True:
        post_address()
        time.sleep(1)
