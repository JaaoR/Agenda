package br.com.joaoraphael.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.joaoraphael.agenda.dao.AlunoDAO;
import br.com.joaoraphael.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("alunoEditar");
        if (aluno != null){
            helper.preencheFormulario(aluno);
            setTitle("Editar aluno");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_formulario_salvar:
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);

                if (helper.nomeIsNull()) Toast.makeText(this, "Preencha o nome do aluno.", Toast.LENGTH_SHORT).show();
                else {
                    // O aluno não tera um ID definido se for novo, a atribuição do ID acontece apenas ao inserir.
                    if (aluno.getId() == null){
                        dao.insere(aluno);
                    } else {
                        Aluno alunoIntent = (Aluno) intent.getSerializableExtra("alunoEditar");
                        dao.atualiza(alunoIntent.getId(), aluno);
                    }
                }

                Toast.makeText(FormularioActivity.this, "Aluno salvo com sucesso!" , Toast.LENGTH_SHORT).show();
                dao.close();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
