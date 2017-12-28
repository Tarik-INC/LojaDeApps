package br.ufla.dcc.ppoo.exceptions;

/**
 * Exceção que acontece quando nenhum item válido de uma lista está selecionado.
 * @author rafael, tarik, william
 */
public class NenhumItemSelecionadoException extends Exception {

    public NenhumItemSelecionadoException(String message) {
        super(message);
    }
    
}
