package com.shinwari.registertion;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.shinwari.registertion.Registeration.LoginActivity;

public class SpleshScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SpleshScreen.this, LoginActivity.class);
                SpleshScreen.this.startActivity(mainIntent);
                SpleshScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}