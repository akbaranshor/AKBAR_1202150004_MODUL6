package com.example.android.akbar_1202150004_modul6;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    // Deklarasi variabel
    EditText email, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inisiasi pada variabel
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        // Jika ada user login maka masuk ke home
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            // pindah aktivitas
            startActivity(new Intent(this, HomeActivity.class));
        }

    }

    public void login(View view) {

        // Mengecek jika valid, maka langsung login
        if (checkValid()) {
            // Login menggunakan firebase auth
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // Jika berhasil, maka pindah aktivitas
                    if (task.isSuccessful()) {
                        finish();
                        // pindah aktivitas
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    } else Toast.makeText(MainActivity.this, "Akun belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean checkValid() {
        // Dinamik inisiaasi
        boolean emailValid = TextUtils.isEmpty(email.getText().toString());
        boolean passwordValid = TextUtils.isEmpty(password.getText().toString());

        // Validator
        if (emailValid || passwordValid) {
            if (emailValid == false) {
                // Memunculkan Toast
                Toast.makeText(this, "Password kosong", Toast.LENGTH_SHORT).show();
            } else {
                // Memunculkan Toast
                Toast.makeText(this, "Email kosong", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    // Pindah aktivitas ke register form
    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            // Logout dari firebase account
            firebaseAuth.signOut();
            finish();
            // pindah aktivitas
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }

}

