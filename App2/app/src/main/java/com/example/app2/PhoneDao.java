package com.example.app2;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {

    @Insert
    void insert(Phone phone);

    @Update
    void update(Phone phone);

    @Delete
    void delete(Phone phone);

    @Query("DELETE FROM phone_table")
    void deleteAllPhones();

    @Query("SELECT * FROM phone_table ORDER BY producent, model")
    LiveData<List<Phone>> getAllPhones();

}
