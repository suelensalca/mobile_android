package com.example.suelen_somativa2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.model.Participant;

import java.util.List;

public class ParticipantAdapter extends ArrayAdapter<Participant> {

    public ParticipantAdapter(Context context, List<Participant> participantes) {
        super(context, 0, participantes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_participant, parent, false);
        }

        Participant p = getItem(position);
        if (p != null) {
            TextView nomeView = convertView.findViewById(R.id.nomeTextView);
            TextView telefoneView = convertView.findViewById(R.id.telefoneTextView);

            nomeView.setText(p.getNome());
            telefoneView.setText(p.getTelefone());
        }

        return convertView;
    }
}
