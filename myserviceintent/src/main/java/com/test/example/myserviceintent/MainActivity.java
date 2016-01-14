package com.test.example.myserviceintent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {
    private EditText editText;
    private Button button,button2,button3,button4, button5;
    private TextView textView;
    private MyIntentService.Binding binding = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button4 = (Button) findViewById(R.id.button_4);
        button5 = (Button) findViewById(R.id.button_5);
        editText.setText("默认数据");
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                Intent intent = new Intent(this, MyIntentService.class);
                intent.putExtra("date", editText.getText().toString());
                startService(intent);
                break;
            }
            case R.id.button_2: {
                stopService(new Intent(this, MyIntentService.class));
                Toast.makeText(this, "停止", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.button_3: {
                bindService(new Intent(this, MyIntentService.class), this, Context.BIND_AUTO_CREATE);
                break;
            }
            case R.id.button_4: {
                unbindService(this);

                break;
            }case R.id.button_5: {
                if (binding != null) {
                    binding.setDate(editText.getText().toString());
                }

                break;
            }




        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binding = (MyIntentService.Binding) service;
        binding.getService().setCallbback(new MyIntentService.Callbback() {
            @Override
            public void onData(String string) {
                Message message = new Message();
                Bundle b = new Bundle();
                b.putString("data", string);
                message.setData(b);
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.getData().getString("data"));

        }
    };
}
