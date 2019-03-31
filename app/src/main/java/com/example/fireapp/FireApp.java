package com.example.fireapp;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FireApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//          Firebase.setAndroidContext(this);

            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
