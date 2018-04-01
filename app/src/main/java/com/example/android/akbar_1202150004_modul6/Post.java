package com.example.android.akbar_1202150004_modul6;

/**
 * Created by Abay on 4/1/2018.
 */

public class Post {
    String id, email, title, description;

    public Post(String id, String email, String title, String description) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
