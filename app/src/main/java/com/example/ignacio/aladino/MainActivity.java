package com.example.ignacio.aladino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference negociosRef = db.collection("negocios");
    private NegocioAdapter negocioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = negociosRef;
        FirestoreRecyclerOptions<Negocio> options = new FirestoreRecyclerOptions.Builder<Negocio>()
                .setQuery(query, Negocio.class)
                .build();
        negocioAdapter = new NegocioAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(negocioAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        negocioAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        negocioAdapter.stopListening();
    }
}
