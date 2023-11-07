package com.example.appraulito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
    private Button btnVerMapa;
    private Button btnIrACrud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnVerMapa = findViewById(R.id.btn_mapa);
        btnIrACrud = findViewById(R.id.btn_registrarpez);

        btnVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(menu.this, mapa.class);
                startActivity(intent);
            }
        });
        btnIrACrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(menu.this, crud.class);
                startActivity(intent);
            }
        });



    }
}