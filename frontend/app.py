from flask import Flask, render_template, request
import requests
import os
import json

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def home():
    # Fetch the URL from Docker environment, or default to 'api'
    BACKEND_URL = os.getenv("BACKEND_URL", "http://api:8080")
    api_url = f"{BACKEND_URL}/api/saints"

    try:
        # send a get to the api 
        response = requests.get(api_url)
        response.raise_for_status()

        if response.status_code == 200:
            # convert json response to a python dic
            json_data = response.json()

            #Scaleble so it can be a dict or a lsit 
            if isinstance(json_data, dict):
                saint_list = json_data("content", [])
            else:
                saint_list = json_data

            return render_template("index.html", saints=saint_list)
        
        else:
           return f"API Error: {response.status_code}" 
    
    except Exception as e:
        return f"Connection Failed: {e}"
    
@app.route("/saveSaint/", methods=['GET', 'POST'])
def saveSaint():
            saint = {
                "name": request.form.get('name'),
                "day": request.form.get('day'),
                "month": request.form.get('month'),
                "patronage": request.form.get('patronage'),
                "tropar": request.form.get('tropar'),
                "kondak": request.form.get('kondak'),
                "description": request.form.get('description'),
                "isMartyr": request.form.get('isMartyr') == 'on'
            }
            API_KEY = "Bearer my-super-secret-key-123"

            # Key to standard HTTP header
            headers = {
                 "Authorization": f"Bearer {API_KEY}"
            }

            requests.post("http://api:8080/api/saints", json=saint, headers=headers)
            return render_template("index.html", f_msg=saint)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)