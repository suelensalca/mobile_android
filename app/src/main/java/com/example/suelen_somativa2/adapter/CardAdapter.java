package com.example.suelen_somativa2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.suelen_somativa2.controller.EventListActivity;
import com.example.suelen_somativa2.model.EventCard;
import com.example.suelen_somativa2.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<EventCard> cardList;

    public CardAdapter(List<EventCard> cardList) {
        this.cardList = cardList;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, vagasView, dateTimeView;
        Button inscreverButton;


        public CardViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.titleTextView);
            vagasView = itemView.findViewById(R.id.vagasTextView);
            dateTimeView = itemView.findViewById(R.id.dateTimeTextView);
            inscreverButton = itemView.findViewById(R.id.inscreverButton);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        EventCard item = cardList.get(position);
        holder.titleView.setText(item.getTitulo());
        holder.vagasView.setText("Vagas: " + item.getVagas());
        holder.dateTimeView.setText(item.getDataHora());
        holder.imageView.setImageResource(item.getImagemResId());
        holder.inscreverButton.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, EventListActivity.class);
            intent.putExtra("titulo", item.getTitulo());
            intent.putExtra("dataHora", item.getDataHora());
            intent.putExtra("vagas", item.getVagas());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
