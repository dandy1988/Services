package dandy1988.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity3 extends MainActivity2 {

    private CalculatorService.MyBinder calcServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calcServiceBinder = (CalculatorService.MyBinder) service;
            calcServiceBinder.setIsBound(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calcServiceBinder.setIsBound(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final TextView TextViewNumber1 = findViewById(R.id.tvNumber1);
        final TextView TextViewNumber2 = findViewById(R.id.tvNumber2);
        final TextView TextViewResult = findViewById(R.id.tvResult);
        final Button buttonCalculate = findViewById(R.id.button3);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((calcServiceBinder != null) && (calcServiceBinder.getIsBound() == true)) {
                    String text1 = String.valueOf(calcServiceBinder.getValue1()).toString();
                    String text2 = String.valueOf(calcServiceBinder.getValue2()).toString();
                    String result = String.valueOf(calcServiceBinder.calculateSum()).toString();
                    if (TextViewNumber1 != null){
                        TextViewNumber1.setText(text1);
                    }
                    if (TextViewNumber2 != null){
                        TextViewNumber2.setText(text2);
                    }
                    if (TextViewResult != null){
                        TextViewResult.setText(result);
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity3.this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    public void newValues(View view) {
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }

}
