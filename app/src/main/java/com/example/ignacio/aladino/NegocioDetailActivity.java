package com.example.ignacio.aladino;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import javax.annotation.Nullable;

public class NegocioDetailActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {

    private static final String TAG = "NegocioDetail";
    public static final String KEY_NEGOCIO_ID = "key_negocio_id";

    //@BindView(R.id.negocio_view)
    private ImageView negocio_image;
    private ImageButton whatsapp_button;

    private FirebaseFirestore mFirestore;
    public DocumentReference negocioRef;
    private ListenerRegistration mRestaurantRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocio_detail);
       // ButterKnife.bind(this);

        negocio_image = findViewById(R.id.negocio_view);
        whatsapp_button = findViewById(R.id.imageButton1);

        whatsapp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                "https://wa.me/+5492494591511"
                        )));
            }
        });

        String negocioId = getIntent().getExtras().getString(KEY_NEGOCIO_ID);

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the restaurant
        negocioRef = mFirestore.collection("negocios").document(negocioId);

    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "restaurant:onEvent", e);
            return;
        }

        onNegocioLoaded(snapshot.toObject(Negocio.class));
    }

    public void onNegocioLoaded(Negocio negocio){
        Glide.with(negocio_image.getContext())
                .load(negocio.getFoto())
                .into(negocio_image);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRestaurantRegistration = negocioRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mRestaurantRegistration != null) {
            mRestaurantRegistration.remove();
            mRestaurantRegistration = null;
        }
    }

    /*@OnClick(R.id.negocio_back)
    public void onBackArrowClicked(View view) {
        onBackPressed();
    }*/
}
