package com.example.fireapp;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class BusInfo extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<BusInfoLay> busInfoLay;
    private MyAdapterss adapter;
    private RecyclerView busInfo;
    private FirebaseAuth mAuth;
    String sss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);
        busInfo=findViewById(R.id.businfo);
        SlidrInterface slidrInterface = Slidr.attach(this);
        RecyclerView.LayoutManager Lm=new LinearLayoutManager(getApplicationContext());
        busInfo.setLayoutManager(Lm);
        busInfo.setHasFixedSize(true);
        busInfo.setItemAnimator(new DefaultItemAnimator());
        busInfo.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mAuth= FirebaseAuth.getInstance();
        busInfoLay=new ArrayList<>();
        adapter=new MyAdapterss(busInfoLay);
        sss=getIntent().getExtras().getString("datebusname");
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("PassengerOfAllBus");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(sss)){
                    Toast.makeText(BusInfo.this,"There is no bus",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BusInfo.this,drawerAct.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        GetBusInfoFirebase();
    }
    void GetBusInfoFirebase(){
        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference().
                child("PassengerOfAllBus").child(sss);

        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BusInfoLay bus=new BusInfoLay();
                bus=dataSnapshot.getValue(BusInfoLay.class);
                busInfoLay.add(bus);
                busInfo.setAdapter(adapter);
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
    public class MyAdapterss extends RecyclerView.Adapter<MyAdapterss.ViewHolder>{
        List<BusInfoLay> listarray;
        public MyAdapterss(List<BusInfoLay> list){
            this.listarray=list;
        }

        @Override
        public MyAdapterss.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.activity_bus_info_layout,parent,false);

            return new MyAdapterss.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapterss.ViewHolder holder, int position) {
            final BusInfoLay bus=listarray.get(position);

           // holder.setArrival("Arrival_Time"+" "+bus.getArrival_time());
          //  holder.setBusname("Busname"+" "+bus.getBusname());
          //  holder.setDeparture("Departure_time"+" "+bus.getDeparture_time());
            // holder.setRemainingSeats("Remaining_Seats"+" "+bus.getRemaining_seats());
          //  holder.setDay("Day"+": "+bus.getDay());
         //   holder.setDate("Date"+": "+bus.getDate());
            holder.setId("Id No"+": "+bus.getIdNo());
          //  holder.setPassengerName("Passenger Name"+": "+bus.getName());
            holder.setSeatNo("Seat Number"+": "+bus.getSeatNo());
            holder.setStatus("Status"+": "+bus.getStatus());
            holder.setnamess("Passenger Name"+": "+bus.getNames());
            holder.setEmailId("Booked By"+": "+bus.getEmail());
            // holder.mView.setBackground();
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    Intent intent=new Intent(BookHistory.this,CancelTicket.class);
//                    intent.putExtra("busname",bus.getBusname());
//                    intent.putExtra("date",bus.getDate());
//                    intent.putExtra("IdNo",bus.getIdNo());
//                    intent.putExtra("SeatNo",bus.getSeatNo());
//                    intent.putExtra("status",bus.getStatus());
//                    intent.putExtra("name",bus.getName());
//                    startActivity(intent);
//                }
//            });
            //  holder.setTotalSeat("Total Seat"+": "+bus.getTotalSeat());
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    Intent bookticket=new Intent(Buslist.this,BookTicket.class);
//                    bookticket.putExtra("busname",bus.getBusname());
//                    bookticket.putExtra("remaining_seats",bus.getRemaining_seats());
//                    bookticket.putExtra("arrival_time",bus.getArrival_time());
//                    bookticket.putExtra("departure_time",bus.getDeparture_time());
//                    bookticket.putExtra("NoOfPass",noOfpass);
//                    bookticket.putExtra("day",bus.getDay());
//                    bookticket.putExtra("date",bus.getDate());
//                    bookticket.putExtra("totalSeat",bus.getTotalSeat());
//                    startActivity(bookticket);
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return listarray.size();
        }
        public  class ViewHolder extends RecyclerView.ViewHolder{

            View mView;

            TextView passNamess,statuss,idNo,seatNo,emailId;
            //        TextView post_title;
//        ImageButton like;
//        DatabaseReference LikeDatabase=FirebaseDatabase.getInstance().getReference().child("Likes");
//        FirebaseAuth mAuth=FirebaseAuth.getInstance();
            public ViewHolder(View itemView) {
                super(itemView);
                mView=itemView;



            }
            public void setnamess(String passName){
                passNamess = mView.findViewById(R.id.pass_namess);
                passNamess.setText(passName);

            }

            public void setSeatNo(String desc){

                seatNo=mView.findViewById(R.id.seatNos);
                seatNo.setText(desc);
            }








            public void setId(String IdNo){
               idNo=mView.findViewById(R.id.IdNos);
                idNo.setText(IdNo);
            }
            public void setStatus(String status){
                statuss=mView.findViewById(R.id.statuss);
                statuss.setText(status);
            }
            public void setEmailId(String emailIds)
            {
              emailId=mView.findViewById(R.id.emailId);
              emailId.setText(emailIds);
            }
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BusInfo.this,BusInfoLogin.class));
    }
}
