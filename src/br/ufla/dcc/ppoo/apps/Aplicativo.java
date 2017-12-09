package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.apps.enums.TipoAplicativo;
import br.ufla.dcc.ppoo.miscellaneous.Gerador;
import br.ufla.dcc.ppoo.miscellaneous.SistemaOperacional;
import br.ufla.dcc.ppoo.users.Empresa;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplicativos cadastrados no sistema
 * @author william
 */
public class Aplicativo {
    private final int nroRegistro;
    private final Empresa desenvolvedor;
    private final String nome;
    private final int tamanho;
    private final TipoAplicativo genero;
    private final String versao;
    private List<SistemaOperacional> sistemas;
    private List<String> idiomas;
    private int faixaEtaria;
    private int downloads;
    private float nota;
    private int numAvaliacoes;

    /**
     * Criar um novo aplicativo
     * @param nome Nome do aplicativo
     * @param desenvolvedor Referência para a empresa desenvolvedora
     * @param genero Tipo ou finalidade do aplicativo
     * @param versao Número de versão (string)
     * @param faixaEtaria Classificação indicativa (0, 10, 12, 14, 16 ou 18)
     * @param tamanho Tamanho do arquivo de download
     */
    public Aplicativo(String nome, Empresa desenvolvedor, TipoAplicativo genero, String versao, int faixaEtaria, int tamanho) {
        this.nroRegistro = Gerador.gerarChave();
        this.desenvolvedor = desenvolvedor;
        this.nome = nome;
        this.tamanho = tamanho;
        this.genero = genero;
        this.versao = versao;
        this.faixaEtaria = faixaEtaria;
        this.sistemas = new ArrayList();
        this.idiomas = new ArrayList();
        this.downloads = 0;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
    }

    /**
     * Insere novo sistema
     * @param sistema Sistema operacional a ser inserido
     */
    public void addSistema(SistemaOperacional sistema) {
        sistemas.add(sistema);
    }
    
    /**
     * Remove um sistema
     * @param sistema Sistema operacional a ser removido
     */
    public void removeSistema(SistemaOperacional sistema) {
        sistemas.remove(sistema);
    }
    
    /**
     * Insere um idioma
     * @param idioma Idioma as ser inserido
     */
    public void addIdioma(String idioma) {
        idiomas.add(idioma);
    }
    
    /**
     * Remove um idioma
     * @param idioma Idioma a ser removido
     */
    public void removeIdioma(String idioma) {
        idiomas.remove(idioma);
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
     * @return Número do registro
     */
    public int getNroRegistro() {
        return nroRegistro;
    }

    /**
     * Getter
     * @return Referência para a empresa desenvolvedora
     */
    public Empresa getDesenvolvedor() {
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
     * @return valor do tamanho do download
     */
    public int getTamanho() {
        return tamanho;
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
     * @return Versão do app
     */
    public String getVersao() {
        return versao;
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
