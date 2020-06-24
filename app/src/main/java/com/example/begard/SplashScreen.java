package com.example.begard;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(Log_In.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#ffffff"))
                .withHeaderText("")
                .withFooterText("")
                .withBeforeLogoText("")
                .withAfterLogoText("Version 0.1")
                .withLogo(R.drawable.begard);


        easySplashScreen.getLogo().setMaxHeight(450);
        easySplashScreen.getLogo().setMaxWidth(450);

        View splashScreen = easySplashScreen.create();
        setContentView(splashScreen);

    }
}
