package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class SeatStructure extends AppCompatActivity {

    private String busname,seats,arrival,departure,days,date,totalSeat,enab;
    private Long noOfPass;
    private DatabaseReference databaseReference;
    private String ss;
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
            ,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36,b37,b38,b39,b40,b41,b42,b43,b44,b45,b46;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_layout);
        //SlidrInterface slidrInterface = Slidr.attach(this);
        Bundle extras=getIntent().getExtras();
        busname = extras.getString("busname").trim();
        seats = extras.getString("remaining_seats").trim();
        arrival = extras.getString("arrival_time");
        departure = extras.getString("departure_time").trim();
        noOfPass = extras.getLong("NoOfPass");
        days = extras.getString("day").trim();
        date = extras.getString("date").trim();
        totalSeat = extras.getString("totalSeat").trim();
        enab=extras.getString("enab").trim();
        final Intent bookticket=new Intent(SeatStructure.this,BookTicket.class);
        bookticket.putExtra("busname",busname);
        bookticket.putExtra("remaining_seats",seats);
        bookticket.putExtra("arrival_time",arrival);
        bookticket.putExtra("departure_time",departure);
        bookticket.putExtra("NoOfPass",noOfPass);
        bookticket.putExtra("day",days);
        bookticket.putExtra("date",date);
        bookticket.putExtra("totalSeat",totalSeat);
        bookticket.putExtra("enab","0");
        b1=findViewById(R.id.one);        b11=findViewById(R.id.eleven);     b21=findViewById(R.id.twentyone);
        b2=findViewById(R.id.two);        b12=findViewById(R.id.twelve);     b22=findViewById(R.id.twentytwo);
        b3=findViewById(R.id.three);      b13=findViewById(R.id.thirteen);   b23=findViewById(R.id.twentythree);
        b4=findViewById(R.id.four);       b14=findViewById(R.id.forteen);    b24=findViewById(R.id.twentyfour);
        b5=findViewById(R.id.five);       b15=findViewById(R.id.fifteen);    b25=findViewById(R.id.twentyfive);
        b6=findViewById(R.id.six);        b16=findViewById(R.id.sixteen);    b26=findViewById(R.id.twentysix);
        b7=findViewById(R.id.seven);      b17=findViewById(R.id.seventeen);  b27=findViewById(R.id.twentyseven);
        b8=findViewById(R.id.eight);      b18=findViewById(R.id.eighteen);   b28=findViewById(R.id.twentyeight);
        b9=findViewById(R.id.nine);       b19=findViewById(R.id.nineteen);   b29=findViewById(R.id.twentynine);
        b10=findViewById(R.id.ten);       b20=findViewById(R.id.twenty);     b30=findViewById(R.id.thirty);
        b31=findViewById(R.id.thirtyone); b32=findViewById(R.id.thirtytwo);  b33=findViewById(R.id.thirtythree);
        b34=findViewById(R.id.thirtyfour); b35=findViewById(R.id.thirtyfive); b36=findViewById(R.id.thirtysix);
        b37=findViewById(R.id.thirtyseven); b38=findViewById(R.id.thirtyeight); b39=findViewById(R.id.thirtynine);
        b40=findViewById(R.id.forty);  b41=findViewById(R.id.fortyone); b42=findViewById(R.id.fortytwo);
        b43=findViewById(R.id.fortythree);  b44=findViewById(R.id.fortyfour); b45=findViewById(R.id.fortyfive);
        b46=findViewById(R.id.fortysix);
        DatabaseReference dfg= FirebaseDatabase.getInstance().getReference();
        dfg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild("PassengerOfAllBus") && dataSnapshot.child("PassengerOfAllBus").hasChild(busname+date))
                {
                    DatabaseReference databaseReference14=FirebaseDatabase.getInstance().getReference();
                    Query query = databaseReference14.child("PassengerOfAllBus").child(busname.trim()+date).orderByChild("SeatNo");

                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                //long score = ds.child("score").getValue(Long.class);
                                if (!ds.child("status").getValue().toString().equals("Cancelled")) {
                                    //  toast= Toast.makeText(BookTicket.this, "This seat is already booked", Toast.LENGTH_SHORT);
                                    // toast.show();
                                      if(ds.child("SeatNo").getValue().toString().equals("1"))
                                      {
                                          b1.setBackgroundColor(getResources().getColor(R.color.ff));
                                          b1.setEnabled(false);
                                      }
                                    if(ds.child("SeatNo").getValue().toString().equals("2"))
                                    {
                                        b2.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b2.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("3"))
                                    {
                                        b3.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b3.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("4"))
                                    {
                                        b4.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b4.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("5"))
                                    {
                                        b5.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b5.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("6"))
                                    {
                                        b6.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b6.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("7"))
                                    {
                                        b7.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b7.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("8"))
                                    {
                                        b8.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b8.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("9"))
                                    {
                                        b9.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b9.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("10"))
                                    {
                                        b10.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b10.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("11"))
                                    {
                                        b11.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b11.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("12"))
                                    {
                                        b12.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b12.setEnabled(false);

                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("13"))
                                    {
                                        b13.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b13.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("14"))
                                    {
                                        b14.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b14.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("15"))
                                    {
                                        b15.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b15.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("16"))
                                    {
                                        b16.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b16.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("17"))
                                    {
                                        b17.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b17.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("18"))
                                    {
                                        b18.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b18.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("19"))
                                    {
                                        b19.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b19.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("20"))
                                    {
                                        b20.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b20.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("21"))
                                    {
                                        b21.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b21.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("22"))
                                    {
                                        b22.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b22.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("23"))
                                    {
                                        b23.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b23.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("24"))
                                    {
                                        b24.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b24.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("25"))
                                    {
                                        b25.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b25.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("26"))
                                    {
                                        b26.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b26.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("27"))
                                    {
                                        b27.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b27.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("28"))
                                    {
                                        b28.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b28.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("29"))
                                    {
                                        b29.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b29.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("30"))
                                    {
                                        b30.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b30.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("31"))
                                    {
                                        b31.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b31.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("32"))
                                    {
                                        b32.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b32.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("33"))
                                    {
                                        b33.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b33.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("34"))
                                    {
                                        b34.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b34.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("35"))
                                    {
                                        b35.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b35.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("36"))
                                    {
                                        b36.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b36.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("37"))
                                    {
                                        b37.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b37.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("38"))
                                    {
                                        b38.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b38.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("39"))
                                    {
                                        b39.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b39.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("40"))
                                    {
                                        b40.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b40.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("41"))
                                    {
                                        b41.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b41.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("42"))
                                    {
                                        b42.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b42.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("43"))
                                    {
                                        b43.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b43.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("44"))
                                    {
                                        b44.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b44.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("45"))
                                    {
                                        b45.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b45.setEnabled(false);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("46"))
                                    {
                                        b46.setBackgroundColor(getResources().getColor(R.color.ff));
                                        b46.setEnabled(false);
                                    }

                                   // pay.setEnabled(false);
                                    //flagss=1;

                                }

                              else  if (ds.child("status").getValue().toString().equals("Cancelled")) {
                                    //  toast= Toast.makeText(BookTicket.this, "This seat is already booked", Toast.LENGTH_SHORT);
                                    // toast.show();
                                    if(ds.child("SeatNo").getValue().toString().equals("1"))
                                    {
                                        b1.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b1.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("2"))
                                    {
                                        b2.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b2.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("3"))
                                    {
                                        b3.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b3.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("4"))
                                    {
                                        b4.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b4.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("5"))
                                    {
                                        b5.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b5.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("6"))
                                    {
                                        b6.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b6.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("7"))
                                    {
                                        b7.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b7.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("8"))
                                    {
                                        b8.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b8.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("9"))
                                    {
                                        b9.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b9.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("10"))
                                    {
                                        b10.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b10.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("11"))
                                    {
                                        b11.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b11.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("12"))
                                    {
                                        b12.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b12.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("13"))
                                    {
                                        b13.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b13.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("14"))
                                    {
                                        b14.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b14.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("15"))
                                    {
                                        b15.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b15.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("16"))
                                    {
                                        b16.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b16.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("17"))
                                    {
                                        b17.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b17.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("18"))
                                    {
                                        b18.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b18.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("19"))
                                    {
                                        b19.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b19.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("20"))
                                    {
                                        b20.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b20.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("21"))
                                    {
                                        b21.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b21.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("22"))
                                    {
                                        b22.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b22.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("23"))
                                    {
                                        b23.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b23.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("24"))
                                    {
                                        b24.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b24.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("25"))
                                    {
                                        b25.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b25.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("26"))
                                    {
                                        b26.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b26.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("27"))
                                    {
                                        b27.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b27.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("28"))
                                    {
                                        b28.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b28.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("29"))
                                    {
                                        b29.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b29.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("30"))
                                    {
                                        b30.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b30.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("31"))
                                    {
                                        b31.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b31.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("32"))
                                    {
                                        b32.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b32.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("33"))
                                    {
                                        b33.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b33.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("34"))
                                    {
                                        b34.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b34.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("35"))
                                    {
                                        b35.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b35.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("36"))
                                    {
                                        b36.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b36.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("37"))
                                    {
                                        b37.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b37.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("38"))
                                    {
                                        b38.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b38.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("39"))
                                    {
                                        b39.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b39.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("40"))
                                    {
                                        b40.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b40.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("41"))
                                    {
                                        b41.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b41.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("42"))
                                    {
                                        b42.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b42.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("43"))
                                    {
                                        b43.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b43.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("44"))
                                    {
                                        b44.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b44.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("45"))
                                    {
                                        b45.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b45.setEnabled(true);
                                    }
                                    if(ds.child("SeatNo").getValue().toString().equals("46"))
                                    {
                                        b46.setBackgroundColor(getResources().getColor(R.color.sppp));
                                        b46.setEnabled(true);
                                    }

                                    // pay.setEnabled(false);
                                    //flagss=1;

                                }
//                                else {
//                                    //flagss=0;
//                                   // pay.setEnabled(true);
//                                }
                                //tt=1;
//                                else {
//                                  //  Toast.makeText(BookTicket.this,ds.child("SeatNo").getValue().toString(),Toast.LENGTH_LONG).show();
//                                }
////                                else
//                                {
//                                    DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
//                                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//                                    //String id=firebaseAuth.getCurrentUser().getEmail();
//                                    String id=ds.child("email").getValue().toString();
//                                    databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
//                                            dattm).child("arrival_time").setValue(newTime.getText().toString().trim());
////                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
////                                                                    child(busnam.getText().toString().trim()+dattm).
////                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
////                                                            databaseReference12.setValue("Bus_Cancelled");
//
//                                }

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
                else {
                   // pay.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                bookticket.putExtra("seatNo","1");
                startActivity(bookticket);
            }
        });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","2");
               startActivity(bookticket);
           }
       });
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","3");
               startActivity(bookticket);
           }
       });
       b4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","4");
               startActivity(bookticket);
           }
       });
       b5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","5");
               startActivity(bookticket);
           }
       });
       b6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","6");
               startActivity(bookticket);
           }
       });
       b7.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","7");
               startActivity(bookticket);
           }
       });
       b8.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","8");
               startActivity(bookticket);
           }
       });
       b9.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","9");
               startActivity(bookticket);
           }
       });
       b10.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","10");
               startActivity(bookticket);
           }
       });
       b11.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","11");
               startActivity(bookticket);
           }
       });
       b12.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","12");
               startActivity(bookticket);
           }
       });
       b13.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","13");
               startActivity(bookticket);
           }
       });
       b14.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","14");
               startActivity(bookticket);
           }
       });
       b15.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","15");
               startActivity(bookticket);
           }
       });
       b16.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","16");
               startActivity(bookticket);
           }
       });
       b17.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","17");
               startActivity(bookticket);
           }
       });
       b18.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","18");
               startActivity(bookticket);
           }
       });
       b19.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","19");
               startActivity(bookticket);
           }
       });
       b20.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","20");
               startActivity(bookticket);
           }
       });
       b21.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","21");
               startActivity(bookticket);
           }
       });
       b22.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","22");
               startActivity(bookticket);
           }
       });
       b23.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","23");
               startActivity(bookticket);
           }
       });
       b24.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","24");
               startActivity(bookticket);
           }
       });
       b25.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","25");
               startActivity(bookticket);
           }
       });
       b26.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","26");
               startActivity(bookticket);
           }
       });
       b27.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","27");
               startActivity(bookticket);
           }
       });
       b28.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","28");
               startActivity(bookticket);
           }
       });
       b29.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","29");
               startActivity(bookticket);
           }
       });
       b30.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","30");
               startActivity(bookticket);
           }
       });
       b31.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","31");
               startActivity(bookticket);
           }
       });
       b32.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","32");
               startActivity(bookticket);
           }
       });
       b33.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","33");
               startActivity(bookticket);
           }
       });
       b34.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","34");
               startActivity(bookticket);
           }
       });
       b35.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","35");
               startActivity(bookticket);
           }
       });
       b36.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","36");
               startActivity(bookticket);
           }
       });
       b37.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","37");
               startActivity(bookticket);
           }
       });
       b38.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","38");
               startActivity(bookticket);
           }
       });
       b39.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","39");
               startActivity(bookticket);
           }
       });
       b40.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","40");
               startActivity(bookticket);
           }
       });
       b41.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","41");
               startActivity(bookticket);
           }
       });
       b42.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","42");
               startActivity(bookticket);
           }
       });
       b43.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","43");
               startActivity(bookticket);
           }
       });
       b44.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","44");
               startActivity(bookticket);
           }
       });
       b45.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","45");
               startActivity(bookticket);
           }
       });
       b46.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               bookticket.putExtra("seatNo","46");
               startActivity(bookticket);
           }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SeatStructure.this,drawerAct.class));
    }
}

