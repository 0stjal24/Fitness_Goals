package com.example.lukeboyde.fitnessgoals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateWorkoutActivity extends AppCompatActivity {

    WorkoutDatabaseHelper db;
    EditText editWname, editExercise, editSetReps;
    Button btnAddW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        db = new WorkoutDatabaseHelper(this);

        editWname = (EditText)findViewById(R.id.etWorkoutName);
        editExercise = (EditText)findViewById(R.id.etEx);
        editSetReps = (EditText)findViewById(R.id.etSr);
        btnAddW = (Button)findViewById(R.id.btnAddW);
        AddWorkout();
    }

    public void AddWorkout() {
        btnAddW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editSetReps.getText().toString();
                int desiredValue = Integer.parseInt(value);
                boolean isInserted = db.insertWorkout(editWname.getText().toString(),
                        editExercise.getText().toString(),
                        desiredValue);
                if (isInserted)
                    Toast.makeText(CreateWorkoutActivity.this, "Workout Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CreateWorkoutActivity.this, "Workout was not inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
