package com.example.m2;

import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializer {
    public static List<MenuItem> toMenuItems(JSONObject object) {
        List<MenuItem> menu = new ArrayList<>();

        try {
            JSONArray jsonMenuItems = object.getJSONArray("items");
            for (int i = 0; i < jsonMenuItems.length(); i++) {
                JSONObject item = jsonMenuItems.getJSONObject(i);

                menu.add(new MenuItem(
                        item.getInt("id"),
                        item.getString("name"),
                        item.getString("description"),
                        item.getString("price"),
                        item.getInt("has_gluten") == 1,
                        item.getInt("calories"),
                        BitmapFactory.decodeStream((InputStream) new URL(item.getString("image")).getContent())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }
}
