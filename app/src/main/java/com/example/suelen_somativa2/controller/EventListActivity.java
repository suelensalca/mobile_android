package com.example.suelen_somativa2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.adapter.ParticipantAdapter;
import com.example.suelen_somativa2.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {
    private int vagasRestantes;
    private TextView vagasTextView;
    private List<String> inscritos = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);

        TextView tituloView = findViewById(R.id.tituloTextView);
        TextView dataHoraView = findViewById(R.id.dataHoraTextView);
        vagasTextView = findViewById(R.id.vagasTextView);
        Button inscreverBtn = findViewById(R.id.enviarButton);
        ListView inscritosList = findViewById(R.id.listaInscritos);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String dataHora = intent.getStringExtra("dataHora");
        vagasRestantes = intent.getIntExtra("vagas", 0);

        tituloView.setText(titulo);
        dataHoraView.setText(dataHora);
        vagasTextView.setText("Vagas: " + vagasRestantes);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inscritos);
        inscritosList.setAdapter(adapter);

        List<Participant> inscritos = new ArrayList<>();
        inscritos.add(new Participant("Ednéia Silva", "(11) 99999-5678"));
        inscritos.add(new Participant("Gerson Silva", "(11) 99999-5678"));
        inscritos.add(new Participant("Ermenegildo Oliveira", "(21) 99999-5432"));

        ParticipantAdapter adapter = new ParticipantAdapter(this, inscritos);
        inscritosList.setAdapter(adapter);

        inscreverBtn.setOnClickListener(v -> {
            Intent nextIntent = new Intent(EventListActivity.this, RegisterParticipantActivity.class);
            startActivity(nextIntent);
        });
    }

}
