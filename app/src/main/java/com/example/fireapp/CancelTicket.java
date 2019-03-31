package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class CancelTicket extends AppCompatActivity {

    Button cancelTick;
    String busname,datee,idNo,SeatNo,status,name;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_ticket);
        SlidrInterface slidrInterface = Slidr.attach(this);
        cancelTick=findViewById(R.id.cancel);
        Bundle bundle=getIntent().getExtras();
        busname=bundle.getString("busname");
        datee=bundle.getString("date");
        idNo=bundle.getString("IdNo");
        SeatNo=bundle.getString("SeatNo");
        name=bundle.getString("name");
        status=bundle.getString("status");
        if(status.equals("Cancelled") || status.equals("Bus_Cancelled")){
            cancelTick.setEnabled(false);
        }
        cancelTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Buses").child(datee).child(busname);
        databaseReference1.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]).child(idNo+SeatNo+busname+datee).child("status").setValue("Cancelled");
                DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference().
                        child("PassengerOfAllBus").child(busname+datee).child(String.valueOf(SeatNo));
                databaseReference2.child("names").setValue(name);
                databaseReference2.child("IdNo").setValue(idNo);
                databaseReference2.child("SeatNo").setValue(String.valueOf(SeatNo));
               // databaseReference2.child("date").setValue(datee);
                databaseReference2.child("status").setValue("Cancelled");
                databaseReference2.child("email").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);

                final DatabaseReference databaseReference3=FirebaseDatabase.getInstance().getReference().child("CancelledTicket").child(busname+datee);
               databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(!dataSnapshot.hasChild("NoOfCancelledTicket"))
                       {
                              databaseReference3.child("NoOfCancelledTicket").setValue("1");
                              databaseReference3.child("Seat"+SeatNo).setValue(String.valueOf(SeatNo));
                       }
                       else {

                           String noOfSe= (String) dataSnapshot.child("NoOfCancelledTicket").getValue();
                           Long no=Long.parseLong(noOfSe);
                           no=no+1;
                           databaseReference3.child("NoOfCancelledTicket").setValue(String.valueOf(no));
                           databaseReference3.child("Seat"+SeatNo).setValue(String.valueOf(SeatNo));
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                   String rem = (String) dataSnapshot.child("remaining_seats").getValue();
                   Long ss=Long.parseLong(rem);
                  // ss=ss+1;
                   databaseReference.child("remaining_seats").setValue(String.valueOf(ss));
                startActivity(new Intent(CancelTicket.this,drawerAct.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CancelTicket.this,drawerAct.class));
    }
}
