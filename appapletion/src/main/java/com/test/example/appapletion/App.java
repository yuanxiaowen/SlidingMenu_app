package com.test.example.appapletion;

import android.app.Application;

/**
 * Created by 袁晓文 on 2016/1/11.
 */
public class App extends Application {
    private String textDate = "default";

    public String getString() {
        return textDate;

    }
    public void setString(String string) {
        this.textDate = string;
    }


}
