package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Account_Act extends AppCompatActivity {

    Button book,history,admin,signout,deletACC;
    GoogleSignInClient mGoogleSignInClient ;

    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gateway);
        book=findViewById(R.id.booking);
        history=findViewById(R.id.history);
        admin=findViewById(R.id.admin);
        signout=findViewById(R.id.signout);
        deletACC=findViewById(R.id.deleteAcc);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("666509707215-efglqr7aoieulg175tktlq64ma4dtekv.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
//        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
//                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                        Toast.makeText(Account_Act.this,"oooppsss",Toast.LENGTH_LONG).show();
//                    }
//                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        if(!FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[1].equals("iitbhilai.ac.in"))
        {
           book.setEnabled(false);
           history.setEnabled(false);
           admin.setEnabled(false);
          // signout.setEnabled(false);
           Toast.makeText(Account_Act.this,"Please login with iit bhilai email Id",Toast.LENGTH_LONG).show();
        }
        deletACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account_Act.this,UserDelete.class));
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // FirebaseAuth.getInstance().signOut();
                //Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mGoogleSignInClient.signOut().addOnCompleteListener(Account_Act.this,
                        new OnCompleteListener<Void>() {  //signout Google
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseAuth.getInstance().signOut();
                                Intent setupIntent = new Intent(Account_Act.this, Google_signIn.class);
                                Toast.makeText(getBaseContext(), "Logged Out", Toast.LENGTH_LONG).show();
                                setupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //    finishAffinity();
                                startActivity(setupIntent);

                            }
                        });
//                startActivity(new Intent(Account_Act.this,Google_signIn.class));
//                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account_Act.this,AdminLogin.class));
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account_Act.this,dayTotal.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Account_Act.this,BookHistory.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               // finishAffinity();
                startActivity(i);
            }
        });
//        logout=findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent login=new Intent(Account_Act.this,Google_signIn.class);
//                startActivity(login);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //finishAffinity();
        startActivity(intent);
    }
}
