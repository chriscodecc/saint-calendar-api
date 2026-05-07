from flask import Flask, render_template, request, redirect, url_for
import requests
import os
import json
from typing import Final
import time

app = Flask(__name__)
API_KEY: Final = "my-super-secret-key-123"

@app.route("/", methods=["GET", "POST"])
def home():
    # Fetch the URL from Docker environment, or default to 'api'
    BACKEND_URL = os.getenv("BACKEND_URL", "http://api:8080")
    api_url = f"{BACKEND_URL}/api/saints"

    time.sleep(1)
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
                "isMartyr": request.form.get('isMartyr') == 'on',
                "quotes" : None, 
                "relics" : None,
                "imageLike" : "www.google.com",
                "link_url" : None
            }
            

            # Key to standard HTTP header
            headers = {
                 "Authorization": f"Bearer {API_KEY}"
            }

            response = requests.post("http://api:8080/api/saints", json=saint, headers=headers)
            if response.status_code == 200:  
                return redirect(url_for('home'))
            else:
                return render_template("index.html", f_msg=f"Status Code: {response.status_code}") 
            
@app.route("/deleteSaint/", methods=['POST'])
def deleteSaint():
    
    saintId = request.form.get('saint_id')

    if not saintId:
        return f"Error: No Saint ID {saintId} provided", 400

    try:
        headers = {
            "Authorization": f"Bearer {API_KEY}"
        }
        print("BEFORE CRASH", flush=True)
        response = requests.delete(f"http://api:8080/api/saints/delete/{saintId}", headers=headers)
        
        if response.status_code == 200 or response.status_code == 204:
            print(f"Successfully deleted saint {saintId}", flush=True)
        else:
            print(f"Failed to delete. API returned: {response.status_code}", flush=True)
    except Exception as e:
        print(f"Connection error: {e}", flush=True)

    return redirect(url_for('home'))


@app.route('/favicon.ico')
def favicon():
    return "", 200 # Returns an empty success response
     


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)