package com.user.estimasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cekDataUser();
            }
        }, 2000);
    }

    private void cekDataUser() {
        SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
        if (spLogin.getString(StaticVars.SP_LOGIN_USERNAME, "").equals("")) {
            startActivity(new Intent(SplashScreen.this, Login.class));
        }
        else {
            startActivity(new Intent(SplashScreen.this, MenuUtama.class));
        }
    }
}
