package com.example.ignacio.aladino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NegocioAdapter.OnNegocioSelectedListener {

    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference negociosRef = db.collection("negocios");
    private NegocioAdapter negocioAdapter;
    private UserLocation userLocation;
    public static String dayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLocation = new UserLocation(this);
        setUpRecyclerView();


        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        String[] days = new String[] { "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" };

        dayName = days[calendar.get(Calendar.DAY_OF_WEEK)-1];

        //Toast.makeText(this,dayName,Toast.LENGTH_SHORT).show();
    }

    private void setUpRecyclerView(){
        Query query = negociosRef;
        FirestoreRecyclerOptions<Negocio> options = new FirestoreRecyclerOptions.Builder<Negocio>()
                .setQuery(query, new SnapshotParser<Negocio>() {
                    @NonNull
                    @Override
                    public Negocio parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        Negocio negocio = snapshot.toObject(Negocio.class);
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
