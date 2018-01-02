package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Base de dados do sistema.
 * @author rafael, tarik, william
 */
public class BaseDados {
    private final List<Usuario> cadastros;

    /**
     * Cria base de dados vazia.
     */
    public BaseDados() {
        cadastros = new LinkedList();
    }
    
    /**
     * Cria base de dados a partir do arquivo.
     * @param file Arquivo a ser lido
     */
    public BaseDados(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(file));
            cadastros = (List<Usuario>) reader.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            throw e;
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    /**
     * Salvar dados no arquivo, fazendo sobrescrita.
     * @param file Arquivo a ser salvo
     */
    public void save(File file) throws IOException {
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeObject(cadastros);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    /**
     * Adiciona novo usuário à lista.
     * @param cadastro Novo cadastro de usuário
     */
    public void addCadastro(Usuario cadastro) throws LoginJaExistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(cadastro.getLogin())) {
                throw new LoginJaExistenteException(
                    String.format("Login \"%s\" já existe no sistema.", cadastro.getLogin())
                );
            }
        }
        
        cadastros.add(cadastro);
    }
    
    /**
     * Busca referência para objeto usuário com o login dado.
     * @param login Login do usuário a ser buscado
     * @return Referência para o objeto
     */
    public Usuario buscarCadastro(String login) throws LoginInexistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(login)) {
                return c;
            }
        }
        
        throw new LoginInexistenteException(
            String.format("Cadastro com login \"%s\" inexistente.", login)
        );
    }
    
    /**
     * Busca um app dentro dos cadastros
     * @param nome Nome do app
     * @return Referência para o objeto
     */
    public Aplicativo buscarApp(String nome) throws AppInexistenteException {
        for (Usuario cadastro : cadastros) {
            for (Aplicativo app : cadastro.getAplicativos()) {
                if (app.getNome().equals(nome)) {
                    return app;
                }
            }
        }
        
        throw new AppInexistenteException( String.format("App \"%s\" não encontrado.", nome) );
    }
    
    /**
     * Busca aplcativo, usado no menu de busca.
     * @param keyword  Nome do app
     * @return Lista de combinações encontradas, ordenada pela nota descrescente
     */
    public List<Aplicativo> buscarAplicativos(String keyword) throws AppInexistenteException {
        List<Aplicativo> lista = new LinkedList();
        
        for (Usuario c : cadastros) {
            for (Aplicativo app : c.getAplicativos()) {
                if (app.nomeContem(keyword) || app.palavrasChaveContem(keyword)) {
                    lista.add(app);
                }
            }
        }
        
        if (!lista.isEmpty()) {
            lista.sort(Collections.reverseOrder(Comparator.comparing(Aplicativo::getNota)));
            return lista;
        } 
        else {
            throw new AppInexistenteException(String.format("Aplicativo \"%s\" não encontrado.", keyword));
        }
    }

    /**
     * Verifica se um app pertence à base de dados.
     * @param aplicativo Nome do aplicativo
     * @return Booleano indicando sim ou não
     */
    public boolean aplicativoExiste(String aplicativo) {
        for (Usuario c: cadastros) {
            for (Aplicativo app : c.getAplicativos()) {
                if (app.getNome().equals(aplicativo)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Retorna todos os aplicativos disponíveis na base de dados.
     * @return Lista contendo todos os apps
     */
    public List<Aplicativo> getAllApps() {
        List<Aplicativo> list = new LinkedList();
        
        for (Usuario user : cadastros) {
            list.addAll(user.getAplicativos());
        }
        
        return list;
    }
    
}
