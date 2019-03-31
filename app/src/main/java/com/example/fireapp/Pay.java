package com.example.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pay extends AppCompatActivity implements PaytmPaymentTransactionCallback {

   private TextView textViewPrice;
   private Button finals;
   private String busname,seats,arrival,departure,days,date,totalSeat;
   private Long noOfPass;
    private String passname,passId,seatNo,forchecks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        SlidrInterface slidrInterface = Slidr.attach(this);
        //getting the textview
        textViewPrice = findViewById(R.id.price);
        finals=findViewById(R.id.finals);
        Bundle extras = getIntent().getExtras();
        busname = extras.getString("busname");
        seats = extras.getString("remaining_seats");
        arrival = extras.getString("arrival_time");
        departure = extras.getString("departure_time");
        noOfPass = extras.getLong("NoOfPass");
        days = extras.getString("day");
        date = extras.getString("date");
        totalSeat = extras.getString("totalSeat");
        passname=extras.getString("passname");
        passId=extras.getString("passId");
        seatNo=extras.getString("SeatNo");
        forchecks=extras.getString("forCheck");
        //attaching a click listener to the button buy
        findViewById(R.id.buttonBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calling the method generateCheckSum() which will generate the paytm checksum for payment
                generateCheckSum();
            }
        });
       finals.setEnabled(false);

    }

    private void generateCheckSum() {

        //getting the tax amount first.

        String txnAmount = textViewPrice.getText().toString().trim();

        //creating a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the retrofit api service
        Api apiService = retrofit.create(Api.class);

        //creating paytm object
        //containing all the values required
        String orderID = Paytm.generateString();
        String callBackurl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;
        final Paytm paytm = new Paytm(
                Constants.M_ID,
                Constants.CHANNEL_ID,
                txnAmount,
                Constants.WEBSITE,
                callBackurl,
                Constants.INDUSTRY_TYPE_ID
        );

        //creating a call object from the apiService
        Call<Checksum> call = apiService.getChecksum(
                paytm.getmId(),
                paytm.getOrderId(),
                paytm.getCustId(),
                paytm.getChannelId(),
                paytm.getTxnAmount(),
                paytm.getWebsite(),
                callBackurl,
                paytm.getIndustryTypeId()
        );

        //making the call to generate checksum
        call.enqueue(new Callback<Checksum>() {
            @Override
            public void onResponse(Call<Checksum> call, Response<Checksum> response) {

                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                initializePaytmPayment(response.body().getChecksumHash(), paytm);
            }

            @Override
            public void onFailure(Call<Checksum> call, Throwable t) {

            }
        });
    }

    private void initializePaytmPayment(String checksumHash, Paytm paytm) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getStagingService();

        //use this when using for production
        //PaytmPGService Service = PaytmPGService.getProductionService();

        //creating a hashmap and adding all the values required
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", Constants.M_ID);
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCustId());
        paramMap.put("CHANNEL_ID", paytm.getChannelId());
        paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
        paramMap.put("WEBSITE", paytm.getWebsite());
        paramMap.put("CALLBACK_URL", paytm.getCallBackUrl());
        paramMap.put("CHECKSUMHASH", checksumHash);
        paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());


        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

    //all these overriden method is to detect the payment result accordingly



    @Override
    public void onTransactionResponse(Bundle bundle) {

        finals.setEnabled(true);
        findViewById(R.id.finals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent bookticket=new Intent(Pay.this,BookTicket.class);
                bookticket.putExtra("enab","1");
                bookticket.putExtra("busname",busname);
                bookticket.putExtra("remaining_seats",seats);
                bookticket.putExtra("arrival_time",arrival);
                bookticket.putExtra("departure_time",departure);
                bookticket.putExtra("NoOfPass",noOfPass);
                bookticket.putExtra("day",days);
                bookticket.putExtra("date",date);
                bookticket.putExtra("totalSeat",totalSeat);
                bookticket.putExtra("passname",passname);
                bookticket.putExtra("passId",passId);
                bookticket.putExtra("seatNo",seatNo);
                bookticket.putExtra("forCheck",forchecks);
                //bookticket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //finishAffinity();
                startActivity(bookticket);
            }
        });
       // findViewById(R.id.book).setEnabled(true);
        //
       // Toast.makeText(Pay.this,bundle.getShortArray("STATUS").toString(),Toast.LENGTH_LONG);
    //   Toast.makeText(this, bundle.toString(), Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"aaaaaaaaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();
    }

    @Override
    public void networkNotAvailable() {
        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();

    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }
}
