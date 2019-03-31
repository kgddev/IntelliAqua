package com.example.kaustav.intelliaqua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int count=5;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Login=(Button) findViewById(R.id.btnLogin);
        Info=(TextView)findViewById(R.id.tvInfo);

        Info.setText("Number of Attempts Remaining: "+Integer.toString(count));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }






    private void validate (String uName,String uPassword)
    {
        if(uName.equals("Admin") && uPassword.equals("12345"))
        {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }

        else
            {
               count--;

                Info.setText("Number of Attempts Remaining: "+Integer.toString(count));

                if (count==0)
                {
                    //Disable button
                    Login.setEnabled(false);

                }
            }
    }
}
