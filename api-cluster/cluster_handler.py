from model_loader import load_model

def predict_cluster(nama_kota):
    model = load_model()
    # Lakukan prediksi kluster menggunakan model
    kluster = model.predict([nama_kota])
    return kluster[0]