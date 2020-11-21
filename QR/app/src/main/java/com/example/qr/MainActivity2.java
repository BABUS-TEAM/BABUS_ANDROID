package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGSaver;

public class MainActivity2 extends AppCompatActivity {
ImageView iv;
    Button sa;
   // String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
iv = findViewById(R.id.iv);

Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        String x = intent.getStringExtra("Key");
iv.setImageBitmap(bitmap);


    }
/*
    public void saveq(View view){
        Intent intent = getIntent();
        String x = intent.getStringExtra("Key");
        QRGSaver.sa(savePath, x.trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);

    }

 */
}


