package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    ListView simpleList;
    String[] przedmioty;
    Button submit;
    int liczbaOcen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        //String liczbaOcenStr = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        liczbaOcen = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        // get the string array from string.xml file
        przedmioty = getResources().getStringArray(R.array.przedmioty);
        // get the reference of ListView and Button
        simpleList = (ListView) findViewById(R.id.simpleListView);
        submit = (Button) findViewById(R.id.buttonObliczSrednia);
        // set the adapter to fill the data in the ListView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), przedmioty, liczbaOcen);
        simpleList.setAdapter(customAdapter);

        // perform setOnClickListener aent on Button
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent resultIntent = new Intent();
                String message = "";
                float suma = 0;
                // get the value of selected answers from custom adapter
                for (int i=0; i<customAdapter.selectedAnswers.size(); i++){
                    suma += CustomAdapter.selectedAnswers.get(i);
                    message = "średnia ocen: " + suma/liczbaOcen;
                }
                // display the message on screen with the help of Toast
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                resultIntent.putExtra("srednia", suma/liczbaOcen);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }


}

