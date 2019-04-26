package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static TextView Temp;
    //public  TextView Temp;
    public static TextView Humid;

    public static double HumidWed;

    //public static double TestHumid;
    public static String TestHumid;
    public static double humidity;// To obtain the humidity from fetch data and use it to call the dialog box

    public static TextView Third;
    private Button Electricity;

    public void test(double b)
    {
       humidity=b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Temp=(TextView)findViewById(R.id.tvTemp);
        Humid=(TextView)findViewById(R.id.tvHumid);

        Third=(TextView)findViewById(R.id.tv3);

        Electricity=(Button) findViewById(R.id.b1);

        Electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent1);

            }
        });




       /* else if (humidity>34)
        {
            //Display the alert for opinion
            AlertDialog.Builder builder3;
            builder3 = new AlertDialog.Builder(SecondActivity.this);
            builder3.setMessage("Do You want to Switch Off The Pump : Yes or No ? ");
            builder3.setTitle("Pump Alert");

            builder3.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder builder2;
                    builder2 = new AlertDialog.Builder(SecondActivity.this);
                    builder2.setMessage("OK !!! ");
                    builder2.setTitle("Pump Alert 2");
                    if(which==1)
                    {
                        // Set JSON Pump Satus to one


                    Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                    startActivity(intent);
                        startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }
                }
            });


            builder3.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which==0)
                    {
                        //Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                        //startActivity(intent);
                        //startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
                        Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        startActivity(intent);
                        startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    //}
                }
            });

            AlertDialog alertDialog=builder3 .create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }*/
                    // Hello
                    /* jjgjfgjmh */

        //Temp.setText("Number of Attempts Remaining: "+Integer.toString(count)); */

        // TODO: 08-04-2019
        // We will try to access Humidity from here and try to call ThirdActivity


        work();

    }

    private void work()
    {
        FetchData process = new FetchData();
        process.execute();
        try {
            Thread.sleep(2000);
            TestHumid=Third.getText().toString();

            if(TestHumid!=null && !TestHumid.isEmpty() &&!TestHumid.equals("null") && !TestHumid.equals(""))
            {
                humidity=0.0;

            }

            else
                humidity=Double.parseDouble(TestHumid);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*FetchData process = new FetchData();
        process.execute();*/

       /* WriteData obj =new WriteData();
        obj.execute();*/



        //Writedata2 obj=new Writedata2();
        //obj.execute();
        //TestHumid=Double.parseDouble(process.parsedSinglTemp);










        /*if (humidity ==0.0)
        {
            Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
            startActivity(intent);
        }*/
        //double humidity2=process.Humidity+100000;
        //String s=Double.toString(humidity2);

        //Third.setText(s );
        //double t=Double.parseDouble(process.parsedDataTemp);
        //double h=Double.parseDouble(process.parsedDataHumid);
        /*   int t=Integer.parseInt(process.parsedDataTemp);*/

        //if(h<=30)
        //if(t==-999)



        if(humidity<10.0)
        {
            //Display the alert for opinion
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage("Do You want to Switch On The Pump : Yes or No ? ");
            builder.setTitle("Pump Alert");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //if(which==1)
                    //{
                    //Set JSON Pump Satus to one


                    Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                    startActivity(intent);
                    //startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    //}

                }
            });


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //if(which==0)
                    //{
                    //Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                    //startActivity(intent);
                    //startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
                    //Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                    //startActivity(intent);
                    //startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    //}


                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }
}
