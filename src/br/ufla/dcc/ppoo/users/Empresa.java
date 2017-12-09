package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.miscellaneous.Endereco;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Uma empresa desenvolvedora
 * @author william
 */
public class Empresa extends Cadastro {
    private final int cnpj;
    private final List<String> emails;
    private final List<String> telefones;
    private Endereco endereco;
    private String site;

    /**
     * Nova empresa, demais atributos usar set
     * @param login Login
     * @param senha Senha
     * @param nome Nome fantasia
     * @param cnpj Código jurídico 
     */
    public Empresa(String login, String senha, String nome, int cnpj) {
        super(login, senha, nome);
        this.cnpj = cnpj;
        this.emails = new LinkedList();
        this.telefones = new LinkedList();
    }

    /**
     * Setter
     * @param endereco Referêmcia para o endereço da empresa
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Setter
     * @param site Site da empresa
     */
    public void setSite(String site) {
        this.site = site;
    }
    
    /**
     * Adcionar novo email
     * @param email Novo e-mail a inserir
     */
    public void addEmail(String email) {
        emails.add(email);
    }
    
    /**
     * Remover email
     * @param email E-mail a remover
     */
    public void removeEmail(String email) {
        emails.remove(email);
    }
    
    /**
     * Adcionar novo telefone
     * @param telefone Novo telefone a inserir
     */
    public void addTelefone(String telefone) {
        emails.add(telefone);
    }
    
    /**
     * Remover telefone
     * @param telefone Telefone a remover
     */
    public void removeTelefone(String telefone) {
        emails.remove(telefone);
    }
    
    /**
     * Getter, Endereço da empresa
     * @return String descrição do endereço
     */
    public String getEndereco() {
        return endereco.toString();
    }

    /**
     * Getter
     * @return Site da empresa
     */
    public String getSite() {
        return site;
    }

    /**
     * Getter
     * @return Lista imutável de e-mails
     */
    public List<String> getEmails() {
        return Collections.unmodifiableList(emails);
    }

    /**
     * Getter
     * @return Lista imutável de telefones
     */
    public List<String> getTelefones() {
        return Collections.unmodifiableList(telefones); 
    }
    
}
