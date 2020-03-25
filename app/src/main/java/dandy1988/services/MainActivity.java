package dandy1988.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String text1 = "";
    String text2 = "";

    CalculatorService.MyBinder calcServiceBinder;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calcServiceBinder = (CalculatorService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        final EditText EditText1 = findViewById(R.id.etValue1Number);
        final EditText EditText2 = findViewById(R.id.etValue2Number);
        final TextView textView1 = findViewById(R.id.tvValue2Text);
        final TextView TextViewNumber1 = findViewById(R.id.tvNumber1);
        final TextView TextViewNumber2 = findViewById(R.id.tvNumber2);
        final TextView TextViewResult = findViewById(R.id.tvResult);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText1.getText() != null) {
                    text1 = EditText1.getText().toString();
                    if (text1.isEmpty()) {
                        text1 = "0";
                    }
                }
                setContentView(R.layout.activity_main2);
                textView1.setText(text1);//!!!!!!!!!!!!!!
            }
        });

        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (EditText2.getText() != null) {
//                        text2 = EditText2.getText().toString();
//                        if (text2.isEmpty()) {
//                            text2 = "0";
//                        }
//                    }
//                    TextViewNumber1.setText(text1);
//                    TextViewNumber2.setText(text2);
//                    float result = calcServiceBinder.calculateSum(Float.valueOf(text1).floatValue(), Float.valueOf(text2).floatValue());
//                    if (TextViewResult != null) {
//                        TextViewResult.setText(String.valueOf(result));
//                    }
                    setContentView(R.layout.activity_main3);
                }
            });
        }else{
            button2 = findViewById(R.id.button2);
        }
    }

}
