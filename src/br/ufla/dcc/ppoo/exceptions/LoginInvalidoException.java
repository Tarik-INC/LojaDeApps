package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção que ocorre quando o login é inválido.
 * @author rafael, tarik, william
 */
public class LoginInvalidoException extends LoginException {

    public LoginInvalidoException(String message) {
        super(message);
    }
    
}
