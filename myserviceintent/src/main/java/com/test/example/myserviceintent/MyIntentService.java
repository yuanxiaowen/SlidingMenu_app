package com.test.example.myserviceintent;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyIntentService extends Service {
    private boolean runing = false;
    private String date = "默认数据";
    public MyIntentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binding();
    }

    public class Binding extends Binder {
        public void setDate(String data) {
            MyIntentService.this.date = data;
        }

        public MyIntentService getService() {
            return MyIntentService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        date = intent.getStringExtra("date");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        runing = true;
        new Thread() {
            public void run() {
                super.run();
                int i = 0;
                while (runing) {
                    i++;
                    String s = i+":"+date;
                    System.out.println(i+":"+date);
                    if (callbback!= null) {
                        callbback.onData(s);

                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.start();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("停止了！！！！");
        runing = false;
        super.onDestroy();
    }

    private Callbback callbback;

    public void setCallbback(Callbback callbback) {
        this.callbback = callbback;
    }

    public Callbback getCallbback() {
        return callbback;
    }

    public static interface Callbback {
        void onData(String string);
    }

}
