package com.example.suelen_somativa2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.model.Participant;
import com.example.suelen_somativa2.model.ParticipantDatabase;

public class RegisterParticipantActivity extends AppCompatActivity {

    private EditText nomeInput, telefoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);

        nomeInput = findViewById(R.id.nomeInput);
        telefoneInput = findViewById(R.id.telefoneInput);
        Button enviarBtn = findViewById(R.id.enviarCadastroButton);
        Button backButton = findViewById(R.id.voltarListButton);

        backButton.setOnClickListener(v -> finish());

        enviarBtn.setOnClickListener(v -> {
            String nome = nomeInput.getText().toString().trim();
            String telefone = telefoneInput.getText().toString().trim();

            if (nome.isEmpty() || telefone.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Participant novo = new Participant(nome, telefone);
            ParticipantDatabase db = new ParticipantDatabase(this);
            db.createParticipantInDB(novo);

            Intent result = new Intent();
            result.putExtra("nome", nome);
            result.putExtra("telefone", telefone);
            setResult(RESULT_OK, result);

            Toast.makeText(this, "Inscrição enviada!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}