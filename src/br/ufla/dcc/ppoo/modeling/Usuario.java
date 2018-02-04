package br.ufla.dcc.ppoo.modeling;

import br.ufla.dcc.ppoo.exceptions.AppJaExistenteException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
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
    public Usuario(String nome, String login, char[] senha) {
        this.login = login;
        this.nome = nome;
        this.apps = new LinkedList();
        this.senha = senhaCriptografada(String.copyValueOf(senha));
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
    public boolean isSenha(char[] senhaIn) {
        return senha.equals( senhaCriptografada(String.copyValueOf(senhaIn)) );
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
        finally {
            // por questão de segurança:
            senhaIn = null;
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
     
    /**
     * Verifica se um app com mesmo nome já está na lista.
     * @param app App a ser conferido
     * @return Verdadeiro ou Falso
     */
    public boolean contains(Aplicativo app) {
        for (Aplicativo oneApp : apps) {
            if ( oneApp.getNome().equals(app.getNome()) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se um outro app possui o novo nome a ser atribuído após edição.
     * @param nomeOriginal string nome original do app a ser conferido
     * @param nomeNovo string novo nome do app a ser conferido
     * @return Verdadeiro ou Falso
     */
    public boolean containsOther(String nomeOriginal, String nomeNovo) {
        for (Aplicativo oneApp : apps) {
            if ( !oneApp.getNome().equals(nomeOriginal) && oneApp.getNome().equals(nomeNovo) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adicionar novo app na lista.
     * @param aplicativo Novo app cadastrado
     */
    public void addApp(Aplicativo aplicativo) throws AppJaExistenteException {
        if (!contains(aplicativo)) {
            addAppOrdenado(aplicativo);
        }
        else {
            throw new AppJaExistenteException(
                String.format("Já existe um app chamado \"%s\" em sua conta.", aplicativo.getNome())
            );
        }
    }
    
    /**
     * Insere elemento na lista de apps em ordem alfabética.
     * @param app Objeto a ser inserido
     */
    private void addAppOrdenado(Aplicativo app) {
        if (apps.isEmpty()) {
            apps.add(app);
        }
        else {
            for (int i = 0; i < apps.size(); i++) {
                if ( app.getNome().compareToIgnoreCase(apps.get(i).getNome()) < 0 ) {
                    apps.add(i, app);
                    return;
                }
            }
            apps.add(app);
        }
    }
    
    /**
     * Get apps.
     * @return Lista imutável de apps
     */
    public List<Aplicativo> getAplicativos() {
        return Collections.unmodifiableList(apps);
    }

    /**
     * Get referêcia para aplicativo.
     * @param i Índice do registro
     * @return Referência para o objeto
     */
    public Aplicativo getAplicativo(int i) {
        return apps.get(i);
    }
    
    /**
     * Ordena lista de aplicativos.
     */
    public void sortAplicativos() {
        apps.sort(Comparator.comparing(Aplicativo::getNome));
    }

    /**
     * Remove app da lista.
     * @param i Índice do app
     */
    public void removeAplicativo(int i) {
        apps.remove(i);
    }
    
    /**
     * Remove app da lista.
     * @param nome Nome do app
     */
    public void removeAplicativo(String nome) {
        int i = 0;
        for (Aplicativo app : apps) {
            if (!app.getNome().equals(nome)) {
                i++;
            }
            else {
                break;
            }
        }
        apps.remove(i);
    }
    
}