package com.example.kaustav.intelliaqua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URL;

public class ArduinoWifiControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_wifi_control);
    }

         /* public class ArduinoWifiControlActivity extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                try {
                /* Change the IP to the IP you set in the arduino sketch */
                   /* URL url = new URL("http://192.168.1.177/?" + params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                        result.append(inputLine).append("\n");

                    in.close();
                    connection.disconnect();
                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }*/

}
