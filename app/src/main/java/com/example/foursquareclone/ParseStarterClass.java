package com.example.foursquareclone;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_ERROR);

        //Parse kullanabilmek için gerekli şeyler
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("rcp1q6NmcNA096DYZqeX0SYj20BiLR4Z3vMBK9SA")
                .clientKey("S3w13Q84NG7kyeejxy6Yzfx7lbhaCNUzvFf5OdBf")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
