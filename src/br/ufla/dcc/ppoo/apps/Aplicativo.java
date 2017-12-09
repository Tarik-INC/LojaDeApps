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
    
}
