package com.example.app2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {

    private PhoneRepository repository;
    private LiveData<List<Phone>> allPhones;

    public PhoneViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneRepository(application);
        allPhones = repository.getAllPhones();
    }

    public void insert(Phone phone){
        repository.insert(phone);
    }

    public void update(Phone phone){
        repository.update(phone);
    }

    public void delete(Phone phone){
        repository.delete(phone);
    }

    public void deleteAllPhones(){
        repository.deleteAllPhones();
    }

    public LiveData<List<Phone>> getAllPhones(){
        return allPhones;
    }
}
