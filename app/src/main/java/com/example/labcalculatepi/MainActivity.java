package com.example.labcalculatepi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public static final int N = 1000; // # of terms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalculatePi();


    }

    public void CalculatePi(){

            long startTime = System.nanoTime();
            BigDecimal sum = new BigDecimal(0);      // final sum
            BigDecimal term = new BigDecimal(0);           // term without sign
            BigDecimal sign = new BigDecimal(1.0);     // sign on each term

            BigDecimal one = new BigDecimal(1.0);
            BigDecimal two = new BigDecimal(2.0);

            for (int k = 0; k < N; k++) {
                BigDecimal count = new BigDecimal(k);
                //term = 1.0/(2.0*k + 1.0);
                BigDecimal temp1 = two.multiply(count);
                BigDecimal temp2 = temp1.add(one);
                term = one.divide(temp2,1000,BigDecimal.ROUND_FLOOR);

                //sum = sum + sign*term;
                BigDecimal temp3 = sign.multiply(term);
                sum = sum.add(temp3);

                sign = sign.negate();
            }
            BigDecimal pi = new BigDecimal(0);
            BigDecimal four = new BigDecimal(4);
            pi = sum.multiply(four);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        totalTime = totalTime / 1000000000;
        //System.out.println(totalTime);
            Log.d("Vastaus", String.valueOf(pi));
            Log.d("AIKA", String.valueOf(totalTime));
        // System.out.println("Calculated pi (approx., " + N + " terms and 50 Decimal Places): " + pi);
         //   System.out.println("Actual pi: " + Math.PI);





    }



}
