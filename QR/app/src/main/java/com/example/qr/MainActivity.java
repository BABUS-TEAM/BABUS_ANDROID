package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    EditText fn;
    EditText ln;
    EditText ea;
    EditText date;
    EditText time;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void QrGen(View view){

        fn = findViewById(R.id.firstname);
        ln = findViewById(R.id.lastname);
        ea = findViewById(R.id.ea);
        date = findViewById(R.id.date);
        time = findViewById(R.id.tim);
        b = findViewById(R.id.bu);

        String fnt = fn.getText().toString();
       String lnt = ln.getText().toString();
       String eat = ea.getText().toString();
        String datet = date.getText().toString();
        String timet = time.getText().toString();
String allinfo;
allinfo = "Name :"+ fnt + " " + lnt +"\n"+ "E-Mail :" + eat +"\n"+ "Date :" + datet +"\n"+ "Time : " + timet ;
        QRGEncoder qrgEncoder = new QRGEncoder(allinfo, null , QRGContents.Type.TEXT , 100);


            Bitmap bitmap =  qrgEncoder.getBitmap();


        Intent intent1 = new Intent(this, MainActivity2.class);
        intent1.putExtra("Key" , allinfo);
        /*intent1.putExtra("Key1" , fnt);
        intent1.putExtra("Key2" , lnt);
        intent1.putExtra("Key3" , eat);
        intent1.putExtra("Key4" , datet);
        intent1.putExtra("Key5" , timet);

         */
        intent1.putExtra("BitmapImage", bitmap);

        startActivity(intent1);

       // Toast.makeText(this,fnt,Toast.LENGTH_LONG).show();
    }
}