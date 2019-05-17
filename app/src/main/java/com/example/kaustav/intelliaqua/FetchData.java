package com.example.kaustav.intelliaqua;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kaustav on 21-03-2019.
 */

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data="";
    int hr,min,sec;
    String time="";
    String date="";
    String parsedSingle = "";
    String parsedData = "";
    String parsedSingle2 = "";
    String parsedData2 = "";

    String parsedSinglTemp = "";
    String parsedSingleHumid = "";
    String parsedDate = "";

    public double Humidity;

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url= new URL("https://api.thingspeak.com/channels/745773/feeds.json?api_key=J3PGU8A0PMMFMCU2&results=2");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while(line != null)
            {
                line=bufferedReader.readLine();
                data=data+line;

            }

            JSONObject JO = (JSONObject) new JSONTokener(data).nextValue();
            JSONArray JA = (JSONArray) JO.get("feeds");
            for (int i =0;i<JA.length();i++){
                JSONObject JO1 = (JSONObject) JA.get(i);
                time=JO1.getString("created_at");
                date=time.substring(0,10);
                hr=Integer.parseInt(time.substring(11,13));
                min=Integer.parseInt(time.substring(14,16));
                sec=Integer.parseInt(time.substring(17,19));

                min = min+30;
                if(min>60){
                    min=min-60;
                    hr++;
                }
                hr=hr+5;
                parsedDate = "Collected on : "+ date +" , \n    "+hr+":"+min+":"+sec+" hrs\n";
                String b=JO1.getString("field1").toString();
                parsedSingle = "       Humidity\n"+"            "+b+"%";

                String a=JO1.getString("field2").toString()  ;
                int p=Integer.parseInt(a);
                //parsedSingle2 = "Pump Status: \n"+ JO1.getString("field2").toString()  ;
                if(p==1)
                parsedSingle2 = "   Pump Status: \n"+"          ON";
                else if(p==0)
                parsedSingle2 = "   Pump Status: \n"+"          OFF";
                else{

                }


                parsedSinglTemp=JO1.getString("field1").toString();
                parsedSingleHumid=JO1.getString("field2").toString();

                parsedData =  parsedSingle;
                parsedData2 =  parsedSingle2;
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        SecondActivity.Temp.setText(this.parsedData);// Sets the Humidity
        SecondActivity.Motor.setText(this.parsedData2);// Shows Pump Status
        SecondActivity.Date.setText(this.parsedDate);

        // We have Received the double data for humidity
        // and now we will try to call ThirdActivity based on Humidity
        // i> Equal to 1024 for test
        // ii> Less Than 40 for actual humidity less
        // TODO: 08-04-2019

         //Humidity=Double.parseDouble(parsedSinglTemp);




    }


}
