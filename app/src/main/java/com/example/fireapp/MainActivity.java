package com.example.fireapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private EditText editText1;
    private TextView textView;
    private Firebase rootRef;
    private ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootRef=new Firebase("https://fireapp-7ba28.firebaseio.com/");
       // final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        button=findViewById(R.id.button);
//        editText=findViewById(R.id.editText);
//        editText1=findViewById(R.id.editText2);
       // textView=findViewById(R.id.textView);
        listView=findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String string=editText.getText().toString();
//                String key=editText1.getText().toString();
//                rootRef.child(key).setValue(string);
//            }
//        });
        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String val=dataSnapshot.getValue(String.class);
                arrayList.add(val);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String v=dataSnapshot.getValue(String.class);  // for single object
//                textView.setText(v);
                Map<String,String> map=dataSnapshot.getValue(Map.class);
                String name=map.get("name");
                String name1=map.get("name1");
                Log.v("name",name);
                Log.v("nam1",name1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//              String v=dataSnapshot.getValue(String.class);
//              textView.setText(v);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
