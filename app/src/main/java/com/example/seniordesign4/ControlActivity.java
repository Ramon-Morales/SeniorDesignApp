package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;

public class ControlActivity extends AppCompatActivity {

    private TextView movementInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
         movementInfo = (TextView) findViewById(R.id.movementInfo);
    }

    public void Left(View view) {
        movementInfo.setText("Left");
    }

    public void Right(View view) {
        movementInfo.setText("Right");
    }

    public void Up(View view) {
        movementInfo.setText("Up");
    }

    public void Down(View view) {
        movementInfo.setText("Down");
    }
}
