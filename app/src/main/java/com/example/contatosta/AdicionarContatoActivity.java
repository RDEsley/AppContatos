package com.example.contatosta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdicionarContatoActivity extends AppCompatActivity {

    Button btnSalvar;
    EditText edtNomeContato, edtTelefoneContato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_contato);
        edtNomeContato = (EditText) findViewById(R.id.txtNomeContato);
        edtTelefoneContato = (EditText) findViewById(R.id.txtTelefoneContato);
        btnSalvar = (Button) findViewById(R.id.cmdSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}