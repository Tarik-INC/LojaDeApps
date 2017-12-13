package br.ufla.dcc.ppoo.miscellaneous;

import java.io.Serializable;

public class Comentario implements Serializable{

    private String txtComentario;
    private String nomeUsuario;

    public Comentario(String txtComentario, String nomeUsuario) {
        this.txtComentario = txtComentario;
        this.nomeUsuario = nomeUsuario;
    }

    public String getComentario() {
        return txtComentario;
    }

    public String getUsuario() {
        return nomeUsuario;
    }

}
