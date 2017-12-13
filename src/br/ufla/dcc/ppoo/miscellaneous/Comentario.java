package br.ufla.dcc.ppoo.miscellaneous;

import java.io.Serializable;

/**
 * Um comentário feito por um usuário em um recurso.
 * @author rafael, tarik, william
 */
public class Comentario implements Serializable {

    private final String txtComentario;
    private final String nomeUsuario;

    /**
     * Cria novo comentário.
     * @param txtComentario Texto do comentário
     * @param nomeUsuario Identificação do usuário que comentou
     */
    public Comentario(String txtComentario, String nomeUsuario) {
        this.txtComentario = txtComentario;
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * Get comentário.
     * @return Texto do comentário
     */
    public String getComentario() {
        return txtComentario;
    }

    /**
     * Get usuário.
     * @return Nome de quem comentou
     */
    public String getUsuario() {
        return nomeUsuario;
    }

}
