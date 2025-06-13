package com.example.contatosta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterContato;
import model.Contato;
import persistencia.ContatoDAO;

public class ListaContatoActivity extends AppCompatActivity {
    RecyclerView recyclerViewContatos;
    Button btnAdicionarContato;
    private AdapterContato contatoAdapter;
    private List<Contato> listaContato = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_contato);

        recyclerViewContatos = (RecyclerView) findViewById(R.id.rvListaContatos);
        btnAdicionarContato = (Button) findViewById(R.id.cmdAdicionarContato);
        btnAdicionarContato.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaContatoActivity.this, AdicionarContatoActivity.class);
                startActivity(intent);
            }
        });

    }
    public void carregarListaContato(){
        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
        listaContato = contatoDAO.listar();
        //Configurar o Adapter com os dados que virão do banco de dados SQLite
        contatoAdapter = new AdapterContato(listaContato);
        //Configurar o listener para chamar os metodos de clique do RecyclerView
        contatoAdapter.setOnItemClickListener(new AdapterContato.OnItemClickListener() {
            @Override
            public void onItemClick(Contato contato, int position) {
                Intent intent = new Intent(ListaContatoActivity.this, AdicionarContatoActivity.class);
                intent.putExtra("contatoSelecionado", contato);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Contato contato, int position) {

            }
        });


    }
}