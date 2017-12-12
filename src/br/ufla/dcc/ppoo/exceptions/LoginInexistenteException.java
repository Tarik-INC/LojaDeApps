package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção que ocorre quando um login não existe no sistema.
 * @author rafael, tarik, william
 */
public class LoginInexistenteException extends LoginException {

    public LoginInexistenteException(String message) {
        super(message);
    }
    
}
