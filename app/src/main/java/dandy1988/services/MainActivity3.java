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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends MainActivity2 {
    String text1 = "";
    String text2 = "";
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        text1 = getIntent().getExtras().getString("text1");
        text2 = getIntent().getExtras().getString("text2");
        result = getIntent().getExtras().getString("result");


        final TextView TextViewNumber1 = findViewById(R.id.tvNumber1);
        final TextView TextViewNumber2 = findViewById(R.id.tvNumber2);
        final TextView TextViewResult = findViewById(R.id.tvResult);

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
    public void btn_close(View view) {
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }
}
