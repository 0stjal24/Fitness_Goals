package com.example.lukeboyde.fitnessgoals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterNew extends AppCompatActivity {

    WorkoutDatabaseHelper db;

    EditText RegFirst, RegLast, RegEmail, RegPass, RegConP;
    Button RegButton, LogButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);


        db = new WorkoutDatabaseHelper(this);
        RegFirst = (EditText)findViewById(R.id.etfirstn);
        RegLast = (EditText)findViewById(R.id.etlastn);
        RegEmail=(EditText)findViewById(R.id.etEmail);
        RegPass=(EditText)findViewById(R.id.etPasswordR);
        RegConP=(EditText)findViewById(R.id.etConPass);
        RegButton=(Button)findViewById(R.id.btnReg);
        LogButton=(Button)findViewById(R.id.btnLogin2);
        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterNew.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = RegFirst.getText().toString();
                String s2 = RegLast.getText().toString();
                String s3 = RegEmail.getText().toString();
                String s4 = RegPass.getText().toString();
                String s5 = RegConP.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s4.equals(s5)){
                        Boolean checkEmail = db.checkEmail(s3);
                        if(checkEmail==true){
                            Boolean insert = db.insert(null,s1,s2,s3,s4);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email Already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Passwords do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

