package br.ufla.dcc.ppoo.devices;

import br.ufla.dcc.ppoo.miscellaneous.Gerador;
import br.ufla.dcc.ppoo.miscellaneous.SistemaOperacional;
import br.ufla.dcc.ppoo.users.Usuario;

/**
 * Um dispositivo cadastrado na loja
 * @author william
 */
public class Dispositivo {
    private final String identificador;
    private final String versao;
    private final String modelo;
    private final Configuracao config;
    private Usuario proprietario;
    private SistemaOperacional sistema;
    
    /**
     * Cria um novo dispositivo
     * @param versao Versão da máquina (imutável)
     * @param modelo Modelo da máquina (imutável)
     * @param sistema Sistema operacional padrão da máquina
     * @param proprietario Dono padrão da máquina
     */
    public Dispositivo(String versao, String modelo, SistemaOperacional sistema, Usuario proprietario) {
        this.identificador = Gerador.gerarId();
        this.proprietario = proprietario;
        this.sistema = sistema;
        this.versao = versao;
        this.modelo = modelo;
        this.config = new Configuracao();
    }

    /**
     * Detalhes do hardware
     * @return Id, Versão e Modelo do dispositivo
     */
    public String getDetalhes() {
        return String.format("id:%s version:%s model:%s", identificador, versao, modelo);
    }

    /**
     * Configuração do dispositivo
     * @return Referência para as configurações
     */
    public Configuracao getConfig() {
        return config;
    }

    /**
     * Proprietário do dispositivo
     * @return Referência para o proprietário
     */
    public Usuario getProprietario() {
        return proprietario;
    }

    /**
     * Sistema operacional do dispositivo
     * @return Referência para o SO
     */
    public SistemaOperacional getSistema() {
        return sistema;
    }

    /**
     * Atualizar para o novo proprietário
     * @param proprietario Novo proprietário
     */
    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Atualizar para o novo sistema operacional
     * @param sistema Novo SO
     */
    public void setSistema(SistemaOperacional sistema) {
        this.sistema = sistema;
    }
    
}
