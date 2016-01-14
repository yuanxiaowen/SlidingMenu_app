package com.test.example.textsa_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
/*
* 2016.1.11
* 拨打电话发送短信
* 以及判断输入内容是否为空
*
* */
public class MainActivity extends Activity {
    private Button button,button1;
    private EditText editText,editText1;
    private String mun, text;
    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.ed_text);
        editText1 = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new MyOnclick());
        button1.setOnClickListener(new MyOnclick());
        //使用第三方资源架构
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindOffsetRes(R.dimen.activity_right_menu);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.sliningmenu);

    }
//设置menu按键的点击事件打开侧边栏
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
//重写方法返回键的效果是关闭侧滑菜单栏
    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing()) {
            slidingMenu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    private class MyOnclick implements View.OnClickListener {
        @Override

        public void onClick(View v) {
            mun = editText.getText().toString().trim();//去点空格键干扰
            text = editText1.getText().toString();
            //判断是否为空
            if (TextUtils.isEmpty(mun)||TextUtils.isEmpty(text)) {
                Toast.makeText(MainActivity.this, "请输入电话号码和短信内容！" + mun, Toast.LENGTH_SHORT).show();
                return;
            }
            switch (v.getId()) {
                //拨打电话
                case R.id.button:{
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + mun));
                    startActivity(intent);
                    break;
                }
                //发送短信
                case R.id.button2: {
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> texts = smsManager.divideMessage(text);
                    for (String string : texts) {
                        smsManager.sendTextMessage(mun,null,string,null,null);
                    }

                }
            }

            //拨打电话


        }
    }
}
