package com.example.m2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository extends SQLiteOpenHelper {
    private static final String table = "menu_items";
    private static final int version = 1;
    private Context context;

    public MenuRepository(@Nullable Context context) {
        super(context, table, null, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + table + "(\n" +
                "  id INT NOT NULL PRIMARY KEY,\n" +
                "  name VARCHAR(255) NOT NULL,\n" +
                "  description VARCHAR(255) NOT NULL,\n" +
                "  price VARCHAR(255) NOT NULL,\n" +
                "  has_gluten TINYINT(1) NOT NULL,\n" +
                "  calories INT NOT NULL,\n" +
                "  image BLOB NOT NULL\n" +
                ");\n";

        try {
            db.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(List<MenuItem> menu) {
        System.out.println("Saving menu to database");

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            for (MenuItem item : menu) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                item.image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                byte[] imageInBytes = byteArrayOutputStream.toByteArray();

                ContentValues values = new ContentValues();

                values.put("id", item.id);
                values.put("name", item.name);
                values.put("description", item.description);
                values.put("price", "a consultar");
                values.put("has_gluten", item.hasGluten);
                values.put("calories", item.calories);
                values.put("image", imageInBytes);

                db.replace(table, null, values);
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public List<MenuItem> getMenu() {
        System.out.println("Getting menu from database");

        List<MenuItem> menu = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + table, null);

        while (cursor.moveToNext()) {
            byte[] imageInBytes = cursor.getBlob(6);

            MenuItem item = new MenuItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1,
                    cursor.getInt(5),
                    BitmapFactory.decodeByteArray(imageInBytes, 0, imageInBytes.length)
            );

            menu.add(item);
        }

        return menu;
    }
}
