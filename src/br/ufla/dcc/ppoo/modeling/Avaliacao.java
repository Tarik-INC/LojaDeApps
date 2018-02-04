package br.ufla.dcc.ppoo.modeling;

import java.io.Serializable;

/**
 * Classe que modela uma avaliação feita por um usuário em um app.
 * @author rafael, tarik, william
 */
public class Avaliacao implements Serializable {
    private Usuario usuario;
    private int nota;
    
    /**
     * Construtor da avaliação.
     * @param usuario Referência para o usuário que a fez
     * @param nota Nota dada pelo usuário
     */
    public Avaliacao(Usuario usuario, int nota) {
        this.usuario = usuario;
        this.nota = nota;
    }
    
    /**
     * Get usuário.
     * @return Referência para o usuário
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Get nota.
     * @return valor da nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * Atualiza o valor da nota.
     * @param nota Novo valor a ser inserido
     */
    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
