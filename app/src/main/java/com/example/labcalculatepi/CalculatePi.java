package com.example.labcalculatepi;

import android.util.Log;

import java.math.BigDecimal;



public class CalculatePi implements Runnable {

    private volatile boolean killed = false;
    public static final int N = 1000; // # of terms
    private MyListener mMyListener = null;

    public interface MyListener{

        void callback(BigDecimal result);

    }

    public void setKuuntelija(MyListener uusiKuuntelija) {
        this.mMyListener = uusiKuuntelija;
    }




    @Override
    public void run() {
        while(!killed){
            laske();
        }
    }




    public void laske()
    {

        int countNumber = 100;

        for (int i = 0; i < 1000000; i++)  {
            if(killed){
                break;
            }
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


                term = one.divide(temp2, i, BigDecimal.ROUND_FLOOR);


                //sum = sum + sign*term;
                BigDecimal temp3 = sign.multiply(term);
                sum = sum.add(temp3);

                sign = sign.negate();

            }


            BigDecimal pi = new BigDecimal(0);
            BigDecimal four = new BigDecimal(4);
            pi = sum.multiply(four);
            if (mMyListener != null)
                mMyListener.callback(pi);
            }

           // Log.d("Vastaus", String.valueOf(pi));
        }


    public void kill() { killed = true; }
}
