package com.example.kaustav.intelliaqua;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {

    public static TextView SMoist;
    public  static TextView RTime;
    public static TextView PumpStat;

    //public static double HumidWed;

    //public static double TestHumid;
    public static String TestHumid;
    //public static double humidity;// To obtain the humidity from fetch data and use it to call the dialog box

    //public static TextView Third;
    //private Button Electricity;

    /*
    Bluetooth On / Off Button
     */

    private Button ON;
    private Button OFF;

    String address = null , name=null; // Address is Bluetooth Address , Name is Bluetooth Device Name
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SMoist=(TextView)findViewById(R.id.tvMoist);
        PumpStat=(TextView)findViewById(R.id.tvPump);
        RTime=(TextView)findViewById(R.id.tvTime);


        /*
        Bluetooth Button findViewById
         */
        ON=(Button) findViewById(R.id.PumpON);
        OFF=(Button) findViewById(R.id.PumpOFF);



        /*Electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent1);

            }
        });*/


        try {
            setw();
        }
        catch (Exception e) {}






        // TODO: 08-04-2019
        // We will try to access Humidity from here and try to call ThirdActivity


        work();

    }

    private void work()
    {
        FetchData process = new FetchData();
        process.execute();

        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        refresh(2000);


    }

    private void refresh(long i)
    {
        FetchData process = new FetchData();
        process.execute();
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                work();
            }
        };
        handler.postDelayed(runnable,i);
    }






    @SuppressLint("ClickableViewAccessibility")
    private void setw() throws IOException
    {
        //ON=(TextView)findViewById(R.id.PumpON);
        bluetooth_connect_device();



        ON=(Button)findViewById(R.id.PumpON);
        OFF=(Button)findViewById(R.id.PumpOFF);

        /*ON.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("f");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("b");}
            return true;}
        });*/

        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                led_on_off("f");
            }
        });

        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                led_on_off("b");
            }
        });

    }

    private void bluetooth_connect_device() throws IOException
    {
        try
        {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size()>0)
            {
                for(BluetoothDevice bt : pairedDevices)
                {
                    address=bt.getAddress().toString();name = bt.getName().toString();
                    Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_SHORT).show();

                }
            }

        }
        catch(Exception we){}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
        btSocket.connect();
        /*try { t1.setText("BT Name: "+name+"\nBT Address: "+address); }
        catch(Exception e){}*/
    }

    private void led_on_off(String i)
    {
        try
        {
            if (btSocket!=null)
            {

                btSocket.getOutputStream().write(i.toString().getBytes());
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}
