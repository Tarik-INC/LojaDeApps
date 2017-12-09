package br.ufla.dcc.ppoo.miscellaneous;

/**
 * Um SO
 * @author william
 */
public class SistemaOperacional {
    private final String nome;
    private final String versao;
    private final String arquitetura;

    /**
     * Novo SO
     * @param nome Nome do SO
     * @param versao Versão do SO
     * @param arquitetura Arquitetura do hardware
     */
    public SistemaOperacional(String nome, String versao, String arquitetura) {
        this.nome = nome;
        this.versao = versao;
        this.arquitetura = arquitetura;
    }

    /**
     * Getter
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Getter
     * @return Versão
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Getter
     * @return Arquitetura
     */
    public String getArquitetura() {
        return arquitetura;
    }
       
}
