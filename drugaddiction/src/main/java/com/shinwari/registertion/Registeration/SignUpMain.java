package com.shinwari.registertion.Registeration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shinwari.registertion.Admin.AdminMain;
import com.shinwari.registertion.MainActivity;
import com.shinwari.registertion.R;

import java.util.Arrays;
import java.util.List;

public class SignUpMain extends AppCompatActivity {

   Button adminSignUp,userSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_main);
        adminSignUp =  (Button)findViewById(R.id.adminLogin);
        userSignUp = (Button) findViewById(R.id.loginUser);

        adminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpMain.this, MainActivity.class);
                startActivity(intent);
            }
        });

        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userI = new Intent(SignUpMain.this,MainActivity.class);
                startActivity(userI);
            }
        });

    }
}
