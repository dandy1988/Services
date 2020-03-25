package dandy1988.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class CalculatorService extends Service {
    private float value1;
    private float value2;

    class MyBinder extends Binder {
        public float calculateSum(float value1, float value2){
            return (value1+value2);
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        value1 = intent.getFloatExtra("value1", 0f);
        value2 = intent.getFloatExtra("value2", 0f);
        return myBinder;
    }

}
