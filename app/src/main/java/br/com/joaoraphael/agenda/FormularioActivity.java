package br.com.joaoraphael.agenda;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
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

                if (helper.nomeIsNull()) Toast.makeText(this, "Preencha o nome do aluno.", Toast.LENGTH_SHORT).show();
                else {
                    AlunoDAO dao = new AlunoDAO(this);
                    dao.insereAluno(aluno);
                    dao.close();

                    Toast.makeText(FormularioActivity.this, "Aluno salvo com sucesso!" , Toast.LENGTH_SHORT).show();

                    finish();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
