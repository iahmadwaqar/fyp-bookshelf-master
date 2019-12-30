package com.shinwari.registertion.Registeration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shinwari.registertion.Admin.AdminMain;
import com.shinwari.registertion.MainActivity;
import com.shinwari.registertion.Model.DonatorInfo;
import com.shinwari.registertion.Physicologest.PhysicoMain;
import com.shinwari.registertion.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterDonater extends AppCompatActivity {

    private static final int GALLARY_INTENT = 123;
    private CircleImageView donaterImage;
    private TextInputEditText regDonaterName;
    private TextInputEditText regDonateremail;
    private TextInputEditText regDonaterpassword;
    private TextInputEditText regDonaterCell;
    private TextInputEditText regDonaterAge;
    private TextInputEditText regDonaterDiseases;
    private TextInputEditText regDonaterLocation;
    private TextInputEditText regDonaterSpeci;
    private Button doanterSubmmit;
    FirebaseAuth mAuth;
    StorageReference mStorageRef;
    DatabaseReference mRef;
    String BaseUrl;
    Uri uri;
    String downloadUri;
    private ProgressBar donerLoder;
    private TextView loading;
    private ScrollView donerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donater);
        initView();
        mAuth = FirebaseAuth.getInstance();
        setTitle("Registration");

        donerLoder.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        donaterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/*"), GALLARY_INTENT);
            }
        });

        doanterSubmmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterDonater();
            }
        });
        //select design for the different user e.g doctor user and admin
        String id = getIntent().getStringExtra("User");

        if (id.equals("1")){
            regDonaterSpeci.setVisibility(View.GONE);
        }
        else if (id.equals("2")){
            regDonaterSpeci.setVisibility(View.GONE);
            regDonaterDiseases.setVisibility(View.GONE);
            regDonaterLocation.setVisibility(View.GONE);
            regDonaterAge.setVisibility(View.GONE);
        }
        else if (id.equals("3")){
            regDonaterAge.setVisibility(View.GONE);
            regDonaterDiseases.setVisibility(View.GONE);

        }


    }


    private void RegisterDonater() {
        String Email = regDonateremail.getText().toString();
        String Password = regDonaterpassword.getText().toString();

        if (regDonaterName.equals("")) {
            regDonaterName.setError("Enter correct name");
        } else if (Email.equals("")) {
            regDonateremail.setError("Enter Email");
        } else if (Password.equals("") || Password.length() < 6) {
            regDonaterpassword.setError("Enter Password of length more then 6");
        } else if (regDonaterCell.equals("") || regDonaterCell.length() != 11) {
            regDonaterCell.setError("Enter Cell of length 11");
        } else if (regDonaterLocation.equals("")) {
            regDonaterLocation.setError("Enter Location ");
        } else if (uri == null) {
            Toast.makeText(this, "Select Profile Image", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                doanterSubmmit.setClickable(false);
                                donerLoder.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.VISIBLE);
                                donerView.setVisibility(View.GONE);

                                SaveUserInformation();

                            } else {
                                Toast.makeText(RegisterDonater.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                donerLoder.setVisibility(View.INVISIBLE);
                                loading.setVisibility(View.INVISIBLE);
                                donerView.setVisibility(View.VISIBLE);

                            }

                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    donerLoder.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.INVISIBLE);
                    donerView.setVisibility(View.VISIBLE);

                    Toast.makeText(RegisterDonater.this, "" + e,
                            Toast.LENGTH_LONG).show();

                }
            });


        }
    }

    private void SaveUserInformation() {
        final String id = getIntent().getStringExtra("User");

        mStorageRef = FirebaseStorage.getInstance().getReference();
        final DonatorInfo donatorInfo = new DonatorInfo();
        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        BaseUrl = "Drugs";
        mRef = FirebaseDatabase.getInstance().getReference(BaseUrl);


        StorageReference imageFile = mStorageRef.child(BaseUrl).child("USERIMAGES").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        imageFile.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> taskUri = taskSnapshot.getStorage().getDownloadUrl();
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        downloadUri = uri.toString();
                        donatorInfo.setDonatorImage(String.valueOf(downloadUri));
                        donatorInfo.setDonatorEmail(regDonateremail.getText().toString());
                        donatorInfo.setDonatorName(regDonaterName.getText().toString());
                        donatorInfo.setDonatorCell(regDonaterCell.getText().toString());
                        donatorInfo.setDonatorLocation(regDonaterLocation.getText().toString());


                        if (id.equals("1")){
                            donatorInfo.setDonatorAge(regDonaterAge.getText().toString());
                            donatorInfo.setDonatorDiseases(regDonaterDiseases.getText().toString());
                            donatorInfo.setDonatorNoCount(1);
                            donatorInfo.setDonatorRatingSum(5);
                            mRef.child("USERS").child(userID).setValue("USER");

                        }
                        if (id.equals("3")){
                            donatorInfo.setDonaterSpecilization(regDonaterSpeci.getText().toString());
                            mRef.child("USERS").child(userID).setValue("DOCTOR");

                        }
                        if (id.equals("2")){
                           mRef.child("USERS").child(userID).setValue("ADMIN");

                        }

                            //mRef.child("USERS").child(userID).setValue("ADMIN");
                        FirebaseUser user = mAuth.getCurrentUser();
                        mRef.child("DrugUser/" + userID).setValue(donatorInfo);
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(regDonaterName.getText().toString())
                                .setPhotoUri(Uri.parse(String.valueOf(downloadUri)))
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterDonater.this, "User Info Is updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                        if (id.equals("1")) {

                            startActivity(new Intent(RegisterDonater.this, MainActivity.class));
                            finish();
                        }
                        else if (id.equals("2")){
                                startActivity(new Intent(RegisterDonater.this, AdminMain.class));
                            finish();
                        }
                        else if (id.equals("3")){
                            startActivity(new Intent(RegisterDonater.this, PhysicoMain.class));
                            finish();
                        }


                    }
                });




            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        donaterImage = (CircleImageView) findViewById(R.id.donater_image);
        regDonaterName = (TextInputEditText) findViewById(R.id.regDonaterName);
        regDonateremail = (TextInputEditText) findViewById(R.id.regDonateremail);
        regDonaterpassword = (TextInputEditText) findViewById(R.id.regDonaterpassword);
        regDonaterCell = (TextInputEditText) findViewById(R.id.regDonaterCell);
        regDonaterAge = (TextInputEditText) findViewById(R.id.regDonaterAge);
        regDonaterDiseases = (TextInputEditText) findViewById(R.id.regDonaterDiseases);
        regDonaterLocation = (TextInputEditText) findViewById(R.id.regDonaterLocation);
        regDonaterSpeci = (TextInputEditText) findViewById(R.id.regDonaterSpecilization);
        doanterSubmmit = (Button) findViewById(R.id.doanterSubmmit);
        donerLoder = (ProgressBar) findViewById(R.id.donerLoder);
        loading = (TextView) findViewById(R.id.loading);
        donerView = (ScrollView) findViewById(R.id.donerView);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLARY_INTENT && resultCode == RESULT_OK) {

            uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            donaterImage.setImageBitmap(bitmap);


        }
    }

}
