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
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class BookHistory extends AppCompatActivity {

    private RecyclerView bookingHis;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private List<BusHistory> historyList;
    private MyAdapters adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        SlidrInterface slidrInterface = Slidr.attach(this);
        bookingHis=findViewById(R.id.bookHistory);
        bookingHis.setHasFixedSize(true);
        RecyclerView.LayoutManager Lm=new LinearLayoutManager(getApplicationContext());
        bookingHis.setLayoutManager(Lm);
        bookingHis.setItemAnimator(new DefaultItemAnimator());
        bookingHis.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mAuth= FirebaseAuth.getInstance();
        historyList=new ArrayList<>();
        adapter=new MyAdapters(historyList);
        GetHistFirebase();

    }
    void GetHistFirebase(){
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getEmail().split("@")[0]);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BusHistory bus=new BusHistory();
                bus=dataSnapshot.getValue(BusHistory.class);
                historyList.add(bus);
                bookingHis.setAdapter(adapter);
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
    public class MyAdapters extends RecyclerView.Adapter<BookHistory.MyAdapters.ViewHolder>{
        List<BusHistory> listarray;
        public MyAdapters(List<BusHistory> list){
            this.listarray=list;
        }

        @Override
        public MyAdapters.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.book_history,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final BusHistory bus=listarray.get(position);

            holder.setArrival("Arrival_Time"+" "+bus.getArrival_time());
            holder.setBusname("Busname"+" "+bus.getBusname());
            holder.setDeparture("Departure_time"+" "+bus.getDeparture_time());
            // holder.setRemainingSeats("Remaining_Seats"+" "+bus.getRemaining_seats());
            holder.setDay("Day"+": "+bus.getDay());
            holder.setDate("Date"+": "+bus.getDate());
            holder.setId("Id No"+": "+bus.getIdNo());
            holder.setPassengerName("Passenger Name"+": "+bus.getName());
            holder.setSeatNo("Seat Number"+": "+bus.getSeatNo());
           holder.setStatus("Status"+": "+bus.getStatus());
           // holder.mView.setBackground();
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                   Intent intent=new Intent(BookHistory.this,CancelTicket.class);
                   intent.putExtra("busname",bus.getBusname());
                   intent.putExtra("date",bus.getDate());
                   intent.putExtra("IdNo",bus.getIdNo());
                   intent.putExtra("SeatNo",bus.getSeatNo());
                   intent.putExtra("status",bus.getStatus());
                   intent.putExtra("name",bus.getName());
                   startActivity(intent);
                }
            });
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
            TextView busnames,days,totalSeat,passNames;
            TextView seats,arrival,depart,datee,stat;
            //        TextView post_title;
//        ImageButton like;
//        DatabaseReference LikeDatabase=FirebaseDatabase.getInstance().getReference().child("Likes");
//        FirebaseAuth mAuth=FirebaseAuth.getInstance();
            public ViewHolder(View itemView) {
                super(itemView);
                mView=itemView;



            }
            public void setPassengerName(String passName){
                passNames = mView.findViewById(R.id.pass_names);
                passNames.setText(passName);

            }
            public void setBusname(String title)
            {
                busnames=mView.findViewById(R.id.busnamess);
                busnames.setText(title);
            }
            public void setSeatNo(String desc){

                seats=mView.findViewById(R.id.seatNo);
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

            public void setId(String IdNo){
                totalSeat=mView.findViewById(R.id.IdNo);
                totalSeat.setText(IdNo);
            }
            public void setStatus(String status){
                stat=mView.findViewById(R.id.status);
                stat.setText(status);
            }
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(BookHistory.this,drawerAct.class);
       // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //finishAffinity();
        startActivity(i);

    }
}
