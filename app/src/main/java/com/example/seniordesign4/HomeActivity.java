package com.example.seniordesign4;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Set;
import android.util.Log;


public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        mStatusBlueTv = findViewById(R.id.mStatusBlueTv);
        mPairedTv = findViewById(R.id.mPairedTv);


        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);

        // Load data passed in from the Login activity and display it in a Toast.
        Intent intent = getIntent();
        String welcome = intent.getStringExtra("welcomeMessage");
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("tag", "Receiving in Broadcast Receiver");
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d("tag", "Found " + deviceName + " at address " + deviceHardwareAddress);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver);
        mBlueAdapter.cancelDiscovery();
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

        mBlueAdapter.cancelDiscovery();
        showToast("Making Your Device Discoverable");
        //Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //getActivity().startActivityForResult(intent, REQUEST_DISCOVER_BT);

        mBlueAdapter.startDiscovery();

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
                Log.d("tag", "resultCode = " + resultCode + " in onActivityResult");
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
