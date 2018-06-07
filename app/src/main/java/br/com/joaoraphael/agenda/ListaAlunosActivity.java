package br.com.joaoraphael.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.joaoraphael.agenda.dao.AlunoDAO;
import br.com.joaoraphael.agenda.helper.AgendaHelper;
import br.com.joaoraphael.agenda.helper.ListaHelper;
import br.com.joaoraphael.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private ListaHelper listaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaHelper = new ListaHelper();

        listaAlunos = findViewById(R.id.lista_alunos);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(posicao);

                Intent goToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                goToForm.putExtra("alunoEditar", aluno);
                startActivity(goToForm);
            }});

        Button botao = findViewById(R.id.lista_adicionar);
        botao.setOnClickListener(v -> {
            Intent goToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
            startActivity(goToForm);
        });

        registerForContextMenu(listaAlunos);
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

        listaAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_deletar, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);
        AlunoDAO dao = new AlunoDAO(this);

        switch (item.getItemId()){
            case R.id.menu_lista_deletar:
                dao.deleta(aluno);

                Toast.makeText(ListaAlunosActivity.this, "Aluno " + aluno.getNome() + " deletado!", Toast.LENGTH_SHORT)
                        .show();
                carregaLista();
                break;

            case R.id.menu_lista_site:
                Intent goToBrowser = new Intent(Intent.ACTION_VIEW);
                String site = aluno.getSite();
                site = listaHelper.corrigeSite(site);

                if (listaHelper.validaSite(site)){
                    goToBrowser.setData(Uri.parse((site)));
                    startActivity(goToBrowser);
                } else {
                    Toast.makeText(this, "Site inválido.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.menu_lista_sms:
                Intent goToSms = new Intent(Intent.ACTION_VIEW);
                String telefone = aluno.getTelefone();

                if (listaHelper.validaTelefone(telefone)){
                    goToSms.setData(Uri.parse("sms:" + telefone));
                    startActivity(goToSms);
                } else {
                    Toast.makeText(this, "Número de telefone inválido.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.menu_lista_endereco:
                Intent goToMap = new Intent(Intent.ACTION_VIEW);
                String endereco = aluno.getEndereco();

                if (listaHelper.validaEndereco(endereco)){
                    goToMap.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
                    startActivity(goToMap);
                } else {
                    Toast.makeText(this, "Endereço inválido.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.menu_lista_chamada:
                Intent callToNumber = new Intent(Intent.ACTION_CALL);
                String telefoneLigar = aluno.getTelefone();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                else {
                    if (listaHelper.validaTelefone(telefoneLigar)){
                        callToNumber.setData(Uri.parse("tel:" + telefoneLigar));
                        startActivity(callToNumber);
                    } else {
                        Toast.makeText(this, "Número de telefone inválido.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

        }

        dao.close();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
                break;

        }
    }
}