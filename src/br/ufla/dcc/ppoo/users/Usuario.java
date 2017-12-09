package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.apps.Licenca;
import br.ufla.dcc.ppoo.miscellaneous.Data;
import java.util.List;

public class Usuario extends Cadastro {
    private String email;
    private String tipo;
    private float credito;
    private Data dataNasce;
    private List<Licenca> apps;
}
