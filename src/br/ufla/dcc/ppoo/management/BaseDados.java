package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BaseDados {
    private final List<Usuario> cadastros;

    /**
     * Cria base de dados vazia.
     */
    public BaseDados() {
        cadastros = new LinkedList();
    }
    
    /**
     * Cria base de dados a partir do arquivo
     * @param file Arquivo a ser lido
     * @throws java.io.IOException
     */
    public BaseDados(File file) throws IOException {
        cadastros = null;
        throw new IOException("NÃO TEM COMO MEXER NO ARQUIVO AINDA");
        //System.exit(0);
        /*
        // ou arquivo vazio...
        if (!file.exists()) {
            file.createNewFile();
            throw new IOException("Sem dados salvos");
        }
        // carregar...
        */
    }
    
    public void addCadastro(Usuario cadastro) throws LoginJaExistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(cadastro.getLogin())) {
                throw new LoginJaExistenteException("Login já existe");
            }
        }
        
        cadastros.add(cadastro);
    }
    
    public Usuario buscarCadastro(String login) throws LoginInexistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(login)) {
                return c;
            }
        }
        
        throw new LoginInexistenteException("Cadastro não encontrado");
    }
}
