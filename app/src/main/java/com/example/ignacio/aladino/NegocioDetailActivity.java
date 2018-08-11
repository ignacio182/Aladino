package com.example.ignacio.aladino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NegocioDetailActivity extends AppCompatActivity {

    private static final String TAG = "NegocioDetail";
    public static final String KEY_NEGOCIO_ID = "key_negocio_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocio_detail);

        String negocioId = getIntent().getExtras().getString(KEY_NEGOCIO_ID);
        TextView tv = findViewById(R.id.textView2);
        tv.setText(negocioId);


    }
}
