package com.example.kaustav.intelliaqua;

import android.app.AlertDialog;
import android.content.Intent;
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
    String dataParsed="";
    String singleParsed="";
    int hr,min,sec;
    String time="";
    String date="";
    String parsedSingle = "";
    String parsedData = "";
    String parsedSingle2 = "";
    String parsedData2 = "";

    String parsedSinglTemp = "";
    String parsedDatapump = "";

    String parsedSingleHumid = "";
    String parsedDataHumid = "";

    public double Humidity;

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url= new URL("https://api.thingspeak.com/channels/745773/feeds.json?api_key=J3PGU8A0PMMFMCU2&results=2");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while(line !=null)
            {
                line=bufferedReader.readLine();
                data=data+line;

            }
            /*JSONArray JA = new JSONArray(data);

            for(int i=0;i<JA.length();i++)
            {
                JSONObject JO= (JSONObject) JA.get(i);
                singleParsed="Temperature"+JO.get("field1")+"\n"+
                             "Humidity"+JO.get("field2")+"\n";

                dataParsed=dataParsed+singleParsed;
            }*/

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
                /*parsedSingle = "Collected on : "+ date +" , "+hr+":"+min+":"+sec+" hrs\n"+
                        "\t\tTemperature: " + JO1.getString("field1").toString() + " °C" +
                        "\n\t\tHumidity:" + JO1.getString("field2").toString() +" g/cubic m" + "\n\n";
                parsedData = parsedData + parsedSingle;*/

                //parsedSingle = "Temperature: " + JO1.getString("field1").toString() + " °C\n" ;

                //parsedSingle2 = "Humidity  : \n" + JO1.getString("field2").toString() + " %\n" ;
                parsedDatapump = JO1.getString("field2").toString();
                if(parsedDatapump.equals("0"))
                    parsedDatapump = "off";
                else
                    parsedDatapump = "on";
                parsedSingle = "Humidity    : " + JO1.getString("field1").toString() + " %\n" ;
                parsedSingle2 = "Pump Status: \n" + parsedDatapump ;

                parsedSinglTemp=JO1.getString("field1").toString();
                parsedSingleHumid=JO1.getString("field2").toString();


                //SecondActivity obj =new SecondActivity();
                //obj.test(Double.parseDouble(parsedSinglTemp));
                /*double t=Double.parseDouble(parsedDataTemp);
                double h=Double.parseDouble(parsedDataHumid);

                if((t>=25)||(h<=30))
                {
                    //Dispaly the alert for opinion
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(SecondActivity.this,FetchData.class);
                }*/



                //parsedData = parsedData + parsedSingle;

                parsedData =  parsedSingle;
                parsedData2 =  parsedSingle2;


                //Humidity=Double.parseDouble(parsedSinglTemp);



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
        //qSecondActivity obj=new SecondActivity();
        SecondActivity.Temp.setText(this.parsedData);// Sets the Humidity
        //obj.Temp.setText(this.parsedData);
        SecondActivity.Humid.setText(this.parsedData2);// Shows Pump Status
        //parsedSinglTemp=parsedSinglTemp+1;
        SecondActivity.Third.setText(this.parsedSinglTemp);// Temporary third option


        //Humidity=Humidity+1;
        //SecondActivity.Third.setText(Double.toString(Humidity));


        // We have Received the double data for humidity
        // and now we will try to call ThirdActivity based on Humidity
        // i> Equal to 1024 for test
        // ii> Less Than 40 for actual humidity less
        // TODO: 08-04-2019

         //Humidity=Double.parseDouble(parsedSinglTemp);




    }


}
