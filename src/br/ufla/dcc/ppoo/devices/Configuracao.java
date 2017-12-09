package br.ufla.dcc.ppoo.devices;

/**
 * Configuralção do dispositivo
 * @author william
 */
public class Configuracao {
    private boolean certificado;
    private int controleParental;
    private boolean update;
    private boolean notificacao;
    
    /**
     * Verifica se é certificado
     * @return Booleano
     */
    public boolean isCertificado() {
        return certificado;
    }

    /**
     * Valor do controle parental
     * @return Idade
     */
    public int getControleParental() {
        return controleParental;
    }
    
    /**
     * Verifica se está habilitado para atualização automática
     * @return Booleano
     */
    public boolean isAbleUpdate() {
        return update;
    }
    
    /**
     * Verifica se está habilitado para receber notificações
     * @return Booleano
     */
    public boolean isAbleNotificacao() {
        return notificacao;
    }

    /**
     * Modifica a habilitação do certificado
     * @param certificado Booleano
     */
    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    /**
     * Modifica a idade de controle parental
     * @param controleParental Idade
     */
    public void setControleParental(int controleParental) {
        this.controleParental = controleParental;
    }

    /**
     * Modifica a habilitação updtade
     * @param update Booleano
     */
    public void setAbleUpdate(boolean update) {
        this.update = update;
    }

    /**
     * Modifica a habiliatação de notificação
     * @param notificacao Booleano
     */
    public void setAbleNotificacao(boolean notificacao) {
        this.notificacao = notificacao;
    }
    
}
