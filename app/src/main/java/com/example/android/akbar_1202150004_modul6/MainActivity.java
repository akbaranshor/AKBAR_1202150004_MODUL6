package com.example.android.akbar_1202150004_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextInputLayout emailWrapper, passwordWrapper;
    EditText email, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            emailWrapper = findViewById(R.id.email_input);
            passwordWrapper = findViewById(R.id.password_input);
            emailWrapper.setError(null);
            emailWrapper.setErrorEnabled(false);
        */
        email = findViewById(R.id.email_edit);
        password = findViewById(R.id.password_edit);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Main2Activity.class));
        }

    }

    public void login(View view) {
        /*
            emailWrapper.setErrorEnabled(true);
            emailWrapper.setError("Required");
        */
        if (checkValid()) {
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    } else Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean checkValid() {
        boolean emailValid = TextUtils.isEmpty(email.getText().toString());
        boolean passwordValid = TextUtils.isEmpty(password.getText().toString());

        if (emailValid || passwordValid) {
            if (emailValid == false) {
                Toast.makeText(this, "Password kosong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Email kosong", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    public void register(View view) {
        finish();
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
