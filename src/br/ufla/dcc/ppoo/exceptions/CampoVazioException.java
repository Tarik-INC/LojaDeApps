package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção genérica que acontece quando um campo de preenchimento estiver vazio.
 * @author rafael, tarik, william
 */
public class CampoVazioException extends Exception {

    public CampoVazioException(String message) {
        super(message);
    }
    
}
