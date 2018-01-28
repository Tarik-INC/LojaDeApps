package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção gerada quando for inserido um app duplicado.
 * @author rafael, tarik, william
 */
public class AppJaExistenteException extends AppException {

    public AppJaExistenteException(String message) {
        super(message);
    }
    
}
