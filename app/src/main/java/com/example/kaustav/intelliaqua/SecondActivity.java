package com.example.kaustav.intelliaqua;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {

    public static TextView Temp;
    public static TextView Motor;
    public static TextView Third;
    public static TextView Date;
    /*
    Bluetooth On / Off Button
     */
    Button btnOn, btnOff;
    Switch sw;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    char status='t';
    Boolean state=true;
    //SPP UUID
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(ThirdActivity.EXTRA_ADDRESS); //receive the address of the bluetooth device

        setContentView(R.layout.activity_second);
        Date=(TextView)findViewById(R.id.textView3);
        Temp=(TextView)findViewById(R.id.tvTemp);
        Motor=(TextView)findViewById(R.id.tvHumid);
        Third=(TextView)findViewById(R.id.textView2);
        //call the widgtes
        sw = (Switch)findViewById(R.id.switch1);
        btnOn = (Button)findViewById(R.id.PumpON);
        btnOff = (Button)findViewById(R.id.PumpOFF);
        sw.setText("Automatic Mode");
        btnOn.setEnabled(false);
        btnOff.setEnabled(false);
        Third.setVisibility(View.INVISIBLE);
        Motor.setVisibility(View.VISIBLE);
        new ConnectBT().execute(); //Call the class to connect
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                state = sw.isChecked();
                if(!state){
                    sw.setText("Manual Mode");
                    Motor.setVisibility(View.INVISIBLE);
                    Third.setVisibility(View.VISIBLE);
                    if(status == 't') {
                        btnOn.setEnabled(true);
                        Third.setText("Motor turned OFF Manually");}
                    else {
                        btnOff.setEnabled(true);
                        Third.setText("Motor turned ON Manually");
                    }
                    //commands to be sent to bluetooth
                    btnOn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnOn.setEnabled(false);
                            btnOff.setEnabled(true);
                            Third.setText("Motor turned ON Manually");
                            status='t';
                            turnOnMotor();      //method to turn on
                        }
                    });

                    btnOff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnOn.setEnabled(true);
                            btnOff.setEnabled(false);
                            Third.setText("Motor turned OFF Manually");
                            status='f';
                            turnOffMotor();   //method to turn off
                        }
                    });
                }
                else {
                    sendInitSignal('a');
                    sw.setText("Automatic Mode");
                    Motor.setVisibility(View.VISIBLE);
                    Third.setVisibility(View.INVISIBLE);
                    btnOn.setEnabled(false);
                    btnOff.setEnabled(false);
                }
            }
        });
        work();

    }

    private void work() {
        FetchData process = new FetchData();
        process.execute();
        //if()
        refresh(1000);
    }

    private void refresh(long i) {
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                work();
            }
        };
        handler.postDelayed(runnable,i);
    }
    private void sendInitSignal(char m) {
        if (btSocket!=null) {
            try { btSocket.getOutputStream().write(Character.toString(m).getBytes()); }
            catch (IOException e) {}
        }
    }
    private void turnOffMotor() {
        if (btSocket!=null) {
            try { btSocket.getOutputStream().write("f".getBytes()); }
            catch (IOException e) { }
        }
    }

    private void turnOnMotor(){
        if (btSocket != null){
            try{ btSocket.getOutputStream().write("t".getBytes());
            } catch (IOException e) { }
        }
    }

    private void msg(String s) { Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show(); }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true;
        @Override
        protected void onPreExecute() { progress = ProgressDialog.show(SecondActivity.this, "Connecting...", "Please wait!!!");}  //show a progress dialog
        @Override
        protected Void doInBackground(Void... devices){ //while the progress dialog is shown, the connection is done in background
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e) { ConnectSuccess = false; }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                msg("Connection Failed.Try Again and check if the bluetooth is available");
                finish();
            }
            else {
                msg("Connected.");
                isBtConnected = true; }
            progress.dismiss(); }
    }
    @Override
    public void onBackPressed(){
        if (btSocket!=null){ //If the btSocket is busy
            try { btSocket.close(); }//close connection

            catch (IOException e) { msg("Error");}
        }
        super.onBackPressed();
    }
}
