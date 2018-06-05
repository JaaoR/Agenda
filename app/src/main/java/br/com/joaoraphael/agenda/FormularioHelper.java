package br.com.joaoraphael.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.joaoraphael.agenda.FormularioActivity;
import br.com.joaoraphael.agenda.R;
import br.com.joaoraphael.agenda.modelo.Aluno;

public class FormularioHelper {

    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private EditText campoSite;
    private RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){
        campoNome = activity.findViewById(R.id.formulario_nome);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoSite = activity.findViewById(R.id.formulario_site);
        campoNota = activity.findViewById(R.id.formulario_nota);

    }

    public Aluno getAluno() {
        return new Aluno(1, getNome(), getEndereco(), getTelefone(), getSite(), getNota());
    }

    private String getNome() {
        return campoNome.getText().toString();
    }

    private String getEndereco() {
        return campoEndereco.getText().toString();
    }

    private String getTelefone() {
        return campoTelefone.getText().toString();
    }

    private String getSite() {
        return campoSite.getText().toString();
    }

    private double getNota(){
        return Double.valueOf(campoNota.getProgress());
    }

    public boolean nomeIsNull(){
        return getNome().equals("") || getNome().equals(" ");
    }

    public boolean enderecoIsNull(){
        return getEndereco().equals("") || getEndereco().equals(" ");
    }

    public boolean telefoneIsNull(){
        return getTelefone().equals("") || getTelefone().equals(" ");
    }

    public boolean siteIsNull(){
        return getSite().equals("") || getSite().equals(" ");
    }

    public boolean isNull(){
        return nomeIsNull() || enderecoIsNull() || telefoneIsNull() || siteIsNull();
    }
}


