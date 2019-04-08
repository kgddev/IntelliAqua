package com.example.kaustav.intelliaqua;

import android.os.AsyncTask;
import android.app.AlertDialog;
//import android.os.AsyncTask;

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
 * Created by Kaustav on 07-04-2019.
 */

public class Writedata2 extends AsyncTask<Void, Void, Void> {

    protected Void doInBackground(Void... params) {

            try
            {
                URL url= new URL("https://api.thingspeak.com/channels/745773/feeds.json?api_key=J3PGU8A0PMMFMCU2&results=2");

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));


                JSONObject object=new JSONObject();
                object.put("field2","23");

     /*String WRITEKEY = "YOUR_KEY";
                String strUpdateBase = "http://api.thingspeak.com/update";
                String strUpdateURI = strUpdateBase + "?key=" + WRITEKEY;
                String strField1 = "18";
                String strField2 = "42";
                HttpWebRequest ThingsSpeakReq;
                HttpWebResponse ThingsSpeakResp;

                strUpdateURI+= "&field1=" + strField1;
                strUpdateURI+= "&field2=" + strField2;

                ThingsSpeakReq = (HttpWebRequest)WebRequest.Create(strUpdateURI);

                ThingsSpeakResp = (HttpWebResponse)ThingsSpeakReq.GetResponse();

                if(!(string.Equals(ThingsSpeakResp.StatusDescription,"OK")))
                {
                    Exception exData = new Exception(ThingsSpeakResp.StatusDescription);
                    throw exData;
                }
            }
            catch (Exception ex)
            {
                lblError.InnerText = ex.Message;
                lblError.Style.Add("display", "block");
                throw;
            }*/
        } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
