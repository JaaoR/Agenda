package br.com.joaoraphael.agenda.modelo;

public class Aluno {

    private long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private double nota;

    public Aluno(long id, String nome, String endereco, String telefone, String site, double nota) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.site = site;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSite() {
        return site;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return   getId() + " - " + getNome();
    }
}
