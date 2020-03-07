package com.example.labcalculatepi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity implements CalculatePi.MyListener {

    Timer timer;
    CalculatePi pi = new CalculatePi();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pi.setKuuntelija(this);

        sekunti(10);
        //laske();
    }

    public void sekunti(int seconds){
        timer = new Timer();
        timer.schedule(new ThreadTimerTask(), seconds * 1000);
        Thread thread = new Thread(pi);
        thread.start();

    }

    @Override
    public void callback(final BigDecimal result) {
        final TextView label = findViewById(R.id.label);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                label.setText(result.toString());

            }
        });
    }

    class ThreadTimerTask extends TimerTask {

        @Override
        public void run() {
            Log.d("*********** LOGIA *************", "thread killed");
            pi.kill();

        }
    }



    }