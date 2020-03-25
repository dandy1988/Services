package dandy1988.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends MainActivity {

    String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
                finish();
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}


