package br.ufla.dcc.ppoo.users;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Qualquer login na loja
 * @author william
 */
public /*abstract*/ class Cadastro {
    private final String login;
    private final String senha;
    private String nome;

    /**
     * Novo cadastro
     * @param login Login de acesso (imutável e único)
     * @param senha Senha de acesso (imutável)
     * @param nome  Nome do usuário
     */
    public Cadastro(String login, String senha, String nome) {
        this.login = login;
        this.senha = senhaCriptografada(senha);
        this.nome = nome;
    }

    /**
     * Validar login (Segurança computacional aplicada)
     * @param loginIn Login digitado durante o login
     * @return Booleano indicando se o login confere
     */
    public boolean isLogin(String loginIn) {
        // Não retorno direto, vou preservá-lo oculto
        return login.equals(loginIn);
    }

    /**
     * Validar senha (Segurança computacional aplicada)
     * @param senhaIn Senha digitada durante o login
     * @return Booleano indicando se a senha confere
     */
    public boolean isSenha(String senhaIn) {
        // Compara só a hash
        return senha.equals( senhaCriptografada(senhaIn) );
    }
    
    /**
     * Método de criptografia SHA-256 (Segurança computacional aplicada)
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
            return null;
        }
    }

    /**
     * Getter
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter - tem que tirar isso depois
     * @return 
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter - tem que tirar isso depois
     * @return 
     */
    public String getSenha() {
        return senha;
    }
    
    
}
