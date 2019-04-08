package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static TextView Temp;
    //public  TextView Temp;
    public static TextView Humid;

    public static double TestHumid;

    public static TextView Third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Temp=(TextView)findViewById(R.id.tvTemp);
        Humid=(TextView)findViewById(R.id.tvHumid);

        Third=(TextView)findViewById(R.id.tv3);

        FetchData process = new FetchData();
        process.execute();
        //Writedata2 obj=new Writedata2();
        //obj.execute();

        //TestHumid=Double.parseDouble(process.parsedSinglTemp);

        double humidity2=process.Humidity+100000;
        String s=Double.toString(humidity2);

        Third.setText(s );



        //double t=Double.parseDouble(process.parsedDataTemp);
        //double h=Double.parseDouble(process.parsedDataHumid);
        /*   int t=Integer.parseInt(process.parsedDataTemp);

        //if(h<=30)
        if(t==-999)
        {
            //Display the alert for opinion
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage("Do You want to Switch On The Pump : Yes or No ? ");
            builder.setTitle("Pump Alert");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==1)
                    {
                        Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        startActivity(intent);
                        startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }
                }
            });


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==0)
                    {
                        Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        startActivity(intent);
                        startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
                    // Hello
                    /* jjgjfgjmh */

        //Temp.setText("Number of Attempts Remaining: "+Integer.toString(count)); */

        // TODO: 08-04-2019
        // We will try to access Humidity from here and try to call ThirdActivity

        if (process.Humidity>=1023)
        {
            Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
            startActivity(intent);
        }


    }
}
