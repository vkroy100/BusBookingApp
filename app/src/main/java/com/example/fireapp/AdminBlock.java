package com.example.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class AdminBlock extends AppCompatActivity {

   private AutoCompleteTextView dayi,month,year;
   private Button addBus;
   private EditText arrival,depart,busname,totalSeat,dayname;
   private   DatabaseReference databaseReference;
   private String datt;
    private SlidrInterface slidrInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_block);
        slidrInterface= Slidr.attach(this);
        dayi=findViewById(R.id.dayi);
        month=findViewById(R.id.monthi);
        year=findViewById(R.id.yeari);
        addBus=findViewById(R.id.addBus);
        arrival=findViewById(R.id.arriv);
        depart=findViewById(R.id.depar);
        busname=findViewById(R.id.busne);
        totalSeat=findViewById(R.id.totalseat);
        dayname=findViewById(R.id.day);
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
        dayi.setThreshold(1);
        month.setThreshold(1);
        year.setThreshold(1);
        dayi.setAdapter(dayss);
        month.setAdapter(mon);
        year.setAdapter(yea);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Buses");
        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                datt = dayi.getText().toString().trim()+"-" + month.getText().toString().trim() + "-" +
                        year.getText().toString().trim();
                if(!TextUtils.isEmpty(dayi.getText().toString().trim()) && !TextUtils.isEmpty(month.getText().toString().trim())
                && !TextUtils.isEmpty(year.getText().toString().trim()) && !TextUtils.isEmpty(arrival.getText().toString().trim())
                && !TextUtils.isEmpty(depart.getText().toString().trim()) && !TextUtils.isEmpty(busname.getText().toString().trim())
                && !TextUtils.isEmpty(totalSeat.getText().toString().trim()) && !TextUtils.isEmpty(dayname.getText().toString().trim())
                && !TextUtils.isEmpty(datt.trim()))
                {
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("date").setValue(datt) ;
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("arrival_time").setValue(arrival.getText().toString().trim());
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("busname").setValue(busname.getText().toString().trim());
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("departure_time").setValue(depart.getText().toString().trim());
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("day").setValue(dayname.getText().toString().trim());
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("remaining_seats").setValue(totalSeat.getText().toString().trim());
                    databaseReference.child(datt).child(busname.getText().toString().trim()).child("totalSeat").setValue(totalSeat.getText().toString().trim());
                    DatabaseReference databaseReference3=FirebaseDatabase.getInstance().getReference().child("CancelledTicket").child(busname.getText().toString().trim()+datt);
                    databaseReference3.child("NoOfCancelledTicket").setValue("0");
                    startActivity(new Intent(AdminBlock.this,drawerAct.class));
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminBlock.this,drawerAct.class));
    }
}
