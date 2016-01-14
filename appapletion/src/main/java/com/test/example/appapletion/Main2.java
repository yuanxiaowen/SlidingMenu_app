package com.test.example.appapletion;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 袁晓文 on 2016/1/11.
 */
public class Main2 extends Activity {
    private Button button;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        button = (Button) findViewById(R.id.but_view);
        editText = (EditText) findViewById(R.id.edit_view);
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText("共享的数据是：" + getData().getString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getApplicationContext()).setString(editText.getText().toString());
                textView.setText("共享的数据是：" + editText.getText().toString());
            }
        });
    }private App getData() {
        return (App) getApplicationContext();
    }

}
