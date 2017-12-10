package br.ufla.dcc.ppoo.apps;

import java.util.Collections;
import java.util.List;

public class Aplicativo {
    private final String nome;
    private final String descricao;
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

    public float getNota() {
        return nota;
    }
    
    public void novaAvaliacao(int nota) {
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }
    
}
