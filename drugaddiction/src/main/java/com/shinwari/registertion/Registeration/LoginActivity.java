package com.shinwari.registertion.Registeration;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shinwari.registertion.MainActivity;
import com.shinwari.registertion.R;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText email;
    private TextInputEditText password;
    private Button loginUser;
    private Button RegisterUser;
    private Button Donate;
    private Button Accept;
    private ImageView Close;
    FirebaseAuth mAuth;
    Dialog registerType;
    DatabaseReference mRef;
    private ProgressBar loginLoder;
    private FrameLayout loginView;
    private TextView upadate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        mAuth = FirebaseAuth.getInstance();

        initView();

        loginLoder.setVisibility(View.INVISIBLE);

        if (mAuth.getCurrentUser() != null) {
            loginLoder.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.GONE);
            CheckType();

        }


        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                loginLoder.setVisibility(View.VISIBLE);
//                loginView.setVisibility(View.GONE);
                LoginUser();
            }
        });

        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterDonater.class);
                startActivity(intent);
            }
        });
    }

    private void CheckType() {

        mRef = FirebaseDatabase.getInstance().getReference("Drugs/USERS/" + mAuth.getCurrentUser().getUid());
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String TYPE = dataSnapshot.getValue(String.class);

                if (TYPE != null && TYPE.equals("USER")) {

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {


    }

    private void LoginUser() {


        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (Email.equals("") || Password.equals("")) {
            Toast.makeText(this, "enter Email", Toast.LENGTH_SHORT).show();

        } else {
                loginLoder.setVisibility(View.VISIBLE);
                loginView.setVisibility(View.GONE);

            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                CheckType();


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                                loginLoder.setVisibility(View.INVISIBLE);
                                loginView.setVisibility(View.VISIBLE);
                            }

                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    loginLoder.setVisibility(View.INVISIBLE);
                    loginView.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


    private void initView() {
        email = (TextInputEditText) findViewById(R.id.email);
        password = (TextInputEditText) findViewById(R.id.password);
        loginUser = (Button) findViewById(R.id.loginUser);
        RegisterUser = (Button) findViewById(R.id.RegisterUser);
        loginLoder = (ProgressBar) findViewById(R.id.loginLoder);
        loginView = (FrameLayout) findViewById(R.id.loginView);


    }
}
