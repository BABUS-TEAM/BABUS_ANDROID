package com.example.babus_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
EditText lem , lps;
Button l;
FirebaseAuth fauth=null;
ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        lem = findViewById(R.id.em1);
        lps = findViewById(R.id.paword);
        l = findViewById(R.id.log);
        fauth=FirebaseAuth.getInstance();
        pb = findViewById(R.id.pb);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String ems2 = lem.getText().toString().trim();
                String pss2 = lps.getText().toString().trim();

                if (TextUtils.isEmpty(ems2)) {
                    lem.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pss2)) {
                    lps.setError("Password is required");
                    return;
                }
                if (pss2.length() < 5) {
                    lps.setError("Password length should be greater than 5");
                    return;
                }

                pb.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(ems2,pss2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LogIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {

                            Toast.makeText(LogIn.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.INVISIBLE);
                        }

                    }
                });



            }
        });
    }


    public void createAcc(View view){

        startActivity(new Intent(getApplicationContext(), Register.class));

    }

    public void forgotPassword(View v){
       lem = findViewById(R.id.em1);
        final String ems2 = lem.getText().toString().trim();
        fauth=FirebaseAuth.getInstance();
        AlertDialog.Builder passwordReset = new AlertDialog.Builder(v.getContext());
        passwordReset.setTitle("Reset Password");
        passwordReset.setMessage("Reset link will be sent to :" + ems2);

        passwordReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fauth.sendPasswordResetEmail(ems2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LogIn.this, "Reset link is sent to your email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogIn.this, "The reset link is not sent", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        passwordReset.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

passwordReset.create().show();


    }
}

