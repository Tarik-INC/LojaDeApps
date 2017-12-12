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
import java.util.ArrayList;
import java.util.List;

public class BaseDados {
    private final List<Usuario> cadastros;

    /**
     * Cria base de dados vazia.
     */
    public BaseDados() {
        cadastros = new ArrayList<Usuario>();
    }
    
    /**
     * Cria base de dados a partir do arquivo
     * @param file Arquivo a ser lido
     * @throws java.io.IOException
     */
    public BaseDados(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
        cadastros = (List<Usuario>) reader.readObject();
    }
    
    public void save(File file) throws IOException {
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(cadastros);
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

    public Aplicativo buscarAplicativo(String aplicativo) throws AppInexistenteException {
        for (Usuario c :
                cadastros) {
            for (Aplicativo app :
                    c.getAplicativos()) {
                if (app.getNome() == aplicativo) {
                    return app;
                }
            }
        }

        throw
    }

}
