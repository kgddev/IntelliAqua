package com.example.kaustav.intelliaqua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {
    private int SLEEP_TIMER=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();


        LogoLauncher logo=new LogoLauncher();
        logo.start();

    }

    private class LogoLauncher extends Thread{
        public void run()
        {
            try
            {
                sleep(1000*SLEEP_TIMER); //1000ns =1 // STOPSHIP: 11-03-2019
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            Intent intent= new Intent(SplashScreenActivity.this,MainActivity.class);
            startActivity(intent);
            SplashScreenActivity.this.finish();
        }

}



}