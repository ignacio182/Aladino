package com.example.ignacio.aladino;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


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

        Glide.with(holder.logo.getContext())
                .load(model.getImage())
                .into(holder.logo);

        holder.textViewName.setText(model.getName());

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

        TextView textViewName;
        ImageView logo;

        public NegocioHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            logo = itemView.findViewById(R.id.imageView);
        }
    }
}
