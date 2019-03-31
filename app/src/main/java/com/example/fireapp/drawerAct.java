package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class drawerAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private GoogleSignInClient mGoogleSignInClient ;
    private NavigationView navigationView;
    private TextView profilename;
    private TextView emailIdss;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        drawerLayout=findViewById(R.id.drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        profilename=findViewById(R.id.proname);
        emailIdss=findViewById(R.id.emailss);
        imageView=findViewById(R.id.imaggg);
        String profilenames=FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
        String emailss=FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        Toast.makeText(drawerAct.this,profilenames,Toast.LENGTH_LONG).show();
//        Toast.makeText(drawerAct.this,emailss,Toast.LENGTH_LONG).show();
        profilename.setText(profilenames.toString());
        emailIdss.setText(emailss.toString());
        Picasso.with(getApplicationContext())
                .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                .resize(110, 110)
                .centerCrop()
                .into(imageView);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle.syncState();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("666509707215-efglqr7aoieulg175tktlq64ma4dtekv.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
            return true;
        }

        return super.onOptionsItemSelected(menuItem);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {

            case R.id.boo: {
                startActivity(new Intent(drawerAct.this,dayTotal.class));
                break;
            }
            case R.id.History: {

                Intent i=new Intent(drawerAct.this,BookHistory.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // finishAffinity();
                startActivity(i);
                break;
            }
            case R.id.Admin:{
                startActivity(new Intent(drawerAct.this,AdminLogin.class));

                break;
            }
            case R.id.Signout:{
                mGoogleSignInClient.signOut().addOnCompleteListener(drawerAct.this,
                        new OnCompleteListener<Void>() {  //signout Google
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseAuth.getInstance().signOut();
                                Intent setupIntent = new Intent(drawerAct.this, Google_signIn.class);
                                Toast.makeText(getBaseContext(), "Logged Out", Toast.LENGTH_LONG).show();
                                setupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                //    finishAffinity();
                                startActivity(setupIntent);

                            }
                        });

                break;
            }
            case R.id.deleteAccount:{

                startActivity(new Intent(drawerAct.this,UserDelete.class));
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //finishAffinity();
        startActivity(intent);
    }
}
