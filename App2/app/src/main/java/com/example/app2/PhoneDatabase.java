package com.example.app2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Phone.class}, version = 1)
public abstract class PhoneDatabase extends RoomDatabase {
    private static PhoneDatabase instance;

    public abstract PhoneDao phoneDao();

    public static synchronized PhoneDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PhoneDatabase.class, "phone_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@androidx.annotation.NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PhoneDao phoneDao;

        private PopulateDbAsyncTask(PhoneDatabase db){
            phoneDao = db.phoneDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            phoneDao.insert(new Phone("Samsung", "S22", "", ""));
            phoneDao.insert(new Phone("Xiaomi", "Redmi8", "", ""));
            phoneDao.insert(new Phone("Samsung", "Galaxy", "", ""));
            return null;
        }
    }
}
