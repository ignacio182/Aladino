package com.example.ignacio.aladino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity implements NegocioAdapter.OnNegocioSelectedListener {

    private RecyclerView recyclerView;
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
                .setQuery(query, new SnapshotParser<Negocio>() {
                    @NonNull
                    @Override
                    public Negocio parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        Negocio negocio = new Negocio();
                        negocio.setImage((String) snapshot.get("image"));
                        negocio.setName((String) snapshot.get("name"));
                        negocio.setId(snapshot.getId());
                        return negocio;
                    }
                })
                .build();
        negocioAdapter = new NegocioAdapter(options, this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
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

    @Override
    public void onNegocioSelected(Negocio negocio) {
        Intent intent = new Intent(this, NegocioDetailActivity.class);
        intent.putExtra(NegocioDetailActivity.KEY_NEGOCIO_ID, negocio.getId());

        startActivity(intent);
    }
}
