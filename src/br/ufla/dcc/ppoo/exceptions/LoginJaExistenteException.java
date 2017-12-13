package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção que ocorre quando um login já existe, não podem haver duplicatas.
 * @author rafael, tarik, william
 */
public class LoginJaExistenteException extends LoginException {

    public LoginJaExistenteException(String message) {
        super(message);
    }
    
}
