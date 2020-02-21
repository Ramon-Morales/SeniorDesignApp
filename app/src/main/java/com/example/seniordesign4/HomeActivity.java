package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void controlRobot(View view) {
        Intent intent = new Intent(this, ControlActivity.class);

        /* Use this to pass info to the next activity
        EditText editText = (EditText) findViewById(R.id.);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        */

        startActivity(intent); // Start control page
    }
}
