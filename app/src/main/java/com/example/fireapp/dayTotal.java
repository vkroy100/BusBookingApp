package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
public class dayTotal extends AppCompatActivity {

    AutoCompleteTextView dates,months,years;
    Button findbus;
    String datee;
    private SlidrInterface slidrInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_total);
        slidrInterface= Slidr.attach(this);
        findbus=findViewById(R.id.findBus);
        dates=findViewById(R.id.datess);
        months=findViewById(R.id.month);
        years=findViewById(R.id.year);
        //Toast.makeText(dayTotal.this,datee,Toast.LENGTH_LONG).show();
        String[] days={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","20","21"
        ,"22","23","24","24","25","26","27","28","29","30","31","15","16","17","18","19"};
        ArrayAdapter<String> dayss = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, days);
        String[] monthss={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> mon = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, monthss);
        String[] yearss={"2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
        ArrayAdapter<String> yea = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, yearss);
        dates.setThreshold(1);
        months.setThreshold(1);
        years.setThreshold(1);
        dates.setAdapter(dayss);
        months.setAdapter(mon);
        years.setAdapter(yea);


        findbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datee=dates.getText().toString().trim()+"-"+months.getText().toString().trim()
                 +"-"+years.getText().toString().trim();
                if(!TextUtils.isEmpty(datee) && !TextUtils.isEmpty(dates.getText().toString()) &&
                        !TextUtils.isEmpty(months.getText().toString()) && !TextUtils.isEmpty(years.getText().toString()))
                {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Buses");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.hasChild(datee))
                            {
                               Toast.makeText(dayTotal.this,"There is no bus on this date",Toast.LENGTH_LONG).show();
                            }
                            else {

                              //  Toast.makeText(dayTotal.this,"",Toast.LENGTH_LONG).show();
                                Intent ghg=new Intent(dayTotal.this,Buslist.class);
                                ghg.putExtra("date",datee);
                                startActivity(ghg);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
//                    Intent ghg=new Intent(dayTotal.this,Buslist.class);
//                    ghg.putExtra("date",datee);
//                    startActivity(ghg);
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(dayTotal.this,drawerAct.class));
    }
}
