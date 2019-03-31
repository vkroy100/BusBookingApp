package com.example.fireapp;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

import static com.example.fireapp.R.layout.bus_layout;

public class Buslist extends AppCompatActivity {

    private RecyclerView busview;
    //private FirebaseListAdapter adapter;
    private DatabaseReference firebaseDatabase,noOfPasss;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Query query;
    MyAdapter adapter;
    List<Bus> listbus;
    FirebaseDatabase fdb;
    DatabaseReference dbr;
    Long noOfpass;
    String datee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslist);
        SlidrInterface slidrInterface = Slidr.attach(this);
        busview = findViewById(R.id.busview);
        busview.setHasFixedSize(true);
        RecyclerView.LayoutManager Lm=new LinearLayoutManager(getApplicationContext());
        busview.setLayoutManager(Lm);
        Bundle bundle=getIntent().getExtras();
        datee=bundle.getString("date");
        busview.setItemAnimator(new DefaultItemAnimator());
        busview.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mAuth=FirebaseAuth.getInstance();
        String userId=mAuth.getCurrentUser().getEmail();
       // Toast.makeText(Buslist.this,datee,Toast.LENGTH_LONG).show();
        noOfPasss=FirebaseDatabase.getInstance().getReference().child("User").child(userId.split("@")[0]);
        noOfPasss.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild("NoOfPass")){
                    noOfPasss.child("NoOfPass").setValue(0);
                }

                    noOfpass= (Long) dataSnapshot.child("NoOfPass").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      //  noOfPasss.child("NoOfPassen").setValue(0);
       // query = FirebaseDatabase.getInstance().getReference().child("Buses");
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Buses");
//        firebaseDatabase.child("s12").child("busname").setValue("s1");
//        firebaseDatabase.child("s12").child("remaining_seats").setValue("11");
//        firebaseDatabase.child("s12").child("arrival_time").setValue("2;00 pm");
//        firebaseDatabase.child("s12").child("departure_time").setValue("9:00 am");
//        firebaseDatabase.child("s13").child("busname").setValue("s2");
//        firebaseDatabase.child("s13").child("remaining_seats").setValue("12");
//        firebaseDatabase.child("s13").child("arrival_time").setValue("3:00 pm");
//        firebaseDatabase.child("s13").child("departure_time").setValue("10:00 am");
        listbus=new ArrayList<>();
       //busview.setDivider(new ColorDrawable(Color.MAGENTA));
        adapter=new MyAdapter(listbus);
        fdb=FirebaseDatabase.getInstance();

        GetDataFirebase();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if( firebaseAuth.getCurrentUser()== null )
