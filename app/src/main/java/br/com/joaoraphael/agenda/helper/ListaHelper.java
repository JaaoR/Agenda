package br.com.joaoraphael.agenda.helper;

public class ListaHelper {

    public String corrigeSite(String site){
        if (!(site.startsWith("http://") || site.startsWith("https://"))){
            site = "http://" + site;
        }
        return site;
    }

    public boolean validaSite(String site){
        if (site.contains(".")){
            return true;
        }
        return false;
    }
}
