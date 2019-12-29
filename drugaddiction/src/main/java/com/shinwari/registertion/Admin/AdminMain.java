package com.shinwari.registertion.Admin;


import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shinwari.registertion.R;
import com.shinwari.registertion.Registeration.SignUpMain;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMain extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(AdminMain.this, "Welcome to Secure Tech.", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(AdminMain.this, "No", Toast.LENGTH_SHORT).show();

                    List<AuthUI.IdpConfig> providers = Arrays.asList(
//                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

//                    AuthMethodPickerLayout customLayout = new AuthMethodPickerLayout
//                            .Builder(R.layout.sign)
//                            .setGoogleButtonId(R.id.Google)
//                            .setEmailButtonId(R.id.Email)
//                            .build();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    //.setTheme(R.style.GreenTheme)
                                    //.setLogo(R.drawable.main_icon)
                                    .setAvailableProviders(providers)
                                    //.setAuthMethodPickerLayout(customLayout)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
