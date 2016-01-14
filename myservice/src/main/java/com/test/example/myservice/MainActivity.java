package com.test.example.myservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements ServiceConnection {
    private Button buttonstart,buttonstop,buttonbind,buttonunbind;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, MyService.class);
        buttonstart = (Button) findViewById(R.id.button);
        buttonstop = (Button) findViewById(R.id.button2);
        buttonbind = (Button) findViewById(R.id.button3);
        buttonunbind = (Button) findViewById(R.id.button4);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Toast.makeText(MainActivity.this, "开始", Toast.LENGTH_LONG).show();
            }
        });
        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_LONG).show();
            }
        });
        buttonbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, MainActivity.this, Context.BIND_AUTO_CREATE);
            }
        });
        buttonunbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(MainActivity.this);
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("绑定了！！！");

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
