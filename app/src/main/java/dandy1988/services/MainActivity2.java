package dandy1988.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity2 extends MainActivity {

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
        setContentView(R.layout.activity_main2);

        final Button button2 = findViewById(R.id.button2);
        final EditText EditText2 = findViewById(R.id.etValue2Number);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text2 = "";
                if (EditText2.getText() != null) {
                    text2 = EditText2.getText().toString();
                    if (text2.isEmpty()) {
                        text2 = "0";
                    }
                }
                if ((calcServiceBinder != null) && (calcServiceBinder.getIsBound() == true)) {
                    float result = calcServiceBinder.calculateSum();
                    calcServiceBinder.setValue2(Float.valueOf(text2).floatValue());
                }
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity2.this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}


