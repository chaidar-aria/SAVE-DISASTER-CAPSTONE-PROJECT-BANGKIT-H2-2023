from flask import Flask, request, jsonify
from cluster_handler import predict_cluster
from image_handler import get_image_by_cluster

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    latitude = data.get('latitude')
    longitude = data.get('longitude')

    if not latitude or longitude :
        return jsonify({'error': 'Input koordinat tidak valid!'})

    koordinat = (latitude, longitude)
    kluster = predict_cluster(koordinat)
    nama_file_gambar = get_image_by_cluster(kluster)

    return jsonify({'kluster': kluster, 'nama_file_gambar': nama_file_gambar})

if __name__ == '__main__':
    app.run(debug=True)
