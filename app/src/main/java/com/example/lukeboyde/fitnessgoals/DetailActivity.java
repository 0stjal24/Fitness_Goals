package com.example.lukeboyde.fitnessgoals;

import android.app.ActionBar;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get a reference to the fragment
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);

        //Get the ID of the workout the user clicks on from intent
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        //Create cursor
        try{
            SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(this);
            SQLiteDatabase db = workoutDatabaseHelper.getReadableDatabase();
            //Create cursor that gets Name & description from WORKOUT table where _id matches workoutID
            Cursor cursor = db.query("WORKOUT",
                    new String[] {"NAME", "DESCRIPTION"},
                    "_id = ?",
                    new String[]{Integer.toString(workoutId)},
                    null, null, null);


            //Move to first record in the cursor
            if (cursor.moveToFirst()) {

                //Get workout details from cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);

                workoutDetailFragment.setWorkout(workoutId);
            }
            cursor.close();
            db.close();

        } catch(SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
