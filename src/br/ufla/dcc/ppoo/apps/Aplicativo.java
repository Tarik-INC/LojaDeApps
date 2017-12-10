package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.apps.enums.TipoAplicativo;
import br.ufla.dcc.ppoo.users.Desenvolvedor;

/**
 * Aplicativos cadastrados no sistema
 * @author william
 */
public class Aplicativo {
    private final Desenvolvedor desenvolvedor;
    private final String nome;
    private final TipoAplicativo genero;
    private int faixaEtaria;
    private int downloads;
    private float nota;
    private int numAvaliacoes;

    /**
     * Criar um novo aplicativo
     * @param nome Nome do aplicativo
     * @param desenvolvedor Referência para a empresa desenvolvedora
     * @param genero Tipo ou finalidade do aplicativo
     * @param faixaEtaria Classificação indicativa (0, 10, 12, 14, 16 ou 18)
     */
    public Aplicativo(String nome, Desenvolvedor desenvolvedor, TipoAplicativo genero, int faixaEtaria) {
        this.desenvolvedor = desenvolvedor;
        this.nome = nome;
        this.genero = genero;
        this.faixaEtaria = faixaEtaria;
        this.downloads = 0;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
    }
    
    /**
     * Muda a idade da classificação indicativa
     * @param faixaEtaria Nova idade para classificação indicativa
     */
    public void setFaixaEtaria(int faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    /**
     * Getter
     * @return Referência para a empresa desenvolvedora
     */
    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    /**
     * Getter
     * @return Nome do aplicativo
     */
    public String getNome() {
        return nome;
    }

    /**
     * Getter
     * @return Tipo de aplicativo ou finalidade
     */
    public TipoAplicativo getGenero() {
        return genero;
    }

    /**
     * Getter
     * @return Idade de censura
     */
    public int getFaixaEtaria() {
        return faixaEtaria;
    }

    /**
     * Getter
     * @return Número de downloads
     */
    public int getDownloads() {
        return downloads;
    }

    /**
     * Getter
     * @return Nota de avaliação média
     */
    public float getNota() {
        return nota;
    }
    
    /**
     * Alguém acabou de baixar, então incrementa
     */
    public void newDownload() {
        downloads++;
    }
    
    /**
     * Alguém avaliou, então atualiza média
     * @param nota Nota de avaliação (1 a 5 estrelas)
     */
    public void avaliar(int nota) {
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }
    
}
