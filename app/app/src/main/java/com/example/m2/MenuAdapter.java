package com.example.m2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.m1.R;

import java.io.Serializable;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private int resource;
    private Context context;

    public MenuAdapter(@NonNull Context context, int resource, List<MenuItem> franchises) {
        super(context, resource, franchises);

        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        MenuItem item = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.image);

        imageView.setImageBitmap(item.image);

        TextView nameTextView = convertView.findViewById(R.id.name);

        nameTextView.setText(item.name);

        TextView descriptionTextView = convertView.findViewById(R.id.description);

        descriptionTextView.setText(item.description);

        TextView priceTextView = convertView.findViewById(R.id.price);

        priceTextView.setText("R$ " + item.price);

        TextView hasGlutenTextView = convertView.findViewById(R.id.has_gluten);

        hasGlutenTextView.setText(item.hasGluten ? "Contém glúten" : "Não contém glúten");

        TextView caloriesTextView = convertView.findViewById(R.id.calories);

        caloriesTextView.setText(item.calories + " Calorias");

        return convertView;
    }
}
