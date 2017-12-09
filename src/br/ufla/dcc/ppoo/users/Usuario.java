package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.apps.Licenca;
import br.ufla.dcc.ppoo.miscellaneous.Data;
import java.util.List;

public class Usuario extends Cadastro {
    private String email;
    private String tipo;
    private double credito;
    private Data dataNasce;
    private List<Licenca> apps;
    
    
    
    
    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCredito() {
        return credito;
    }

    public Data getDataNasce() {
        return dataNasce;
    }

    public List<Licenca> getApps() {
        return apps;
    }
    
   
}