//                {
////                    Intent loginIntent=new Intent(MainActivity.this,
////                            Login_Activity.class);
////                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    startActivity(loginIntent);
//                }
////                else
////                    {
////                    chek();
////                }
//            }
//        };
//        FirebaseListOptions<Bus> options=new FirebaseListOptions.Builder<Bus>().setLayout(R.layout.bus_layout)
//                .setQuery( query , Bus.class).build();
//        adapter = new FirebaseListAdapter(options) {
//            @Override
//            protected void populateView(View v, Object model, int position) {
//
//                TextView busname=v.findViewById(R.id.busnames);
//                TextView seats=v.findViewById(R.id.seats);
//                TextView arrival=v.findViewById(R.id.arrival);
//                TextView depart=v.findViewById(R.id.departure);
//                Bus buss=(Bus) model;
//                Toast.makeText(Buslist.this, buss.getBusname(),Toast.LENGTH_LONG).show();
//                busname.setText((buss.getBusname()));
//                seats.setText((buss.getRemaining_seats()));
//                arrival.setText((buss.getArrival_time()));
//                depart.setText((buss.getDeparture_time()));
//            }
//
//        };
//        busview.setAdapter(adapter);
    }
    void GetDataFirebase(){
        dbr=fdb.getReference().child("Buses").child(datee);
        dbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Bus bus=new Bus();
                bus=dataSnapshot.getValue(Bus.class);
                listbus.add(bus);
                busview.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        List<Bus> listarray;
        public MyAdapter(List<Bus> list){
            this.listarray=list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(parent.getContext()).inflate
                   (R.layout.bus_layout,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position)
        {
           final Bus bus=listarray.get(position);
           holder.setArrival("Arrival_Time"+" "+bus.getArrival_time());
           holder.setBusname("Busname"+" "+ bus.getBusname());
           holder.setDeparture("Departure_time"+" "+bus.getDeparture_time());
           holder.setRemainingSeats("Remaining_Seats"+" "+bus.getRemaining_seats());
           holder.setDay("Day"+": "+bus.getDay());
           holder.setDate("Date"+": "+bus.getDate());
           holder.setTotalSeat("Total Seat"+": "+bus.getTotalSeat());
           holder.mView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   Intent bookticket=new Intent(Buslist.this,SeatStructure.class);
                   bookticket.putExtra("busname",bus.getBusname());
                   bookticket.putExtra("remaining_seats",bus.getRemaining_seats());
                   bookticket.putExtra("arrival_time",bus.getArrival_time());
                   bookticket.putExtra("departure_time",bus.getDeparture_time());
                   bookticket.putExtra("NoOfPass",noOfpass);
                   bookticket.putExtra("day",bus.getDay());
                   bookticket.putExtra("date",bus.getDate());
                   bookticket.putExtra("totalSeat",bus.getTotalSeat());
                   bookticket.putExtra("enab","0");
                   bookticket.putExtra("flag","0");
                   DatabaseReference dbr=FirebaseDatabase.getInstance().getReference().child("CancelledTicket").child(bus.getBusname()+bus.getDate());
                   dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           String nos= (String) dataSnapshot.child("NoOfCancelledTicket").getValue();
                           Toast.makeText(Buslist.this,"No of extra seats which can be booked"+": "+nos,Toast.LENGTH_LONG).show();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                   //bookticket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(bookticket);
               }
           });

        }

        @Override
        public int getItemCount() {
            return listarray.size();
        }
        public  class ViewHolder extends RecyclerView.ViewHolder{

            View mView;
            TextView busname,days,totalSeat;
            TextView seats,arrival,depart,datee;
            //TextView post_title;
//        ImageButton like;
//        DatabaseReference LikeDatabase=FirebaseDatabase.getInstance().getReference().child("Likes");
//        FirebaseAuth mAuth=FirebaseAuth.getInstance();
            public ViewHolder(View itemView) {
                super(itemView);
                mView=itemView;



            }
            public void setBusname(String title)
            {
              busname=mView.findViewById(R.id.busnames);
                busname.setText(title);
            }
            public void setRemainingSeats(String desc){

                seats=mView.findViewById(R.id.seats);
                seats.setText(desc);
            }



            public void setArrival(String arrval)
            {
                 arrival=mView.findViewById(R.id.arrival);
                 arrival.setText(arrval);

            }
            public void setDeparture(String departure)
            {
                depart=mView.findViewById(R.id.departure);
                depart.setText(departure);
            }

            public void setDay(String day){
                days=mView.findViewById(R.id.day);
                days.setText(day);
            }
            public void setDate(String date)
            {
                datee=mView.findViewById(R.id.date);
                datee.setText(date);
            }

            public void setTotalSeat(String totalseat){
                totalSeat=mView.findViewById(R.id.totalSeat);
                totalSeat.setText(totalseat);
            }
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//
//        FirebaseRecyclerAdapter<Bus,BusViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter
//                <Bus,BusViewHolder>(Bus.class, R.layout.bus_layout,BusViewHolder.class,firebaseDatabase){
//
//
//            @Override
//            public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull BusViewHolder holder, int position, @NonNull Bus model) {
//
//            }
//        };
//    }



    public static class BusViewHolder extends RecyclerView.ViewHolder{

        View mView;
//        TextView post_title;
//        ImageButton like;
//        DatabaseReference LikeDatabase=FirebaseDatabase.getInstance().getReference().child("Likes");
//        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        public BusViewHolder(View itemView) {
            super(itemView);
            mView=itemView;



        }
        public void setBusname(String title)
        {
      TextView post_title=mView.findViewById(R.id.busnames);
            post_title.setText(title);
        }
        public void setRemainingSeats(String desc){

            TextView post_desc=mView.findViewById(R.id.seats);
            post_desc.setText(desc);
        }



        public void setArrival(String arrval){
            TextView post_username=mView.findViewById(R.id.arrival);
            post_username.setText(arrval);

        }
        public void setDeparture(String departure){
            TextView depart=mView.findViewById(R.id.departure);
            depart.setText(departure);
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
       // adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Buslist.this,drawerAct.class);
       // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       // finishAffinity();
        startActivity(i);


    }
}
