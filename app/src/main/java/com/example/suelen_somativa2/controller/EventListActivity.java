package com.example.suelen_somativa2.controller;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {
    private int vagasRestantes;
    private TextView vagasTextView;
    private List<Participant> inscritos = new ArrayList<>();
    private ParticipantAdapter adapter;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String nome = result.getData().getStringExtra("nome");
                    String telefone = result.getData().getStringExtra("telefone");

                    if (nome != null && telefone != null) {
                        inscritos.add(new Participant(nome, telefone));
                        adapter.notifyDataSetChanged();
                        vagasRestantes--;
                        atualizarVagas();
                    }
                }
            });

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
        atualizarVagas();

        inscritos.add(new Participant("Ednéia Silva", "(11) 99999-5678"));
        inscritos.add(new Participant("Gerson Silva", "(11) 99999-5678"));
        inscritos.add(new Participant("Ermenegildo Oliveira", "(21) 99999-5432"));

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
