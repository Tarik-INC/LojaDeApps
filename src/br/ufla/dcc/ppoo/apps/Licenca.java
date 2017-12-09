package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.apps.enums.TipoLicenca;
import br.ufla.dcc.ppoo.apps.enums.TipoPagamento;
import br.ufla.dcc.ppoo.miscellaneous.Gerador;

/**
 * Registro de uma licenca de uso de um aplicativo
 * @author william
 */
public class Licenca {
    private final int registro;
    private final Aplicativo app;
    private final TipoLicenca tipo;
    private final float preco;
    private final TipoPagamento metodoPag;
        
    // Número de Registro do aplicativo é gerado automaticamente com o relógio 
    // do sistema, para que seja único. 
    
    // Atributos não aplicáveis para apps gratuitos são colocados null.
    
    /**
     * Construtor para aplicativos gratuitos
     * @param app Referência para o aplicativo licenciado
     * @param tipo Tipo de licença
     */
    public Licenca(Aplicativo app, TipoLicenca tipo) {
        this.registro = Gerador.gerarChave();
        this.app = app;
        this.tipo = tipo;
        this.preco = 0;
        this.metodoPag = null;
    }
    
    /**
     * Construtor para aplicativos pagos
     * @param app Referência para o aplicativo licenciado
     * @param tipo Tipo de licenca (enum)
     * @param metodoPag Tipo do pagamento (enum)
     * @param preco Valor pago
     */
    public Licenca(Aplicativo app, TipoLicenca tipo, TipoPagamento metodoPag, float preco) {
        this.registro = Gerador.gerarChave();
        this.app = app;
        this.tipo = tipo;
        this.preco = preco;
        this.metodoPag = metodoPag;
    }
    
    /**
     * Getter
     * @return Chave de registro
     */
    public int getRegistro() {
        return registro;
    }

    /**
     * Getter
     * @return Referência para o aplicativo
     */
    public Aplicativo getApp() {
        return app;
    }

    /**
     * Getter
     * @return Tipo de aplicativo (enum)
     */
    public TipoLicenca getTipo() {
        return tipo;
    }

    /**
     * Getter
     * @return Valor pago
     */
    public float getPreco() {
        return preco;
    }
    
    /**
     * Getter
     * @return Método de pagamento usado na compra
     */
    public TipoPagamento getMetodoPagamento() {
        return metodoPag;
    }
    
} 
