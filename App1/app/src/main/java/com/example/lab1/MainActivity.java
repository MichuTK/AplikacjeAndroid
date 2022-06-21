package com.example.lab1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    private String imie;
    private String nazwisko;
    private String ocenyStr;
    private Integer oceny;
    private Boolean imieOk = false;
    private Boolean nazwiskoOk = false;
    private Boolean ocenyOk = false;
    private float srednia = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Button button = findViewById(R.id.button);
        outState.putInt("buttonVisible", button.getVisibility());
        outState.putBoolean("imieOk", imieOk);
        outState.putBoolean("nazwiskoOk", nazwiskoOk);
        outState.putBoolean("ocenyOk", ocenyOk);
        outState.putString("imie", imie);
        outState.putString("nazwisko", nazwisko);
        outState.putString("oceny", ocenyStr);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Button button = findViewById(R.id.button);
        button.setVisibility(savedInstanceState.getInt("buttonVisible"));
        imieOk = savedInstanceState.getBoolean("imieOk");
        nazwiskoOk = savedInstanceState.getBoolean("nazwiskoOk");
        ocenyOk = savedInstanceState.getBoolean("ocenyOk");
        imie = savedInstanceState.getString("imie");
        nazwisko = savedInstanceState.getString("nazwisko");
        ocenyStr = savedInstanceState.getString("oceny");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputImie = findViewById(R.id.inputName);
        EditText inputNazwisko = findViewById(R.id.inputLastName);
        EditText inputOceny = findViewById(R.id.inputNumberOfMark);

        Toast toastImie = Toast.makeText(getApplicationContext(), "Podaj imie!", Toast.LENGTH_SHORT);
        Toast toastNazwisko = Toast.makeText(getApplicationContext(), "Podaj nazwisko!", Toast.LENGTH_SHORT);
        Toast toastOceny = Toast.makeText(getApplicationContext(), "Podaj liczbę ocen!", Toast.LENGTH_SHORT);

        inputImie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    imie = inputImie.getText().toString();
                    imieOk = true;
                    if (TextUtils.isEmpty(imie)) {
                        imieOk = false;
                        toastImie.show();
                        inputImie.setError("Imie nie może być puste");
                    }
                    showButton();
                }
            }
        });

        inputNazwisko.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    nazwisko = inputNazwisko.getText().toString();
                    nazwiskoOk = true;
                    if (TextUtils.isEmpty(nazwisko)) {
                        nazwiskoOk = false;
                        toastNazwisko.show();
                        inputNazwisko.setError("Nazwisko nie może być puste");
                    }
                    showButton();
                }
            }
        });

        inputOceny.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    ocenyStr = inputOceny.getText().toString();
                    ocenyOk = true;
                    if (TextUtils.isEmpty(ocenyStr)) {
                        ocenyOk = false;
                        toastOceny.show();
                        inputOceny.setError("Nie moze byc puste");
                    } else {
                        try {
                            oceny = Integer.parseInt(inputOceny.getText().toString());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                        if (oceny < 5 || oceny > 15) {
                            ocenyOk = false;
                            inputOceny.setError("Liczba poza zakresem 5-15");
                        }
                    }
                    showButton();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK){
                srednia = data.getFloatExtra("srednia", 0);
                TextView textSrednia = (TextView) findViewById(R.id.textSrednia);
                textSrednia.setText("Twoja średnia:" + String.valueOf(srednia));

                Button button = (Button) findViewById(R.id.button);
                if (srednia >= 3.0){
                    button.setText("Super!");
                } else {
                    button.setText("Tym razem mi nie poszło");
                }
            }
        }
    }

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    String message;
    public void buttonClick(View view) {
        if (srednia == 0) {
            Intent intent = new Intent(this, SecondActivity.class);
            EditText editText = (EditText) findViewById(R.id.inputNumberOfMark);
            String liczbaOcen = editText.getText().toString();
            //int liczbaOcen = editText.getText();
            intent.putExtra(EXTRA_MESSAGE, liczbaOcen);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        } else {
            if (srednia >= 3)
                message = "Gratuluję! Otrzymujesz zaliczenie";
            else
                message = "Wysyłam podanie o zaliczenie warunkowe";
            new AlertDialog.Builder(this)
                    .setTitle("Wyjście")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        }
    }

    public void showButton(){
        Button button = findViewById(R.id.button);
        if (imieOk && nazwiskoOk && ocenyOk){
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }

}