package com.test.example.appapletion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        button = (Button) findViewById(R.id.but_view);
        editText = (EditText) findViewById(R.id.edit_view);
        textView = (TextView) findViewById(R.id.text_view);
        //取出数据通过getData方法，并显示出来
        textView.setText("共享的数据是：" + getData().getString());

        //保存数据
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存到Application
                ((App) getApplicationContext()).setString(editText.getText().toString());
                textView.setText("共享的数据是：" + editText.getText().toString());
            }
        });

    }
    private App getData() {
        return (App) getApplicationContext();
    }
}
