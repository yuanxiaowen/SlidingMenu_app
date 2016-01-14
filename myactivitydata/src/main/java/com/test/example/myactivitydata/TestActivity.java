package com.test.example.myactivitydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private TextView view;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        view = (TextView) findViewById(R.id.textView2);
        Button button = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        //获取传递的数据并显示出来
         Intent i = getIntent();
//        Bundle bundle = i.getBundleExtra("date");
        //设置显示数据，显示数据key，默认无数据是显示的参数
        /*view.setText(String.format("data=%s,age=%d,name=%s", bundle.getString("data"),
                bundle.getInt("age"), bundle.getString("name","name")));*/
//        view.setText(bundle.getString("data")+"\n"+bundle.getInt("age")+"\n"+bundle.getString("name", "name"));
        User user = i.getParcelableExtra("user");
        //使用Parcelable传递对象，自定义序列化
        view.setText(String.format("Usrt info(name=%s,age=%d) ", user.getName(), user.getAge()));
        button.setOnClickListener(new View.OnClickListener() {
         @Override
               public void onClick(View v) {
             Intent i = new Intent();
             i.putExtra("date", editText.getText().toString());
             setResult(1, i);
             finish();
         }  });
    }
}
