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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class RemoveBus extends AppCompatActivity {

    AutoCompleteTextView date,month,year;
    EditText busna;
    Button remove;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bus);
        SlidrInterface slidrInterface = Slidr.attach(this);
        remove=findViewById(R.id.removeBus);
        busna=findViewById(R.id.busna);
        date=findViewById(R.id.datet);
        month=findViewById(R.id.montht);
        year=findViewById(R.id.yeart);
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
        date.setThreshold(1);
        month.setThreshold(1);
        year.setThreshold(1);
        date.setAdapter(dayss);
        month.setAdapter(mon);
        year.setAdapter(yea);
        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String datet=date.getText().toString().trim()+"-"+month.getText().toString().trim()+"-"+
                        year.getText().toString().trim();
                if(!TextUtils.isEmpty(busna.getText().toString().trim()) && !TextUtils.isEmpty(date.getText().toString().trim())
                 && !TextUtils.isEmpty(month.getText().toString().trim()) && !TextUtils.isEmpty(year.getText().toString().trim()))
                {
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Buses").child(datet).child(busna.getText().toString().trim());
                    databaseReference.removeValue();
                    DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                    Query query = databaseReference1.child("PassengerOfAllBus").child(busna.getText().toString().trim()+datet).orderByChild("SeatNo");
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
                                        databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busna.getText().toString().trim()+
                                                datet).child("status").setValue("Bus_Cancelled");
                                        DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").child(busna.getText().toString().trim()+datet).
                                                child(ds.child("SeatNo").getValue().toString()).child("status");
                                        databaseReference12.setValue("Bus_Cancelled");

                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                    startActivity(new Intent(RemoveBus.this,drawerAct.class));

                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RemoveBus.this,drawerAct.class));
    }
}
