from flask import Flask, render_template, request, redirect, url_for
import requests
import os
import json
from typing import Final
import time
from datetime import date, timedelta

app = Flask(__name__)
API_KEY: Final = "my-super-secret-key-123"
BACKEND_URL: Final = os.getenv("BACKEND_URL", "http://api:8080")
api_url: Final = f"{BACKEND_URL}/api/saints"

@app.route("/", methods=["GET", "POST"])
@app.route("/saint/<int:day>/<int:month>/<int:year>", methods=["GET", "POST"])
def home(day=None, month=None, year=None):

    if day and month and year:
        try:
            today = date(year, month, day)
        except ValueError:
            return "Error: Invalid date provided in the URL.", 400
    else:
        today = date.today()

    today_formatted = today.strftime("%d.%m.%Y")

    prev_date = today - timedelta(days=1)
    next_date = today + timedelta(days=1)

    try:
        response = requests.get(api_url + "/saintOfTheDay/day_" + str(today.day) + "/month_" + str(today.month))
        response.raise_for_status()

        if response.status_code == 200:
            # convert json response to a python dic
            saint_list = response.json()

            # Get the requested index from the URL (defaults to 0)
            selected_idx = request.args.get('idx', 0, type=int)
            # Safety check
            if saint_list and selected_idx >= len(saint_list):
                selected_idx = 0

            print("DEBUG: " + str(saint_list), flush=True)

            return render_template(
                "index.html", 
                saintsoftheday=saint_list, 
                today_date=today_formatted,
                current_date=today,
                selected_idx=selected_idx,
                prev_date=prev_date,
                next_date=next_date
            )
        
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

@app.route("/saintOfTheDay/", methods=['GET', 'POST'])
def saintOfTheDay():
    today = date.today()
    try:
        response = requests.get(api_url + "/saintOfTheDay/day_" + str(today.day) + "/month_" + str(today.month))
        response.raise_for_status()

        if response.status_code == 200:
            json_data = response.json()

            if isinstance(json_data, dict):
                saint_list = json.loads(json_data)
            else:
                saint_list = json_data

            return render_template("index.html", saintsoftheday=saint_list)
        
        else:
           return f"API Error: {response.status_code}" 
    
    except Exception as e:
        return f"Connection Failed: {e}"


@app.route('/favicon.ico')
def favicon():
    return "", 200 # Returns an empty success response
     


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)