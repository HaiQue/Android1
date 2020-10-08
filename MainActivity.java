package com.example.kontrolinis1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    Handler handler;

    private boolean shouldGenerateRandomNums = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResult);
        handler = new Handler(getApplicationContext().getMainLooper());
    }

    private class MyRunnableImoplementation implements Runnable {

        @Override
        public void run() {

            while (shouldGenerateRandomNums) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtResult.setText(String.valueOf(generateNumbers()));

                        //int a; a -> string: a.ToString();
                        //int a; a -> string: String.valueOf(a);

                    }
                });


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Paspaudus trečią mygtuką, kas 0,5 sekundės tekstiniame lauke rodomas vis naujas sveikasis atsitiktinis skaičius nuo 0 iki 100. Skaičių pasiskirstymo funkcija nesvarbu. Gali būti bet koks skirstinys.
    // Paspaudus ketvirtą mygtuką atsitiktinių skaičių generavimas sustoja.

    public void onGenerateNumbersBtn (View view) {

        shouldGenerateRandomNums = true;
        new Thread(new MyRunnableImoplementation()).start();

        //txtResult.setText("Test123: " + "abc");

    }

    public void stopRandomNumberGeneration(View view) {
        shouldGenerateRandomNums = false;
        Intent browserIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        browserIntent.putExtra(SearchManager.QUERY, txtResult.getText().toString());
        startActivity(browserIntent);
    }

    public int generateNumbers(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        return randomNumber;

    }

}