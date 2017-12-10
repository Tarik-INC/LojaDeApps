package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Cadastro;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Todos os dados do sistema
 * @author william
 */
public class BaseDados {
    private final List<Cadastro> cadastros;
    private final List<Aplicativo> aplicativos;

    /**
     * Cria base de dados vazia.
     */
    public BaseDados() {
        cadastros = new LinkedList();
        aplicativos = new LinkedList();
    }
    
    /**
     * Cria base de dados a partir do arquivo
     * @param file Arquivo a ser lido
     * @throws java.io.IOException
     */
    public BaseDados(File file) throws IOException {
        cadastros = null;
        aplicativos = null; 
        throw new IOException("NÃO TEM COMO MEXER NO ARQUIVO AINDA");
        /*
        // ou arquivo vazio...
        if (!file.exists()) {
            file.createNewFile();
            throw new IOException("Sem dados salvos");
        }
        // carregar...
        */
    }
    
    public void addCadastro(Cadastro cadastro) throws LoginJaExistenteException {
        for (Cadastro c : cadastros) {
            if (c.isLogin(cadastro.getLogin())) {
                throw new LoginJaExistenteException("Login já existe");
            }
        }
        
        cadastros.add(cadastro);
    }
    /*
    public void removeCadastro(Cadastro cadastro) throws LoginInexistenteException {
        if (! cadastros.remove(cadastro)) {
            throw new LoginInexistenteException("Cadastro não encontrado");
        }
    }
    */
    public void addNovoApp(Aplicativo app) {
        aplicativos.add(app);
    }
    /*
    public void removeApp(Aplicativo app) throws AppInexistenteException {
        if (! aplicativos.remove(app)) {
            throw new AppInexistenteException("App não encontrado");
        }
    }
    */
    public Cadastro buscarCadastro(String login) throws LoginInexistenteException {
        for (Cadastro c : cadastros) {
            if (c.isLogin(login)) {
                return c;
            }
        }
        
        throw new LoginInexistenteException("Cadastro não encontrado");
    }
}
