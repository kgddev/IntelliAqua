package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static TextView Temp;
    public static TextView Humid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Temp=(TextView)findViewById(R.id.tvTemp);
        Humid=(TextView)findViewById(R.id.tvHumid);

        FetchData process = new FetchData();
        process.execute();

        /*double t=Double.parseDouble(process.parsedDataTemp);
        double h=Double.parseDouble(process.parsedDataHumid);*/

        /*
        if((t>=25)||(h<=30))
        {
            //Display the alert for opinion
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage("Do You want to Switch On The Pump : Yes or No ? ");
            builder.setTitle("Pump Alert");

            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==1)
                    {
                        Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        startActivity(intent);
                        //startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }
                }
            });


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==0)
                    {
                        /*Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        startActivity(intent); */
                        /*startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }  */
                    // Hello
                    /* jjgjfgjmh */

        //Temp.setText("Number of Attempts Remaining: "+Integer.toString(count));
    }
}
