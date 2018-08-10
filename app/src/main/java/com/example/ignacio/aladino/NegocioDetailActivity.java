package com.example.ignacio.aladino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NegocioDetailActivity extends AppCompatActivity {

    private static final String TAG = "RestaurantDetail";
    public static final String KEY_NEGOCIO_ID = "key_restaurant_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocio_detail);
    }
}
