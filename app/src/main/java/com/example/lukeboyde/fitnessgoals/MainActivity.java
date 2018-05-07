package com.example.lukeboyde.fitnessgoals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                        if (position == 0){
//                            Intent intent = new Intent(MainActivity.this, CreateWorkoutActivity.class);
//                            startActivity(intent);
                        } else if (position == 1){
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(intent);
                        } else if (position == 2){
                            Intent intent = new Intent(MainActivity.this, Camera.class);
                            startActivity(intent);
                        } else if (position == 3){
                            Intent intent = new Intent(MainActivity.this, StopwatchActivity.class);
                            startActivity(intent);
                        } else if (position == 4){
                            Intent intent = new Intent(MainActivity.this, Running.class);
                            startActivity(intent);
                        }
                    }
                };
        //Add listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_workout:
                //code to run when the create workout item is clicked
                return true;
            case R.id.action_choose_workout:
                //code to run when the choose workout item is clicked
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                return true;
            case R.id.action_pictures:
                //code to run when the picture item is clicked
                intent = new Intent(this, Camera.class);
                startActivity(intent);
                return true;
            case R.id.action_stopwatch:
                //code to run when the stopwatch item is clicked
                intent = new Intent(this, StopwatchActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_run:
                //code to run when track run item is clicked
                intent = new Intent(this, Running.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
