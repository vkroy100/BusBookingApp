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

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class BusInfoLogin extends AppCompatActivity {

   private AutoCompleteTextView datee,month,year;
   private Button busInfo;
   private EditText busname;
    private SlidrInterface slidrInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info_login);

        busInfo=findViewById(R.id.businfor);
        datee=findViewById(R.id.dayinfo);
        month=findViewById(R.id.monthinfo);
        year=findViewById(R.id.yearinfo);
        busname=findViewById(R.id.busnamee);
        slidrInterface= Slidr.attach(this);
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
        datee.setThreshold(1);
        month.setThreshold(1);
        year.setThreshold(1);
        datee.setAdapter(dayss);
        month.setAdapter(mon);
        year.setAdapter(yea);
        busInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateee=datee.getText().toString().trim()+"-"+month.getText().toString().trim()+"-"+
                        year.getText().toString().trim();
                if(!TextUtils.isEmpty(datee.getText().toString().trim()) && !TextUtils.isEmpty(month.getText().toString().trim())
                && !TextUtils.isEmpty(year.getText().toString().trim()))
                {
                    Intent intent = new Intent(BusInfoLogin.this, BusInfo.class);
                    intent.putExtra("datebusname", busname.getText().toString().trim()+dateee);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BusInfoLogin.this,drawerAct.class));
    }
}
