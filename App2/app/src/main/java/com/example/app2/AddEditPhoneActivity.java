package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditPhoneActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.app2.EXTRA_ID";
    public static final String EXTRA_PRODUCENT = "com.example.app2.EXTRA_PRODUCENT";
    public static final String EXTRA_MODEL = "com.example.app2.EXTRA_MODEL";
    public static final String EXTRA_WERSJA = "com.example.app2.EXTRA_WERSJA";
    public static final String EXTRA_URL = "com.example.app2.EXTRA_URL";


    private EditText editTextProducent;
    private EditText editTextModel;
    private EditText editTextWersja;
    private EditText editTextUrl;
    private Button buttonZapisz;
    private Button buttonAnuluj;
    private Button buttonWww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        editTextProducent = findViewById(R.id.editTextProducent);
        editTextModel = findViewById(R.id.editTextModel);
        editTextWersja = findViewById(R.id.editTextWersja);
        editTextUrl = findViewById(R.id.editTextUrl);

        buttonZapisz = findViewById(R.id.buttonDodaj);
        buttonAnuluj = findViewById(R.id.buttonAnuluj);
        buttonWww = findViewById(R.id.buttonWeb);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edytuj telefon");
            editTextProducent.setText(intent.getStringExtra(EXTRA_PRODUCENT));
            editTextModel.setText(intent.getStringExtra(EXTRA_MODEL));
            editTextWersja.setText(intent.getStringExtra(EXTRA_WERSJA));
            editTextUrl.setText(intent.getStringExtra(EXTRA_URL));
        } else {
            setTitle("Dodaj telefon");
            buttonWww.setEnabled(false);
        }

        buttonZapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePhone();
            }
        });

        buttonAnuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        buttonWww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextUrl.getText().toString();

                if (!url.startsWith("http://") && !url.startsWith("https://")){
                    url = "http://" + url;
                }

                Intent przegladarka = new Intent("android.intent.action.VIEW", Uri.parse(url));
                startActivity(przegladarka);
            }
        });

    }

    public void exit() {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void savePhone() {
        String producent = editTextProducent.getText().toString();
        String model = editTextModel.getText().toString();
        String wersja = editTextWersja.getText().toString();
        String url = editTextUrl.getText().toString();

        if (producent.trim().isEmpty()) {
            editTextProducent.setError("Pole nie może być puste");
        }
        if (model.trim().isEmpty()) {
            editTextModel.setError("Pole nie może być puste");
        }
        if (wersja.trim().isEmpty()) {
            editTextWersja.setError("Pole nie może być puste");
        }
        if (url.trim().isEmpty()) {
            editTextUrl.setError("Pole nie może być puste");
        }
        if (producent.trim().isEmpty() || model.trim().isEmpty() || wersja.trim().isEmpty() || url.trim().isEmpty()) {
            Toast.makeText(this, "Uzupełnij dane", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_PRODUCENT, producent);
        data.putExtra(EXTRA_MODEL, model);
        data.putExtra(EXTRA_WERSJA, wersja);
        data.putExtra(EXTRA_URL, url);

        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}