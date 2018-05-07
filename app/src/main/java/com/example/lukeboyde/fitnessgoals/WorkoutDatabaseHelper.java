package com.example.lukeboyde.fitnessgoals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lukeboyde on 09/04/2018.
 */

public class WorkoutDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "workouts"; //name of the database
    private static final int DB_VERSION = 1; //Version of the database


    public WorkoutDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table customWorkout(Id INTEGER PRIMARY KEY, workoutname TEXT, exercise TEXT, setrep INTEGER)");
        db.execSQL("Create table user(Id INTEGER PRIMARY KEY,firstname TEXT, lastname TEXT, email TEXT,password TEXT)");
        db.execSQL("CREATE TABLE WORKOUT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT) ;");
        insertWorkout(db, "6-round Bodyweight WOD", "Do 6 rounds of:\n10 Pushups\n15 V-Ups\n20 Jump squats");
        insertWorkout(db, "16-minute AMRAP","In 16 minutes, do as many rounds as possible(AMRAP)of:\n10 Burpees\n10 Pullups\n20 Lunges\n20 Situps");
        insertWorkout(db,"20-minute EMOM", "Do this pattern 5 times for 20 total minutes.\nMinute 1:15 Pushups\nMinute 2:10 Pullups\nMinute3:15 Situps\nMinute 4:20 Lunges");
        insertWorkout(db,"Ten to one", "This workout uses an ascending/descending rep scheme,\nBurpees: 10-9-8-7-6-5-4-3-2-1\nPullups: 1-2-3-4-5-6-7-8-9-10");
        insertWorkout(db, "10 rounds of 20", "Do 10 rounds of the following:\n20 Mountain Climbers\n20 Situps\n20 Air squats\n20 Lunges");
        insertWorkout(db, "Murph", "1-mile Run\n100 Pullups\n200 Pushups\n300 Air squats\n 1-mile Run");
        insertWorkout(db, "7-minute AMRAP", "In seven minutes, do as many rounds as possible of the following:\n10 Lunges\n10 Mountain Climbers\n10 Pushups");
        insertWorkout(db, "16-minute Upper-Body AMRAP", "In 16 minutes do as many rounds as possible of:\n8 Burpees\n8 Push ups\n16 Pullups\n16 Hanging Knee tucks or Situps");
        insertWorkout(db, "Full-throttle AMRAP", "In 20 minutes, do as many rounds as possible of:\n21 Jumping Air Squats\n15 Pushups\n9 Burpee Pullups");
        insertWorkout(db, "High-rep Bodyweight Classic", "Do eight rounds of the following:\n10 Pushups\n10 Air squats\n10 Burpees\n10 Air squats");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);

    }

    //insert into database
    public boolean insert(Integer Id, String firstname, String lastname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", Id);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }

    //checking if email exists
    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking email and password
    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    private void insertWorkout(SQLiteDatabase db, String name, String description) {

        ContentValues workoutValues = new ContentValues();
        workoutValues.put("NAME", name);
        workoutValues.put("DESCRIPTION", description);
        db.insert("WORKOUT", null, workoutValues);
    }

    public Boolean insertWorkout(String workout, String exercise, Integer setreps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutname", workout);
        contentValues.put("exercise", exercise);
        contentValues.put("setrep", setreps);
        long result = db.insert("customWorkout", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

}
