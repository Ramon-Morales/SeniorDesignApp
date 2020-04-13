package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Error extends AppCompatActivity {

    private int notificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Intent intent = getIntent();
        String errorMessage = intent.getStringExtra("errorMessage");
        String errorTitle = intent.getStringExtra("errorTitle");
        notificationID = intent.getIntExtra("id", -1);

        TextView title = findViewById(R.id.errorTitle);
        TextView description = findViewById(R.id.errorDescription);

        title.setText(errorTitle);
        description.setText(errorMessage);

    }

    // Android versions that do not support
    public void clearClick(View view) {
        Log.i("Channel", "ID" + notificationID);
        if (notificationID != -1) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.cancel(notificationID);
            }
        }

        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
        this.finish();
    }
}
