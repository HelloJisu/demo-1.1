package com.reziena.user.reziena_1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;import java.util.UUID;

public class BTOnActivity extends AppCompatActivity {

    BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    int countDown;
    TextView txt1, txt2, txt3;
    Handler mHandler;
    Thread t;
    Button retry, no_retry;
    boolean isFound=false;
    boolean nowInter=false;
    String action="";
    BluetoothDevice device;
    TimerTask timerTask;

    private static final UUID MY_UUID = UUID.fromString("00000003-0000-1000-8000-00805f9b34fb");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bton);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        retry = findViewById(R.id.retry);
        no_retry = findViewById(R.id.no_retry);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.9); //Display 사이즈의 100%
        int height = (int) (display.getHeight() * 0.4);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        Log.e("aakkkkk", "init");

        mHandler = new Handler();

        txt1.setText("기기없음\n앱과 연결하려면 기기를 켜세요\n기기를 켜면 BLUE불빛이 30초동안 반짝 거릴겁니다\n");

        startThread();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.no_retry:
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.retry:
                        intent = new Intent(getApplicationContext(), BTOnActivity.class);
                        startActivity(intent);
                        finish();
                        //txt2.setText(countDown+"초");
                        //retry.setVisibility(View.INVISIBLE);
                        //no_retry.setVisibility(View.INVISIBLE);
                        //startThread();
                        break;
                }
            }
        };
        retry.setOnClickListener(onClickListener);
        no_retry.setOnClickListener(onClickListener);
    }

    private void startThread() {
        Log.e("init", "startThread");
        countDown = 45;

        txt2.setText(countDown+"초");
        retry.setVisibility(View.INVISIBLE);
        no_retry.setVisibility(View.INVISIBLE);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                countDown--;
                Log.e("countdown", String.valueOf(countDown));
                // UI 작업 X

                // UI 작업 O
                runOnUiThread(() -> {
                    txt3.setText(countDown+"초");
                    if (countDown<=0) {
                        unregisterReceiver(mBroadcastReceiver1);
                        timerTask.cancel();
                        txt2.setText("디바이스를 못 찾았어요");
                        retry.setVisibility(View.VISIBLE);
                        no_retry.setVisibility(View.VISIBLE);
                    }
                });
            } // end of run
        }; // end of timerTask
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
        discoveryStart();
    }

    private void discoveryStart() {
        Log.e("init: ", "discoveryStart!!;");

        /** Filtering Broadcast Receiver */
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(BluetoothDevice.ACTION_FOUND);
        filter1.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter1.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        /** Start Broadcast Receiver */
        this.registerReceiver(mBroadcastReceiver1, filter1);

        mBtAdapter.startDiscovery();
    }

    private void bond() {
        Log.e("init: ", "bonding!!;");

        /** Filtering Broadcast Receiver */
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        /** Start Broadcast Receiver */
        this.registerReceiver(mBroadcastReceiver2, filter2);
    }

    public boolean connectToDevice(String address) {
        device = mBtAdapter.getRemoteDevice(address);

        if (mBtAdapter == null || address == null) {
            Log.e("BT", "mBtAdapter==null & address==null");
            return false;
        }

        Log.e("BT", "startBTConnection: initializing RFCOM Bluetooth Connection");

        BluetoothConnectionService mBluetoothConnection = new BluetoothConnectionService(getApplicationContext());
        mBluetoothConnection.startClient(device, MY_UUID);

        //discoveryStart();

        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            action = intent.getAction();
            //Log.e("BT", "onReceive: ACTION____________come in Receiver1");

            /** When Deviece Found */
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.e("BT", "action_found_______________");
                /*if (HomeActivity.isHave) {
                    Log.e("isHave??", "Yes~!!~!~!!!");
                    unregisterReceiver(mBroadcastReceiver1);
                    timerTask.cancel();
                    connectToDevice(HomeActivity.deviceAddress);
                }*/
                device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                if (device.getName()!=null) {
                    Log.e("action_foundt", device.getName());
                    if (device.getName().equals(HomeActivity.devName)) {
                        isFound = true;
                        nowInter=true;
                        Log.e(HomeActivity.devName, "디바이스 찾ㅇa!");
                        //nowInter=true;
                        unregisterReceiver(mBroadcastReceiver1);
                        timerTask.cancel();

                        if (device.getBondState()==BluetoothDevice.BOND_NONE) {
                            Log.e("bondedState?!", String.valueOf(device.getBondState())+"BOND_NONE");
                            device.createBond();
                            bond();
                        } else if (device.getBondState()==BluetoothDevice.BOND_BONDED) {
                            Log.e("bondedState?!", String.valueOf(device.getBondState())+"BOND_BONDED");// BOND_BONDED -> 12
                            connectToDevice(device.getAddress());
                        }
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.e("꺅!!", action);
                unregisterReceiver(mBroadcastReceiver1);
                timerTask.cancel();
                if (BluetoothConnectionService.success) {
                    Log.e("bt connection", "success");
                    intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    txt2.setText("디바이스를 못 찾았어요");
                    txt3.setText("0");
                    nowInter=true;
                    retry.setVisibility(View.VISIBLE);
                    no_retry.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            action = intent.getAction();
            Log.e("BT", "onReceive: ACTION____________come in mBroadcastReceiver2");
            Log.e("action", action);

            device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (device.getBondState()==BluetoothDevice.BOND_NONE) {
                Log.e("BT", "BOND_NONE");
            } else if (device.getBondState()==BluetoothDevice.BOND_BONDED) {
                Log.e("bondedState?!", "BOND_BONDED");
                Log.e("Address", device.getAddress());
                connectToDevice(device.getAddress());
            }
        }
    };
}