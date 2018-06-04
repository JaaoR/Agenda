package br.com.joaoraphael.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoraphael.agenda.dao.AlunoDAO;
import br.com.joaoraphael.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Button botao = findViewById(R.id.lista_adicionar);
        botao.setOnClickListener(v -> {
            Intent goToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
            startActivity(goToForm);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaTodos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        ListView listaAlunos = findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter);
    }

}
