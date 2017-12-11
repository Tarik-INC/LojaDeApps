package br.ufla.dcc.ppoo.miscellaneous;

/**
 * Data no formato PT/BR
 * @author william
 */
public class Data {
    private final int dia;
    private final int mes;
    private final int ano;

    /**
     * Nova data
     * @param dia Dia
     * @param mes Mês
     * @param ano Ano
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    /**
     * Data em formato texto
     * @return Data
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", dia, mes, ano);
    }

    /**
     * Getter
     * @return Dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Getter
     * @return Mês
     */
    public int getMes() {
        return mes;
    }

    /**
     * Getter
     * @return Ano
     */
    public int getAno() {
        return ano;
    }
    
}
