package com.example.android.akbar_1202150004_modul6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AddPhotoActivity extends AppCompatActivity {
    // Deklarasi beberapa variabel
    ImageView imageView;
    Uri filePath;
    EditText title, description;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        // Inisiasi pada variabel
        imageView = findViewById(R.id.image_view);
        title = findViewById(R.id.title);
        description = findViewById(R.id.desc);
        databaseReference = FirebaseDatabase.getInstance().getReference("post");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 234 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Mendapatkan path directory
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Set image pada ImageView
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *  Mengupload file pada FirebaseStorage
     */
    public void uploadFile() {

        if (filePath != null) {
            StorageReference riversRef = FirebaseStorage.getInstance().getReference().child(title.getText().toString()+".jpg");
            // Menaruh file pada FirebaseStorage
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            // Menampilkan toast jika tidak ada file diupload
            Toast.makeText(this, "Tidak ada File", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *  Menginputkan data pada pada Database, dengan key "post"
     */
    public void insertData() {

        // Mengecek User yang login
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        // Mendapatkan id yang di Database
        String id = databaseReference.push().getKey();
        // Memasukkannya kedalam objek yang nantinya akan di masukkan di Firebase Database
        Post post = new Post(id, firebaseAuth.getCurrentUser().getEmail(), title.getText().toString(), description.getText().toString());
        // Menginput data
        databaseReference.child(id).setValue(post);
    }


    public void showFileChooser() {

        Intent intent = new Intent();
        // Mengesett di directory image
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Memanggil method startActivityForResult and berisi argumen
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 234);

    }
    public void choose(View view) {
        // Memanggil method
        showFileChooser();
    }

    public void upload(View view) {
        // Memanggil beberapa method
        uploadFile();
        insertData();
        // Pindah ke aktivitas lain
        startActivity(new Intent(this, HomeActivity.class));
    }
}
