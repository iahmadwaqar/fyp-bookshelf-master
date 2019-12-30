package com.shinwari.registertion.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shinwari.registertion.Adapter.DonaterAdapter;
import com.shinwari.registertion.MainActivity;
import com.shinwari.registertion.Model.DonatorInfo;
import com.shinwari.registertion.R;
import com.shinwari.registertion.Registeration.LoginActivity;
import com.shinwari.registertion.Registeration.SignUpMain;
import com.shinwari.registertion.UserProfile.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminMain extends AppCompatActivity {
    List<DonatorInfo> list;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    public DonaterAdapter adapter;

    private RecyclerView mRecyclerView;

    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        list = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DonaterAdapter(AdminMain.this,list);
        mRecyclerView.setAdapter(adapter);



        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        setTitle("Drug Addicts Counselling Home ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(AdminMain.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        startActivity(new Intent(new Intent(AdminMain.this, UserProfile.class)));
                        break;
                    case R.id.share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Your body here";
                        String shareSub = "Your subject here";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(new Intent(AdminMain.this, LoginActivity.class)));
                        isDestroyed();
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });
        displayUsers();


    }

    public void displayUsers(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Drugs").child("DrugUser");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    final DonatorInfo donatorInfo = ds.getValue(DonatorInfo.class);
                     if (donatorInfo.getDonatorDiseases() != null || donatorInfo.getDonaterSpecilization() != null){
                         list.add(donatorInfo);

                     }
                     else {

                     }

//                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Drugs/USERS/" +ds.getKey());
//                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            String TYPE = dataSnapshot.getValue(String.class);
//
//                            if (TYPE != null && TYPE.equals("ADMIN")){
//
////                                Toast.makeText(AdminMain.this, "Admin", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Log.i("HELOOO", donatorInfo.getDonatorName());
//                                list.add(donatorInfo);
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//                        Toast.makeText(AdminMain.this, "listener", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}



