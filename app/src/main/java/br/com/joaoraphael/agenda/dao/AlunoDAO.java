package br.com.joaoraphael.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoraphael.agenda.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos(id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT NOT NULL, " +
                "telefone TEXT NOT NULL, " +
                "site TEXT NOT NULL, " +
                "nota REAL NOT NULL);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS alunos;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("endereco", aluno.getEndereco());
        valores.put("telefone", aluno.getTelefone());
        valores.put("site", aluno.getSite());
        valores.put("nota", aluno.getNome());

        db.insert("alunos", null, valores);

    }

    public List<Aluno> buscaTodos() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM alunos;";
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();

        while (c.moveToNext()){
            long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String endereco = c.getString(c.getColumnIndex("endereco"));
            String telefone = c.getString(c.getColumnIndex("telefone"));
            String site = c.getString(c.getColumnIndex("site"));
            double nota = c.getDouble(c.getColumnIndex("nota"));

            alunos.add(new Aluno(id, nome, endereco, telefone, site, nota));
        }

        c.close();
        return alunos;
    }
}