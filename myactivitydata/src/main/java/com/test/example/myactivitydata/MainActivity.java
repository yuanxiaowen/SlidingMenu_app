package com.test.example.myactivitydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but = (Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TestActivity.class);
                /*i.putExtra("data", "传递参数——1");*/
                /*i.putExtras(b);*/
//                Bundle b = new Bundle();
//                b.putString("data", "传递参数-1");
//                b.putInt("age", 26);
//                i.putExtra("date", b);
                //使用Parcelable进行对象传递
                i.putExtra("user", new User("第一条", 26));
                startActivityForResult(i, 0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textView.setText("返回来的数据\t"+data.getStringExtra("date"));
    }
}

