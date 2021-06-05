package com.example.m2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.m1.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MenuActivity extends AppCompatActivity {
    private static final String apiUrl = "http://10.0.2.2:3333/menu";
    private ListView menuView;
    private RequestQueue requestQueue;
    private MenuRepository menuRepository;

    private void getJsonMenu() {
        requestQueue.add(new JsonObjectRequest(
                                 Request.Method.GET,
                                 apiUrl,
                                 null,
                                 (JSONObject response) -> new Thread(() -> {
                                     List<MenuItem> menu = JsonSerializer.toMenuItems(response);

                                     runOnUiThread(() -> menuView.setAdapter(new MenuAdapter(getApplicationContext(), R.layout.menu_item, menu)));

                                     menuRepository.save(menu);
                                 }).start(),
                                 (VolleyError error) -> new Thread(() -> {
                                     List<MenuItem> menu = menuRepository.getMenu();

                                     runOnUiThread(() -> menuView.setAdapter(
                                             new MenuAdapter(getApplicationContext(), R.layout.menu_item, menu)));
                                 }).start()
                         ) {
                             @Override
                             public Map<String, String> getHeaders() {
                                 HashMap headers = new HashMap();

                                 headers.put("Accept", "application/json");
                                 headers.put("Content-Type", "application/json");

                                 return headers;
                             }
                         }
        );
    }


    private void getXmlMenu() {
        requestQueue.add(new StringRequest(
                Request.Method.GET,
                apiUrl,
                (String response) -> new Thread(() -> {
                    List<MenuItem> menu = XmlSerializer.toMenuItems(response);

                    runOnUiThread(() -> menuView.setAdapter(new MenuAdapter(getApplicationContext(), R.layout.menu_item, menu)));

                    menuRepository.save(menu);
                }).start(),
                (VolleyError error) -> new Thread(() -> {
                    List<MenuItem> menu = menuRepository.getMenu();

                    runOnUiThread(() -> menuView.setAdapter(
                            new MenuAdapter(getApplicationContext(), R.layout.menu_item, menu)));
                }).start()
        ) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap headers = new HashMap();

                headers.put("Accept", "application/xml");
                headers.put("Content-Type", "application/xml");

                return headers;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);

        menuRepository = new MenuRepository(this);

        requestQueue = Volley.newRequestQueue(this);

        menuView = findViewById(R.id.menu);

        getJsonMenu();
        //getXmlMenu();
    }
}