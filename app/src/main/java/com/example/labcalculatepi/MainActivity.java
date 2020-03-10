package com.example.labcalculatepi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity implements CalculatePi.MyListener, View.OnClickListener {

    Timer timer;
    CalculatePi pi = new CalculatePi();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pi.setKuuntelija(this);
        Button button = (Button) findViewById(R.id.button);




    }
    public void laske(View view){
        sekunti(10);
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

    @Override
    public void onClick(View v) {

    }

    class ThreadTimerTask extends TimerTask {

        @Override
        public void run() {
            Log.d("*********** LOGIA *************", "thread killed");
            pi.kill();

        }
    }



    }