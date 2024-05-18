package com.tdilcs.tdilconsultancyservices;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Register extends AppCompatActivity {
    FrameLayout registerFrameLayout;
    public static boolean onResetPasswordFragment=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerFrameLayout=findViewById(R.id.registerFrameLayout);
        setDefaultFragment(new SignIn());
    }
    public void setDefaultFragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(registerFrameLayout.getId(), fragment);
        ft.commit();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(onResetPasswordFragment){
                onResetPasswordFragment=false;
                setFragment(new SignIn());
                return false;
            }
        }
        return  super.onKeyDown(keyCode, event);
    }
    private void setFragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        ft.replace(registerFrameLayout.getId(), fragment);
        ft.commit();
    }
}