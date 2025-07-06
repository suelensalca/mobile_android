package com.example.suelen_somativa2.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.adapter.ParticipantAdapter;
import com.example.suelen_somativa2.model.Participant;
import com.example.suelen_somativa2.model.ParticipantDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {
    private int vagasRestantes;
    private TextView vagasTextView;
    private ParticipantDatabase db;
    private List<Participant> inscritos;
    private ParticipantAdapter adapter;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    inscritos.clear();
                    inscritos.addAll(db.getParticipantFromDB());
                    adapter.notifyDataSetChanged();
                    vagasRestantes--;
                    atualizarVagas();
                }
            });

    private void addInitialParticipants() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean hasValues = prefs.getBoolean("dados_iniciais_inseridos", false);

        if (!hasValues) {
            db.createParticipantInDB(new Participant("Ednéia Silva", "(11) 99999-5678"));
            db.createParticipantInDB(new Participant("Gerson Silva", "(11) 99999-5678"));
            db.createParticipantInDB(new Participant("Ermenegildo Oliveira", "(21) 99999-5432"));

            prefs.edit().putBoolean("dados_iniciais_inseridos", true).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);

        db = new ParticipantDatabase(this);
        addInitialParticipants();
        inscritos = db.getParticipantFromDB();

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
        atualizarVagas();

        adapter = new ParticipantAdapter(this, inscritos);
        inscritosList.setAdapter(adapter);

        inscreverBtn.setOnClickListener(v -> {
            if (vagasRestantes > 0) {
                launcher.launch(new Intent(this, RegisterParticipantActivity.class));
            } else {
                Toast.makeText(this, "Não há vagas disponíveis", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void atualizarVagas() {
        vagasTextView.setText("Vagas: " + vagasRestantes);
    }

}
