package com.example.android.akbar_1202150004_modul6;

/**
 * Created by Abay on 4/2/2018.
 */

public class Comment {
    // Deklarasi variabel
    String email, comment;
    // Konstruktor kosong yang membantu untuk memodifikasi firebase
    public Comment() {

    }
    // Konstruktor
    public Comment(String email, String comment) {
        this.email = email;
        this.comment = comment;
    }
    // Method getter
    public String getEmail() {
        return email;
    }
    // Method getter
    public String getComment() {
        return comment;
    }
}
