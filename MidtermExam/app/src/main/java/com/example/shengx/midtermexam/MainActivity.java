package com.example.shengx.midtermexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mathBtn, triviaBtn;
    EditText num1,num2;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GetNumberService myservice=new GetNumberService("myservice");
        myintent=new Intent(this,GetNumberService.class);

        mathBtn=(Button)findViewById(R.id.mathBtn);
        triviaBtn=(Button)findViewById(R.id.triviaBtn);
        num1=(EditText)findViewById(R.id.num1);
        num2=(EditText)findViewById(R.id.num2);

        mathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberBuilder(GetNumberService.numberMethod_Math);
            }
        });

        triviaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberBuilder(GetNumberService.numberMethod_trivia);
            }
        });



    }

    public void numberBuilder(String method){
        String number_1=num1.getText().toString();
        String number_2=num2.getText().toString();
        if(number_1.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter first number!",Toast.LENGTH_LONG).show();
        }else if(number_2.equals("")){
            myintent.setAction(method);
            myintent.putExtra("number",number_1);
        }else if(!number_1.equals("")&&!number_2.equals("")) {
            myintent.setAction(method);
            myintent.putExtra("number",number_1+".."+number_2);
        }
        startService(myintent);
    }
}
