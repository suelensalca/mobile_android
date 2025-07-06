package com.example.suelen_somativa2.controller;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suelen_somativa2.R;
import com.example.suelen_somativa2.adapter.CardAdapter;
import com.example.suelen_somativa2.model.EventCard;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<EventCard> lista = new ArrayList<>();
        lista.add(new EventCard("Aulo de hidroginástica", "20/07/2025 14:00", 22, R.drawable.hidro));
        lista.add(new EventCard("Aula de dança", "25/07/2025 20:00", 13, R.drawable.dance));
        lista.add(new EventCard("Noite de jogos", "26/07/2025 18:00", 9, R.drawable.boardgame));

        CardAdapter adapter = new CardAdapter(lista);
        recyclerView.setAdapter(adapter);
    }
}
