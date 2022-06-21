package com.example.app2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone_table")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "producent")
    private String producent;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;
    //@NonNull
    @ColumnInfo(name = "wersja")
    private String wersja;

    //@NonNull
    @ColumnInfo(name = "www")
    private String www;

    public Phone(@NonNull String producent, @NonNull String model, String wersja, String www) {
        this.producent = producent;
        this.model = model;
        this.wersja = wersja;
        this.www = www;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getProducent() {
        return producent;
    }

    public void setProducent(@NonNull String producent) {
        this.producent = producent;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getWersja() {
        return wersja;
    }

    public void setWersja(@NonNull String wersja) {
        this.wersja = wersja;
    }

    @NonNull
    public String getWww() {
        return www;
    }

    public void setWww(@NonNull String www) {
        this.www = www;
    }
}
