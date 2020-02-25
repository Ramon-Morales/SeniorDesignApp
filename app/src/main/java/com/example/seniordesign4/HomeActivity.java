package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;
    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mStatusBlueTv = findViewById(R.id.mStatusBlueTv);
        mPairedTv = findViewById(R.id.mPairedTv);

        // TODO: Initialize the button variables...

        if (mBlueAdapter == null) {
            mStatusBueTv.setText("Bluetooth is not available");
        } else {
            mStatusBlueTv.setText("Bluetooth is available");
        }

        if (mBlueAdapter.isEnabled()) {

        }

        // Load data passed in from the Login activity and display it in a Toast.
        Intent intent = getIntent();
        String welcome = intent.getStringExtra("welcomeMessage");
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    public void onclick(View view) {

        if (!mBlueAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        } else {
            showToast("Bluetooth is already enabled");
        }

    }

    public void discoverclick(View view) {
        if (!mBlueAdapter.isDiscovering()) {
            showToast("Making Your Device Discoverable");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(intent, REQUEST_DISCOVER_BT);
        }
    }

    public void offclick(View view) {
        if (mBlueAdapter.isEnabled()) {
            mBlueAdapter.disable();
            showToast("Turning Bluetooth Off");
        } else {
            showToast("Bluetooth is already off");
        }
    }

    public void pairedclick(View view) {
        if (mBlueAdapter.isEnabled()) {
            mPairedTv.setText("Paired Devices");
            Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                mPairedTv.append("\nDevice: " + device.getName() + "," + device);
            }
        } else {
            // bluetooth is off so can't get paired devices
            showToast("Turn on bluetooth to get paired devices");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    showToast("Bluetooth is on");
                } else {
                    // User denied to turn Bluetooth on
                    showToast("Could not turn on Bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    public void connectToRobot(View view) {

        // Bluetooth stuff here...
    }
}
