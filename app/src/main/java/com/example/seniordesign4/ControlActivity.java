package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ControlActivity extends AppCompatActivity {

    private TextView movementInfo;
    private BluetoothChatService mChatService;
    private StringBuffer mOutStringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        movementInfo = (TextView) findViewById(R.id.movementInfo);

        Intent intent = getIntent();
        mChatService = (BluetoothChatService)intent.getSerializableExtra("chatService");
    }

    public void Left(View view) {
        movementInfo.setText("Left");
        sendMessage("Left");
    }

    public void Right(View view) {
        movementInfo.setText("Right");
        sendMessage("Right");
    }

    public void Up(View view) {
        movementInfo.setText("Up");
        sendMessage("Up");
    }

    public void Down(View view) {
        movementInfo.setText("Down");
        sendMessage("Down");
    }

    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }
        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);
            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
        }
    }
}
