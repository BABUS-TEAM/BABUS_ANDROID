package com.example.babus_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText fn;
    EditText ln;
    EditText un;
    EditText ki;
    EditText pn;
    EditText em;
    EditText ps;
    FirebaseAuth fauth;
    ProgressBar pb2;
    Button reg;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fn = findViewById(R.id.fn);
        ln = findViewById(R.id.ln);
        un = findViewById(R.id.un);
        ki = findViewById(R.id.ki);
        pn = findViewById(R.id.pn);
        em = findViewById(R.id.em);
        ps = findViewById(R.id.ps2);
        pb2 = findViewById(R.id.pb2);
        reg = findViewById(R.id.reg);
        fauth = FirebaseAuth.getInstance();

        if(fauth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                finish();
        }


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String sfn = fn.getText().toString();
                String  sln = ln.getText().toString();
                String  sun = un.getText().toString();
                String  ski = ki.getText().toString();
                String spn = pn.getText().toString();

                mDatabase = FirebaseDatabase.getInstance();
               //  userId = mDbRef.push().getKey();
                mDbRef = mDatabase.getReference("User/First Name");
                mDbRef.setValue(sfn);
                //mDbRef.child(userId).setValue(sfn);
                mDbRef = mDatabase.getReference("User/Last Name");
                mDbRef.setValue(sln);
               // mDbRef.child(userId).setValue(sln);
                mDbRef = mDatabase.getReference("User/Username");
                mDbRef.setValue(sun);
             //   mDbRef.child(userId).setValue(sun);
                mDbRef = mDatabase.getReference("User/Kebele ID");
               mDbRef.setValue(ski);
               // mDbRef.child(userId).setValue(ski);
                mDbRef = mDatabase.getReference("User/Phone Number");
              mDbRef.setValue(spn);
               // mDbRef.child(userId).setValue(spn);



                //Users user = new Users("Hillary", "hillary@xyz.com", "90097863873", "Tokyo");





                String ems = em.getText().toString().trim();
                String pss = ps.getText().toString().trim();
               final String uns = un.getText().toString().trim();


                if (TextUtils.isEmpty(ems)) {
                    em.setError("Email is required");
                }
                if (TextUtils.isEmpty(pss)) {
                    ps.setError("Password is required");
                }
                if (pss.length() < 5) {
                    ps.setError("Password length should be greater than 5");
                }

                pb2.setVisibility(view.VISIBLE);

                fauth.createUserWithEmailAndPassword(ems, pss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Welcome" + uns, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {

                            Toast.makeText(Register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb2.setVisibility(view.INVISIBLE);
                        }
                    }
                });


            }
        });

    }

    public void logi(View view){

        startActivity(new Intent(getApplicationContext(), LogIn.class));
    }
}