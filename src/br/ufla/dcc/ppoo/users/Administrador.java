package br.ufla.dcc.ppoo.users;

/**
 * Usuário com previlégio no sistema (Root)
 * @author william
 */
public class Administrador extends Cadastro {
    /**
     * Construtor idem superclasse
     * @param login Login
     * @param senha Senha
     * @param nome  Nome
     */
    public Administrador(String login, String senha, String nome) {
        super(login, senha, nome);
    }
    
}
