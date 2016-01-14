package com.test.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Binder();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(){
            public void run() {
                while (true) {
                        System.out.println("正在运行...");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        return super.onStartCommand(intent, flags, startId);

    }
}
