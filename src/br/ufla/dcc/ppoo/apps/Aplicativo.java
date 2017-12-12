package br.ufla.dcc.ppoo.apps;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Aplicativo implements Serializable {

    private final String nome;
    private String descricao;
    private final List<String> palavrasChave;
    private float nota;
    private int numAvaliacoes;

    public Aplicativo(String nome, String descricao, List<String> palavrasChave) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
      public void setNota(float nota) {
        this.nota = nota;
    }
}
