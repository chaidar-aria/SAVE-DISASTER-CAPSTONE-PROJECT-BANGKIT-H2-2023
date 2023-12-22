from flask import Flask, jsonify, request
import requests

app = Flask(__name__)

@app.route('/earthquake', methods=['GET'])
def get_earthquake_data():
    url = "https://indonesia-most-accurate-weather-and-earthquake.p.rapidapi.com/quake"

    headers = {
        "X-RapidAPI-Key": "89bb0478aamsh0bcbdd01be5abdfp149f0ejsn5b83f6311b6a",
        "X-RapidAPI-Host": "indonesia-most-accurate-weather-and-earthquake.p.rapidapi.com"
    }

    response = requests.get(url, headers=headers)

    # Check if the request was successful
    if response.status_code == 200:
        return jsonify(response.json())
    else:
        return jsonify({"error": "Failed to fetch earthquake data"}), response.status_code

if __name__ == '__main__':
    app.run(debug=True)
