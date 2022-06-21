package com.example.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Rysownik rysownik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rysownik = (Rysownik) findViewById(R.id.rysownik);
    }

    public void onClick(View v){
        rysownik.Czysc();
    }

    public void setRed(View v){
        rysownik.setRed();
    }

    public void setGreen(View v){
        rysownik.setGreen();
    }

    public void setBlue(View v){
        rysownik.setBlue();
    }
}