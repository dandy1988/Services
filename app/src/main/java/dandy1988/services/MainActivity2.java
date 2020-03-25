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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends MainActivity {

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
        setContentView(R.layout.activity_main2);
        text1 = getIntent().getExtras().getString("text1");

        Intent intent = new Intent(MainActivity2.this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        Button button2 = findViewById(R.id.button2);
        final EditText EditText2 = findViewById(R.id.etValue2Number);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText2.getText() != null) {
                    text2 = EditText2.getText().toString();
                    if (text2.isEmpty()) {
                        text2 = "0";
                    }
                }
                float result = calcServiceBinder.calculateSum(
                        Float.valueOf(text1).floatValue(),
                        Float.valueOf(text2).floatValue());

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("text1", text1);
                intent.putExtra("text2", text2);
                intent.putExtra("result", String.valueOf(result));
                startActivity(intent);
            }
        });
    }
}


