package com.example.android.akbar_1202150004_modul6;

/**
 * Created by Abay on 4/1/2018.
 */

public class ImageUploadInfo {
    String imageName, imageURL;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
