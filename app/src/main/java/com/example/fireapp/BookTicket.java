package com.example.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class BookTicket extends AppCompatActivity
{

    String busname,seats,arrival,departure,days,date,totalSeat;
    DatabaseReference databaseReference,passInfo,noOfpass;
    String forcheck;
    EditText name,idno;
    FirebaseAuth userAuth;
    Toast toast;
    Button book,pay;
    private Long noOfPass;
    private  String enab;
    String passName;
    String passIdNo;
    Button check;
    String SeatNo;
    private int flagss=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        SlidrInterface slidrInterface = Slidr.attach(this);
        Bundle extras = getIntent().getExtras();

            name = findViewById(R.id.cusName);
            idno = findViewById(R.id.cusId);
            //seatNoo=findViewById(R.id.seatNo);
            busname = extras.getString("busname").trim();
            seats = extras.getString("remaining_seats").trim();
            arrival = extras.getString("arrival_time");
            departure = extras.getString("departure_time").trim();
            noOfPass = extras.getLong("NoOfPass");
            days = extras.getString("day").trim();
            date = extras.getString("date").trim();
            totalSeat = extras.getString("totalSeat").trim();
            enab=extras.getString("enab").trim();
            SeatNo=extras.getString("seatNo");
           // check=findViewById(R.id.check);
        userAuth=FirebaseAuth.getInstance();
        String userId=userAuth.getCurrentUser().getEmail();
        book=findViewById(R.id.book);
        //String s="s.sffs";
        passInfo=FirebaseDatabase.getInstance().getReference().child("Users").child(userId.split("@")[0]);
        noOfpass=FirebaseDatabase.getInstance().getReference().child("User").child(userId.split("@")[0]);
        pay=findViewById(R.id.pay);

        int sea=Integer.parseInt(seats);
        if(enab.equals("0"))
        {
            book.setEnabled(false);
        }
        if(enab.equals("1"))
        {
           book.setEnabled(true);
           forcheck=extras.getString("forCheck");
           name.setText(extras.getString("passname"));
           idno.setText(extras.getString("passId"));
          // seatNoo.setText(extras.getString("SeatNo"));
           passIdNo=idno.getText().toString().trim();
           passName=name.getText().toString().trim();
           SeatNo=extras.getString("seatNo");
        }
        if(sea==0)
        {
            pay.setEnabled(false);
            book.setEnabled(false);
        }
       // pay.setEnabled(false);
//        check.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//               // SeatNo=seatNoo.getText().toString().trim();
//                forcheck=SeatNo;
//                if(!TextUtils.isEmpty(SeatNo)){
//                    DatabaseReference dfg=FirebaseDatabase.getInstance().getReference();
//                    dfg.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                     if(dataSnapshot.hasChild("PassengerOfAllBus") && dataSnapshot.child("PassengerOfAllBus").hasChild(busname+date))
//                     {
//                    DatabaseReference databaseReference14=FirebaseDatabase.getInstance().getReference();
//                    Query query = databaseReference14.child("PassengerOfAllBus").child(busname.trim()+date).orderByChild("SeatNo");
//
//                    ValueEventListener valueEventListener = new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                //long score = ds.child("score").getValue(Long.class);
//                                if (!ds.child("status").getValue().toString().equals("Cancelled") && ds.child("SeatNo").getValue().
//                                        toString().equals(SeatNo)) {
//                                 //  toast= Toast.makeText(BookTicket.this, "This seat is already booked", Toast.LENGTH_SHORT);
//                                   // toast.show();
//
//                                    pay.setEnabled(false);
//                                    //flagss=1;
//                                    break;
//                                } else {
//                                    //flagss=0;
//                                    pay.setEnabled(true);
//                                }
//                                //tt=1;
////                                else {
////                                  //  Toast.makeText(BookTicket.this,ds.child("SeatNo").getValue().toString(),Toast.LENGTH_LONG).show();
////                                }
//////                                else
////                                {
////                                    DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
////                                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
////                                    //String id=firebaseAuth.getCurrentUser().getEmail();
////                                    String id=ds.child("email").getValue().toString();
////                                    databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
////                                            dattm).child("arrival_time").setValue(newTime.getText().toString().trim());
//////                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
//////                                                                    child(busnam.getText().toString().trim()+dattm).
//////                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
//////                                                            databaseReference12.setValue("Bus_Cancelled");
////
////                                }
//
//                            }
//                        }
//
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    };
//                        query.addListenerForSingleValueEvent(valueEventListener);
//                        }
//                        else {
//                            pay.setEnabled(true);
//                     }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//            }
//        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                passName=name.getText().toString().trim();
                passIdNo=idno.getText().toString().trim();
                //SeatNo=seatNoo.getText().toString().trim();
