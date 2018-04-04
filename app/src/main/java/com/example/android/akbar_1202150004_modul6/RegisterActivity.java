package com.example.android.akbar_1202150004_modul6;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    // Deklarasi variabel
    FirebaseAuth firebaseAuth;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Inisiasi ke variabel
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_edit);
        password = findViewById(R.id.password_edit);

    }

    /**
     * Meethod untuk mendaftar pada firebase
     *
     * @param view
     */
    public void register(View view) {
        // mendaftarkan account
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Jika berhasil maka muncul toast
                if(task.isSuccessful()) Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                else Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
