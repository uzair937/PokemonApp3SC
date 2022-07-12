package com.example.pokemonapp3sc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pokemonapp3sc.R;

public class TextViewAdapter extends BaseAdapter { //sets text dynamically based on pokemon list
    private Context context;
    private final String[] textViewValues;

    public TextViewAdapter(Context context, String[] textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.text_item, null);

            // set value into textview
            TextView textView = gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(textViewValues[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
