package com.example.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] questionsList;
    LayoutInflater inflter;
    int liczbaOcen;
    public static ArrayList<Integer> selectedAnswers;

    public CustomAdapter(Context context, String[] questionsList, int liczbaOcen) {
        this.context = context;
        this.questionsList = questionsList;
        this.liczbaOcen = liczbaOcen;
        // initialize arraylist and add static string for all the questions
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < liczbaOcen; i++) {
            selectedAnswers.add(2);
        }
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return liczbaOcen;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.wiersz_oceny, null);
        // get the reference of TextView and Button's
        TextView ocena = (TextView) view.findViewById(R.id.ocena);
        RadioGroup mGrupaOceny = (RadioGroup) view.findViewById(R.id.RadioGroup);
        RadioButton ocena2 = (RadioButton) view.findViewById(R.id.radioButton2);
        RadioButton ocena3 = (RadioButton) view.findViewById(R.id.radioButton3);
        RadioButton ocena4 = (RadioButton) view.findViewById(R.id.radioButton4);
        RadioButton ocena5 = (RadioButton) view.findViewById(R.id.radioButton5);
        mGrupaOceny.check(R.id.radioButton2);

        // perform setOnCheckedChangeListener event on button ocenaX
        ocena2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, 2);
            }
        });
        ocena3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, 3);
            }
        });
        ocena4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, 4);
            }
        });
        ocena5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, 5);
            }
        });
        // set the value in TextView
        ocena.setText(questionsList[i]);
        return view;
    }
}