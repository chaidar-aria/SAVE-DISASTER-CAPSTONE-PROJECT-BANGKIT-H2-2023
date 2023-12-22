import requests
import firebase_admin
from firebase_admin import storage

# Inisialisasi Firebase Storage (pastikan sudah ada konfigurasi)
firebase_admin.initialize_app()

def get_image_from_firebase_storage(firebase_storage_url, local_save_path):
    try:
        # Mengambil URL gambar dari Firebase Storage
        blob = storage.bucket().blob(firebase_storage_url)

        # Mendownload gambar ke path lokal
        with open(local_save_path, 'wb') as f:
            blob.download_to_file(f)
            print(f"File downloaded to {local_save_path}")
            return local_save_path
    except Exception as e:
        print(f"Failed to download the file: {str(e)}")
        return None

def get_image_by_cluster(kluster):
    # Lakukan penentuan URL Firebase Storage berdasarkan klaster
    if kluster == 0:
        firebase_storage_url = 'https://console.firebase.google.com/project/capstone-project-407004/storage/capstone-project-407004.appspot.com/files/~2Fcluster%200'
    elif kluster == 1:
        firebase_storage_url = 'url_ke_firebase_storage_klaster_2'
    else:
        firebase_storage_url = 'url_ke_firebase_storage_klaster_lainnya'

    local_save_path = f'local_folder/gambar_{kluster}.png'
    result = get_image_from_firebase_storage(firebase_storage_url, local_save_path)
    
    return result

def get_download_url(bucket_name, file_path):
    try:
        blob = storage.bucket(bucket_name).blob(file_path)
        url = blob.generate_signed_url(expiration=600)  # Menentukan durasi URL yang akan berlaku
        return url
    except Exception as e:
        print(f"Failed to get download URL: {str(e)}")
        return None

download_url = get_download_url('cluster')
print(download_url)
