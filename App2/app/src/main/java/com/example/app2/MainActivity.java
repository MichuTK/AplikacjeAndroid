package com.example.app2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_PHONE_REQUEST = 1;
    public static final int EDIT_PHONE_REQUEST = 2;
    private PhoneViewModel phoneViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddPhone = findViewById(R.id.button_add_phone);
        buttonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);
                startActivityForResult(intent, ADD_PHONE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PhoneAdapter adapter = new PhoneAdapter();
        recyclerView.setAdapter(adapter);

        phoneViewModel = ViewModelProviders.of(this).get(PhoneViewModel.class);
        phoneViewModel.getAllPhones().observe(this, new Observer<List<Phone>>() {
            @Override
            public void onChanged(@Nullable List<Phone> phones) {
                adapter.setPhones(phones);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                phoneViewModel.delete(adapter.getPhoneAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Telefon usunięty", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Phone phone) {
                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);

                intent.putExtra(AddEditPhoneActivity.EXTRA_ID, phone.getId());
                intent.putExtra(AddEditPhoneActivity.EXTRA_PRODUCENT, phone.getProducent());
                intent.putExtra(AddEditPhoneActivity.EXTRA_MODEL, phone.getModel());
                intent.putExtra(AddEditPhoneActivity.EXTRA_WERSJA, phone.getWersja());
                intent.putExtra(AddEditPhoneActivity.EXTRA_URL, phone.getWww());

                startActivityForResult(intent, EDIT_PHONE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PHONE_REQUEST && resultCode == RESULT_OK){
            String producent = data.getStringExtra(AddEditPhoneActivity.EXTRA_PRODUCENT);
            String model = data.getStringExtra(AddEditPhoneActivity.EXTRA_MODEL);
            String wersja = data.getStringExtra(AddEditPhoneActivity.EXTRA_WERSJA);
            String url = data.getStringExtra(AddEditPhoneActivity.EXTRA_URL);

            Phone phone = new Phone(producent, model, wersja, url);
            phoneViewModel.insert(phone);

            Toast.makeText(this, "Telefon dodany", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PHONE_REQUEST && resultCode == RESULT_OK){
            long id = data.getLongExtra(AddEditPhoneActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Telefon nie może być dodany", Toast.LENGTH_SHORT).show();
                return;
            }

            String producent = data.getStringExtra(AddEditPhoneActivity.EXTRA_PRODUCENT);
            String model = data.getStringExtra(AddEditPhoneActivity.EXTRA_MODEL);
            String wersja = data.getStringExtra(AddEditPhoneActivity.EXTRA_WERSJA);
            String url = data.getStringExtra(AddEditPhoneActivity.EXTRA_URL);

            Phone phone = new Phone(producent, model, wersja, url);
            phone.setId(id);

            phoneViewModel.update(phone);

            Toast.makeText(this, "Telefon zaktualizowany", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Telefon nie został dodany", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_phones:
                phoneViewModel.deleteAllPhones();
                Toast.makeText(this, "Usunięto wszystko", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}