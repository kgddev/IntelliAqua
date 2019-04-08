package com.example.kaustav.intelliaqua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ThirdActivity extends AppCompatActivity {

    public static TextView PumpMsg;

    public static Button ON;
    public static Button OFF;

    public static ToggleButton Test;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        PumpMsg=(TextView)findViewById(R.id.tvPumpMessage);

        ON=(Button)findViewById(R.id.onBtn);
        OFF=(Button)findViewById(R.id.off_Btn);

        Test=(ToggleButton)findViewById(R.id.tbtnTest);


        PumpMsg.setText("Do You WANT TO SWITCH ON THE PUMP ?");
    }
}
