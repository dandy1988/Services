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
        final EditText EditText1 = findViewById(R.id.etValue1Number);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText1.getText() != null) {
                    text1 = EditText1.getText().toString();
                    if (text1.isEmpty()) {
                        text1 = "0";
                    }
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }
//
//    public void goToActivity2(View view) {
//        Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
//        startActivity(intent2);
//    }
}
