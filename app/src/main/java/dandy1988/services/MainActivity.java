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

public class MainActivity extends AppCompatActivity {

    private CalculatorService.MyBinder calcServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calcServiceBinder = (CalculatorService.MyBinder) service;
            calcServiceBinder.setBound(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calcServiceBinder.setBound(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        final EditText EditText1 = findViewById(R.id.etValue1Number);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = "";
                if (EditText1.getText() != null) {
                    text1 = EditText1.getText().toString();
                    if (text1.isEmpty()) {
                        text1 = "0";
                    }
                    if ((calcServiceBinder != null) && (calcServiceBinder.getBound() == true)) {
                        calcServiceBinder.setValue1(Float.valueOf(text1).floatValue());
                    }
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

}
