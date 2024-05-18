package com.tdilcs.tdilconsultancyservices;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser=firebaseAuth.getCurrentUser();
        if(currentUser==null){
            Intent registerIntent=new Intent(Splash.this,Register.class);
            startActivity(registerIntent);
            finish();
        }else{
            Intent mainIntent=new Intent(Splash.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}