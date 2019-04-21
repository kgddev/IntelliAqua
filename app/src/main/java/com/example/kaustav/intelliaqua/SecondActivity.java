package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SecondActivity extends AppCompatActivity {

    public static TextView Temp;
    //public  TextView Temp;
    public static TextView Humid;

    //public static double TestHumid;
    public static String TestHumid;
    public static double humidity;// To obtain the humidity from fetch data and use it to call the dialog box

    public static TextView Third;

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

        FetchData process = new FetchData();
        process.execute();



        //Writedata2 obj=new Writedata2();
        //obj.execute();
        //TestHumid=Double.parseDouble(process.parsedSinglTemp);


        TestHumid=Third.getText().toString();

       if(TestHumid!=null && !TestHumid.isEmpty() &&!TestHumid.equals("null") && !TestHumid.equals(""))
       {
           humidity=0.0;

       }

       else
          humidity=Double.parseDouble(TestHumid);



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



         if(humidity < 30.0) {
            /* Display the alert for opinion */
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage("Do You want to Switch On The Pump : Yes or No ? ");
            builder.setTitle("Pump Alert");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(which==1)
                    {
                        sendDataToServer(which);//Set JSON Pump Satus to one
                        Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
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
                        sendDataToServer(which);
                        //Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                        //startActivity(intent);
                        //startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
                        //Intent intent=new Intent(SecondActivity.this,ArduinoWifiControlActivity.class);
                        //startActivity(intent);
                        //startActivity(new Intent(SecondActivity.this,SecondActivity.class));
                    }


                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }





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




    }
    private String formatDataAsJson(int w){
        final JSONObject root = new JSONObject();
        try {
            if(w==1) {
                root.put("field3", "1");
            }
            else {
                root.put("field3", "0");
            }
            return root.toString();
        } catch (JSONException e) {
            Log.d("JWP","CAN'T FORMAT JSON");
        }
        return null;
    }
    private void sendDataToServer(int w){
        final String json = formatDataAsJson(w);


        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                return getServerResponse(json);
            }


        }.execute();
    }
    private String getServerResponse(String json) {
        HttpPost post=new HttpPost("https://api.thingspeak.com/update?api_key=EGGJKPU3QDCY8UAT");
        try {
            StringEntity entity=new StringEntity(json);
            post.setEntity(entity);
            DefaultHttpClient client = new DefaultHttpClient();
            BasicResponseHandler handler=new BasicResponseHandler();
            String response = client.execute(post,handler);
            return response;
        } catch (UnsupportedEncodingException e) {
            Log.d("JWP",e.toString());
        } catch (ClientProtocolException e) {
            Log.d("JWP",e.toString());
        } catch (IOException e) {
            Log.d("JWP",e.toString());
        }
        return "Unable to contact server";
    }
}
