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

    public boolean validaTelefone(String telefone){
        try {
            long tel = Long.parseLong(telefone);
            if (telefone.length() >= 8){
                return true;
            }
        } catch (Exception e){ }

        return false;
    }

    public boolean validaEndereco(String endereco){
        if (!(endereco.isEmpty() || endereco.equals(" "))){
            if (endereco.length() > 2){
                return true;
            }
        }

        return false;
    }

}
