package com.example.android.akbar_1202150004_modul6;

/**
 * Created by Abay on 4/1/2018.
 */

public class Post {
    // Deklarasi variable
    String id, email, title, description;

    // Konstruktor kosong yang akan berguna pada saat menggunakan firebase
    public Post() {

    }

    /**
     * Konstruktor
     *
     * @param id id post
     * @param email email orang yang memposting
     * @param title judul
     * @param description deskripsi post
     */
    public Post(String id, String email, String title, String description) {
        // Inisiasi ke variabel
        this.id = id;
        this.email = email;
        this.title = title;
        this.description = description;
    }

    // Method getter
    public String getId() {
        return id;
    }

    // Method getter
    public String getEmail() {
        return email;
    }

    // Method getter
    public String getTitle() {
        return title;
    }

    // Method getter
    public String getDescription() {
        return description;
    }
}
