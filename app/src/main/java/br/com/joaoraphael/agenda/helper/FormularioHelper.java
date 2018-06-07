package br.com.joaoraphael.agenda.helper;

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
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = activity.findViewById(R.id.formulario_nome);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoSite = activity.findViewById(R.id.formulario_site);
        campoNota = activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(getNomeValue());
        aluno.setEndereco(getEnderecoValue());
        aluno.setTelefone(getTelefoneValue());
        aluno.setSite(getSiteValue());
        aluno.setNota(getNotaValue());

        return aluno;
    }

    private String getNomeValue() {
        return campoNome.getText().toString();
    }

    private String getEnderecoValue() {
        return campoEndereco.getText().toString();
    }

    private String getTelefoneValue() {
        return campoTelefone.getText().toString();
    }

    private String getSiteValue() {
        return campoSite.getText().toString();
    }

    private Double getNotaValue(){
        return Double.valueOf(campoNota.getProgress());
    }

    public boolean nomeIsNull(){
        return getNomeValue().equals("") || getNomeValue().equals(" ");
    }

    public boolean enderecoIsNull(){
        return getEnderecoValue().equals("") || getEnderecoValue().equals(" ");
    }

    public boolean telefoneIsNull(){
        return getTelefoneValue().equals("") || getTelefoneValue().equals(" ");
    }

    public boolean siteIsNull(){
        return getSiteValue().equals("") || getSiteValue().equals(" ");
    }

    public boolean isNull(){
        return nomeIsNull() || enderecoIsNull() || telefoneIsNull() || siteIsNull();
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}


