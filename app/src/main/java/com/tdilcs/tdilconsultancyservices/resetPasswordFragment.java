package com.tdilcs.tdilconsultancyservices;

import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
public class resetPasswordFragment extends Fragment {
TextView signUp,signIn, forgotPasswordNotice;
EditText forgotPasswordEmail;
private FrameLayout parentFrameLayout;
private FirebaseAuth firebaseAuth;
Button forgotPasswordBtn;
    public resetPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reset_password, container, false);
        signUp=(TextView) view.findViewById(R.id.signUp);
        signIn=(TextView) view.findViewById(R.id.signIn);
        forgotPasswordNotice=(TextView) view.findViewById(R.id.forgotPasswordNotice);
        forgotPasswordBtn=(Button) view.findViewById(R.id.forgotPasswordBtn);
        forgotPasswordEmail=(EditText) view.findViewById(R.id.forgotPasswordEmail);
        parentFrameLayout=(FrameLayout) getActivity().findViewById(R.id.registerFrameLayout);
        firebaseAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUp());
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignIn());
            }
        });
        forgotPasswordEmail.addTextChangedListener(new TextWatcher() {
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
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordBtn.setEnabled(false);
                firebaseAuth.sendPasswordResetEmail(forgotPasswordEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    forgotPasswordNotice.setText("Email already send.");
                                    forgotPasswordNotice.setTextColor(Color.GREEN);
                                    Toast.makeText(getActivity(), "Email Send Successfully!", Toast.LENGTH_SHORT).show();

                                }else{
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                                forgotPasswordBtn.setEnabled(true);

                            }
                        });
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
    private  void checkInputs(){
        if(!TextUtils.isEmpty(forgotPasswordEmail.getText())){
            forgotPasswordBtn.setEnabled(true);
            forgotPasswordNotice.setText("");
        }else{
            forgotPasswordNotice.setText("Enter the registered Email!");
            forgotPasswordBtn.setEnabled(false);
        }
    }
}