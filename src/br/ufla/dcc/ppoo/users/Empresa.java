package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.miscellaneous.Endereco;
import java.util.List;

public class Empresa extends Cadastro {
    private int cnpj;
    private Endereco endereco;
    private String site;
    private List<String> emails;
    private List<String> telefones;
}
