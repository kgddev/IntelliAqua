package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
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

        Temp = (TextView) findViewById(R.id.tvTemp);
        Humid = (TextView) findViewById(R.id.tvHumid);

        Third = (TextView) findViewById(R.id.tv3);

        content();
    }
    public void content(){
        FetchData process = new FetchData();
        process.execute();
        refresh(2000);
    }

    private void refresh(long i) {
        FetchData process = new FetchData();
        process.execute();
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                content();
            }
        };
        handler.postDelayed(runnable,i);
    }

}
