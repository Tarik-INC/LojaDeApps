package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Usuário.
 * Um usuário do sistema
 * @author rafael, tarik, william
 */
public class Usuario implements Serializable {
    private final String nome;
    private final String login;
    private final String senha;
    private final List<Aplicativo> apps;

    /**
     * Construtor para criar um novo usuário.
     * @param nome Nome do usuário
     * @param login Login do usuário
     * @param senha Senha do usuário
     */
    public Usuario(String nome, String login, String senha) {
        this.login = login;
        this.senha = senhaCriptografada(senha);
        this.nome = nome;
        this.apps = new LinkedList();
    }

    /**
     * Validar login.
     * @param loginIn Login digitado durante o login
     * @return Booleano indicando se o login confere
     */
    public boolean isLogin(String loginIn) {
        return login.equals(loginIn);
    }

    /**
     * Validar senha.
     * @param senhaIn Senha digitada durante o login
     * @return Booleano indicando se a senha confere
     */
    public boolean isSenha(String senhaIn) {
        return senha.equals( senhaCriptografada(senhaIn) );
    }
    
    /**
     * Método de criptografia SHA-256.
     * @link https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
     * @param senhaIn Senha a ser criptografada
     * @return Senha criptografada, senão null se ocorrer erro
     */
    private String senhaCriptografada(String senhaIn) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senhaIn.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // Nunca vai entrar aqui, pois "UTF-8" e "SHA-256" estão corretos
            return null;
        }
    }

    /**
     * Get Nome.
     * @return Nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Login.
     * @return Login do usuário
     */
    public String getLogin() {
        return login;
    }
    
}