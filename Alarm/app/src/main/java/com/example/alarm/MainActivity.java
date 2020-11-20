package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText h ;
    EditText m ;
    EditText n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }





    public void button(View view){

   /*   Calendar calendar = Calendar.getInstance();
        int dm = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH;
       int y = calendar.get(Calendar.YEAR);
       alarmDays.add(Calendar.MONDAY);
alarmDays.add(Calendar.WEDNESDAY);
alarmDays.add(Calendar.FRIDAY);

    */

        ArrayList<Integer> alarmDays = new ArrayList<>();

        int dw = Calendar.MONDAY;
        alarmDays.add(dw);

        ArrayList<Integer> al = new ArrayList<>();

        al.add(Calendar.OCTOBER);

        findViewById(R.id.hour);
        findViewById(R.id.min);

        n = findViewById(R.id.name);
        String x = n.getText().toString();

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)


                .putExtra(AlarmClock.EXTRA_DAYS, alarmDays)



                .putExtra(AlarmClock.EXTRA_MESSAGE, x)



                .putExtra(AlarmClock.EXTRA_HOUR, 10)



                .putExtra(AlarmClock.EXTRA_MINUTES, 55)



                //.putExtra(AlarmClock.EXTRA_IS_PM , false)



                .putExtra(AlarmClock.EXTRA_VIBRATE, false);

        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);


        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }

    }
}