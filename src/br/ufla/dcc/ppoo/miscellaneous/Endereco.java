package br.ufla.dcc.ppoo.miscellaneous;

/**
 * Um lugar de Endereço
 * @author william
 */
public class Endereco {
    private final String logradouro;
    private final int numero;
    private final String bairro;
    private final String cidade;
    private final String pais;

    /**
     * Novo endereço completo
     * @param logradouro Rua, avenida ou outra info (obrigatório)
     * @param numero Número
     * @param bairro Bairro
     * @param cidade Cidade (obrigatório)
     * @param pais País (obrigatório)
     */
    public Endereco(String logradouro, int numero, String bairro, String cidade, String pais) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.pais = pais;
    }

    /**
     * Novo endereço de roça sem número nem bairro
     * @param logradouro Rua, avenida ou outra info
     * @param cidade Cidade
     * @param pais País
     */
    public Endereco(String logradouro, String cidade, String pais) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.pais = pais;
        this.numero = -1; // Significa "Sem Número"
        this.bairro = null; // Significa "Bairro Desconhecido"
    }

    @Override
    public String toString() {
        if (numero != -1) {
            return String.format("%s, Nº %d, bairro %s. %s/%s", logradouro, numero, bairro, cidade, pais);
        }
        else {
            return String.format("%s, sem Nº. %s/%s", logradouro, cidade, pais);
        }
    }
    
}
