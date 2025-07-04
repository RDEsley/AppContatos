package com.example.contatosta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
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
                mostraDialogoExclusao(contato);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContatos.setLayoutManager(layoutManager);
        recyclerViewContatos.setHasFixedSize(true);
        recyclerViewContatos.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerViewContatos.setAdapter(contatoAdapter);

    }

    private void mostraDialogoExclusao(Contato contato){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ListaContatoActivity.this);
        dialog.setTitle("Confirma a exclusão");
        dialog.setMessage("Deseja excluir o contato:" + contato.getNomeContato()+ "?");
        dialog.setPositiveButton("Sim", (dialogInteface,i) -> {
            ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
            if(contatoDAO.excluir(contato)){
                carregarListaContato();
                Toast.makeText(ListaContatoActivity.this, "Sucesso ao excluir o registro",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ListaContatoActivity.this, "Erro ao excluir o registro",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Não", null);
        dialog.create();
        dialog.show();
    }
}

