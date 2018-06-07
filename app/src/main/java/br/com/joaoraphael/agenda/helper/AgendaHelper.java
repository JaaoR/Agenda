package br.com.joaoraphael.agenda.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AgendaHelper {

    private ListaHelper listaHelper;
    private FormularioHelper formularioHelper;

    public AgendaHelper(){
        this.listaHelper = new ListaHelper();
        this.formularioHelper = new FormularioHelper();
    }

    public AgendaHelper(ListaHelper helper){
        this.listaHelper = helper;
        this.formularioHelper = new FormularioHelper();
    }

    public AgendaHelper(FormularioHelper helper){
        this.listaHelper = new ListaHelper();
        this.formularioHelper = helper;
    }

    public AgendaHelper(ListaHelper listaHelper, FormularioHelper formularioHelper){
        this.listaHelper = listaHelper;
        this.formularioHelper = formularioHelper;
    }

    public void realizaChamada(AppCompatActivity activity, String telefone){
        Intent intent = new Intent(Intent.ACTION_CALL);
        if (listaHelper.validaTelefone(telefone)){
            intent.setData(Uri.parse(telefone));
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "Número de telefone inválido.", Toast.LENGTH_SHORT).show();
        }
    }




}
