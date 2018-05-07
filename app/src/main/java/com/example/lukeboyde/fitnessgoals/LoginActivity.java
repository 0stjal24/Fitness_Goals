package com.example.lukeboyde.fitnessgoals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailLogin, passwordLogin;
    Button btnLogin;

    WorkoutDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new WorkoutDatabaseHelper(this);
        emailLogin = (EditText) findViewById(R.id.etEmailLogin);
        passwordLogin = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                Boolean Checkemailpass = db.emailpassword(email,password);
                if(Checkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Successful Login!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
