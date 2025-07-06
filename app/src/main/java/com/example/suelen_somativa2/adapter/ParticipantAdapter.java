package com.example.suelen_somativa2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.model.Participant;
import com.example.suelen_somativa2.model.ParticipantDatabase;

import java.util.List;

public class ParticipantAdapter extends ArrayAdapter<Participant> {
    private final ParticipantDatabase db;
    private final List<Participant> participantes;

    public ParticipantAdapter(Context context, List<Participant> participantes) {
        super(context, 0, participantes);
        this.participantes = participantes;
        this.db = new ParticipantDatabase(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_participant, parent, false);
        }

        Participant p = getItem(position);
        if (p != null) {
            TextView nomeView = convertView.findViewById(R.id.nomeTextView);
            TextView telefoneView = convertView.findViewById(R.id.telefoneTextView);
            Button deleteButton = convertView.findViewById(R.id.deleteButton);

            nomeView.setText(p.getNome());
            telefoneView.setText(p.getTelefone());

            deleteButton.setOnClickListener(v -> {
                db.removeParticipantInDB(p);
                participantes.remove(p);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Participante removido!", Toast.LENGTH_SHORT).show();
            });
        }

        return convertView;
    }
}
