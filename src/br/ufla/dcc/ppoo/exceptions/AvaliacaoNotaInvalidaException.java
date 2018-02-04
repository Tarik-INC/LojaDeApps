package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção em tempo de execução de uma avaliação de app.
 * @author rafael, tarik, william
 */
public class AvaliacaoNotaInvalidaException extends RuntimeException {

    public AvaliacaoNotaInvalidaException(String message) {
        super(message);
    }
    
}
