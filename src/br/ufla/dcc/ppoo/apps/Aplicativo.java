package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.miscellaneous.Comentario;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Aplicativo implements Serializable {
    private String nome;
    private String descricao;
    private List<String> palavrasChave;
    private float nota;
    private int numAvaliacoes;
    private List<Comentario> comentarios;
    private String autor;

    public Aplicativo(String nome, String descricao, List<String> palavrasChave, String autor) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
        this.comentarios = new LinkedList();
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getPalavrasChave() {
        return Collections.unmodifiableList(palavrasChave);
    }

    public String getPalavrasChaveString() {
        // Isso aqui que é gambiarra seu mongol
        String result = palavrasChave.get(0)+", ";
        for (String palavraChave : palavrasChave) {
            palavraChave = palavraChave + ", ";
            if(!palavraChave.equals(result)) {
                result += palavraChave;
            }
        }
        return result;
    }

    public float getNota() {
        return nota;
    }
    
    public void novaAvaliacao(int nota) {
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }

    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public int getComentariosSize() {
        return comentarios.size();
    }

    public String getComentario(int i) {
        String comentario = comentarios.get(i).getUsuario() + ": " + comentarios.get(i).getComentario();
        return comentario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getAutor() {
        return autor;
    }
}
