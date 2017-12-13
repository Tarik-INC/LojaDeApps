package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção que acontece quando o nome de um app está vazio.
 * @author rafael, tarik, william
 */
public class AppNomeVazioException extends AppException {

    public AppNomeVazioException(String message) {
        super(message);
    }
    
}
