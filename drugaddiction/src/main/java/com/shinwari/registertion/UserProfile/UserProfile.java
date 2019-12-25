package com.shinwari.registertion.UserProfile;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shinwari.registertion.Model.DonatorInfo;
import com.shinwari.registertion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    private CircleImageView donaterPimage;
    private TextView donerName;
    private TextView donerLocation;
    private TextView donerCellNo;
    private TextView donerPEmail;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        UserId = mAuth.getUid();
        initView();
        setTitle("Your Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mRef.child("Drugs/DrugUser/" + UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DonatorInfo donatorInfo = dataSnapshot.getValue(DonatorInfo.class);
                try {
                    Glide.with(donaterPimage.getContext()).load(donatorInfo.getDonatorImage()).into(donaterPimage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                donerName.setText(donatorInfo.getDonatorName());
                donerLocation.setText(donatorInfo.getDonatorLocation());
                donerCellNo.setText(donatorInfo.getDonatorCell());
                donerPEmail.setText(donatorInfo.getDonatorEmail());




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        ratingBar.setEnabled(false);
//        ratingBar.setFocusable(true);


    }

    private void initView() {
        donaterPimage = (CircleImageView) findViewById(R.id.donater_pimage);
        donerName = (TextView) findViewById(R.id.donerName);
        donerPEmail = (TextView) findViewById(R.id.donerPEmail);
        donerLocation = (TextView) findViewById(R.id.donerLocation);
        donerCellNo = (TextView) findViewById(R.id.donerCellNo);

    }
}
