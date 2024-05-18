package com.tdilcs.tdilconsultancyservices;

import static com.tdilcs.deartdilcs.Register.onResetPasswordFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class SignIn extends Fragment {
    private ProgressBar signInProgressBar;
    private String emailPattern="[A-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private EditText signInEmail, signInPassword;
    private Button signInBtn;
    FirebaseAuth firebaseAuth;
    private TextView signUp, signInResetPassword, signInNotice;
    private FrameLayout parentFrameLayout;
    public SignIn() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //As FramLayout isn't in layout, it's outside in Activity
        parentFrameLayout=getActivity().findViewById(R.id.registerFrameLayout);
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);
        signUp=(TextView) view.findViewById(R.id.signUp);
        signInEmail=(EditText) view.findViewById(R.id.signInEmail);
        signInPassword=(EditText) view.findViewById(R.id.signInPassword);
        signInBtn=(Button) view.findViewById(R.id.signInBtn);
        signInResetPassword=(TextView) view.findViewById(R.id.signInResetPassword);
        signInNotice=(TextView) view.findViewById(R.id.signInNotice);
        signInProgressBar=(ProgressBar) view.findViewById(R.id.signInProgressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onResetPasswordFragment = true;
                setFragment(new SignUp());
            }
        });
        signInResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onResetPasswordFragment = true;
                setFragment(new resetPasswordFragment());
            }
        });
        signInEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        signInPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailAndPassword();
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentManager fm=getActivity().getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        ft.replace(parentFrameLayout.getId(), fragment);
        ft.commit();
    }
    private void checkInputs(){
        if(!TextUtils.isEmpty(signInEmail.getText())){
            if(!TextUtils.isEmpty(signInPassword.getText())){
                signInBtn.setEnabled(true);
                signInNotice.setText("");
            }else{
                signInNotice.setText("All Fields are mandatory!");
                signInBtn.setEnabled(false);}
        }else{
            signInNotice.setText("All Fields are mandatory!");
            signInBtn.setEnabled(false);
        }
    }
    private void checkEmailAndPassword(){
        if(signInEmail.getText().toString().matches(emailPattern)){
            if(signInPassword.length()>=6){
                signInProgressBar.setVisibility(View.VISIBLE);
                signInBtn.setEnabled(false);
                firebaseAuth.signInWithEmailAndPassword(signInEmail.getText().toString(), signInPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent mainIntent=new Intent(getActivity(), MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }else{
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                    signInProgressBar.setVisibility(View.GONE);
                                    signInBtn.setEnabled(true);
                                }
                            }
                        });
            }else{
                Toast.makeText(getActivity(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                signInProgressBar.setVisibility(View.GONE);
                signInBtn.setEnabled(true);
            }
        }else{
            signInProgressBar.setVisibility(View.GONE);
            signInBtn.setEnabled(true);
            Toast.makeText(getActivity(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
        }
    }
}