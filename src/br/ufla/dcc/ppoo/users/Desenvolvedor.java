package br.ufla.dcc.ppoo.users;

/**
 * Uma empresa desenvolvedora
 * @author william
 */
public class Desenvolvedor extends Cadastro {
    private String site;

    /**
     * Nova empresa, demais atributos usar set
     * @param login Login
     * @param senha Senha
     * @param nome Nome fantasia
     */
    public Desenvolvedor(String login, String senha, String nome) {
        super(login, senha, nome);
        this.site = null;
    }

    /**
     * Setter
     * @param site Site da empresa
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Getter
     * @return Site da empresa
     */
    public String getSite() {
        return site;
    }
    
}
