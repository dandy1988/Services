package dandy1988.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class CalculatorService extends Service {
    private float valueActivity1;
    private float valueActivity2;

    class MyBinder extends Binder {
        public float calculateSum(float value1, float value2){
            return (value1+value2);
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
