package com.shinwari.registertion.Registeration;

import androidx.appcompat.app.AppCompatActivity;

import com.shinwari.registertion.Admin.AdminMain;
import com.shinwari.registertion.MainActivity;
import com.shinwari.registertion.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
        Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn1 = findViewById(R.id.userLogin);
        btn2 = (Button)findViewById(R.id.adminLogin);
        btn3 = findViewById(R.id.physicologestLogin);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,RegisterDonater.class);
                intent.putExtra("User","1");
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, RegisterDonater.class);
                intent.putExtra("User","2");
                 startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, RegisterDonater.class);
                intent.putExtra("User","3");
                startActivity(intent);

            }
        });

    }
}
