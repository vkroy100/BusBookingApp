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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ModifyBusSchedule extends AppCompatActivity {

    AutoCompleteTextView datem,monthm,yearm;
    Button modifyArrival,modifyDepart;
    EditText busnam,newTime;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bus_schedule);
        SlidrInterface slidrInterface = Slidr.attach(this);
        datem=findViewById(R.id.datem);
        monthm=findViewById(R.id.monthm);
        yearm=findViewById(R.id.yearm);
        modifyArrival=findViewById(R.id.ModifyArrivalTime);
        modifyDepart=findViewById(R.id.ModifyDepartTiem);
        busnam=findViewById(R.id.busnam);
        newTime=findViewById(R.id.newTime);
        String[] days={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","20","21"
                ,"22","23","24","25","26","27","28","29","30","31","15","16","17","18","19"};
        ArrayAdapter<String> dayss = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, days);
        String[] monthss={"01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> mon = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, monthss);
        String[] yearss={"2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
        ArrayAdapter<String> yea = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, yearss);
        datem.setThreshold(1);
        monthm.setThreshold(1);
        yearm.setThreshold(1);
        datem.setAdapter(dayss);
        monthm.setAdapter(mon);
        yearm.setAdapter(yea);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Buses");
        modifyArrival.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               final String dattm=datem.getText().toString().trim()+"-"+monthm.getText().toString().trim()
                        +"-"+yearm.getText().toString().trim();
                if(!TextUtils.isEmpty(dattm) && !TextUtils.isEmpty(datem.getText().toString().trim())
                && !TextUtils.isEmpty(monthm.getText().toString().trim()) && !TextUtils.isEmpty(yearm.getText().toString().trim()))
                {
                      // databaseReference.child(dattm).child(busnam.getText().toString().trim()).child("arrival_time").setValue(newTime.getText().toString().trim());
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(dattm))
                            {
                               // databaseReference.child(dattm);
                             final DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Buses").child(dattm);
                                databaseReference11.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshots)
                                    {
                                        if(dataSnapshots.hasChild(busnam.getText().toString().trim()))
                                        {


                                            databaseReference11.child(busnam.getText().toString().trim()).child("arrival_time").setValue(newTime.getText().toString().trim());
                                            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                                            Query query = databaseReference1.child("PassengerOfAllBus").child(busnam.getText().toString().trim()+dattm).orderByChild("SeatNo");
                                            ValueEventListener valueEventListener = new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                                        //long score = ds.child("score").getValue(Long.class);
                                                        if(ds.child("status").getValue().toString().equals("Cancelled")){
                                                            continue;
                                                        }
                                                        else
                                                        {
                                                            DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
                                                            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                                                            //String id=firebaseAuth.getCurrentUser().getEmail();
                                                            String id=ds.child("email").getValue().toString();
                                                            databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
                                                                    dattm).child("arrival_time").setValue(newTime.getText().toString().trim());
//                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
//                                                                    child(busnam.getText().toString().trim()+dattm).
//                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
//                                                            databaseReference12.setValue("Bus_Cancelled");

                                                        }

                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            };
                                            query.addListenerForSingleValueEvent(valueEventListener);
                                            startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
                                        }
                                        else
                                        {

                                            Toast.makeText(ModifyBusSchedule.this,"This bus does not exist",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError)
                                    {

                                    }
                                });
                            }
                            else
                                {

                                Toast.makeText(ModifyBusSchedule.this,"There is no bus on this date",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });


                }
            }
        });
        modifyDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String dattm=datem.getText().toString().trim()+"-"+monthm.getText().toString().trim()
                        +"-"+yearm.getText().toString().trim();
                if(!TextUtils.isEmpty(dattm) && !TextUtils.isEmpty(datem.getText().toString().trim())
                        && !TextUtils.isEmpty(monthm.getText().toString().trim()) && !TextUtils.isEmpty(yearm.getText().toString().trim()))
                {
                    //databaseReference.child(dattm).child(busnam.getText().toString().trim()).child("departure_time").setValue(newTime.getText().toString().trim());
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(dattm))
                            {
                               final DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Buses").child(dattm);
                                   //databaseReference.child(dattm);
                                   databaseReference11.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshots)
                                       {
                                           if(dataSnapshots.hasChild(busnam.getText().toString().trim()))
                                           {
                                               databaseReference11.child(busnam.getText().toString().trim()).child("departure_time").setValue(newTime.getText().toString().trim());
                                               DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                                               Query query = databaseReference1.child("PassengerOfAllBus").child(busnam.getText().toString().trim()+dattm).orderByChild("SeatNo");
                                               ValueEventListener valueEventListener = new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(DataSnapshot dataSnapshot) {
                                                       for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                                           //long score = ds.child("score").getValue(Long.class);
                                                           if(ds.child("status").getValue().toString().equals("Cancelled")){
                                                               continue;
                                                           }
                                                           else
                                                           {
                                                               DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
                                                               FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                                                               //String id=firebaseAuth.getCurrentUser().getEmail();
                                                               String id=ds.child("email").getValue().toString();
                                                               databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
                                                                       dattm).child("departure_time").setValue(newTime.getText().toString().trim());
//                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
//                                                                    child(busnam.getText().toString().trim()+dattm).
//                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
//                                                            databaseReference12.setValue("Bus_Cancelled");

                                                           }

                                                       }
                                                   }

                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                                   }
                                               };
                                               query.addListenerForSingleValueEvent(valueEventListener);
                                               startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
                                           }
                                           else
                                               {

                                                   Toast.makeText(ModifyBusSchedule.this,"This bus does not exist",Toast.LENGTH_LONG).show();
                                                   startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError)
                                       {

                                       }
                                   });
                            }
                            else{

                                Toast.makeText(ModifyBusSchedule.this,"There is no bus on this date",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ModifyBusSchedule.this,drawerAct.class));
    }
}
