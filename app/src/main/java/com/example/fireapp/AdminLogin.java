package com.example.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class AdminLogin extends AppCompatActivity {

    Button modify,add,remove,businfo;
    EditText password;
    String PASSWORD="1234567";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        SlidrInterface slidrInterface = Slidr.attach(this);
        modify=findViewById(R.id.Modify);
        add=findViewById(R.id.addbus);
        remove=findViewById(R.id.removeBus);
        password=findViewById(R.id.password);
        businfo=findViewById(R.id.businfo);
        businfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(PASSWORD))
                {
                    // password.setText("");
                    startActivity(new Intent(AdminLogin.this,BusInfoLogin.class));
                }
                else
                {
                    password.setText("");
                    Toast.makeText(AdminLogin.this,"Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(PASSWORD))
                {
                   // password.setText("");
                startActivity(new Intent(AdminLogin.this,ModifyBusSchedule.class));
                }
                else
                    {
                    password.setText("");
                    Toast.makeText(AdminLogin.this,"Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString().equals(PASSWORD))
                {
                startActivity(new Intent(AdminLogin.this,AdminBlock.class));
                }
                else
                {
                    password.setText("");
                    Toast.makeText(AdminLogin.this,"Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString().equals(PASSWORD))
                {
                  startActivity(new Intent(AdminLogin.this,RemoveBus.class));
                }
                else
                {
                    password.setText("");
                    Toast.makeText(AdminLogin.this,"Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminLogin.this,drawerAct.class));
    }
}
