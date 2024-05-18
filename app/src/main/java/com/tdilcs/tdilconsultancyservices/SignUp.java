package com.tdilcs.tdilconsultancyservices;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp extends Fragment {
    private EditText signUpName, signUpEmail, signUpPhone, signUpPassword, signUpConfPassword;
    ProgressBar signUpProgressBar;
    private String emailPattern="[A-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private Button signUpBtn;
    private TextView signIn, signUpNotice;
    private FirebaseAuth firebaseAuth;
    private FrameLayout parentFrameLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseFirestore firebaseFirestore;
    public SignUp() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        signIn=(TextView) view.findViewById(R.id.signIn);
        signUpNotice=(TextView) view.findViewById(R.id.signUpNotice);
        signUpName=(EditText) view.findViewById(R.id.signUpName);
        signUpEmail=(EditText) view.findViewById(R.id.signUpEmail);
        signUpPhone=(EditText) view.findViewById(R.id.signUpPhone);
        signUpPassword=(EditText) view.findViewById(R.id.signUpPassword);
        signUpConfPassword=(EditText) view.findViewById(R.id.signUpConfPassword);
        signUpBtn=(Button) view.findViewById(R.id.signUpBtn);
        parentFrameLayout=(FrameLayout) getActivity().findViewById(R.id.registerFrameLayout);
        signUpProgressBar=(ProgressBar) view.findViewById(R.id.signUpProgressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignIn());

            }
        });
        signUpName.addTextChangedListener(new TextWatcher() {
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
        signUpEmail.addTextChangedListener(new TextWatcher() {
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
        signUpPhone.addTextChangedListener(new TextWatcher() {
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
        signUpPassword.addTextChangedListener(new TextWatcher() {
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
        signUpConfPassword.addTextChangedListener(new TextWatcher() {
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
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailAndPassword();
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentManager fm=getActivity().getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        ft.replace(parentFrameLayout.getId(), fragment);
        ft.commit();
    }
    private void checkInputs(){
        if(!TextUtils.isEmpty(signUpName.getText())){
            if(!TextUtils.isEmpty(signUpEmail.getText())){
             if(!TextUtils.isEmpty(signUpPhone.getText())){
                 if(!TextUtils.isEmpty(signUpPassword.getText()) && signUpPassword.length()>=6){
                     if(!TextUtils.isEmpty(signUpConfPassword.getText())){
                            signUpBtn.setEnabled(true);
                            signUpNotice.setText("");
                     }else{
                            signUpNotice.setText("All Fields are mandatory!");
                         signUpBtn.setEnabled(false);
                     }
                 }else{
                     signUpNotice.setText("All Fields are mandatory!");
                     signUpBtn.setEnabled(false);
                 }
             }else{
                 signUpNotice.setText("All Fields are mandatory!");
                 signUpBtn.setEnabled(false);
             }
            }else{
                signUpNotice.setText("All Fields are mandatory!");
                signUpBtn.setEnabled(false);
            }
        }else{
            signUpNotice.setText("All Fields are mandatory!");
            signUpBtn.setEnabled(false);
        }
    }
    private void checkEmailAndPassword(){
            if(signUpEmail.getText().toString().matches(emailPattern)){
                if(signUpPassword.getText().toString().equals(signUpConfPassword.getText().toString())){
                    signUpProgressBar.setVisibility(View.VISIBLE);
                    signUpBtn.setEnabled(false);
                    firebaseAuth.createUserWithEmailAndPassword(signUpEmail.getText().toString(), signUpPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Map<Object, String> userdata=new HashMap<>();
                                        userdata.put("Name",signUpName.getText().toString());
                                        userdata.put("Email",signUpEmail.getText().toString());
                                        userdata.put("Phone",signUpPhone.getText().toString());
                                        firebaseFirestore.collection("USERS")
                                                .add(userdata)
                                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                                        if(task.isSuccessful()){
                                                            Intent mainIntent=new Intent(getActivity(), MainActivity.class);
                                                             startActivity(mainIntent);
                                                            getActivity().finish();
                                                        }else{
                                                            String error=task.getException().getMessage();
                                                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                            signUpProgressBar.setVisibility(View.GONE);
                                                            signUpBtn.setEnabled(true);
                                                        }
                                                    }
                                                });
                                    }else{
                                        String error=task.getException().getMessage();
                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                        signUpProgressBar.setVisibility(View.GONE);
                                        signUpBtn.setEnabled(true);
                                    }
                                }
                            });
                }else{
                    signUpNotice.setText("Passwords don't match!");
                    signUpProgressBar.setVisibility(View.GONE);
                }
            }else{
                signUpNotice.setText("Invalid Email!");
                signUpProgressBar.setVisibility(View.GONE);
            }
    }
}