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

import java.io.IOException;


public class NegocioAdapter extends FirestoreRecyclerAdapter<Negocio, NegocioAdapter.NegocioHolder> {

    public NegocioAdapter(@NonNull FirestoreRecyclerOptions<Negocio> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NegocioHolder holder, int position, @NonNull Negocio model) {

        Glide.with(holder.imageViewLogo.getContext())
                .load(model.getImage())
                .into(holder.imageViewLogo);
        System.out.println(model.getName());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.textViewName.setText("putoooooo");
    }

    @NonNull
    @Override
    public NegocioHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_negocio, parent, false);
        return new NegocioHolder(v);
    }

    class NegocioHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewLogo;
        //TextView prioridad;

        public NegocioHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            imageViewLogo = itemView.findViewById(R.id.imageView);
        }
    }
}
