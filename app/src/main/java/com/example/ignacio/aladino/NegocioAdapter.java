package com.example.ignacio.aladino;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import org.imperiumlabs.geofirestore.util.GeoUtils;
import java.text.ParseException;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NegocioAdapter extends FirestoreRecyclerAdapter<Negocio, NegocioAdapter.NegocioHolder> {

    public interface OnNegocioSelectedListener {

        void onNegocioSelected(Negocio negocio);

    }

    private OnNegocioSelectedListener negocioListener;

    public NegocioAdapter(@NonNull FirestoreRecyclerOptions<Negocio> options, OnNegocioSelectedListener listener) {
        super(options);
        this.negocioListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull NegocioHolder holder, int position, @NonNull final Negocio model) {

        Glide.with(holder.photo.getContext())
                .load(model.getFoto())
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20)))
                .into(holder.photo);


        //Double distance = GeoUtils.distance(UserLocation.getLatitud(),UserLocation.getLongitud(),model.getLocalizacion().getLatitude(),model.getLocalizacion().getLongitude());
        //distance /= 1000;
        Double distance = 2.50;
        holder.name.setText(model.getNombre());
        holder.numRecos.setText(String.valueOf(model.getNumrecos()));

        holder.open.setText(model.getOpenOrClose());
        if ((model.getOpenOrClose().equals("abierto"))) {
            holder.open.setTextColor(Color.parseColor("#a4c639"));
        } else {
            holder.open.setTextColor(Color.RED);
        }
        holder.distance.setText(String.format("%.2f",distance) + " km");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                negocioListener.onNegocioSelected(model);
            }
        });
    }

    @NonNull
    @Override
    public NegocioHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_negocio, parent, false);
        return new NegocioHolder(v);
    }

    class NegocioHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.open)
        TextView open;

        @BindView(R.id.distance)
        TextView distance;

        @BindView(R.id.numRecos)
        TextView numRecos;

        public NegocioHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
