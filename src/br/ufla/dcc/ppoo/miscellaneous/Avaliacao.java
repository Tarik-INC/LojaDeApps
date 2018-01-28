package br.ufla.dcc.ppoo.miscellaneous;

import br.ufla.dcc.ppoo.users.Usuario;
import java.io.Serializable;

public class Avaliacao implements Serializable {
    private Usuario usuario;
    private int nota;

    public Avaliacao(Usuario usuario, int nota) {
        this.usuario = usuario;
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
