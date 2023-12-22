from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

@app.route('/get_weather', methods=['GET'])
def get_weather():
    city = request.args.get('city')
    api_key = request.headers.get('X-Weather-API-Key')

    if not city or not api_key:
        return jsonify({'error': 'Missing city or API key'}), 400

    url = f"https://open-weather13.p.rapidapi.com/city/{city}"

    headers = {
        'X-RapidAPI-Key': api_key,
        'X-RapidAPI-Host': 'open-weather13.p.rapidapi.com'
    }

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        return jsonify(response.json())
    else:
        return jsonify({'error': 'Failed to fetch weather data'}), response.status_code

if __name__ == '__main__':
    app.run(debug=True)
