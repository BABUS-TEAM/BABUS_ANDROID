package com.example.babus_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phone_verification extends AppCompatActivity {
EditText VerifCode;
Button sendCode;
ProgressBar progBar;
FirebaseAuth fauth;
String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        Intent intent = getIntent();
      String phonenum =  intent.getStringExtra("phonenum");


        VerifCode = findViewById(R.id.EntCode);
        sendCode = findViewById(R.id.sendVn);
        progBar = findViewById(R.id.progressBar3);
        fauth = FirebaseAuth.getInstance();


sendVerificationCode(phonenum);

    sendCode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            verifyCode();

        }
    });


    }

    private void verifyCode(){
        VerifCode = findViewById(R.id.EntCode);
        String code = VerifCode.getText().toString();
        Intent intent = getIntent();
        String ems = intent.getStringExtra("email");
        String pss = intent.getStringExtra("password");

        PhoneAuthProvider.getCredential(codeSent,code);

        if (codeSent == code){

            fauth.createUserWithEmailAndPassword(ems, pss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {
                        Toast.makeText(phone_verification.this, "Welcome" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {

                        Toast.makeText(phone_verification.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        //progBar.setVisibility(view.INVISIBLE);
                    }
                }
            });
        }

else {

    Toast.makeText(getApplicationContext(),"Failed" , Toast.LENGTH_SHORT).show();
        }
    }


    private void sendVerificationCode(String phoneNum){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };
}