package com.example.android.akbar_1202150004_modul6;

/**
 * Created by Abay on 4/1/2018.
 */

public class ImageUploadInfo {
    // Dekalarasi variabel
    String imageName, imageURL;

    // Konstruktor kosong untuk penggunaan Firebase
    public ImageUploadInfo() {

    }

    /**
     * Konstruktor
     *
     * @param imageName nama gambar
     * @param imageURL url
     */
    public ImageUploadInfo(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
    }

    // method getter
    public String getImageName() {
        return imageName;
    }

    // method getter
    public String getImageURL() {
        return imageURL;
    }
}
