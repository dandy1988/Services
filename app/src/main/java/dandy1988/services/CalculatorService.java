package dandy1988.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class CalculatorService extends Service {
    private boolean isBound = false;
    private float value1 = 0f;
    private float value2 = 0f;

    @Override
    public void onCreate(){
        super.onCreate();

    }


    class MyBinder extends Binder {
        public float calculateSum(float value1, float value2){
            return (value1+value2);
        }
        public float calculateSum(){
            return (value1+value2);
        }
        public float getValue1(){
            return value1;
        }
        public void setValue1(float value){
            value1 = value;
        }
        public float getValue2(){
            return value2;
        }
        public void setValue2(float value){
            value2 = value;
        }
        public void setIsBound(boolean connect){
            isBound = connect;
        }
        public boolean getIsBound(){
            return isBound;
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        value1 = intent.getFloatExtra("value1", value1);
        value2 = intent.getFloatExtra("value2", value2);
        return myBinder;
    }

}
