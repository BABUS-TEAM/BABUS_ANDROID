package com.example.googlemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText gs , gd;
    Button trackb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gs = findViewById(R.id.s1);
        gd = findViewById(R.id.d);
        trackb = findViewById(R.id.b);

    trackb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ss1 = gs.getText().toString().trim();
                String sd = gd.getText().toString().trim();
                if(ss1.equals("") && sd.equals("")){

                    Toast.makeText(getApplicationContext() , "Fill the blanks" , Toast.LENGTH_SHORT).show();
                }

                else {
                    DisplayTrack(ss1 , sd);
                }
            }
        });
    }

    private void DisplayTrack(String ss1, String sd) {

        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + ss1 + "/" + sd);
            Intent intent = new Intent(Intent.ACTION_VIEW , uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW , uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}