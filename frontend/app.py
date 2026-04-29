from flask import Flask, render_template
import requests
import os

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

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)