//                if(!TextUtils.isEmpty(SeatNo)){
//                    DatabaseReference databaseReference14=FirebaseDatabase.getInstance().getReference();
//                    Query query = databaseReference14.child("PassengerOfAllBus").child(busname.trim()+date).orderByChild("SeatNo");
//
//                    ValueEventListener valueEventListener = new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                                //long score = ds.child("score").getValue(Long.class);
//                                if(!ds.child("status").getValue().toString().equals("Cancelled") &&  ds.child("SeatNo").getValue().
//                                        toString().equals(SeatNo))
//                                {
//                                    flagss=1;
//                                    break;
//                                }
//                                //tt=1;
////                                else {
////                                  //  Toast.makeText(BookTicket.this,ds.child("SeatNo").getValue().toString(),Toast.LENGTH_LONG).show();
////                                }
//////                                else
////                                {
////                                    DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
////                                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
////                                    //String id=firebaseAuth.getCurrentUser().getEmail();
////                                    String id=ds.child("email").getValue().toString();
////                                    databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
////                                            dattm).child("arrival_time").setValue(newTime.getText().toString().trim());
//////                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
//////                                                                    child(busnam.getText().toString().trim()+dattm).
//////                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
//////                                                            databaseReference12.setValue("Bus_Cancelled");
////
////                                }
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    };
//                    query.addListenerForSingleValueEvent(valueEventListener);
//                }
                if(!TextUtils.isEmpty(passIdNo) && !TextUtils.isEmpty(passName) && passIdNo.trim().length()==8 )
                {

//                    DatabaseReference databaseReference14=FirebaseDatabase.getInstance().getReference();
//                    Query query = databaseReference14.child("PassengerOfAllBus").child(busname.trim()+date).orderByChild("SeatNo");
//
//                    ValueEventListener valueEventListener = new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                                //long score = ds.child("score").getValue(Long.class);
//                                if(!ds.child("status").getValue().toString().equals("Cancelled") &&  ds.child("SeatNo").getValue().toString().equals(SeatNo))
//                                {
//                                    flagss=1;
//                                    break;
//                                }
//                                else {
//                                    Toast.makeText(BookTicket.this,ds.child("SeatNo").getValue().toString(),Toast.LENGTH_LONG).show();
//                                }
////                                else
////                                {
////                                    DatabaseReference databaseReference11=FirebaseDatabase.getInstance().getReference().child("Users");
////                                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
////                                    //String id=firebaseAuth.getCurrentUser().getEmail();
////                                    String id=ds.child("email").getValue().toString();
////                                    databaseReference11.child(id).child(ds.child("IdNo").getValue()+ds.child("SeatNo").getValue().toString()+busnam.getText().toString().trim()+
////                                            dattm).child("arrival_time").setValue(newTime.getText().toString().trim());
//////                                                            DatabaseReference databaseReference12=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus").
//////                                                                    child(busnam.getText().toString().trim()+dattm).
//////                                                                    child(ds.child("SeatNo").getValue().toString()).child("status");
//////                                                            databaseReference12.setValue("Bus_Cancelled");
////
////                                }
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    };
//                    query.addListenerForSingleValueEvent(valueEventListener);
                    //Toast.makeText(BookTicket.this,String.valueOf(flagss),Toast.LENGTH_LONG).show();

                        Intent bookticket = new Intent(BookTicket.this, Pay.class);

                        bookticket.putExtra("busname", busname);
                        bookticket.putExtra("remaining_seats", seats);
                        bookticket.putExtra("arrival_time", arrival);
                        bookticket.putExtra("departure_time", departure);
                        bookticket.putExtra("NoOfPass", noOfPass);
                        bookticket.putExtra("day", days);
                        bookticket.putExtra("date", date);
                        bookticket.putExtra("totalSeat", totalSeat);
                        bookticket.putExtra("passname", passName);
                        bookticket.putExtra("passId", passIdNo);
                        bookticket.putExtra("SeatNo", SeatNo);
                        bookticket.putExtra("forCheck",forcheck);
                        //bookticket.putExtra("status","Booked");
                    //toast.cancel();
                       // bookticket.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(bookticket);


                }
                else
                    {
                       // toast.cancel();
                    Toast.makeText(BookTicket.this,"Wrong credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
        book.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

               // SeatNo=seatNoo.getText().toString().trim();  && !TextUtils.isEmpty(SeatNo) && forcheck.equals(SeatNo)
                if(!TextUtils.isEmpty(passIdNo) && !TextUtils.isEmpty(passName) )
                {

                    final DatabaseReference databaseReference3=FirebaseDatabase.getInstance().getReference().child("CancelledTicket")
                            .child(busname+date);
                    databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                             if(dataSnapshot.hasChild("NoOfCancelledTicket") && !dataSnapshot.child("NoOfCancelledTicket").getValue().equals("0") && dataSnapshot.hasChild("Seat"+forcheck)
                                     )
                             {
                                 noOfPass=noOfPass+1;
                                 String noC= (String) dataSnapshot.child("NoOfCancelledTicket").getValue();
                                // String seatNo= (String) dataSnapshot.child("Seat"+noC).getValue();
                                 Long noc=Long.parseLong(noC);
                                 noc=noc-1;
                                 databaseReference3.child("NoOfCancelledTicket").setValue(String.valueOf(noc));
                                 databaseReference3.child("Seat"+SeatNo).removeValue();
                                 noOfpass.child("NoOfPass").setValue(noOfPass);
                                 String passNo= passIdNo + String.valueOf(SeatNo) + busname + date;
                                 passInfo.child(passNo).child("name").setValue(passName);
                                 passInfo.child(passNo).child("IdNo").setValue(passIdNo);
                                 passInfo.child(passNo).child("day").setValue(days);
                                 passInfo.child(passNo).child("busname").setValue(busname);
                                 passInfo.child(passNo).child("date").setValue(date);
                                 passInfo.child(passNo).child("arrival_time").setValue(arrival);
                                 passInfo.child(passNo).child("departure_time").setValue(departure);
                                 passInfo.child(passNo).child("SeatNo").setValue(SeatNo);
                                 passInfo.child(passNo).child("status").setValue("Booked");
//                                 databaseReference = FirebaseDatabase.getInstance().getReference().child("Buses").child(date)
//                                         .child(busname);
//                                 int seat = Integer.parseInt(seats);
//                                 seat = seat - 1;
                                // databaseReference.child("remaining_seats").setValue(String.valueOf(seat));
                                 DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference().
                                         child("PassengerOfAllBus").child(busname+date).child(String.valueOf(SeatNo));
                                 databaseReference1.child("names").setValue(passName);
                                 databaseReference1.child("IdNo").setValue(passIdNo);
                                 databaseReference1.child("SeatNo").setValue(SeatNo);
                                 // databaseReference1.child("date").setValue(date);
                                 databaseReference1.child("status").setValue("Booked");
                                 databaseReference1.child("email").setValue(FirebaseAuth.getInstance().
                                         getCurrentUser().getEmail().split("@")[0]);
//                                 FirebaseUser firebaseAuth=FirebaseAuth.getInstance().getCurrentUser();
//                                 String email=firebaseAuth.getEmail();
                                // toast.cancel();
                                 Intent ss=new Intent(BookTicket.this, drawerAct.class);
                                 ss.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
                                 startActivity(ss);
                             }
                            else
                                {
                                 noOfPass=noOfPass+1;
                                 //Long seatNo=Long.parseLong(totalSeat)-Long.parseLong(seats)+1;
                                 noOfpass.child("NoOfPass").setValue(noOfPass);
                                 String passNo= passIdNo + SeatNo + busname + date;
                                 passInfo.child(passNo).child("name").setValue(passName);
                                 passInfo.child(passNo).child("IdNo").setValue(passIdNo);
                                 passInfo.child(passNo).child("day").setValue(days);
                                 passInfo.child(passNo).child("busname").setValue(busname);
                                 passInfo.child(passNo).child("date").setValue(date);
                                 passInfo.child(passNo).child("arrival_time").setValue(arrival);
                                 passInfo.child(passNo).child("departure_time").setValue(departure);
                                 passInfo.child(passNo).child("SeatNo").setValue(SeatNo);
                                 passInfo.child(passNo).child("status").setValue("Booked");
                                 databaseReference = FirebaseDatabase.getInstance().getReference().child("Buses").child(date)
                                         .child(busname);
                                 int seat = Integer.parseInt(seats);
                                 seat = seat - 1;
                                 databaseReference.child("remaining_seats").setValue(String.valueOf(seat));
                                 DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference().
                                         child("PassengerOfAllBus").child(busname+date).child(String.valueOf(SeatNo));
                                 databaseReference1.child("names").setValue(passName);
                                 databaseReference1.child("IdNo").setValue(passIdNo);
                                 databaseReference1.child("SeatNo").setValue(SeatNo);
                                 // databaseReference1.child("date").setValue(date);
                                 databaseReference1.child("status").setValue("Booked");
                                 databaseReference1.child("email").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
                                 Intent ss=new Intent(BookTicket.this, drawerAct.class);
                                 ss.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
                                 //   toast.cancel();
                                 startActivity(ss);
                             }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
//                    noOfPass=noOfPass+1;
//                    Long seatNo=Long.parseLong(totalSeat)-Long.parseLong(seats)+1;
//                    noOfpass.child("NoOfPass").setValue(noOfPass);
//                    String passNo= passIdNo + String.valueOf(seatNo) + busname + date;
//                    passInfo.child(passNo).child("name").setValue(passName);
//                    passInfo.child(passNo).child("IdNo").setValue(passIdNo);
//                    passInfo.child(passNo).child("day").setValue(days);
//                    passInfo.child(passNo).child("busname").setValue(busname);
//                    passInfo.child(passNo).child("date").setValue(date);
//                    passInfo.child(passNo).child("arrival_time").setValue(arrival);
//                    passInfo.child(passNo).child("departure_time").setValue(departure);
//                    passInfo.child(passNo).child("SeatNo").setValue(String.valueOf(seatNo));
//                    passInfo.child(passNo).child("status").setValue("Booked");
//                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Buses").child(date)
//                            .child(busname);
//                    int seat = Integer.parseInt(seats);
//                    seat = seat - 1;
//                    databaseReference.child("remaining_seats").setValue(String.valueOf(seat));
//                    DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference().
//                            child("PassengerOfAllBus").child(busname+date).child(String.valueOf(seatNo));
//                    databaseReference1.child("names").setValue(passName);
//                    databaseReference1.child("IdNo").setValue(passIdNo);
//                    databaseReference1.child("SeatNo").setValue(String.valueOf(seatNo));
//                   // databaseReference1.child("date").setValue(date);
//                    databaseReference1.child("status").setValue("Booked");
//                    startActivity(new Intent(BookTicket.this, Account_Act.class));
                }
                else
                    {
                    Toast.makeText(BookTicket.this,"Enter valid credentials",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BookTicket.this,dayTotal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_CLEAR_TASK);
       // toast.cancel();
        startActivity(intent);
    }
}
