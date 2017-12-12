package br.ufla.dcc.ppoo.apps;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Classe Aplicativo.
 * Um recurso do trabalho
 * @author rafael, tarik, william
 */
public class Aplicativo implements Serializable {
    private String nome;
    private String descricao;
    private List<String> palavrasChave;
    private float nota;
    private int numAvaliacoes;
    
    /**
     * Construtor de um novo app.
     * @param nome Nome do recurso
     * @param descricao Descrição do recurso
     * @param palavrasChave Lista de palavras chaves
     */
    public Aplicativo(String nome, String descricao, List<String> palavrasChave) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
    }

    /**
     * Get Nome.
     * @return Nome do recurso
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Descrição.
     * @return Descrição do recurso
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Get Palavras-chave.
     * @return Lista imutável de palavras-chave
     */
    public List<String> getPalavrasChave() {
        return Collections.unmodifiableList(palavrasChave);
    }

    /**
     * Get Nota.
     * @return Nota média das avaliações
     */
    public float getNota() {
        return nota;
    }
    
    /**
     * Cadastra nNova avaliação feita por um usuário.
     * @param nota Nota avaliada (1 a 5 estrelas)
     */
    public void novaAvaliacao(int nota) {
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }
    
    /**
     * Atualiza descrição.
     * @param descricao Nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Atualiza Nome do app.
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Atualiza palavras-chave
     * @param palavrasChave Nova lista de palavras-chave
     */
    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    
}
